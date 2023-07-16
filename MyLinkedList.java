public class MyLinkedList<E> {
    // This class represents a linked list
    public Node head;
    public int size = 0;

    /**
     * This method adds an element to the head of a linked list
     * @param element
     */
    public void add(int element) {
        Node newNode = new Node(element); //contains int element and null for next
        newNode.next = this.head; //links new node to the previous head
        this.head = newNode; //global variable head is now new node
        this.size++;
    }

    /**
     * This method adds a given element after the first occurrence of the input value (afterInt), otherwise add it to tail
     * @param element
     * @param afterInt
     */
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

    /**
     * This method removes the given value at first occurrence if contained in linkedlist
     * @param value
     */
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


    /**
     * This method returns true if the input value is contained in the linked list
     * @param value
     * @return
     */
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

    /**
     * This method returns the size of the linked list
     * @return
     */
    public int size() {
        return this.size;
    }


    /**
     * This method prints the elements of the linked list in the order they are placed head to tail
     * @return
     */
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

    /**
     * This method returns true if the two linked lists (instance and input) are the same
     * @param otherList
     * @return
     */
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

    /**
     * This method adds a given element to the linked list such that it is placed after the element that is smaller than it
     * @param element
     */
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

/**
 * This class represents a node in a linked list
 * @param <E>
 */
class Node<E> {
    public E element;
    public Node<E> next;

    public Node(E element) {
        this.element = element;
        this.next = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
