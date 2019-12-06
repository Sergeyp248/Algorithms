import java.util.*;

public class Graph {

    private Stack<Vertex> stack;

    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private ArrayList<ArrayList<Boolean>> adjacencyMatrix;
    private ArrayList<ArrayList<Vertex>> adjacencyList;
    private ArrayList<ArrayList<Integer>> incidenceMatrix;

    public Graph() {
        adjacencyMatrix = new ArrayList<>();
        adjacencyList = new ArrayList<>();
        incidenceMatrix = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        stack = new Stack<>();
    }

    public Graph(int graphOrder) {

        adjacencyMatrix = new ArrayList<>();

        for (int i = 0; i < graphOrder; i++) {
            adjacencyMatrix.add(new ArrayList<>());
        }

        for (int i = 0; i < graphOrder; i++) {
            for (int j = 0; j < graphOrder; j++) {
                adjacencyMatrix.get(i).add(false);
            }
        }

    }

    public boolean isEmpty() {
        return edges.isEmpty();
    }

    public int size() {
        return vertices.size();
    }

    public boolean hasEdge(Edge edge) {
        return edges.contains(edge);
    }

    public boolean addVertex(Vertex v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            if (adjacencyMatrix.size() < vertices.size()) {
                increaseMatrixSizeByOne();
            }

            adjacencyList.add(new ArrayList<>());

            incidenceMatrix.add(new ArrayList<>());

            return true;
        }
        return false;
    }

    public boolean removeVertex(Vertex v) {
        if (vertices.contains(v)) {
            int vIndex = vertices.indexOf(v);
            for (ArrayList<Boolean> row : adjacencyMatrix) {
                row.remove(vIndex);
            }
            adjacencyMatrix.remove(vIndex);

            incidenceMatrix.remove(vIndex);

            for (Vertex adjacencyVertex : adjacencyList.get(vIndex)) {

            }

            adjacencyList.remove(vIndex);
            for (ArrayList<Vertex> adjacencyVertices : adjacencyList) {
                for (int j = 0; j < adjacencyVertices.size(); j++) {
                    if (adjacencyVertices.get(j).equals(v)) {
                        adjacencyVertices.remove(j);
                        j--;
                    }
                }
            }
            vertices.remove(v);
            return true;
        }
        return false;
    }

    public boolean addEdge(Vertex source, Vertex destination) {
        addVertex(source);
        addVertex(destination);

        int sourceValue = vertices.indexOf(source);
        int destinationValue = vertices.indexOf(destination);

        if (!hasEdge(new Edge(source, destination))) {
            adjacencyMatrix.get(sourceValue).set(destinationValue, true);
            adjacencyList.get(sourceValue).add(destination);
            edges.add(new Edge(source, destination));

            int edgeIndex = edges.indexOf(new Edge(source, destination));

            for (int i = 0; i < incidenceMatrix.size(); i++) {
                int size = edges.size() - incidenceMatrix.get(i).size();
                for (int j = 0; j < size; j++) {
                    incidenceMatrix.get(i).add(0);
                }
            }

            incidenceMatrix.get(sourceValue).set(edgeIndex, 1);
            incidenceMatrix.get(destinationValue).set(edgeIndex, 2);

            return true;
        }
        return false;
    }

    public boolean removeEdge(Vertex source, Vertex destination) {
        if (hasEdge(new Edge(source, destination))) {
            int sourceIndex = vertices.indexOf(source);
            int destinationIndex = vertices.indexOf(destination);
            adjacencyMatrix.get(sourceIndex).set(destinationIndex, false);
            adjacencyList.get(sourceIndex).remove(destination);
            edges.remove(new Edge(source, destination));
            return true;
        }
        return false;
    }

    public void printEdges() {
        if (!isEmpty()) {
            for (Edge edge : edges) {
                System.out.println(edge.toString());
            }
        } else {
            System.out.println("Graph has no edges!");
        }
    }

    public void printVertices() {
        if (!vertices.isEmpty()) {
            for (Vertex vertex : vertices) {
                System.out.println(vertices.indexOf(vertex) + 1 + " - " + vertex.getLabel());
            }
        }
    }

    private void increaseMatrixSizeByOne() {

        adjacencyMatrix.add(new ArrayList<>());

        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            int size = adjacencyMatrix.size() - adjacencyMatrix.get(i).size();
            for (int j = 0; j < size; j++) {
                adjacencyMatrix.get(i).add(false);
            }
        }
    }

    public void printAdjacencyList() {

        for (int i = 0; i < adjacencyList.size(); i++) {
            int size = adjacencyList.get(i).size();
            System.out.print(vertices.get(i).getLabel() + " ---> ");
            for (int j = 0; j < size; j++) {
                if (j > 0) System.out.print(", " + adjacencyList.get(i).get(j).getLabel());
                else System.out.print(adjacencyList.get(i).get(j).getLabel());
            }
            System.out.println();
        }

    }

    public void printIncidenceMatrix() {
        StringBuilder result = new StringBuilder();
        String value;
        for (int i = 0; i < incidenceMatrix.size(); i++) {
            for (int j = 0; j < incidenceMatrix.get(i).size(); j++) {
                value = incidenceMatrix.get(i).get(j) + "  ";
                result.append(value);
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    public void printAdjacencyMatrix() {
        if (!isEmpty()) {

            StringBuilder result = new StringBuilder();

            String value;

            for (int i = 0; i < adjacencyMatrix.size(); i++) {
                for (int j = 0; j < adjacencyMatrix.size(); j++) {
                    value = (adjacencyMatrix.get(i).get(j) ? 1 : 0) + " ";
                    result.append(value);
                }
                result.append('\n');
            }
            System.out.println(result);
        }
    }

    public void dfs(Vertex v) {
        stack.push(v);
        while (!stack.empty()) {
            Vertex current = stack.pop();
            if(!current.isVisited()) {
                vertices.get(vertices.indexOf(current)).setVisited(true);
                for(Vertex elem : adjacencyList.get(vertices.indexOf(current))) {
                    stack.push(elem);
                }
            }
        }
    }
}