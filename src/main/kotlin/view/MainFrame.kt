package org.example.view

import org.example.controller.EdgesController
import org.example.model.DirectGraph
import org.example.model.DirectGraphModel
import org.example.model.Graph
import javax.swing.JFrame

class MainFrame : JFrame() {
    val directGraphModel = DirectGraphModel(mutableMapOf(), mutableSetOf())
    val graph: Graph = DirectGraph(directGraphModel)
    val graphView: GraphView = GraphView(graph)
    val verticesView: VerticesView = VerticesView(graph)
    val edgesView: EdgesView = EdgesView()
    val edgesController: EdgesController = EdgesController(graph, edgesView)

    init {
        title = "Rider Project Dependency Diagram"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800, 800)
        add(MainPanel(graphView, verticesView, edgesView))

    }
}