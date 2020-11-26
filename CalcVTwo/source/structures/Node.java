
package source.structures;

public class Node <T>{
    private T data;
    private Node<T> next;
    private Node<T> prev;
    private int index;

    public Node(){

    }

    public Node(T l_information){
        this.data = l_information;
    }

    public Node(Node<T> prochaine){
        this.next = prochaine;
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int indx){
        this.index = indx;
    }

    public void setData(T l_information){
        this.data = l_information;
    }

    public T getData(){
        return this.data;
    }

    public void setNext(Node<T> prochaine){
        this.next = prochaine;
    }

    public Node<T> getNext(){
        return this.next;
    }

    public Node<T> getPrevious() {
        return this.prev;
    }

    public void setPrevious(Node<T> p) {
        this.prev = p;
    }
}
