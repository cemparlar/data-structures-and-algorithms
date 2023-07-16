//Name: Cem Parlar
//McGill ID: 260776326
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class runs a program that allows the user to play with stacks
 * The user can add items to the stack, remove items from the stack, and perform operations on the stack
 * The user can also see the contents of the stack
 */
public class ArrayStackCreator {

    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>(100);
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Stacks!");
        System.out.println("-List of commands");
        System.out.println("""
                - Enter (a) to the above question and enter an integer you wish to add to your stack
                - Enter (+ or *) as an operator to be applied to the last two items added to your stack
                - Enter (?) if you wish to see everything in your stack printed to the screen
                - Enter (p) if you wish to remove the last item
                - Enter (z) if you are done playing with stacks""");
        while(true) {
            System.out.println("Would you like to input a command?");
            String inputAll = scan.nextLine();
            //separate scanner inputs by spaces
            String[] inputArray = inputAll.split(" ");
            String input = inputArray[0].toLowerCase();
            String inputNumber = "";
            //If there is a second input, it is the number
            if(inputArray.length > 1)
                inputNumber = inputArray[1];

            //TODO: Implement a switch for the commands
            switch (input) {
                case "a" -> {
                    //pushes an item to the stack
                    stack.push(Integer.parseInt(inputNumber));
                    System.out.println("Your value has been added to the stack!");
                }
                case "+" -> {
                    //Adds the last two items in the stack together and pushes them to the stack as one element
                    int first = stack.pop();
                    int second = stack.pop();
                    stack.push(first + second);
                    System.out.println("You have added together the last two items of your stack and pushed the total: " + (first + second));
                }
                case "*" -> {
                    //Multiplies the last two items in the stack together and pushes them to the stack as one element
                    int first = stack.pop();
                    int second = stack.pop();
                    stack.push(first * second);
                    System.out.println("You have multiplied together the last two items of your stack and pushed the total: " + (first * second));
                }
                case "?" -> {
                    //Prints the stack
                    System.out.println("Your stack items are: " + stack);
                    System.out.println("Your stack size is: " + stack.size());
                }
                case "p" ->
                    //Removes the last item in the stack
                        stack.pop();
                case "z" -> {
                    //Exits the program and loop
                    System.out.println("Thank you for playing with stacks!");
                    return;
                }
            }
        }

    }
}

/**
 * This class implements the Stack ADT using an array
 * @param <E> the type of the elements stored in the stack
 */
class ArrayStack<E> implements Stack<E> {

    // Array holding the stack elements

    private int[] S;

    //Index of top element (for pop)
    private int top = -1;

    //Constructor
    public ArrayStack(int capacity) {
        S = new int[capacity];
    }

    /**
     * Pushes the element onto the stack
     * @param element the element to be pushed onto the stack
     */
    @Override
    public void push(int element) {
        if(top == S.length-1) {
            //if the array capacity is reached grow array by doubling its capacity
            S = Arrays.copyOf(S, S.length * 2);
        }
        top += 1; //an element has been added to the top of the stack, so top is incremented
        S[top] = element;
    }

    /**
     * Removes the top element from the stack
     * @return the element that was removed from the stack
     */
    @Override
    public int pop() {
        if(isEmpty())
            return 0;

        //else temp holds the element at the top that is removed, which is then returned
        int temp = S[top];
        S[top] = 0;
        top -= 1; //an element has been removed from the top of the stack, so top is decremented
        return temp;
    }

    /**
     * Returns the size of the stack
     * @return the size of the stack
     */
    @Override
    public int size() {
        if (isEmpty())
            return 0;
        else
            //top+1 indicates the size of the array type stack
            return top+1;
    }

    /**
     * Checks if the stack is empty
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (top == -1); //if top is -1 the stack is empty
    }

    /**
     * Prints the stack
     * @return the stack as a string
     */
    @Override
    public String toString() {
        if(isEmpty())
            return "[]";

        //else print the stack from top the 0th element in the array S
        String print = "[";
        for(int i = top; i >= 1; i--) {
            print += S[i] + "-";
        }
        return print + S[0] + "]";


    }
}
/**
 * Interface for the Stack ADT
 * @param <E> the type of the elements stored in the stack
 */
interface Stack<E> {
    void push(int element);
    int pop();
    int size();
    boolean isEmpty();
}