package org.example.view

import java.awt.Component
import javax.swing.*

class EdgesView : JPanel() {
    private val textArea = JTextArea(30, 10)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        val enterEdgesLabel = JLabel("Enter graph edges below:")
        enterEdgesLabel.alignmentX = Component.LEFT_ALIGNMENT
        val textAreaScrollPane = JScrollPane(textArea)
        textAreaScrollPane.alignmentX = Component.LEFT_ALIGNMENT
        this.add(enterEdgesLabel)
        this.add(Box.createVerticalStrut(10))
        this.add(textAreaScrollPane)
    }

    val getTextArea: JTextArea
        get() = textArea
}