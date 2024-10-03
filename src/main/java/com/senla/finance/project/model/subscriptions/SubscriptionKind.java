package com.senla.finance.project.model.subscriptions;

public enum SubscriptionKind {
    DISABLED(0), LITE(1), PRO(10), PREMIUM(30);

    private final int price;

    SubscriptionKind(int price) {
        this.price = price;
    }
}
