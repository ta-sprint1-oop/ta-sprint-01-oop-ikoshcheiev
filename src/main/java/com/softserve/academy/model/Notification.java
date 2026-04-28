package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;
import com.softserve.academy.exception.NotDeliverableException;

public abstract class Notification implements Comparable<Notification> {
    protected String recipient;
    protected String message;
    protected int priority;
    protected NotificationStatus status;

    public Notification(String recipient, String message, int priority) {
        // TODO: Базова валідація в конструкторі:
        // порожній отримувач -> InvalidNotificationException
        if(recipient == null || recipient.isBlank()){
            throw new InvalidNotificationException("Recipient is empty");
        }
        this.recipient = recipient;

        // порожнє повідомлення (null) -> InvalidNotificationException
        if(message == null){
            throw new InvalidNotificationException("Message is empty");
        }
        this.message = message;

        // priority від 1 до 5, інакше -> InvalidNotificationException
        if (priority < 1 || priority > 5) {
            throw new InvalidNotificationException("Priority must be between 1 and 5");
        }
        this.priority = priority;
        this.status = NotificationStatus.PENDING;
    }

    public abstract boolean isDeliverable();

    public abstract String getFormattedMessage();

    public abstract int estimateDeliverySeconds();

    public boolean isHighPriority() {
        // TODO: Пріоритет >= 4
        return getPriority() >= 4;
    }

    public void send() throws NotDeliverableException {
        // TODO: Шаблонний метод (Template Method):
        // 1. Перевірка isDeliverable()
        // 2. Якщо !isDeliverable() -> статус FAILED та throw NotDeliverableException
        if(!isDeliverable()){
            status = NotificationStatus.FAILED;
            throw new NotDeliverableException("Not deliverable");
        }
        // 3. performSend()
        performSend();
        // 4. Успіх -> статус SENT
        status = NotificationStatus.SENT;
    }

    protected abstract void performSend() throws NotDeliverableException;

    @Override
    public int compareTo(Notification other) {
        // TODO: Сортування за priority descending
        return Integer.compare(other.getPriority(), this.priority);
    }

    // Getters
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public int getPriority() { return priority; }
    public NotificationStatus getStatus() { return status; }
}
