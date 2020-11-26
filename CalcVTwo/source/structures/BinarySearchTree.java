package source.structures;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeInterface<T>{
    private TreeNode<T> root;
    private int height;
    private int size;

    public BinarySearchTree(){
        this.height = -1;
        this.size = 0;
    }

    public BinarySearchTree(T data){
        this.root.setData(data);
        this.root.setHeight(0);
        this.height = 0;
        this.size = 1;
    }

    @Override
    public boolean isEmpty() {
        boolean theAnswer = true;
        if(this.root != null){
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
    public void reset() {
        this.root = null;
        this.height = -1;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException("The data passed in is null");
        }

        return false;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new NullPointerException("The data passed in is null");
        }

        return false;
    }

    @Override
    public T get(T element) {
        if (element == null) {
            throw new NullPointerException("The data passed in is null");
        }


        return null;
    }

    @Override
    public void add(T element) {
        if(element == null){
            throw new NullPointerException("The data passed in is null");
        }
        TreeNode<T> newNode = new TreeNode<T>(element);
        if(this.size == 0 ){
            this.root = newNode;
            this.size = this.size + 1;
            this.height = this.height + 1;
        }
        else{
            this.adder(this.root, newNode);
        }
    }

    private void adder(TreeNode<T> curNode, TreeNode<T> nodeToAdd){
        if(curNode == null){
            throw new NullPointerException("The recursive add method got to a null node");
        }
        int comparison = curNode.getData().compareTo(nodeToAdd.getData());
        if ((curNode.getLeft() == null) && (curNode.getRight() == null)) {
            this.height = this.height + 1;
        }
        if(comparison < 0){
            if (curNode.getRight() == null) {
                nodeToAdd.setParent(curNode);
                nodeToAdd.setHeight(curNode.getHeight() + 1);
                curNode.setRight(nodeToAdd);
                this.size = this.size + 1;
                return;
            }

            else{
                this.adder(curNode.getRight(), nodeToAdd);
            }
        }

        else{
            if(curNode.getLeft() == null){
                nodeToAdd.setParent(curNode);
                nodeToAdd.setHeight(curNode.getHeight() + 1);
                curNode.setLeft(nodeToAdd);
                this.size = this.size + 1;
                return;
            }

            else{
                this.adder(curNode.getLeft(), nodeToAdd);
            }
        }
    }

    public int subTreeSize(TreeNode<T> node){
        if(node == null){
            return 0;
        }

        else {
            return 1 + subTreeSize(node.getLeft()) + subTreeSize(node.getRight());
        }
    }

    private void heightUpdater(TreeNode<T> node, int startIndx){
        if(node == null){

            return;
        }
        else{
            node.setHeight(startIndx);
            this.heightUpdater(node.getLeft(), startIndx + 1);
            this.heightUpdater(node.getRight(), startIndx + 1);
        }
    }

    @Override
    public T getMinimum() {
        T theAnswer = null;
        if(this.root != null){
            theAnswer = this.minHelper(this.root);
        }
        return theAnswer;
    }

    private T minHelper(TreeNode<T> curNode){
        if(curNode.getLeft() == null){
            return curNode.getData();
        }
        else{
            return minHelper(curNode.getLeft());
        }
    }

    @Override
    public T getMaximum() {
        T theAnswer = null;
        if(this.root != null){
            theAnswer = this.getMaxHelper(this.root);
        }
        return theAnswer;
    }

    private T getMaxHelper(TreeNode<T> curNode){
        if(curNode.getRight() == null){
            return curNode.getData();
        }
        else{
            return getMaxHelper(curNode.getRight());
        }
    }

    @Override
    public int height() {
        int theAnswer = this.height;
        return theAnswer;
    }

    @Override
    public int getTreeHeight(TreeNode<T> node) {
        int left = 0;
        int right = 0;
        if(node == null){
            return 0;
        }

        else{
            if(node.getRight() != null){
                return getTreeHeight(node.getRight());
            }
            else{
                right =  node.getHeight();
            }
            if(node.getLeft() != null){
                return getTreeHeight(node.getLeft());
            }
            else{
                left = node.getHeight();
            }
        }
        int theAnswer = Math.max(left, right);
        return theAnswer;
    }

    @Override
    public Iterator<T> preorderIterator() {
        QueList<T> que = new QueList<T>();
        this.addtoQuePreorder(this.root, que);
        Iterator<T> theAnswer = que.iterate();
        return theAnswer;
    }

    private void addtoQuePreorder(TreeNode<T> node, QueList<T> que){
        if(node != null){
            que.push(node.getData());
            addtoQuePreorder(node.getLeft(), que);
            addtoQuePreorder(node.getRight(), que);
        }
    }
    @Override
    public Iterator<T> inorderIterator() {
        QueList<T> que = new QueList<T>();
        this.addtoQueInorder(this.root, que);
        Iterator<T> theAnswer = que.iterate();
        return theAnswer;
    }

    private void addtoQueInorder(TreeNode<T> node, QueList<T> que){
        if(node != null){
            addtoQueInorder(node.getLeft(), que);
            que.push(node.getData());
            addtoQueInorder(node.getRight(), que);
        }
    }

    @Override
    public Iterator<T> postorderIterator() {
        QueList<T> que = new QueList<T>();
        this.addtoQuePostorder(this.root, que);
        Iterator<T> theAnswer = que.iterate();
        return theAnswer;
    }

    private void addtoQuePostorder(TreeNode<T> node, QueList<T> que){
        if(node != null){
            addtoQuePostorder(node.getLeft(), que);
            addtoQuePostorder(node.getRight(), que);
            que.push(node.getData());
        }
    }

    @Override
    public boolean equals(BinarySearchTreeInterface<T> other) {
        TreeNode<T> otherRoot = other.getRoot();
        boolean theAnswer = this.isEqual(this.root, otherRoot);
        return theAnswer;
    }

    private boolean isEqual(TreeNode<T> thisRoot, TreeNode<T> otherRoot){
        if((thisRoot == null) && (otherRoot == null)){
            return true;
        }

        else if((thisRoot == null) || (otherRoot == null)){
            return false;
        }

        else{
            if(thisRoot.getData().equals(otherRoot.getData()) == false){
                return false;
            }
            return isEqual(thisRoot.getLeft(), otherRoot.getLeft()) && isEqual(thisRoot.getRight(), otherRoot.getRight());
        }
    }

    @Override
    public boolean sameValues(BinarySearchTreeInterface<T> other) {
        boolean theAnswer = true;
        Iterator<T> otherIterator = other.inorderIterator();
        Iterator<T> thisIterator = this.inorderIterator();
        while((thisIterator.hasNext() == true) && (otherIterator.hasNext() == true)){
            if(thisIterator.next() != otherIterator.next()){
                theAnswer = false;
                break;
            }
        }

        if(thisIterator.hasNext() == true){
            theAnswer = false;
        }
        else if(otherIterator.hasNext() == true){
            theAnswer = false;
        }
        return theAnswer;
    }

    @Override
    public boolean isBalanced() {
        boolean theAnswer = false;
        boolean firstCondition = (this.size - Math.pow(2, this.height) >= 0);
        boolean secondCondition = (this.size - Math.pow(2, this.height + 1) < 0);
        if((firstCondition == true) && (secondCondition == true)){
            theAnswer = true;
        }
        return theAnswer;
    }

    @Override
    public void balance() {
        QueList<T> que = new QueList<T>();
        this.addtoQueInorder(this.root, que);
        ArrayList<T> array = new ArrayList<T>();
        this.arrayListFiller(array, que);
        this.root = balancer(array, 0, array.size() - 1);
        this.heightUpdater(this.root, 0);
        int newHeight = this.getTreeHeight(this.root);
        this.height = newHeight;
    }

    private TreeNode<T> balancer(ArrayList<T> array, int min, int max){
        if(min > max ){
            return null;
        }
        int sum = min + max;
        int avg = sum / 2;
        TreeNode<T> newNode = new TreeNode<T>();
        newNode.setData(array.get(avg));
        newNode.setLeft(balancer(array, min, avg - 1));
        newNode.setRight(balancer(array, avg + 1, max));
        return newNode;
    }

    private void arrayListFiller(ArrayList<T> array, QueList<T> que){
        if(que.isEmpty() == false){
            array.add(que.removeFirst());
            arrayListFiller(array, que);
        }
    }

    @Override
    public TreeNode<T> getRoot() {
        TreeNode<T> theAnswer = this.root;
        return theAnswer;
    }
}
