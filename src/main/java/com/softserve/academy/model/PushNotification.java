package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;
import com.softserve.academy.exception.NotDeliverableException;

public class PushNotification extends Notification {
    private String deviceToken;
    private String iconUrl;

    public PushNotification(String recipient, String message, int priority, String deviceToken, String iconUrl) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.deviceToken = deviceToken;
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean isDeliverable() {
        // TODO: deviceToken не blank і довжина > 10
        String devTok = getDeviceToken();
        if(devTok == null){
            return false;
        }
        return !devTok.isBlank() && devTok.length() > 10;
    }

    public boolean isSilent() {
        // TODO: true якщо message порожнє (тільки тайтл)
        return getMessage().isBlank();
    }

    @Override
    public String getFormattedMessage() {
        // TODO: 🔔 {message} (якщо silent -> 🔔 (silent))
        if (isSilent()){
            return String.format("\uD83D\uDD14 (silent) %s", getMessage()).trim();
        }else {
            return String.format("\uD83D\uDD14 %s", getMessage()).trim();
        }
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 1
        return 1;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        System.out.println();
    }

    public String getDeviceToken() { return deviceToken; }
    public String getIconUrl() { return iconUrl; }
}
