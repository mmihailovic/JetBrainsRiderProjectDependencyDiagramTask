package org.example.observer

import org.example.observer.notification.Notification

interface Subscriber {

    /**
     * This method updates the subscriber based on a specified notification
     * @param notification [Notification] object represents the notification
     */
    fun update(notification: Notification)
}