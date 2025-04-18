package org.example.observer.notification

import org.example.model.Vertex
import org.example.observer.Subscriber
import org.example.observer.VertexSubscriber

data class RemoveVertexNotification(private val vertex: Vertex) : Notification(){
    override fun handleNotification(subscriber: Subscriber) {
        if(subscriber is VertexSubscriber) {
            subscriber.removeVertex(vertex)
        }
    }
}