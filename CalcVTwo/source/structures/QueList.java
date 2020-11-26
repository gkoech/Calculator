package source.structures;

//import java.util.*;

/**
 * {@inheritDoc}
 */
public class QueList<T> implements QueListInterface<T> {

	private Node<T> head;
	private Node<T> curNode;
	private Node<T> tail;
	private int size;

	public QueList() {
		super();
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public QueList(T data) {
		Node<T> newNode = new Node<T>(data);
		newNode.setIndex(0);
		this.head = newNode;
		this.tail = newNode;
		this.head.setNext(this.tail);
		this.tail.setPrevious(this.head);
		this.size = 1;
	}

	public boolean isEmpty(){
		boolean theAnswer = true;
		if(this.size > 0){
			theAnswer = false;
		}
		return theAnswer;
	}
	@Override
	public int size() {
		int theAnswer = this.size;
		return theAnswer;
	}

	@Override
	public void push(T data) {
		if (data == null) {
			throw new NullPointerException("The element being pushed onto the que is empty");
		}
		Node<T> newNode = new Node<T>(data);
		newNode.setIndex(this.size);
		if (this.size == 0) {
			this.head = newNode;
			this.tail = newNode;
			this.head.setPrevious(null);
			this.tail.setNext(null);
			this.head.setNext(this.tail);
			this.tail.setPrevious(this.head);
		} 
		else if(this.size == 1){
			this.head.setPrevious(null);
			this.tail.setNext(newNode);
			newNode.setPrevious(this.tail);
			this.tail = this.tail.getNext();
		}
		else if (this.size > 1) {
			this.tail.setNext(newNode);
			newNode.setPrevious(this.tail);
			this.tail = this.tail.getNext();
		}
		this.size = this.size + 1;
	}

	@Override
	public void push(int index, T data) {
		if (data == null) {
			throw new NullPointerException("The element passed in is null");
		}
		if (index <= 0) {
			this.insertFirst(data);
		} 
		
		else if (index >= this.size) {
			this.push(data);
		} 
		
		else {
			Node<T> newNode = new Node<T>(data);
			newNode.setIndex(index);
			this.curNode = this.head;
			this.shifter(this.curNode, 0, index - 1);
			newNode.setPrevious(this.curNode);
			newNode.setNext(this.curNode.getNext());
			this.curNode.setNext(newNode);
			this.indexShifter(newNode, index, this.size);
			this.shifter(this.curNode, index - 1, 0);
			this.head = curNode;
			this.size = this.size + 1;
		}
	}

	public void insertFirst(T data){
		if(data == null){
			throw new NullPointerException("The data being pushed to the front of the que is null");
		}
		if(this.size == 0){
			this.push(data);
			this.tail.setNext(null);
			this.head.setPrevious(null);
		}
		else if(this.size >= 1){
			Node<T> newNode = new Node<T>(data);
			newNode.setIndex(0);
			newNode.setNext(this.head);
			this.head.setPrevious(newNode);
			this.head = this.head.getPrevious();
			this.indexShifter(this.head, 0, this.size);
			this.curNode = this.head;
			this.size = this.size + 1;
		}
	}


	private void indexShifter(Node<T> thisNode, int curIndx, int endIndx) {
		if (curIndx == endIndx) {
			thisNode.setIndex(curIndx);
			return;
		} 
		
		else {
			if (curIndx > endIndx) {
				thisNode.setIndex(curIndx);
				indexShifter(thisNode.getPrevious(), curIndx - 1, endIndx);
			} 
			
			else {
				thisNode.setIndex(curIndx);
				indexShifter(thisNode.getNext(), curIndx + 1, endIndx);
			}
		}
	}
	

	private void shifter(Node<T> thisNode, int curIndx, int endIndx){
		if(curIndx == endIndx){
			this.curNode = thisNode;
			return;
		}

		else{
			if(curIndx > endIndx){
				thisNode = thisNode.getPrevious();
				shifter(thisNode, curIndx - 1, endIndx);
			}
			else{
				thisNode = thisNode.getNext();
				shifter(thisNode, curIndx + 1, endIndx);
			}
		}
	}

	@Override
	public void reset() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.curNode = null;
	}

