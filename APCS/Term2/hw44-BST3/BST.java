// Soojin Choi, Aidan Griffin
// Team Golden Eagle
// APCS2 pd08
// HW44 -- Pruning Your Tree
// 2018-05-01
/*****************************************************
 * class BST - v1:partial
 * Implementation of the BINARY SEARCH TREE abstract data type (ADT)
 *
 * A BST maintains the invariant that, for any node N with value V,
 * L<V && V<R, where L and R are node values in N's left and right
 * subtrees, respectively.
 * (Any value in a node's left subtree must be less than its value,
 *  and any value in its right subtree must be greater.)
 * This BST only holds ints (its nodes have int cargo)
 *****************************************************/

public class BST
{
    
    //instance variables / attributes of a BST:
    TreeNode _root;
    
    /*****************************************************
     * default constructor
     *****************************************************/
    BST( )
    {
	_root = null;
    }
    
    
    /*****************************************************
     * void insert( int )
     * Adds a new data element to tree.
     *****************************************************/
    
    public void insert( int newVal )
    {
	TreeNode newNode = new TreeNode( newVal );
	
	if ( _root == null ) {
	    _root = newNode;
	    return;
	}
	insert( _root, newNode );
    }
    //recursive helper for insert(int)
    public void insert( TreeNode stRoot, TreeNode newNode )
    {
	if ( newNode.getValue() < stRoot.getValue() ) {
	    //if no left child, make newNode the left child
	if ( stRoot.getLeft() == null )
	    stRoot.setLeft( newNode );
	else //recurse down left subtree
	    insert( stRoot.getLeft(), newNode );
	return;
	}
	else { // new val >= curr, so look down right subtree
	//if no right child, make newNode the right child
	    if ( stRoot.getRight() == null )
		stRoot.setRight( newNode );
	    else //recurse down right subtree
		insert( stRoot.getRight(), newNode );
	    return;
	}
    }//end insert()
    
    

    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~v~~TRAVERSALS~~v~~~~~~~~~~~~~~~~~~~~~
    
    // each traversal should simply print to standard out
    // the nodes visited, in order
    
    //process root, recurse left, recurse right
    public void preOrderTrav()
    {
	preOrderTrav( _root );
    }
    public void preOrderTrav( TreeNode currNode )
    {
	if ( currNode == null ) //stepped beyond leaf
	    return;
	System.out.print( currNode.getValue() + " " );
	preOrderTrav( currNode.getLeft() );
	preOrderTrav( currNode.getRight() );
    }

    //recurse left, process root, recurse right
    public void inOrderTrav()
    {
	inOrderTrav( _root );
    }
    public void inOrderTrav( TreeNode currNode )
    {
	if ( currNode == null ) //stepped beyond leaf
	    return;
	inOrderTrav( currNode.getLeft() );
	System.out.print( currNode.getValue() + " " );
	inOrderTrav( currNode.getRight() );
    }
    
    //recurse left, recurse right, process root
    public void postOrderTrav()
    {
	postOrderTrav( _root );
    }
    public void postOrderTrav( TreeNode currNode )
    {
	if ( currNode == null ) //stepped beyond leaf
	    return;
	postOrderTrav( currNode.getLeft() );
	postOrderTrav( currNode.getRight() );
	System.out.print( currNode.getValue() + " "  );
    }
    
    //~~~~~~~~~~~~~^~~TRAVERSALS~~^~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    
    /*****************************************************
     * TreeNode search(int)
     * returns pointer to node containing target,
     * or null if target not found
     *****************************************************/
    TreeNode search( int target )
    {
	return search(target, _root);
	
    }
    
    TreeNode search (int target, TreeNode curr){
	if (curr == null){
	    return null;
	}
	else if (curr.getValue() == target){
	    TreeNode temp = new TreeNode(target);
	    return temp;
	}
	else{
	    if (curr.getValue() > target){
		return search(target, curr.getLeft());
	    }
	    else{
		return search(target, curr.getRight());
	    }
	}
    }

    
    /*****************************************************
     * int height()
     * returns height of this tree (length of longest leaf-to-root path)
     * eg: a 1-node tree has height 0
     *****************************************************/
    public int height()
    {
	return height(_root);
    }
    
    private int height( TreeNode curr ){
	if (curr == null){
	    return 0;
	}
	else if (curr.getLeft() == null && curr.getRight() == null){
	    return 0;
	}
	else{
	    int right = height(curr.getRight()) + 1;
	    int left = height(curr.getLeft()) + 1;
	    return Math.max(right, left);
	}
    }
    
