package org.example.observer

import org.example.observer.notification.Notification

interface Publisher {

    /**
     * This method adds a subscriber to the subscribers list
     * @param subscriber [Subscriber] object represents the subscriber to be added
     */
    fun addSubscriber(subscriber: Subscriber)

    /**
     * This method notifies all subscribers sending notification
     * @param notification [Notification] object represents notification to be sent
     */
    fun notifySubscribers(notification: Notification)
}