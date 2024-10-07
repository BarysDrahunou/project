package com.senla.finance.project.controller;

import com.senla.finance.project.dto.SubscriptionRequestDto;
import com.senla.finance.project.dto.SubscriptionResponseDto;
import com.senla.finance.project.mapper.SubscriptionMapper;
import com.senla.finance.project.model.subscriptions.SubscriptionKind;
import com.senla.finance.project.model.users.User;
import com.senla.finance.project.service.SubscriptionService;
import com.senla.finance.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.senla.finance.project.utils.Constants.*;
import static com.senla.finance.project.utils.PropertiesValidator.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/current")
    public SubscriptionResponseDto getCurrentSubscription() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findUserByEmail(email);

        return new SubscriptionMapper().mapFromUser(user);
    }

    @PostMapping(value = "/update")
    public void updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionKind subscriptionKind = subscriptionValidated(SUBSCRIPTION, subscriptionRequestDto.getSubscriptionKind());
        int days = numberValidated(DAYS, subscriptionRequestDto.getDays());

        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findUserByEmail(email);
        String bankAccountId = validated(VALID_BANK_ACCOUNT_ID_PROPERTY, subscriptionRequestDto.getId());
        String bankAccountSecret = validated(VALID_BANK_ACCOUNT_SECRET_PROPERTY, subscriptionRequestDto.getSecret());

        subscriptionService.updateSubscription(user, subscriptionKind, days, bankAccountId, bankAccountSecret);
    }
}
