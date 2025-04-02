package org.example.view

import java.awt.BorderLayout
import javax.swing.JPanel


class MainPanel(graphView: GraphView, verticesView: VerticesView, edgesView: EdgesView) : JPanel() {

    init {
        layout = BorderLayout(20, 20)
        add(graphView, BorderLayout.CENTER)
        add(verticesView, BorderLayout.SOUTH)
        add(edgesView, BorderLayout.EAST)
    }
}