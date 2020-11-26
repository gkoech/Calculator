package source.structures;

public interface BinarySearchTreeInterface <T>{
    
    /** @return true if and only if the tree contains no elements */
    boolean isEmpty();

    /** @return the number of elements stored in the tree */
    int size();

    /**
     * This resets the tree to empty.
     */
    void reset();

    /**
     * @param element
     * @return true if and only if the element is stored in the tree
     * @throws NullPointerException if element is null
     */
    boolean contains(T element);

    /**
     * Attempts to remove one copy of an element from the tree, returning true if
     * and only if such a copy was found and removed.
     *
     * <p>
     * The modified tree must still obey the BST rule, though it might not be
     * balanced.
     *
     * @throws NullPointerException if element is null
     * @param element
     * @return true if and only if an element removed
     * 
     */
    boolean remove(T element);

    /**
     * @param element
     * @return the element in the tree which .equals() the argument, or null if no
     *         such element exists
     * @throws NullPointerException if element is null
     */
    T get(T element);

    /**
     * Adds an element to the tree.
     *
     * <p>
     * The modified tree must still obey the BST rule, though it might not be
     * balanced.
     *
     * @param element
     * @throws NullPointerException if element is null
     */
    void add(T element);

    /** @return the least value stored in this tree, or null if the tree is empty */
    T getMinimum();

    /**
     * @return the greatest value stored in this tree, or null if the tree is empty
     */
    T getMaximum();

    /** @return the height of the tree, or -1 if the tree is empty */
    int height();

    /**
     * Returns the height of the tree node passed in.
     * @return the height of the passed in node's subtree.
     */
    int getTreeHeight(TreeNode<T> node);

    /**
     * Returns an Iterator that performs a preorder traversal of the tree.
     *
     * <p>
     * The Iterator's behavior is not defined in the case that the tree is modified
     * before the iteration is finished.
     *
     * @return an Iterator over the elements in preorder
     */
    Iterator<T> preorderIterator();

    /**
     * Returns an Iterator that performs a inorder traversal of the tree.
     *
     * <p>
     * The Iterator's behavior is not defined in the case that the tree is modified
     * before the iteration is finished.
     *
     * @return an Iterator over the elements in preorder
     */
    Iterator<T> inorderIterator();

    /**
     * Returns an Iterator that performs a postorder traversal of the tree.
     *
     * <p>
     * The Iterator's behavior is not defined in the case that the tree is modified
     * before the iteration is finished.
     *
     * @return an Iterator over the elements in preorder
     */
    Iterator<T> postorderIterator();

    /**
     * Returns true if and only if this tree and the other tree have the same
     * structure, and equivalent values at each node. Uses equals() to check for
     * node value equivalence.
     *
     * @param other
     * @return true if and only if the trees are structure- and value-equivalent
     * @throws NullPointerException if other is null
     */
    boolean equals(BinarySearchTreeInterface<T> other);

    /**
     * Returns true if and only if this tree and the other tree store the same
     * values, regardless of structure. Uses equals() to check for stored value
     * equivalence.
     *
     * @param other
     * @return true if and only if the trees are value-equivalent
     * @throws NullPointerException if other is null
     */
    boolean sameValues(BinarySearchTreeInterface<T> other);

    /**
     * Returns true if and only if the tree is completely balanced. A balanced tree
     * is either a full tree, or a tree that would be full if all leaves in the
     * highest level were removed.
     *
     * <p>
     * An empty tree is considered balanced.
     *
     * @return true if and only if the tree is balanced
     */
    boolean isBalanced();

    /**
     * Modifies the tree such that it is completely balanced.
     *
     * <p>
     * The modified tree must still obey the BST rule, and must contain the same
     * values it did before it was balanced.
     */
    void balance();

    /**
     * (This is a utility method that you would not include in a real BST's public
     * interface. It's here to help us grade and to help you write tests.)
     *
     * @return the root BSTNode of this tree
     */
    TreeNode<T> getRoot();

}
