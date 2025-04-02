package org.example.model

abstract class Edge(open val from: Vertex, open val to: Vertex) {
    override fun toString(): String {
        return "$from -> $to"
    }
}
