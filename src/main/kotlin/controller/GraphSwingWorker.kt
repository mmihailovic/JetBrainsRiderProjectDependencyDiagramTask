package org.example.controller

import org.example.model.Graph
import javax.swing.SwingWorker

class GraphSwingWorker(private val graph: Graph, private val edges: String) : SwingWorker<Unit, Unit>() {
    override fun doInBackground() {
        graph.showEdges(edges)
    }
}