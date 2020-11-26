package source.structures;

public class Iterator <T> implements java.util.Iterator<T>{
    
    private T data; 
    private Node<T> next;

    public Iterator(Node<T> headOfQue) {
        this.next = headOfQue;
    }

    @Override
    public boolean hasNext() {
        boolean theAnswer = false;
        if(next != null){
            theAnswer = true;
        }
        return theAnswer;
    }

    @Override
    public T next() {
        T theAnswer = null;
        if(this.hasNext() == true){
            if(this.data == null){
                theAnswer = next.getData();
                this.next = this.next.getNext();
            }
            else if(this.data != null){
                theAnswer = this.data;
                this.next = this.next.getNext();
            }
        }
        return theAnswer;
    }
    
}
