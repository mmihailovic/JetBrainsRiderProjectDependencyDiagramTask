package org.example.model

data class Vertex(val name: String) {
    var isEnabled = true

    override fun toString(): String {
        return name
    }
}
