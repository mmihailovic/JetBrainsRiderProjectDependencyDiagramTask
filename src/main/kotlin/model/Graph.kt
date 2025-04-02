package org.example.model

import org.example.observer.Publisher
import org.example.observer.Subscriber
import org.example.observer.notification.Notification

abstract class Graph : Publisher {
    private val subscribers: MutableList<Subscriber> = mutableListOf()

    /**
     * This method filters the edges, including only those where both vertices are enabled, and notifies the observers
     * to display it.
     * @param edges [String] containing edges, with each edge on a new line.
     */
    abstract fun showEdges(edges: String)

    /**
     * This method enables a vertex and notifies the observers to show only the edges where both vertices are enabled.
     * @param vertex [Vertex] object represents the vertex to be enabled
     */
    abstract fun enableVertex(vertex: Vertex)

    /**
     * This method disables a vertex and notifies the observers to show only the edges where both vertices are enabled.
     * @param vertex [Vertex] object represents the vertex to be enabled
     */
    abstract fun disableVertex(vertex: Vertex)

    override fun addSubscriber(subscriber: Subscriber) {
        if(!subscribers.contains(subscriber)) {
            subscribers.add(subscriber)
        }
    }

    override fun notifySubscribers(notification: Notification) {
        for (subscriber in subscribers) {
            subscriber.update(notification)
        }
    }
}