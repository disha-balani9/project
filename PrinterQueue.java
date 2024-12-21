import java.util.Scanner;

public class PrinterQueue {

    private String[] queue;  // Array to store print commands
    private int front;       // Points to the front of the queue
    private int rear;        // Points to the rear of the queue
    private int size;        // Maximum size of the queue
    private int currentSize; // Tracks the current number of elements

    // Constructor to initialize the queue
    public PrinterQueue(int size) {
        this.size = size;
        this.queue = new String[size];
        this.front = 0;
        this.rear = -1;
        this.currentSize = 0;
    }

    // Method to add a print command to the queue
    public void enqueue(String document) {
        if (currentSize == size) {
            System.out.println("Queue is full! Cannot add: " + document);
            return;
        }
        rear = (rear + 1) % size; // Circular queue logic
        queue[rear] = document;
        currentSize++;
        System.out.println("Added to queue: " + document);
    }

    // Method to remove and return the front print command from the queue
    public String dequeue() {
        if (currentSize == 0) {
            System.out.println("Queue is empty! No documents to print.");
            return null;
        }
        String document = queue[front];
        front = (front + 1) % size; // Circular queue logic
        currentSize--;
        return document;
    }

    // Method to process print commands
    public void processPrintCommands() {
        while (currentSize > 0) {
            String document = dequeue();
            if (document != null) {
                System.out.println("Printing: " + document);
                try {
                    Thread.sleep(2000); // Simulating printing time (2 seconds)
                } catch (InterruptedException e) {
                    System.out.println("Printing interrupted for: " + document);
                }
            }
        }
        System.out.println("All documents printed.");
    }

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the number of documents to print
        System.out.print("How many documents do you want to print? ");
        int numDocuments = scanner.nextInt(); // Read the number of documents
        scanner.nextLine(); // Consume the newline left over by nextInt()

        // Create a PrinterQueue instance with the number of documents as max size
        PrinterQueue printerQueue = new PrinterQueue(numDocuments);

        // Ask the user for the document names
        for (int i = 1; i <= numDocuments; i++) {
            System.out.print("Enter the name of document " + i + ": ");
            String documentName = scanner.nextLine(); // Read the document name
            printerQueue.enqueue(documentName);
        }

        // Process and print the documents
        printerQueue.processPrintCommands();

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
