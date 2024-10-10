package com.senla.finance.project.controller;

import com.senla.finance.project.dto.SubscriptionRequestDto;
import com.senla.finance.project.dto.SubscriptionResponseDto;
import com.senla.finance.project.service.SubscriptionService;
import com.senla.finance.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

        return userService.getSubscriptionResponse(email);
    }

    @PostMapping(value = "/update")
    public void updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        subscriptionService.updateSubscription(email, subscriptionRequestDto);
    }
}
