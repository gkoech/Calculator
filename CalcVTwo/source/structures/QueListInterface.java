package source.structures;

/**
 * A {@link QueListInterface} is the interface for the queue.
 */
public interface QueListInterface <T> {


    /**
     * Returns the size of the queue, with the number
     * of elements that the queue currently contains.
     * 
     * @return Returns the number of elements in the queue.
     */
    int size();

    /**
     * Pushes the element passed in onto the front of the list.
     * @throws NullPointerException
     *          - if the data passed in is null.
     * @param data
     *          -the element passed in
     */
    void insertFirst(T data);
    
    /**
     * Pushes the element passed in onto the queue, adding "data" onto the end of the queue.
     * @param data
     * @throws NullPointerException
     * if the {@param data} passed in is empty.
     */
    void push(T data);

    /**
     * Adds the data to the index of the queue.
     * If index is smaller equal to or smaller than 0, 
     * data is then added to the front of the queue. If
     * link is greater than or equal to the size of the queue,
     * link data is added to the end of the queue.
     * @param index
     *          - where data is added to.
     * @param data
     *          - to be added to the queue.
     * @throws NullPointerException
     *          if {@link data} is null.
     */
    void push(int index, T data);

    /**
     * Resets the queue to empty and the size to 0.
     */
    void reset();

    /**
     * Removes and returns the head of the queue.
     * @throws NullPointerException
     *          if the que is empty.
     * @return The head of the queue.
     */
    T removeFirst();

    /**
     * Removes the element at the index passed in, and returns it.
     * 
     * @param index the index of the element to be removed.
     * @throws IndexOutOfBoundsException if the {@link index} passed in is less than
     *                                   0, or greater than the size of the queue.
     * @throws NullPointerException
     *          if the que is empty
     * @return the old element at the previous {@link index} from the parameter
     *         passed in.
     */
    T removeAt(int index);    

    /**
     * Removes the tail of the queue.
     * @throws NullPointerException
     * if the queue is empty.
     * @return the old tail of the list.
     */
    T removeLast();

    

    /**
     * Returns the first element in the queue, without deleting the element
     * @throws NullPointerException
     * if the queue is empty.
     * @return The head of the queue.
    */
    T getFirst();

    /**
     * Returns the element at the index passed in, but does not delete it.
     * 
     * @param index The index of the queue that is to be accessed.
     * @throws IndexOutOfBoundsException
     *          if the index passed in is greater than the size of the que,
     *          or less than 0.
     * @throws NullPointerException
     *          if the que is empty.
     * @return The element at the index passed in.
     */
    T getAt(int index);

    /**
     * This returns the index of the element passed in. Returns -1 
     * if the que is empty.
     * @param data
     *        - The elment passed in
     * @throws NullPointerException
     *        if the paramter passed in is empty.
     * @return the index of data passed in.
     */    
    int indexOf(T data);

    /**
     * Returns element at the tail of the queue, but does not delete it.
     * @throws NullPointerException
     * if the queue is empty.
     * 
     * @return the queue's tail.
     */
    T getLast();
    /**
     * Changes the element at the specified index to the new element passed in.
     * @param index
     *          The index you want to change.
     * @param newData
     *          The new data to go onto the index.
     * @throws indexOutOfBoundsException;
     *          throws exception if index is greater the last index or less than 0.
     * @throws nullPointerException
     *          throws exception if the new element passed in is null.
     */
    void changeAt(int index, T newData);

    /**
     * Returns an iterator that iterates over each index in the que.
     * @return an iterator.
     */
    Iterator<T> iterate();


}