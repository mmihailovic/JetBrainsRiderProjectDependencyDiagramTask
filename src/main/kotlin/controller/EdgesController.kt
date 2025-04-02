package org.example.controller

import org.example.model.Graph
import org.example.view.EdgesView

class EdgesController(graph: Graph, edgesView: EdgesView) {

    init {
        edgesView.getTextArea.document.addDocumentListener(EdgesDocumentListener(graph, edgesView))
    }
}