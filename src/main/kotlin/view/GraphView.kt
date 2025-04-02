package org.example.view

import net.sourceforge.plantuml.SourceStringReader
import net.sourceforge.plantuml.core.DiagramDescription
import org.example.model.Edge
import org.example.observer.EdgeSubscriber
import org.example.observer.notification.Notification
import org.example.observer.Publisher
import java.awt.Graphics
import java.awt.Image
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import javax.swing.JPanel
import javax.swing.SwingUtilities

class GraphView(publisher: Publisher) : JPanel(), EdgeSubscriber {
    private var image: Image? = null

    init {
        publisher.addSubscriber(this)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        if(image != null) {
            image.let {
                val scaledImage = it?.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH)
                g?.drawImage(scaledImage, 0, 0, this)
            }
        }
        else {
            g?.clearRect(0,0, this.width, this.height)
            g?.drawString("No edges to display", this.width / 2, this.height / 2)
        }
    }

    private fun refreshGraph(edges: String) {
        val code = "@startuml\n$edges@enduml"
        val sourceStringReader = SourceStringReader(code)
        val outputStream = ByteArrayOutputStream()
        val diagramDescription: DiagramDescription? = sourceStringReader.outputImage(outputStream)

        if (diagramDescription == null || diagramDescription.description.equals("(Error)")
            || diagramDescription.description.equals("(Empty)")) {
            image = null
        }
        else {
            image = ImageIO.read(outputStream.toByteArray().inputStream())
        }

        SwingUtilities.invokeLater { repaint() }
    }

    override fun showEdges(edges: List<Edge>) {
        val stringBuilder = StringBuilder()

        for(edge in edges) {
            stringBuilder.append(edge.toString() + "\n")
        }

        refreshGraph(stringBuilder.toString())
    }

    override fun update(notification: Notification) {
        notification.handleNotification(this)
    }
}