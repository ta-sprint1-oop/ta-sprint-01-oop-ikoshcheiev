package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;

import java.util.Set;

public class EmailNotification extends Notification {
    private String senderEmail;
    private String subject;
    private boolean hasAttachment;

    public EmailNotification(String recipient, String message, int priority, String senderEmail, String subject,
                             boolean hasAttachment) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.hasAttachment = hasAttachment;
    }

    @Override
    public boolean isDeliverable() {
        // TODO: Перевірка що email містить @ і .
        return getRecipient().contains(".") && getRecipient().contains("@");
    }

    public boolean isSpam() {
        // TODO: Якщо subject містить "free", "win", "click" (case insensitive)
        return Set.of("free", "win", "click").stream().anyMatch(str -> getSubject().toLowerCase().contains(str));
    }

    @Override
    public String getFormattedMessage() {
        // TODO: Subject: {subject}\n{message}
        return String.format("Subject: %s\n%s", getSubject(), getMessage());
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 30
        return 30;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        System.out.println();
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isHasAttachment() {
        return hasAttachment;
    }
}
