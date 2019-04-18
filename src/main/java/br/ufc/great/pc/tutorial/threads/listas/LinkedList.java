package br.ufc.great.pc.tutorial.threads.listas;

public class LinkedList {   
    Node head; // head of list
    int size=0;
    // Linked list Node. 
    // This inner class is made static 
    // so that main() can access it 
    static class Node { 
        int data; 
        Node next; 
        int size;
  
        // Constructor 
        Node(int d) 
        { 
            data = d; 
            next = null;
        } 
    } 
    
    // Method to insert a new node 
    public static LinkedList insert(LinkedList list, int data) 
    { 
        // Create a new node with given data 
        Node new_node = new Node(data); 
        new_node.next = null; 
  
        // If the Linked List is empty, 
        // then make the new node as head 
        if (list.head == null) { 
            list.head = new_node;
            list.size = list.size + 1;
        } 
        else { 
            // Else traverse till the last node 
            // and insert the new_node there 
            Node last = list.head; 
            while (last.next != null) { 
                last = last.next; 
            } 
  
            // Insert the new_node at last node 
            last.next = new_node;
            list.size = list.size + 1;
        } 
  
        // Return the list by head 
        return list; 
    } 
    
    public static LinkedList deleteByKey(LinkedList list, int key) 
    { 
        // Store head node 
        Node currNode = list.head, prev = null; 
  
        // 
        // CASE 1: 
        // If head node itself holds the key to be deleted 
        if (currNode != null && currNode.data == key) { 
            list.head = currNode.next; // Changed head 
  
            // Display the message 
            System.out.println(key + " found and deleted"); 
  
            list.size = list.size - 1;
            // Return the updated List 
            return list;
        }
        
        // If the key was present, it should be at currNode 
        // Therefore the currNode shall not be null 
        if (currNode != null) { 
            // Since the key is at currNode 
            // Unlink currNode from linked list 
            prev.next = currNode.next; 
            list.size = list.size - 1;  
            // Display the message 
            System.out.println(key + " found and deleted"); 
        } 
        
        // If key was not present in linked list 
        // currNode should be null 
        if (currNode == null) { 
            // Display the message 
            System.out.println(key + " not found"); 
        } 
        
		return null; 
    }
    
 // Method to print the LinkedList. 
    public static void printList(LinkedList list) 
    { 
        Node currNode = list.head; 
   
        System.out.print("LinkedList: "); 
   
        // Traverse through the LinkedList 
        while (currNode != null) { 
            // Print the data at current node 
            System.out.print(currNode.data + " "); 
   
            // Go to next node 
            currNode = currNode.next; 
        } 
    } 
    
 // Driver code 
    public static void main(String[] args) 
    { 
        /* Start with the empty list. */
        LinkedList list = new LinkedList(); 
  
        // 
        // ******INSERTION****** 
        // 
  
        // Insert the values 
        list = insert(list, 1); 
        list = insert(list, 2); 
        list = insert(list, 3); 
        list = insert(list, 4); 
        list = insert(list, 5); 
        list = insert(list, 6); 
        list = insert(list, 7); 
        list = insert(list, 8); 
  
        // Print the LinkedList 
        printList(list); 
        
        System.out.println("Remove o elemento 1"); 
        deleteByKey(list, 1); 
  
        System.out.println("");
        // Print the LinkedList 
        printList(list);
        System.out.println("Tamanho: " + list.size);
       
        System.out.println("Remove o elemento 2");
        deleteByKey(list, 2); 

        System.out.println("");
        // Print the LinkedList 
        printList(list);
        System.out.println("Tamanho: " + list.size);
        
    } 
} 