	@Override
	public T removeFirst() {
		if(this.size == 0){
			throw new NullPointerException("The que is empty");
		}
		T theAnswer = this.head.getData();
		if(this.size == 1){
			this.reset();
		}
		else if(this.size > 1){
			this.head = this.head.getNext();
			this.head.setPrevious(null);
			this.curNode = this.head;
			this.indexShifter(this.curNode, 0, this.size - 2);
			this.head = this.curNode;
			this.size = this.size - 1;
		}
		return theAnswer;
	}

	@Override
	public T removeAt(int index) {
		if((index < 0) || (index >= this.size)){
			throw new IndexOutOfBoundsException("The index being accessed doesn't exist");
		}
		T theAnswer = null;
		if(index == 0){
			theAnswer = this.removeFirst();
		}
		else if(index == this.size - 1){
			theAnswer = this.removeLast();
		}
		else{
			this.curNode = this.head;
			this.shifter(this.curNode, 0, index - 1);
			theAnswer = this.curNode.getNext().getData();
			this.curNode.setNext(this.curNode.getNext().getNext());
			this.curNode.getNext().setPrevious(this.curNode);
			this.indexShifter(this.curNode, index - 1, this.size - 2);
			this.curNode = this.head;
			this.size = this.size - 1;
		}
		return theAnswer;
	}

	@Override
	public T removeLast() {
		if(this.size == 0){
			throw new NullPointerException("The que is empty");
		}
		T theAnswer = null;
		if(this.size == 1){
			theAnswer = this.removeFirst();
		}
		else{
			theAnswer = this.tail.getData();
			this.tail = this.tail.getPrevious();
			this.tail.setNext(null);
			this.size = this.size - 1;
		}
		return theAnswer;
	}

	@Override
	public T getFirst() {
		if(this.size == 0){
			throw new NullPointerException("This que is empty");
		}
		T theAnswer = this.head.getData();
		return theAnswer;
	}

	@Override
	public T getAt(int index) {
		if (this.size == 0) {
			throw new NullPointerException("The que is empty");
		}
		if((index < 0) || (index >= this.size)){
			throw new IndexOutOfBoundsException("The index being accessed is out of bounds");
		}
		T theAnswer = null;
		if(this.size == 1){
			theAnswer = this.head.getData();
		}
		else {
			if(index == 0){
				theAnswer = this.head.getData();
			}
			else if(index == this.size - 1){
				theAnswer = this.tail.getData();
			}
			else{
				this.curNode = this.head;
				this.shifter(this.curNode, 0, index);
				theAnswer = this.curNode.getData();
				curNode = this.head;
			}
		}
		return theAnswer;
	}

	@Override
	public int indexOf(T data) {
		if(data == null){
			throw new NullPointerException("The element passed in is null");
		}
		int theAnswer = -1;
		int theIndex = getIndex(this.curNode, data, 0);
		if(theIndex != -1){
			theAnswer = theIndex;
		}
		return theAnswer;
	}

	private int getIndex(Node<T> curNode, T data,int curIndex){
		if (curIndex > this.size - 1) {
			return -1;
		}
		if(curNode.getData() == data){
			return curIndex;
		}
		else{
			return getIndex(curNode.getNext(), data, curIndex + 1);
		}
	}

	@Override
	public T getLast() {
		if(this.size == 0){
			throw new NullPointerException("The que being accessed is empty");
		}
		T theAnswer = this.tail.getData();
		return theAnswer;
	}

	@Override
	public void changeAt(int index, T newData) {
		if(newData == null){
			throw new NullPointerException("The element being passed in is empty");
		}
		if((index < 0) || (index >= this.size)){
			throw new IndexOutOfBoundsException("The index being accessed is out of bounds");
		}
		if(index == 0){
			this.head.setData(newData);
		}
		else if(index == this.size - 1){
			this.tail.setData(newData);
		}
		else{
			this.curNode = this.head;
			shifter(curNode, 0, index);
			curNode.setData(newData);
			shifter(curNode, index, 0);
			this.head = curNode;
		}
	}

	@Override
	public Iterator<T> iterate() {
		Node<T> newNode = new Node<T>();
		newNode = this.head;
		Iterator<T> theIterator = new Iterator<T>(newNode);
		return theIterator;
	}

}
