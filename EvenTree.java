import java.util.*;

class SimpleTreeNode<T> {
    public T Value;
    public SimpleTreeNode(T val)
    {
        Value = val;
    }

    public T getValue() {
        return Value;
    }
}

class SimpleTree<T> {
    SimpleTreeNode[] vertex;
    int[][] m_adjacency;
    int max_vertex;
    private int vertexSize;

    public SimpleTree(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new SimpleTreeNode[size];
    }

    public void AddVertex(T value) {
        if (getVertexSize() == max_vertex) {
            return;
        }
        vertex[getVertexSize()] = new SimpleTreeNode(value);
        vertexSizeIncrement();
    }

    public void AddVertex(SimpleTreeNode<T> simpleTreeNode) {
        if (getVertexSize() != max_vertex) {
            vertex[getVertexSize()] = simpleTreeNode;
            vertexSizeIncrement();
        }
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        if (v < 0 || v >= getVertexSize()) {
            return;
        }
        vertex[v] = null;
        for (int i = 0; i < getVertexSize(); i++) {
            m_adjacency[v][i] = 0;
        }
        for (int i = 0; i < getVertexSize(); i++) {
            m_adjacency[i][v] = 0;
        }
        vertexSizeDecrement();
    }

    private boolean checkCorrectVertex(int v1, int v2) {
        if (v1 < 0 || v1 >= getMax_vertex() || v2 < 0 || v2 >= getMax_vertex()) {
            return false;
        }
        return true;
    }

    public boolean IsEdge(int v1, int v2) {
        if (!checkCorrectVertex(v1, v2)) {
            return false;
        }
        // true если есть ребро между вершинами v1 и v2
        if (m_adjacency[v1][v2] == 1) {
            return true;
        }
        return false;
    }

    public void AddEdge(int v1, int v2) {
        if (!checkCorrectVertex(v1, v2)) {
            return;
        }
        // добавление ребра между вершинами v1 и v2
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // удаление ребра между вершинами v1 и v2
        if (!checkCorrectVertex(v1, v2)) {
            return;
        }
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    private void vertexSizeIncrement() {
        vertexSize++;
    }

    private void vertexSizeDecrement() {
        vertexSize--;
    }

    public int getMax_vertex() {
        return max_vertex;
    }

    public int getVertexSize() {
        return vertexSize;
    }

    public ArrayList<T> EvenTrees() {
        ArrayList<T> list = new ArrayList<>();
        if (getVertexSize() % 2.0 == 0) {
            deleteEdge(list, 0);
        }
        return list;
    }

    private List deleteEdge(List listEdgeDelete, int vertexPresent) {
        for (int i = vertexPresent + 1; i < getVertexSize(); i++) {
            if (m_adjacency[i][vertexPresent] == 1) {
                if (counterVertex(i)  % 2 == 0) {
                    listEdgeDelete.add(vertex[vertexPresent].getValue());
                    listEdgeDelete.add(vertex[i].getValue());
                }
                deleteEdge(listEdgeDelete, i);
            }
        }
        return listEdgeDelete;
    }

    private int counterVertex(int vertex) {
        int counter = 1;
        for (int i = vertex + 1; i < getVertexSize(); i++) {
            if (m_adjacency[i][vertex] == 1) {
                counter += counterVertex(i);
            }
        }
        return counter;
    }

}
