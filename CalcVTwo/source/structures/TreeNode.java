package source.structures;

public class TreeNode <T>{
    private T data;
    private TreeNode<T> parent;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    private int thisNodeHeight;

    public TreeNode(){

    }

    public TreeNode(T d){
        this.data = d;
    }

    public void setData(T d){
        this.data = d;
    }

    public T getData(){
        return this.data;
    }

    public void setParent(TreeNode<T> paren){
        this.parent = paren;
    }

    public TreeNode<T> getParent(){
        return this.parent;
    }

    public void setLeft(TreeNode<T> left){
        this.leftChild = left;
    }

    public void setRight(TreeNode<T> right){
        this.rightChild = right;
    }

    public TreeNode<T> getLeft(){
        return this.leftChild;
    }

    public TreeNode<T> getRight(){
        return this.rightChild;
    }

    public void setHeight(int height){
        this.thisNodeHeight = height;
    }

    public int getHeight(){
        return this.thisNodeHeight;
    }
}
