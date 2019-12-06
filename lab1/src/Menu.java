import java.util.Scanner;

public class Menu {

    private final int OPTIONS_NUM = 13;
    private boolean exit;
    private Vertex vertex;
    private Vertex[] edge = new Vertex[2];

    private Graph graph = new Graph();

    public void run() {
        printMenu();
        while (!exit) {
            int userChoice = getInput();
            performAction(userChoice);
        }
    }

    private void printMenu() {
        System.out.println("==================================");
        System.out.println("|         MENU SELECTION         |");
        System.out.println("==================================");
        System.out.println("| Options:                       |");
        System.out.println("|  1. Add vertex                 |");
        System.out.println("|  2. Add edge                   |");
        System.out.println("|  3. Remove vertex              |");
        System.out.println("|  4. Remove edge                |");
        System.out.println("|  5. Check for edge             |");
        System.out.println("|  6. Check for emptiness        |");
        System.out.println("|  7. Print edges                |");
        System.out.println("|  8. Print vertices             |");
        System.out.println("|  9. Print adjacency matrix     |");
        System.out.println("|  10. Print adjacency list      |");
        System.out.println("|  11. Print incidence matrix    |");
        System.out.println("|  12. DFS                       |");
        System.out.println("|  13. Exit                      |");
        System.out.println("==================================");
    }

    private int getInput() {
        Scanner in = new Scanner(System.in);
        int userChoice = 0;
        while (userChoice < 1 || userChoice > OPTIONS_NUM) {
            try {
                System.out.print("\nEnter a choice: ");
                userChoice = Integer.valueOf(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return userChoice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 1:
                addVertex();
                break;
            case 2:
                addEdge();
                break;
            case 3:
                removeVertex();
                break;
            case 4:
                removeEdge();
                break;
            case 5:
                hasEdge();
                break;
            case 6:
                System.out.println(graph.isEmpty());
                break;
            case 7:
                graph.printEdges();
                break;
            case 8:
                graph.printVertices();
                break;
            case 9:
                graph.printAdjacencyMatrix();
                break;
            case 10:
                graph.printAdjacencyList();
                break;
            case 11:
                graph.printIncidenceMatrix();
                break;
            case 12:
                graph.dfs(new Vertex("minsk"));
                break;
            case 13:
                System.out.println("Exit the program...");
                exit = true;
                break;
            default:
                break;
        }
    }

    private void addVertex() {
        getVertex();
        if (graph.addVertex(vertex)) System.out.println("Vertex added successfully");
        else System.out.println("Vertex already exists");
    }

    private void addEdge() {
        getEdges();
        if (graph.addEdge(edge[0], edge[1])) System.out.println("Edge added successfully");
        else System.out.println("Edge already exists");
    }

    private void removeVertex() {
        getVertex();
        if (graph.removeVertex(vertex)) System.out.println("Vertex removed successfully");
        else System.out.println("Such a vertex doesn't exist");
    }

    private void removeEdge() {
        getEdges();
        if (graph.removeEdge(edge[0], edge[1])) System.out.println("Edge removed successfully");
        else System.out.println("Such an edge doesn't exist");
    }

    private void hasEdge() {
        getEdges();
        System.out.println(graph.hasEdge(new Edge(edge[0], edge[1])));
    }

    private void getEdges() {
        Scanner sc = new Scanner(System.in);
        String sourceLabel;
        String destinationLabel;

        System.out.println("Enter a source vertex label: ");
        sourceLabel = sc.nextLine();
        System.out.println("Enter a destination vertex label: ");
        destinationLabel = sc.nextLine();

        edge[0] = new Vertex(sourceLabel);
        edge[1] = new Vertex(destinationLabel);
    }

    private void getVertex() {
        Scanner sc = new Scanner(System.in);
        String label;

        System.out.println("Enter a vertex label: ");
        label = sc.nextLine();

        vertex = new Vertex(label);
    }
}
