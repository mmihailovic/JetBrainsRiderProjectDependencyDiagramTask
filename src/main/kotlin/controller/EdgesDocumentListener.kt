package org.example.controller

import org.example.model.Graph
import org.example.view.EdgesView
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class EdgesDocumentListener(private val graph: Graph, private val edgesView: EdgesView) : DocumentListener {

    override fun insertUpdate(e: DocumentEvent?) {
        GraphSwingWorker(graph, edgesView.getTextArea.text).execute()
    }

    override fun removeUpdate(e: DocumentEvent?) {
        GraphSwingWorker(graph, edgesView.getTextArea.text).execute()
    }

    override fun changedUpdate(e: DocumentEvent?) {
        GraphSwingWorker(graph, edgesView.getTextArea.text).execute()
    }
}