
package source.structures;

public interface StackInterface <T>{
    

    /*
    methods :
    size
    push
    pull

    */

    /**
     * This gets the size of the queue.
     * @return the size of the queue.
     */
    int size();

    /**
     * Resets the stack to empty
     */
    void reset();

    /**
     * This pushes the parameter passed in onto the stack.
     * @throws NullPointerException
     *          - if the element being pushed onto the stack is empty
     */
    void push(T data);

    /**
     * This gets the element at the top of the stack and removes them.
     * @throws NullPointerException
     *          if the stack is empty
     * @return the top of the stack
     */
    T pull();

    /**
     * This gets the element at the top of the stack, but does not delete it.
     * @throws NullPointerException
     *          if the stack is empty
     * @return the element at the top of the stack.
     */
    T peek();

    /**
     * This gets the element at the specified index of the stack, but does not delete it.
     * 
     * 
     * @throws NullPointerException
     *          if the stack is empty
     * @throws IndexOutOfBoundsException;
     *          if the index passed in is greater than 0, or larger than the size of the stack.
     * @return the element at the top of the stack.
     */
    T peek(int index);

}
