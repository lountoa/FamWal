package com.example.familywallet;

public class User {
    public String marketCount, caffeCount, transportCount, paymentCount, pocketCount, passwordKEY, wallet, exp;

    public User() {
    }

    public User(String marketCount, String caffeCount, String transportCount,
                String paymentCount,String pocketCount,
                String passwordKEY, String wallet, String exp) {
        this.marketCount = marketCount;
        this.caffeCount = caffeCount;
        this.transportCount = transportCount;
        this.paymentCount = paymentCount;
        this.pocketCount = pocketCount;
        this.passwordKEY = passwordKEY;
        this.wallet = wallet;
        this.exp = exp;
    }
}
