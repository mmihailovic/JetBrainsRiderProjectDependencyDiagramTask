package org.example.observer.notification

import org.example.observer.Subscriber

abstract class Notification {
    /**
     * This method handles the notification for the given subscriber
     * @param subscriber [Subscriber] object represents the subscriber
     */
    abstract fun handleNotification(subscriber: Subscriber)
}
