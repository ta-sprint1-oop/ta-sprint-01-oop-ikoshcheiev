package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;
import com.softserve.academy.exception.NotDeliverableException;

public class SmsNotification extends Notification {
    private String phoneNumber;
    private boolean isFlash;

    public SmsNotification(String recipient, String message, int priority, String phoneNumber, boolean isFlash) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.phoneNumber = phoneNumber;
        this.isFlash = isFlash;
    }

    @Override
    public boolean isDeliverable() {
        // TODO: Номер починається з + і має довжину 10-15 символів
        if(getPhoneNumber() == null || getPhoneNumber().isBlank()){
            return false;
        }
        return getPhoneNumber().startsWith("+") && getPhoneNumber().length() >= 10 && getPhoneNumber().length() <= 15;
    }

    public boolean isOverLimit() {
        // TODO: true якщо message > 160 символів
        return getMessage().length() > 160;
    }

    @Override
    public String getFormattedMessage() {
        // TODO: Обрізає до 160 символів якщо довше
        String msg = getMessage();
        if (msg.length() <= 160){
            return msg;
        }
        return msg.substring(0,160);
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 5
        return 5;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        System.out.println();
    }

    public String getPhoneNumber() { return phoneNumber; }
    public boolean isFlash() { return isFlash; }
}
