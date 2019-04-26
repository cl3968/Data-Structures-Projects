package project6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * The BST class implements all methods from Collection interface; it also implements 
 * Comparable and Iterable.
 * It also has four internal classes: BSTNode, PreItr, PostItr, Itr (which implement Iterator).
 * A binary search tree consists of left and right nodes that differentiates it from a linked list. 
 * The advantages of using a binary search tree is that it is more efficient in terms of sorting, 
 * since it's already sorted.
 * 
 * Note: The unimplemented methods are: addAll, removeAll, retainAll, removeIf, spliterator, stream, and parallelStream.
 * 
 * 
 * @author Christina Liu 
 *
 */

public class BST<E extends Comparable<E>> implements Collection<E>, Iterable<E>, Comparable<E>{

	private int size;
	private BSTNode<E> root;

	//default constructor
	public BST() {
		size =0;
		root = null;

	}

	/**
	 * Returns the reference to the element stored in the tree with a value equal to the 
	 * value passed as the parameter or null if no equal element exist in this tree.
	 * @param value - an element to search for in this tree
	 * @returns the reference to the element equal to the parameter value or null if not such element exists 
	 */

	public E get(E value) {
		if (value == null) return null;
		else return get(this.root, value);
	}

	/**
	 * Returns the reference to the element stored in the tree with a value equal to the 
	 * value passed as the parameter or null if no equal element exist in this tree.
	 * @param value, an element to search for in this tree, and node, a BSTNode to check for value
	 * @returns the reference to the element equal to the parameter value or null if node does not exist 
	 */

	private E get(BSTNode<E> node, E val) {
		if (node == null) {
			return null;
		}

		//if value is less than node, go left
		else if (node.data.compareTo(val)  > 0) {
			return get( node.left, val);
		}

		//if value is more than node, go right
		else if (node.data.compareTo(val)  < 0) {
			return get(node.right, val);
		}

		return node.data;

	}

	/**
	 * Returns a string representation of this collection
	 * @returns the string representation of this BST<E> tree object  
	 */

	public String toString() {
		String str = "[";
		Iterator<E> itr = this.iterator();
		while(itr.hasNext()){
			str += String.valueOf(itr.next()) + ", ";
		}
		str += "]";
		return str;
	}

	/**
	 * Returns a string representation of this collection as a tree
	 * @returns the string representation of this BST<E> tree object as a tree  
	 */

