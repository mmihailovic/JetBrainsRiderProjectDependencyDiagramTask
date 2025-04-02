package org.example.view

import org.example.model.Graph
import org.example.model.Vertex
import org.example.observer.*
import org.example.observer.notification.Notification
import java.awt.*
import java.awt.event.ItemEvent
import javax.swing.*

class VerticesView(private val graph: Graph) : JPanel(), VertexSubscriber{
    private val buttons: MutableMap<String, JToggleButton> = mutableMapOf()
    private val buttonsPanel: JPanel = JPanel()

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        buttonsPanel.layout = GridLayout(0, 10, 10, 10)
        val pane = JScrollPane(buttonsPanel)
        pane.preferredSize = Dimension(width, 100)
        val label = JLabel("Disable vertices")
        label.horizontalAlignment = SwingConstants.LEFT
        label.alignmentX = Component.LEFT_ALIGNMENT
        pane.alignmentX = Component.LEFT_ALIGNMENT
        add(label)
        this.add(Box.createVerticalStrut(10))
        add(pane)
        graph.addSubscriber(this)
    }

    override fun addVertex(vertex: Vertex) {
        val button = JToggleButton(vertex.name)

        button.addItemListener { e ->
            if(e.stateChange == ItemEvent.SELECTED) {
                graph.disableVertex(vertex)
            }
            else {
                graph.enableVertex(vertex)
            }
        }
        buttons[vertex.name] = button

        SwingUtilities.invokeLater {
            buttonsPanel.add(button)
            revalidate()
            repaint()
        }
    }

    override fun removeVertex(vertex: Vertex) {
        val button = buttons[vertex.name]
        buttons.remove(vertex.name)
        SwingUtilities.invokeLater {
            buttonsPanel.remove(button)
            revalidate()
            repaint()
        }
    }

    override fun update(notification: Notification) {
        notification.handleNotification(this)
    }
}