//Student Name: Cem Parlar
//Student McGill ID: 260776326
public class MyLinkedList<E> {
    //This code was written based on the guidelines set in the assignment 1 pdf
    //Code exercises regarding Linked Lists in the class were used as basis for the node buffer concept
    public Node head;
    public int size = 0;

    //Add an element to the head of a linked list
    public void add(int element) {
        Node newNode = new Node(element); //contains int element and null for next
        newNode.next = this.head; //links new node to the previous head
        this.head = newNode; //global variable head is now new node
        this.size++;
    }

    //Add a given element after the first occurrence of the input value (afterInt), otherwise add it to tail
    public void addAfter(int element, int afterInt) {
        Node newNode = new Node(element);
        if(head == null) {
            this.head = newNode;
            this.size++;
        } else {
            Node buffer = this.head;
            //The number of value(int)s in the linkedlist
            while (buffer != null) {
                if((int)buffer.element == afterInt) {
                    //new node here will get wedged in between the buffer and the element after
                    // buffer -> newNode -> element previously following buffer
                    newNode.setNext(buffer.next); //new node gets linked to the element after buffer
                    buffer.setNext(newNode); //new node gets linked also back to the buffer
                    this.size++;
                    return; //only the first occurrence
                } else if(buffer.next == null) {
                    //when buffer.next is null buffer.element is the tail
                    //This indicates that the loop has reached the end of the linked list
                    //This will run if no element of value(int) is found in the linked list
                    newNode.setNext(null); //newNode is pointing to null (newNode = tail)
                    buffer.setNext(newNode); //buffer pointing to newNode
                    this.size++;
                    return; //to avoid infinity loop
                }
                buffer = buffer.next; //iteration through the linked list
            }
        }

    }

    //remove the given value at first occurrence if contained in linkedlist
    public void remove(int value) {
        Node buffer = this.head;
        while(buffer.next != null) {
            if((int) buffer.next.getElement() == value) {
                buffer.setNext(buffer.next.getNext()); //pointing buffer next to the further next node
                this.size--;
                return; //first occurrence only
            }
            buffer = buffer.next; //iteration through the linked list
        }
    }

    //return true if the input value is contained in the linked list
    public boolean contains(int value) {
        Node buffer = this.head;
        while(buffer != null) {
            if((int)buffer.element == value) {
                //the if condition is met and this means that the linked list contains the value
                return true;
            }
            buffer = buffer.next; //iteration through the linked list
        }
        //if the loop does not return true after iterating through the whole linked list return false
        return false;
    }
    public int size() {
        return this.size;
    }


    //print the elements of the linked list in the order they are placed head to tail
    @Override
    public String toString() {
        Node buffer = this.head;
        String print = "[";
        while(buffer.next != null) {
            print += buffer.element + "-->";
            buffer = buffer.next; //iteration through the linked list
        }
        return print + buffer.element + "]";
    }

    //return true if the two linked lists (instance and input) are the same
    public boolean compare(MyLinkedList otherList) {
        if(this.size != otherList.size)
            return false;
        Node buffer = this.head;
        Node bufferOther = otherList.head;
        while(buffer != null) {
            if(buffer.element != bufferOther.element)
                return false;
            buffer = buffer.next;
            bufferOther = bufferOther.next;
        }
        return true;
    }

    //add given element to the linked list such that it is placed after the element that is smaller from it
    public void addInOrder(int element) {
        Node newNode = new Node(element);
        //If the element is smaller than head, add to the head of the linked list
        if((int)head.element >= element) {
            newNode.next = this.head; //links new node to the previous head
            this.head = newNode; //global variable head is now new node
            this.size++;
            return;
        }
        //Iterate through the list to find the place of the element in the ordered linked list
        Node buffer = this.head;
        while(buffer.next != null) {
            if((int)buffer.next.getElement() >= element) {
                //This condition if true places the element before the element that is larger from it
                newNode.next = buffer.next;
                buffer.next = newNode;
                this.size++;
                return;
            }
            buffer = buffer.next;
        }
        //Code reaching here means that the element is larger than all elements in the linked list
        //So, it will be added to the tail (the end)
        newNode.next = null;
        buffer.next = newNode;
        this.size++;
    }


}
