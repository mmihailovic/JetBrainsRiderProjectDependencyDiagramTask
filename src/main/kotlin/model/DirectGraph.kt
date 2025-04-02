package org.example.model

import org.example.observer.notification.*

class DirectGraph(private val directGraphModel: DirectGraphModel) : Graph() {
    private val lock = Any()

    override fun showEdges(edges: String) {
        synchronized(lock) {
            val newVerticesMap: MutableMap<String, Vertex> = mutableMapOf()
            var verticesMap: MutableMap<String, Vertex> = directGraphModel.getVerticesMap
            val edgesSet: MutableSet<DirectEdge> = directGraphModel.getEdgesSet

            for (edge in edges.split("\n")) {
                val line = edge.split(Regex("->")).map(String::trim)
                if (line.size > 1 && line[0].isNotEmpty() && line[1].isNotEmpty()) {
                    val from = getOrAddVertex(line[0], newVerticesMap)
                    val to = getOrAddVertex(line[1], newVerticesMap)
                    edgesSet.add(DirectEdge(from, to))
                }
            }

            for (v in verticesMap.values) {
                notifySubscribers(RemoveVertexNotification(v))
            }

            directGraphModel.setVerticesMap(newVerticesMap)
            verticesMap = newVerticesMap
            edgesSet.removeIf { !verticesMap.containsKey(it.from.name) || !verticesMap.containsKey(it.to.name) }
            notifySubscribers(ShowEdgesNotification(edgesSet.filter { verticesMap[it.from.name]!!.isEnabled && verticesMap[it.to.name]!!.isEnabled }))
        }
    }

    override fun enableVertex(vertex: Vertex) {
        synchronized(lock) {
            val verticesMap: MutableMap<String, Vertex> = directGraphModel.getVerticesMap
            val edgesSet: MutableSet<DirectEdge> = directGraphModel.getEdgesSet
            verticesMap[vertex.name]?.isEnabled = true
            val notification = ShowEdgesNotification(edgesSet.filter { verticesMap[it.from.name]!!.isEnabled && verticesMap[it.to.name]!!.isEnabled })
            notifySubscribers(notification)
        }
    }

    override fun disableVertex(vertex: Vertex) {
        synchronized(lock) {
            val verticesMap: MutableMap<String, Vertex> = directGraphModel.getVerticesMap
            val edgesSet: MutableSet<DirectEdge> = directGraphModel.getEdgesSet
            verticesMap[vertex.name]?.isEnabled = false
            notifySubscribers(ShowEdgesNotification(edgesSet.filter { verticesMap[it.from.name]!!.isEnabled && verticesMap[it.to.name]!!.isEnabled }))
        }
    }

    private fun getOrAddVertex(vertexName: String, newVerticesMap: MutableMap<String, Vertex>): Vertex {
        val vertex = Vertex(vertexName)
        val verticesMap: MutableMap<String, Vertex> = directGraphModel.getVerticesMap

        if(verticesMap.containsKey(vertex.name)) {
            vertex.isEnabled = verticesMap[vertex.name]!!.isEnabled
            verticesMap.remove(vertex.name)
        }
        else if(newVerticesMap.containsKey(vertex.name)) {
            vertex.isEnabled = newVerticesMap[vertex.name]!!.isEnabled
        }
        else {
            notifySubscribers(AddVertexNotification(vertex))
        }

        newVerticesMap[vertex.name] = vertex
        return vertex
    }

}