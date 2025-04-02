package org.example.observer.notification

import org.example.model.Edge
import org.example.observer.EdgeSubscriber
import org.example.observer.Subscriber

data class ShowEdgesNotification(val edges: List<Edge>): Notification() {
    override fun handleNotification(subscriber: Subscriber) {
        if(subscriber is EdgeSubscriber) {
            subscriber.showEdges(edges)
        }
    }
}