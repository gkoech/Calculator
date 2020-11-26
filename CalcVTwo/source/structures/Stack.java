package source.structures;

public class Stack <T> implements StackInterface<T>{
    
    private int size;
    private Node<T> top;
    private Node<T> bottom;

    public Stack(){

    }

    public Stack(T data){
        Node<T> newNode = new Node<T>(data);
        this.top = newNode;
        this.bottom = newNode;
        this.top.setNext(this.bottom);
        this.bottom.setPrevious(this.top);
        this.size = this.size + 1;
    }

    @Override
    public int size() {
        int theAnswer = this.size;
        return theAnswer;
    }

    @Override
    public void reset() {
        this.top = null;
        this.bottom = null;
        this.size = 0;

    }

    @Override
    public void push(T data) {
        if(data == null){
            throw new NullPointerException("The parameter being passed in is empty");
        }
        Node<T> newNode = new Node<T>(data);
        if(this.size == 0){
            this.top = newNode;
            this.bottom = newNode;
            this.top.setNext(this.bottom);
            this.bottom.setPrevious(this.top);
        }
        else {
            this.top.setPrevious(newNode);
            newNode.setNext(this.top);
            this.top = this.top.getPrevious();
        }
        this.size = this.size + 1;
    }

    @Override
    public T pull() {
        if(this.size == 0){
            throw new NullPointerException("Cannot retrieve data from an empty stack");
        }
        T theAnswer = this.top.getData();
        if(this.size == 1){
            this.reset();
        }
        else if(this.size > 1){
            this.top = this.top.getNext();
            this.top.setPrevious(null);
        }
        return theAnswer;
    }

    @Override
    public T peek() {
        if(this.size == 0){
            throw new NullPointerException("The stack is empty");
        }
        T theAnswer = this.top.getData();
        return theAnswer;
    }

    @Override
    public T peek(int index) {
        if(this.size == 0){
            throw new NullPointerException("The stack is empty");
        }
        if((index < 0) || (index >= this.size)){
            throw new IndexOutOfBoundsException("The index passed into this stack doesn't exist in this stack");
        }

        Node<T> newNode = this.top;
        newNode = shifter(newNode, 0, index);
        T theAnswer = newNode.getData();
        return theAnswer;
    }

    private Node<T> shifter(Node<T> curNode, int curIndx, int endIndx) {
        if (curIndx == endIndx) {
            return curNode;
        } else {
            if (curIndx > endIndx) {
                return shifter(curNode.getPrevious(), curIndx - 1, endIndx);
            } else {
                return shifter(curNode.getNext(), curIndx + 1, endIndx);
            }
        }
    }

}
