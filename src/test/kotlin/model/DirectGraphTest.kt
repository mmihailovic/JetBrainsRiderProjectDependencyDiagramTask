package model

import org.example.model.*
import org.example.observer.EdgeSubscriber
import org.example.observer.VertexSubscriber
import org.example.observer.notification.AddVertexNotification
import org.example.observer.notification.RemoveVertexNotification
import org.example.observer.notification.ShowEdgesNotification
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DirectGraphTest {

    @InjectMocks
    lateinit var directGraph: DirectGraph

    @Mock
    lateinit var directGraphModel: DirectGraphModel

    @Mock
    lateinit var edgeSubscriber: EdgeSubscriber

    @Mock
    lateinit var vertexSubscriber: VertexSubscriber

    @Test
    fun test_ShowEdges() {
        val vertex = Vertex("A")
        vertex.isEnabled = false
        val verticesMap = mutableMapOf("A" to vertex, "B" to Vertex("B"), "C" to Vertex("C"), "D" to Vertex("D"))
        val edgesSet = mutableSetOf(DirectEdge(vertex, Vertex("B")), DirectEdge(Vertex("C"), Vertex("D")))
        directGraph.addSubscriber(edgeSubscriber)
        directGraph.addSubscriber(vertexSubscriber)

        `when`(directGraphModel.getEdgesSet).thenReturn(edgesSet)
        `when`(directGraphModel.getVerticesMap).thenReturn(verticesMap)

        directGraph.showEdges("A->C\nB->C\nC->F\nA->\n")

        verify(vertexSubscriber).update(RemoveVertexNotification(Vertex("D")))
        verify(vertexSubscriber).update(AddVertexNotification(Vertex("F")))
        verify(edgeSubscriber).update(ShowEdgesNotification(listOf(DirectEdge(Vertex("B"), Vertex("C")), DirectEdge(Vertex("C"), Vertex("F")))))
    }

    @Test
    fun test_DisableVertex() {
        val verticesMap = mutableMapOf("A" to Vertex("A"), "B" to Vertex("B"), "C" to Vertex("C"))
        val edgesSet = mutableSetOf(DirectEdge(Vertex("A"), Vertex("B")), DirectEdge(Vertex("A"), Vertex("C")),
            DirectEdge(Vertex("B"), Vertex("C")))

        directGraph.addSubscriber(edgeSubscriber)

        `when`(directGraphModel.getEdgesSet).thenReturn(edgesSet)
        `when`(directGraphModel.getVerticesMap).thenReturn(verticesMap)

        directGraph.disableVertex(Vertex("C"))

        verify(edgeSubscriber).update(ShowEdgesNotification(listOf(DirectEdge(Vertex("A"), Vertex("B")))))
    }

    @Test
    fun test_EnableVertex() {
        val vertexA = Vertex("A")
        vertexA.isEnabled = false
        val vertexB = Vertex("B")
        vertexB.isEnabled = false
        val vertexC = Vertex("C")
        val edgesSet = mutableSetOf(DirectEdge(vertexA, vertexB), DirectEdge(vertexA, vertexC), DirectEdge(vertexB, vertexC))
        val verticesMap = mutableMapOf(vertexA.name to vertexA, vertexB.name to vertexB, vertexC.name to vertexC)
        directGraph.addSubscriber(edgeSubscriber)

        `when`(directGraphModel.getEdgesSet).thenReturn(edgesSet)
        `when`(directGraphModel.getVerticesMap).thenReturn(verticesMap)

        directGraph.enableVertex(vertexA)

        verify(edgeSubscriber).update(ShowEdgesNotification(listOf(DirectEdge(vertexA, vertexC))))
    }

}