    /*****************************************************
     * int numLeaves()
     * returns number of leaves in tree
     *****************************************************/
    public int numLeaves()
    {
	return numLeaves( _root );
    }
    
    private int numLeaves( TreeNode curr ){
	if (curr == null){
	    return 0;
	}
	else if (curr.getLeft() == null && curr.getRight() == null){
	    return 1;
	}
	else{
	    return numLeaves(curr.getLeft()) + numLeaves(curr.getRight());
	}
    }
    
    public TreeNode remove(int target){
	
	TreeNode _follower = _root;
	TreeNode _leader = _root;
	boolean found = false;

	//Finds the value in the tree;
	while (!found){ //Check until the value is confirmed absent.
	    if ( _leader.getValue() == target){ //Found Value
		found = true;
	    }
	    
	    else if (target < _leader.getValue()){ //if the target is less then the root value...
		_follower = _leader; //Sets follower to the node that _leader was on.
		_leader = _leader.getLeft(); //_leader moves forward to the left.
	    }
	    else{ //else the target is greater than the root value
		_follower = _leader; //Sets follower to the node that _leader was on.
		_leader = _leader.getRight(); //leader moves forward to the right.
	    }
	}
	
	System.out.println(_leader + "<leader follower>" + _follower);
	
	//Case I: The node being removed is a leaf
	if (_leader.getLeft() == null && _leader.getRight() == null){
	    if (target < _follower.getValue()){
		_follower.setLeft(null);
	    }
	    else{
		_follower.setRight(null);
	    }
	    return _leader;
	}

	//Case II: The node being removed has one child
	else if ( _leader.getLeft() == null || _leader.getRight() == null ) {
	    TreeNode tmp;
	    if (_leader.getLeft() == null){
		tmp = _leader.getRight();
	    }
	    else{
		tmp = _leader.getLeft();
	    }
	    
	    if (target < _follower.getValue()){
		_follower.setLeft(tmp);
	    }
	    else{
		_follower.setRight(tmp);
	    }
	    return _leader;
	}
	
	//Case III: The node has two children.
	else{
	    int rightVal = rightMost(_leader.getLeft());
	    System.out.println(rightVal);
	    TreeNode hold = new TreeNode(_leader.getValue());
	    _leader.setValue(rightVal);
	    return hold;
	}
    }
    
    public int rightMost(TreeNode curr){
	if (curr.getRight() == null){
	    int holder = curr.getValue();
	    remove(holder);
	    return holder;
	}
	return rightMost(curr.getRight());
    }
    
    //main method for testing
    public static void main( String[] args )
    {
	BST arbol = new BST();
	
	//PROTIP: sketch state of tree after each insertion
	//        ...BEFORE executing these.
	arbol.insert( 4 );
	arbol.insert( 2 );
	arbol.insert( 5 );
	arbol.insert( 6 );
	arbol.insert( 1 );
	arbol.insert( 3 );

	System.out.println( "\n-----------------------------");
	System.out.println( "pre-order traversal:" );
	arbol.preOrderTrav();

	System.out.println( "\n-----------------------------");
	System.out.println( "in-order traversal:" );
	arbol.inOrderTrav();

	System.out.println( "\n-----------------------------");
	System.out.println( "post-order traversal:" );
	arbol.postOrderTrav();

	System.out.println( "\n-----------------------------");

	System.out.println(arbol.search(8));
	System.out.println(arbol.search(2));

	System.out.println(arbol.height());

	System.out.println(arbol.numLeaves());

	System.out.println(arbol.remove(2));

	System.out.println( "\n-----------------------------");
	System.out.println( "in-order traversal:" );
	arbol.inOrderTrav();
	System.out.println( "\n-----------------------------");

	//arbol.insert(2);

	System.out.println(arbol.remove(5));

	System.out.println( "\n-----------------------------");
	System.out.println( "in-order traversal:" );
	arbol.inOrderTrav();
	System.out.println( "\n-----------------------------");

	System.out.println(arbol.remove(1));

	System.out.println( "\n-----------------------------");
	System.out.println( "in-order traversal:" );
	arbol.inOrderTrav();
	System.out.println( "\n-----------------------------");

	/*~~~~~~~~~~~~move~me~down~~~~~~~~~~~~~~~~~~~~~~
	  <<< YOUR NEW TEST CALLS HERE >>>
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }
    
}//end class
