package com.senla.finance.project.config;

import com.senla.finance.project.dao.UsersDao;
import com.senla.finance.project.model.currency.Balance;
import com.senla.finance.project.model.roles.Authority;
import com.senla.finance.project.model.roles.Role;
import com.senla.finance.project.model.subscriptions.Subscription;
import com.senla.finance.project.model.subscriptions.SubscriptionAuthorities;
import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.AuthorityService;
import com.senla.finance.project.service.SubscriptionService;
import com.senla.finance.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.senla.finance.project.utils.Constants.*;

@Component
public class SetupDataLoader {

    private static final String NON_DIGITS_REPLACE_PATTERN = "[^\\d]";
    private static final String DOT = ".";
    private static final String EMPTY = "";
    private static final String COMMA = ",";

    @Value("${admin.user.firstname}")
    private String defaultAdminUserFirstName;
    @Value("${admin.user.lastname}")
    private String defaultAdminUserLastName;
    @Value("${admin.user.email}")
    private String defaultAdminUserEmail;
    @Value("${admin.user.password}")
    private String defaultAdminUserPassword;
    @Value("${subscriptions.update}")
    private boolean updateSubscriptions;
    @Value("${authorities.update}")
    private boolean updateAuthorities;
    @Value("${authorities.values}")
    private String givenAuthorities;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    Environment env;

    @EventListener(ApplicationReadyEvent.class)
    public void addAdminIfNotExists() {
        if (!userService.userExists(defaultAdminUserEmail)) {
            User user = User.builder()
                    .firstName(defaultAdminUserFirstName)
                    .lastName(defaultAdminUserLastName)
                    .email(defaultAdminUserEmail)
                    .password(passwordEncoder.encode(defaultAdminUserPassword))
                    .role(Role.ADMIN)
                    .balance(new Balance())
                    .build();

            userService.addUser(user);
            subscriptionService.logNewDefaultSubscription(defaultAdminUserEmail);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void updateAuthorities() {
        if (updateAuthorities) {
            List<Authority> authorities = Arrays.stream(givenAuthorities.split(COMMA)).map(x -> new Authority(x.trim())).toList();

            authorityService.deleteAllAuthorities();
            authorityService.addAllAuthorities(authorities);
        }
    }


    @EventListener(ApplicationReadyEvent.class)
    public void updateAvailableSubscriptions() {
        if (updateSubscriptions) {

            Map<Integer, Map<String, String>> subscriptionMap = new HashMap<>();
            for (PropertySource<?> propertySource : ((ConfigurableEnvironment) env).getPropertySources()) {
                if (propertySource instanceof EnumerablePropertySource) {
                    for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                        if (key.startsWith(SUBSCRIPTIONS_PROPERTY)) {
                            if (key.equals(SUBSCRIPTIONS_UPDATE_PROPERTY)) {
                                continue;
                            }

                            int subscriptionId = Integer.parseInt(key.replaceAll(NON_DIGITS_REPLACE_PATTERN, EMPTY).trim());

                            Map<String, String> subscriptionProperties = subscriptionMap.getOrDefault(subscriptionId, new HashMap<>());
                            subscriptionProperties.put(key.substring(key.lastIndexOf(DOT) + 1), String.valueOf(propertySource.getProperty(key)));
                            subscriptionMap.put(subscriptionId, subscriptionProperties);
                        }
                    }
                }
            }

            List<Subscription> subscriptions = new ArrayList<>();
            List<SubscriptionAuthorities> subscriptionAuthorities = new ArrayList<>();
            subscriptionMap.values().forEach(value -> {
                Subscription subscription = Subscription.builder()
                        .subscriptionName(value.get(NAME))
                        .subscriptionPrice(Integer.parseInt(value.get(PRICE)))
                        .build();
                subscriptions.add(subscription);

                List<String> authorities = Arrays.stream(value.get(AUTHORITIES).split(COMMA)).map(String::trim).toList();
                authorities.forEach(authority -> {
                    subscriptionAuthorities.add(SubscriptionAuthorities.builder()
                                    .subscriptionName(subscription.getSubscriptionName())
                                    .authorityName(authority)
                            .build());
                });
            });

            subscriptionService.deleteAllSubscriptions();
            subscriptionService.addAllSubscriptions(subscriptions);

            subscriptionService.deleteAllSubscriptionAuthoritiesRelations();
            subscriptionService.addAllSubscriptionsAuthorities(subscriptionAuthorities);
        }
    }
}
