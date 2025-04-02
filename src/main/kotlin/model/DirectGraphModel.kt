package org.example.model

data class DirectGraphModel(private var verticesMap: MutableMap<String, Vertex>, private val edgesSet: MutableSet<DirectEdge>) {
    val getVerticesMap: MutableMap<String, Vertex>
        get() = verticesMap

    val getEdgesSet: MutableSet<DirectEdge>
        get() = edgesSet

    fun setVerticesMap(map: MutableMap<String, Vertex>) {
        verticesMap = map
    }
}