	//from Project 6 instructions 
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();
		preOrderPrint(root, 0, s);
		return s.toString();
	}

	//prints the tree in preorder traversal
	private void preOrderPrint(BSTNode<E> tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += " ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data);
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right , level + 1, output);
		}
		else { // print the null children
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += " ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}
	}

	/**
	 * Returns an iterator over the elements in this collection through preorder traversal.	 
	 * @returns an iterator from PreItr class
	 */

	public Iterator<E> preorderIterator(){
		Iterator<E> preItr = new PreItr();
		return preItr;
	}

	/**
	 * Returns an iterator over the elements in this collection through postorder traversal.	 
	 * @returns an iterator from PostItr class
	 */

	public Iterator<E> postorderIterator(){
		Iterator<E> postItr = new PostItr();
		return postItr;
	}

	/**
	 * Returns a default iterator over the elements in this collection through inorder traversal.	 
	 * @returns an iterator from Itr class
	 */

	@Override
	public Iterator<E> iterator() {
		Iterator<E> itr = new Itr();
		return itr;
	}

	/**
	 * Returns the least element in this set greater than or equal to the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match
	 * @returns the least element greater than or equal to e, or null if there is no such element
	 * @throws NullPointerException if the specified element is null and
	 * 		   ClassCastException if the value to match is of a different class
	 */

	public E ceiling(E e) throws NullPointerException, ClassCastException{
		if (e == null) 
			throw new NullPointerException("Element is null.");
		if (!this.root.data.getClass().equals(e.getClass()))
			throw new NullPointerException("Element is different class.");
		else return ceiling(this.root, e);

	}

	/**
	 * Returns the least element in this set greater than or equal to the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match and current - the node looked at 
	 * @returns the least element greater than or equal to e, or null if there is no such element
	 */

	private E ceiling(BSTNode<E> current, E val) {
		if (current == null) {
			return null;
		}
		else {
			int compare = current.data.compareTo(val);

			//if the current is less than value, go right to find greater than value
			if (compare < 0) {
				return ceiling(current.right, val);
			}

			//once greater value is found, go left for the least amongst the greater than value
			else if (compare > 0) {
				E nextVal = ceiling(current.left,val);

				//check how far left until null
				if (nextVal != null) return nextVal;
				else  return current.data;
			}
			return current.data;
		}
	}

	/**
	 * Returns a shallow copy of this BST<E> tree instance. 
	 * (The elements themselves are not cloned.)
	 * @returns a shallow copy of this set
	 */

	public Object clone() {

		//uses preorder traversal to create a clone
		BST<E> clone = new BST<E>();
		Iterator preitr = new PreItr();
		while (preitr.hasNext()) {
			clone.add((E) preitr.next());
		}
		return clone; 
	}

	/**
	 * Returns the first (lowest) element currently in this set.
	 * @returns the first (lowest) element currently in this set
	 * @throws NoSuchElementException - if this set is empty
	 */

	public E first() throws NoSuchElementException{
		BSTNode<E> current = this.root;
		if (current == null) 
			throw new NoSuchElementException("Set is empty");

		//find the element furthest left for the lowest 
		while (current.left != null) {
			current = current.left;
		}
		return current.data;
	}

	/**
	 * Returns the greatest element in this set less than or equal to the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match  
	 * @returns the greatest element less than or equal to e, or null if there is no such element
	 * @throws NullPointerException if the specified element is null and
	 * 		   ClassCastException if the value to match is of a different class
	 */

	public E floor(E e) throws NullPointerException, ClassCastException{
		if (e == null) 
			throw new NullPointerException("Element is null.");
		if (!this.root.data.getClass().equals(e.getClass()))
			throw new NullPointerException("Element is different class.");
		else return floor(this.root, e);

	}
	/**
	 * Returns the greatest element in this set less than or equal to the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match and current - the node looked at 
	 * @returns the greatest element less than or equal to e, or null if there is no such element
	 */

	private E floor(BSTNode<E> current, E val){
		if (current == null) {
			return null;
		}
		else {
			int compare = current.data.compareTo(val);

			//if node data is greater than value, go left to find smaller than value
			if (compare > 0) {
				return floor(current.left, val);
			}

			//once smaller than value is found, go right to find the largest of the least
			else if (compare < 0) {
				E nextVal = floor(current.right,val);

				//check how far left until null
				if (nextVal != null) 
					return nextVal;
				else  
					return current.data;
			}

			//if node data and value are equal return that
			return current.data;
		}
	}

	/**
	 * Returns the least element in this set strictly greater than the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match 
	 * @returns the least element greater than e, or null if there is no such element
	 * @throws NullPointerException if the specified element is null and
	 * 		   ClassCastException if the value to match is of a different class
	 */

	public E higher(E e) throws NullPointerException, ClassCastException{
		if (e == null) 
			throw new NullPointerException("Element is null.");
		if (!this.root.data.getClass().equals(e.getClass()))
			throw new NullPointerException("Element is different class.");
		else return higher(this.root, e);
	}

	/**
	 * Returns the least element in this set strictly greater than the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match and current - the node looked at 
	 * @returns the least element greater than e, or null if there is no such element
	 */

	private E higher(BSTNode<E> current, E val) {
		if (current == null) {
			return null;
		}
		else {
			int compare = current.data.compareTo(val);

			//if node data is less than or equal to val, go right for a value greater than val
			if (compare < 0 || compare ==0) {
				return higher(current.right, val);
			}

			//after finding a value greater than val, go left for the least element
			else if (compare > 0 ) {
				E nextVal = higher(current.left,val);

				//check how far left until null
				if (nextVal != null) 
					return nextVal;
				else  
					return current.data;
			}
			return current.data;
		}
	}

	/**
	 * Returns the last (highest) element currently in this set.
	 * @returns the last (highest) element currently in this set
	 * @throws NoSuchElementException - if this set is empty
	 */

	public E last() throws NoSuchElementException{
		BSTNode<E> current = this.root;
		if (current == null) 
			throw new NoSuchElementException("Set is empty");

		//find the element furthest right for the greatest 
		while (current.right != null) {
			current = current.right;
		}
		return current.data;
	}
	/**
	 * Returns the greatest element in this set strictly less than the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match  
	 * @returns the greatest element less than e, or null if there is no such element
	 * @throws NullPointerException if the specified element is null and
	 * 		   ClassCastException if the value to match is of a different class
	 */

	public E lower(E e) throws NullPointerException, ClassCastException{
		if (e == null) 
			throw new NullPointerException("Element is null.");
		if (!this.root.data.getClass().equals(e.getClass()))
			throw new NullPointerException("Element is different class.");
		else return lower(this.root, e);
	}

	/**
	 * Returns the greatest element in this set strictly less than the given element, 
	 * or null if there is no such element.
	 * @param e - the value to match and current - the node looked at 
	 * @returns the least element greater than e, or null if there is no such element
	 */

	private E lower(BSTNode<E> current, E val) {
		if (current == null) {
			return null;
		}
		else {
			int compare = current.data.compareTo(val);

			//if the current data is greater than or equal to val, go left for element lower than val
			if (compare > 0 || compare ==0) {
				return lower(current.left, val);
			}

			//after finding lower than val, go right to find the greatest element lower than val
			else if (compare < 0) {
				E nextVal = lower(current.right,val);

				//check how far left until null
				if (nextVal != null) 
					return nextVal;
				else  
					return current.data;
			}
			return current.data;
		}
	}

	/**
	 * Returns true if this collection changed as a result of the call.
	 * Returns false if this collection does not permit duplicates and already contains the specified element.
	 * @param e - element to be added to this set
	 * @returns true if this collection changed as a result of the call
	 * @throws NullPointerException if the value to add is null
	 */

	public boolean add(E e) throws NullPointerException{
		if (e == null) 
			throw new NullPointerException("Element is null.");

		//try to add root recursively and increment size, else return false
		try {
			this.root = add(e, this.root);
			size++;
			return true;

		}
		catch (IllegalArgumentException E) {
			return false;
		}
	}

	/**
	 * Adds the specified element to this set if it is not already present. 
	 * If this set already contains the element, the call leaves the set unchanged and returns false.
	 * @param e - element to be added to this set and node - the current node to look at
	 * @returns BSTNode<E> of the new node if the new node is added
	 * @throws IllegalArgumentException - if node has not been added
	 */

	private BSTNode<E> add(E e, BSTNode<E> node) throws IllegalArgumentException{

		//if empty tree, add node with e data 
		if (node == null) {
			BSTNode<E> newNode = new BSTNode<E>(e);
			return newNode;
		}
		else {

			//if data is less than node data, go left 
			if (e.compareTo(node.data) < 0) {
				node.left = add(e, node.left);
			}

			//if data is more than node data, go right
			else if (e.compareTo(node.data) > 0) {
				node.right = add(e, node.right);
			}

			//if data is equal to node data, throw error, did not add because duplicate
			else {
				throw new IllegalArgumentException("This should be false");
			}
			return node;
		}
	}

	/**
	 * The addAll operation is not supported by this implementation of
	 * {@code BST}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public boolean addAll (Collection<? extends E> c) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	//clears the whole binary search tree
	public void clear() {
		this.root = null;
		size = 0;

	}

	/**
	 * Returns true if this set contains the specified element. 
	 * @param o - element whose presence in this collection is to be tested
	 * @returns true if this set contains the specified element
	 * @throws NullPointerException if the specified element is null 
	 */

	public boolean contains(Object o) throws NullPointerException{
		if (o == null) 
			throw new NullPointerException("Element is null.");
		return contains (o, this.root);
	}

	/**
	 * Returns true if this set contains the specified element. 
	 * @param true if this collection contains the specified element and node - the current node to look at
	 * @returns true if this set contains the specified element
	 */

	private boolean contains(Object o, BSTNode<E> root) {
		if (root == null)
			return false;

		//if node data is equal then true
		if (root.data.equals(o)) 
			return true;

		//if node data is less than object, go right for greater object
		if (root.data.compareTo((E) o) < 0)
			return contains(o, root.right);

		//if node data is greater than object, go left for lesser object
		else 
			return contains(o, root.left);
	}

	/**
	 * Returns true if this collection contains all of the elements in the specified collection. 
	 * @param c - collection to be checked for containment in this collection
	 * @returns true if this collection contains all of the elements in the specified collection
	 */

	public boolean containsAll(Collection<?> c) {
		if (this.size == c.size()) {
			return true;
		}

		//loop through all the elements in the collection and see if it is contained in this tree
		for(Object e : c) {
			if (!this.contains(e)) 
				return false;
		}
		return true;
	}



	//auto-generated
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		result = prime * result + size;
		return result;
	}

	/**
	 * Returns a boolean representing the equality of desired object and this binary search tree
	 * @param the desired object
	 * @returns true if desired object and this binary search tree are equivalent, false if otherwise
	 */	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		BST other = (BST) obj;
		if (this.size() != other.size()) {
			return false;
		}
		return equals(this.root, other);
	}

	/**
	 * Returns a boolean representing the equality of desired object and this binary search tree
	 * @param the desired object as a tree and the node we are traversing through
	 * @returns true if desired object and this binary search tree are equivalent, false if otherwise
	 */

	private boolean equals( BSTNode<E> node, BST<E> tree) {
		if (node == null) {
			return true;
		}

		//checking if nodes are contained in the tree
		if (!tree.contains(root.data)) {
			return false;
		}
		else return equals(node.left, tree) && equals(node.right, tree);
	}

	/**
	 * Returns true if this binary search tree is empty.
	 * @returns true if this binary search tree is empty.
	 */

	public boolean isEmpty() {
		if (this.root == null) 
			return true;
		if (this.size() ==0)
			return true;
		else 
			return false;
	}

	/**
	 * The parallelStream operation is not supported by this implementation of
	 * {@code BST}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public  Stream<E> parallelStream() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns true if this collection contained the specified element 
	 * (or equivalently, if this collection changed as a result of the call).
	 * @param o - element to be removed from this collection, if present
	 * @returns true if an element was removed as a result of this call
	 * adapted from @author Joanna Klukowska's Binary Search Tree notes
	 */

	public boolean remove ( Object o ) {
		root = recRemove( root, o );
		if (root == null) return false;
		else {
			size--;
			return true;
		}
	}

	/**
	 * Returns the Binary Search Tree node to be removed and calls the recursive removes method 
	 * @param o - element to be removed from this collection, if present, and the node we are looking at
	 * @returns BSTNode to be removed or null if node is not found 
	 * adapted from @author Joanna Klukowska's Binary Search Tree notes
	 */

	private BSTNode <E> recRemove (BSTNode <E> node, Object o ) {
		if (node == null)
			return null;
		else if ( node.data.compareTo((E) o)  > 0 ) {
			node.left = recRemove ( node.left, o);} //search in the left subtree
		else if ( node.data.compareTo((E) o)  < 0 ) {
			node.right = recRemove ( node.right, o );} //search in the right subtree
		else  
			//remove the data stored in the node
			node = remove( node);
		return node;
	}		

	/**
	 * Returns the Binary Search Tree node to be removed and actually removes the node
	 * under the condition of it having one children or two children 
	 * @param the node being removed
	 * @returns BSTNode removed  
	 * adapted from @author Joanna Klukowska's Binary Search Tree notes
	 */

	private BSTNode<E> remove ( BSTNode<E> node ) {
		if (node.left == null ) return node.right;
		if (node.right == null )	return node.left;
		//otherwise we have two children
		else {
			BSTNode<E> dataNode = getPredecessor(node);
			node.data = dataNode.data;
			node.left = recRemove ( node.left, dataNode.data );
			return node;
		}
	}

	/**
	 * Returns the predecessor BSTNode, or the largest on the left 
	 * @param the node we are trying to find the predecessorfor
	 * @returns predecessor BSTNode  
	 * adapted from @author Joanna Klukowska's Binary Search Tree notes
	 */

	private BSTNode<E> getPredecessor ( BSTNode<E> n ) {
		if (n.left == null)
			throw new IllegalArgumentException("This shouldn't happen");
		else {
			BSTNode<E> current = n.left;
			while (current.right != null ) {
				current = current.right;
			}
			return current;
		}
	}
	/**
	 * The removeAll operation is not supported by this implementation of
	 * {@code BST}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public boolean removeAll (Collection<?> c) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	/**
	 * The removeIf operation is not supported by this implementation of
	 * {@code BST}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public  boolean removeIf (Predicate<? super E> filter) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	/**
	 * The retainAll operation is not supported by this implementation of
	 * {@code BST}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public boolean retainAll (Collection<?> c) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns size of tree
	 * @returns integer size of tree
	 */	


	public int size() {
		return size;
	}
	/**
	 * The spliterator operation is not supported by this implementation of
	 * {@code BST}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public  Spliterator<E> spliterator() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	/**
	 * The stream operation is not supported by this implementation of
	 * {@code BST}.
	 *
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public  Stream<E> stream() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns array of objects that were once this binary search tree
	 * @returns array of objects
	 */	

	public Object[] toArray() {
		if (this.size == 0) {
			return null;
		}
		Object[] array = new Object[this.size];
		Iterator<E> itr = new Itr();

		for (int i = 0; i < this.size; i++) {
			array[i] = itr.next();
		}
		return array;
	}

	//copied from linked list source code
	@Override
	public <T> T[] toArray(T[] arg0) {
		if (arg0.length < size())
			arg0 = (T[])java.lang.reflect.Array.newInstance(
					arg0.getClass().getComponentType(), size());
		int i = 0;
		Object[] result = arg0;
		Iterator<E> x = iterator();
		while (x.hasNext()) {
			result[i++] = x.next();
		}

		if (arg0.length > size())
			arg0[size()] = null;
		return arg0;
	}

	@Override
	public int compareTo(E arg0) {
		if (((Collection<E>) arg0).size() > this.size()) {
			return 1; 
		}
		if (((Collection<E>) arg0).size() < this.size()) {
			return -1;
		}
		else 
			if (this.equals((Collection<?>) arg0)) {
				return 0;
			}
			else 
				return 1;
	}

	/**
	 * The BSTNode class is an internal class that provides the BSTNode that makes up a single unit of the BST.
	 * It has a data element stored, and the left and right nodes stored.
	 * 
	 * 
	 * @author Christina Liu 
	 *
	 */

	private class BSTNode<E> {
		private BSTNode<E> left;
		private BSTNode<E> right;
		E data;


		/**
		 * Constructs a new default BSTNode object. 
		 */

		BSTNode() {
			left = null;
			right = null;
		}

		/**
		 * Constructs a new BSTNode object with the data as E element. 
		 * @param the element E as the data stored in the node
		 */

		BSTNode( E element) {
			this.data = element;
			left = null;
			right = null;
		}	        


	}

	/**
	 * The PreItr class is an internal class that implements Iterator
	 * It is used in the BST class preorderIterator() method. 
	 * 
	 * 
	 * @author Christina Liu 
	 *
	 */		

	private class PreItr implements Iterator<E> {
		private ArrayList<BSTNode<E>> list = new ArrayList<BSTNode<E>>(); 
		int counter;

		/**
		 * Constructs a new PreItr object 
		 */

		public PreItr() {
			counter = 0;
			preOrder(root);
		}

		//adds the arraylist through preorder traversal (root, left, right)
		private void preOrder(BSTNode<E> node) {
			if (node == null) {
				return;
			}
			list.add(node);
			preOrder(node.left);
			preOrder(node.right);
		}

		/**
		 * Returns boolean 
		 * @returns true if there is a next BSTNode, false if otherwise
		 */

		@Override
		public boolean hasNext() {
			return counter < list.size();
		}

		/**
		 * Returns the BSTNode and increments counter
		 * @returns the BSTNode. 
		 */

		@Override
		public E next() {
			if (hasNext()) 
				return list.get(counter++).data;
			else 
				return null;
		}
	}

	/**
	 * The PostItr class is an internal class that implements Iterator
	 * It is used in the BST class postorderIterator() method. 
	 * 
	 * 
	 * @author Christina Liu 
	 *
	 */	

	private	class PostItr implements Iterator<E> {
		private ArrayList<BSTNode<E>> list = new ArrayList<BSTNode<E>>(); 
		int counter;

		/**
		 * Constructs a new PostItr object 
		 */

		public PostItr() {
			counter = 0;
			postOrder(root);

		}

		//adds the arraylist through postorder traversal (left, right, root)
		private void postOrder(BSTNode<E> node) {
			if (node == null) {
				return;
			}
			postOrder(node.left);
			postOrder(node.right);
			list.add(node);
		}

		/**
		 * Returns boolean 
		 * @returns true if there is a next BSTNode, false if otherwise
		 */

		@Override
		public boolean hasNext() {
			return counter < list.size();
		}

		/**
		 * Returns the BSTNode and increments counter
		 * @returns the BSTNode. 
		 */

		@Override
		public E next() {
			if (hasNext()) 
				return list.get(counter++).data;
			else 
				return null;
		}
	}

	/**
	 * The Itr class is an internal class that implements Iterator
	 * It is used in the BST class Iterator() method. 
	 * 
	 * 
	 * @author Christina Liu 
	 *
	 */	

	private class Itr implements Iterator<E> {
		private ArrayList<BSTNode<E>> list = new ArrayList<BSTNode<E>>(); 
		int counter;

		/**
		 * Constructs a new Itr object 
		 */

		public Itr() {
			BSTNode<E> node = root;
			counter = 0;
			inOrder(node);
		}

		//adds the arraylist through inorder traversal (left, root, right)
		private void inOrder(BSTNode<E> node) {
			if (node == null) {
				return;
			}
			inOrder(node.left);
			list.add(node);
			inOrder(node.right);
		}

		/**
		 * Returns boolean 
		 * @returns true if there is a next BSTNode, false if otherwise
		 */

		@Override
		public boolean hasNext() {
			//return next() != null; 
			return counter < list.size() ;
		}

		/**
		 * Returns the BSTNode and increments counter
		 * @returns the BSTNode. 
		 */

		@Override
		public E next() {
			if (hasNext()) 
				return list.get(counter++).data;
			else 
				return null;
		}
	}
}
