/**
 * MyBinaryTree is the main class that tests the binary tree ADT implementation
 * The binary tree is implemented with a node class that holds a Worker object as its element
 * Then visualized with a basicPrint method that prints the tree with the order of the nodes
 */
public class MyBinaryTree {

    public static void main(String[] args) {

        //Initializing the workers
        Worker w1 = new Worker("CEO", 100);
        Worker w2 = new Worker("CTO", 120);
        Worker w3 = new Worker("HR", 77);
        Worker w4 = new Worker("RD", 140);
        Worker w5 = new Worker("Eng", 110);
        Worker w6 = new Worker("PR", 22);
        Worker w7 = new Worker("Hiring", 20);

        //Initializing the binary tree nodes
        BinaryTreeNode n1 = new BinaryTreeNode(w1);
        BinaryTreeNode n2 = n1.setLeft(w2);
        BinaryTreeNode n3 = n1.setRight(w3);
        BinaryTreeNode n4 = n2.setLeft(w4);
        BinaryTreeNode n5 = n2.setRight(w5);
        BinaryTreeNode n6 = n3.setLeft(w6);
        BinaryTreeNode n7 = n3.setRight(w7);

        //testing printing method for the binary tree
        basicPrint(n1,0);

        //testing static size method
        System.out.println("The binary tree has " + BinaryTreeNode.size + " nodes");

        //The worker of the main parent of n7 of the Binary Tree
        System.out.println(n7.root()); //should be w1

        //The worker of the parent of n7 of the Binary Tree
        System.out.println(n7.parent()); //should be w3

        //The worker of the left child of n7 of the Binary Tree
        System.out.println(n2.left()); //should be w4

        //The worker of the right child of n7 of the Binary Tree
        System.out.println(n2.right()); //should be w5

    }

    /**
     * The basicPrint method is used to test the binary tree implementation and visualize the tree structure with the order of the nodes
     * Inspired from the textbook material (Data Structures and Algorithms in Java, 6th edition, Ch 8.4.5 Applications of Tree Traversals)
     * This method is a more simplified version of the code on pg.344, which is preorder traversal
     * @param root the node to start the printing from recursively
     * @param n recursive parameter to keep track of the level of the tree
     */
    public static void basicPrint(BinaryTreeNode root, int n) {
        //printing the tree with recursion to do preorder order traversal (parent -> children order)
        if (root == null)
            return;
        //printing the node with attribute values of its worker, its position (1 or 2), and its level in the tree by proportional space indentation
        System.out.println(root.getText(n));
        basicPrint(root.left,n+1);
        basicPrint(root.right,n+1);
    }
}
/**
 * The BinaryTreeNode class is a node class for a binary tree that holds in each node object a Worker object as its element
 * The node contains parent, left child, right child, and element attributes
 */
class BinaryTreeNode {

    BinaryTreeNode parent; //Parent node of a given instance node
    BinaryTreeNode left; //Left child node of a given instance node
    BinaryTreeNode right; //Right child node of a given instance node
    Worker element; //Thw "Worker" type object element of a given instance node
    int position = 0; //Position of the node in the tree

    static int size = 0; //size of the total tree


    public BinaryTreeNode(Worker element) {
        this.parent = null; //sets the parent to null
        this.element = element; //sets the element to the given Worker object
        size++; //increments the size of the tree
        position = 1; //the position for the main parent element is '1'
    }
    public BinaryTreeNode(Worker element, BinaryTreeNode parent) {
        this.parent = parent; //sets the parent to the parent
        this.element = element; //sets the element to the given Worker object
        size++; //increments the size of the tree
    }

    /**
     * The root method returns the root Node's element of the tree or null if empty
     * @return the root node's element of the tree or null if empty
     */
    public Worker root() {
        //Returns the root of the tree or null if empty
        BinaryTreeNode buffer = this.parent;
        while(buffer.parent != null) {
            //goes up the tree until it reaches the parent
            buffer = buffer.parent;
        }
        //returns the root
        return buffer.element;
    }

    /**
     * The parent method returns the parent Node's element of given instance node or null if instance is the binary tree's parent
     * @return the parent node's element of the given instance node or null if instance is the binary tree's parent
     */
    public Worker parent() {
        //Returns the parent Node's element of given instance node or null if instance is the binary tree's parent
        if(this.parent == null)
            return null;
        else
            return this.parent.element;
    }

    /**
     * The left method returns the left child Node's element of given instance node or null if there is no left child
     * @return the left child node's element of the given instance node or null if there is no left child
     */
    public Worker left() {
        //Returns the left child of current instance or null if there is no left child
        if(this.left == null)
            return null;
        else
            return this.left.element;
    }

    /**
     * The right method returns the right child Node's element of given instance node or null if there is no right child
     * @return the right child node's element of the given instance node or null if there is no right child
     */
    public Worker right() {
        //Returns the right child of current instance or null if there is no right child
        if(this.right == null)
            return null;
        else
            return this.right.element;
    }

    /**
     * The setLeft method sets the left child of the given instance node to the given Worker object
     * @return the left child node of the given instance node
     */
    public BinaryTreeNode setLeft(Worker c) {
        //Sets the left child of instance to c
        if(this.left == null) {
            //creates a new node with the given element and sets the parent to the current node
            this.left = new BinaryTreeNode(c, this);
            left.position = 1; //sets the position to 1 (representing left-side nodes)
        } else
            //If a left node already exists, sets the element of the left child to the given element
            this.left.element = c;

        return this.left;
    }

    /**
     * The setRight method sets the right child of the given instance node to the given Worker object
     * @return the right child node of the given instance node
     */
    public BinaryTreeNode setRight(Worker c) {
        //Sets the right child of instance to c
        if(this.right == null) {
            //creates a new node with the given element and sets the parent to the current node
            this.right = new BinaryTreeNode(c, this);
            right.position = 2; //sets the position to 2 (representing right-side nodes)

        } else
            //If a right node already exists, sets the element of the right child to the given element
            this.right.element = c;

        return this.right;
    }

    /**
     * The getText method returns the text of the element with the tree node position, and its element (worker) attributes
     * @param n number of spaces to be added to the left of the text
     * @return the text of the element with the tree node position, and its element (worker) attributes
     */
    public String getText(int n) {
        //n is the number of spaces to be added to the left of the text, further down the height of the binary tree the number of spaces increases
        return spaces(n) + position + "." + this.element.name + " " + this.element.salary;
    }

    /**
     * The spaces method returns a string of 2n spaces for the better visual representation of the binary tree
     * @param n number of spaces to be added to the left of the text
     * @return a string of 2n spaces for the better visual representation of the binary tree
     */
    public String spaces(int n) {
        //Returns a string of 2n spaces for the better visual representation of the binary tree
        return "  ".repeat(Math.max(0, n));
    }
}

/**
 * The Worker class is a class that holds the attributes of a worker
 * The class has a constructor that takes a name and salary as parameters and sets the attributes of the worker to those parameters
 */
class Worker {

    public String name;
    public double salary;

    public Worker(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * The toString method returns a string of the worker's name and salary
     * @return a string of the worker's name and salary
     */
    public String toString() {
        return "Name: " + name + " Salary: " + salary;
    }
}
