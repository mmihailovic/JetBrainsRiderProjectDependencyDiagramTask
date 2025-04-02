package org.example.model

data class DirectEdge(override val from: Vertex, override val to: Vertex) : Edge(from, to) {
    override fun toString(): String {
        return super.toString()
    }
}