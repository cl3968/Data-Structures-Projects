package project3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator; 

/**
 * The LinkedList class implements all methods from Collection interface; it also implements 
 * Comparable and Iterable.
 * It also has two internal classes: Node and Itr (which implements Iterator).
 * A linked list consists of nodes that differentiates it from an array list. 
 * The advantages of using a linked list is that it is more efficient in terms of adding or
 * removing elements.
 * 
 * Note: The unimplemented methods are: addAll, removeAll, and retainAll.
 * 
 * 
 * @author Christina Liu 
 *
 */

public class LinkedList<E> implements Comparable<E>, Collection<E>, Iterable<E>{
	
	//default constructor 
	public LinkedList () {	
	}
	
	private Node<E> head;
	private Node<E> tail;
	
	/**
	 * Returns the index of an object
	 * @param the desired object
	 * @returns the index of the first occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element
	 */
	
	public int indexOf(Object o) {
		 int index = 0;
		 Node<E> current = head;
		 
		 	//verifying if object exists
	        if (o == null) {
	        	
	        		//trying to find a null object
	            while ( current != null) {
	                if (current.data == null) {
	                    return index;
	                }
	                index++;
	                current = current.next;
	            }
	        } 
	        else {
	        	
	        		//running through list to find object
	            while(current != null) {
	                if (o.equals(current.data)) {
	                    return index;
	                }
	                index++;
	                current = current.next;
	            }
	        }
	        return -1;
	}
	
	/**
	 * Returns the element at the specified position in this list.
	 * @param the integer index
	 * @returns the data of the node at the integer index
	 * @throws IndexOutOfBounds when index is negative, equal or greater than size, or if linked list is null
	 */
	
	public E get(int index) throws IndexOutOfBoundsException{
		
		//verfiying bounds of index
		if (index < 0 || index >= this.size() || head == null) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		
		//running though list up until index and then returning data of the node at the index
		Node<E> current = head;
		for (int i=0; i < index; i++) {
			current = current.next;
			}
			return current.data;
	}
	
	/**
	 * Returns a string representation of this collection
	 * @returns the string representation of this LinkedList object  
	 */
	
	public String toString(){
        String str = "[";
        Node<E> current = head;
        while(current != null){
            str += String.valueOf(current.data) + ", ";
            current = current.getNext();
        }
        str += "]";
        return str;
    }
	
	//copied from project guidelines
	public void sort () {
		Object [] array = toArray();
		Arrays.sort(array);
		this.clear();
		for (Object o : array ) {
			this.add( (E)o );
		}
		}

	/**
	 * Adds a new node to the end of the linked list, increments size, and returns true
	 * @param the desired object
	 * @returns true if linked list has been changed
	 * @throws NullPointerException when desired object is null
	 */
	
	@Override
	public boolean add(E arg0) throws NullPointerException{
		int size = this.size();
		
		//verifying object is valid
		if (arg0 == null) {
			throw new NullPointerException("Item is null.");
		}
		size++;
		
		//if linked list is empty, object will be the first node
		if (head == null ) {
			head = new Node<E>(arg0);
			tail = head;
		}
		else {
			
			//looping through linked list until the last node, then adding the new node the end
			Node<E> current = head;
			while (current.next!= null ) {
				current = current.next;
			}
			current.next = new Node<E>(arg0);
		}	
		
		//if object is valid, it's always true
		return true;		
	}

	/**
     * The addAll operation is not supported by this implementation of
     * {@code LinkedList}.
     *
     * @throws UnsupportedOperationException if this method is invoked.
     * @see java.util.LinkedList
     */
	
	@Override
	public boolean addAll(Collection<? extends E> arg0) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	//clears the whole linked list
	@Override
	public void clear() {
        head = tail = null;
	}

	/**
	 * Verifies if object is contained in this linked list
	 * @param the desired object
	 * @returns true if linked list contains object, false if not
	 */
	
	@Override
	public boolean contains(Object arg0) {		
		for (Object o : this) {
			 if(o.equals(arg0)) {
				 return true;
			 }
		 }
		return false;
	}

	/**
	 * Returns true if this collection contains all of the elements in the specified collection.
	 * @param the desired collection
	 * @returns true if linked list contains all objects from desired collection, false if not
	 */
	
	@Override
	public boolean containsAll(Collection<?> arg0) {
			for (Object o: arg0) {
				if (!this.contains(o) ) {
					return false;
				}				
			}
			 return true;
	}

	/**
	 * Returns true if this linked list is empty.
	 * @returns true if linked list head is null, or size is 0; false otherwise
	 */
	
	@Override
	public boolean isEmpty() {
		if (head == null || this.size()==0) {
			return true; 
		}
		return false;
	}

	
	/**
	 * Returns an iterator over the elements in this collection.	 
	 * @returns an iterator from Itr class
	 */
	
	@Override
	public Iterator<E> iterator() {
			return new Itr();
	}

	/**
	 * Removes a desired object and returns true	
	 * @param the desired object 
	 * @returns true if object has been removed, false if object is not in this list
	 */
	
	@Override
	public boolean remove(Object arg0)  {
		int size = this.size();
		Node<E> current = head;
	    Node<E> prev = null;
	    
	    //verifying list is not empty
		if (head == null ) {
			return false;
		}
		
		//verifying object is not null
		if (arg0 == null)  {
			return false;
		}
		
		//if first element is object, remove the head and the decrement size
		if (head.data.equals(arg0)) {
			head = head.next;
			size--;
			return true;
		}
		else 
			
			//search through this linked list until list ends or object is found
			while(current != null && !current.data.equals(arg0) ) {
		         prev = current;
		         current = current.next;
			}
		
			//the object does not exist in this linked list
		    if(current == null) {
		      return false;
		    }
		    
		    //the object is found and removed, size is decremented 
		    prev.next = current.next;
		    size--;
			return true;
	}

	/**
     * The removeAll operation is not supported by this implementation of
     * {@code LinkedList}.
     *
     * @throws UnsupportedOperationException if this method is invoked.
     * @see java.util.LinkedList
     */
	
	@Override
	public boolean removeAll(Collection<?> arg0) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	/**
     * The retainAll operation is not supported by this implementation of
     * {@code LinkedList}.
     *
     * @throws UnsupportedOperationException if this method is invoked.
     * @see java.util.LinkedList
     */
	
	@Override
	public boolean retainAll(Collection<?> arg0) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns size of list
	 * @returns integer size of list
	 */	
	
	@Override
	public int size () {
		int size = 0;
		Node<E> current = head;
		while(current != null) {
			size++;
			current = current.next;
		}
		return size;			
	}

	/**
	 * Returns array of objects that were once this linked list 
	 * @returns array of objects
	 */	
	
	@Override
	public Object[] toArray() {
		Object[] array = new Object[this.size()];
		for (int i = 0; i < this.size(); i++) {
			array[i] = this.get(i);   
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
	    for (Node<E> x = head; x != null; x = x.next)
	    		result[i++] = x.data;
	    if (arg0.length > size())
	    		arg0[size()] = null;
	    return arg0;
	    }

	/**
	 * Returns an integer comparing two lists
	 * @param the desired list
	 * @returns an integer 0 if the size of both lists are the same and they contain the same elements,
	 * an integer -1 if the size of the desired list is less than this list
	 * and an integer 1 if the size of the desired list is greater than this list
	 */	
	
	@Override
	public int compareTo(E arg0) {
		if (((Collection<E>) arg0).size() > this.size()) {
			return 1; 
		}
		if (((Collection<E>) arg0).size() < this.size()) {
			return -1;
		}
		else 
			if (this.containsAll((Collection<?>) arg0)) {
				return 0;
			}
			else 
				return 1;
	}
	
	//auto-generated
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + size();
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
		return result;
	}

	 /**
	 * Returns a boolean representing the equality of desired object and this linked list
	 * @param the desired object
	 * @returns true if desired object and this linked list are equivalent, false if otherwise
	 */	
	 
	 //adapted this from the AbstractList class 
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
            return true;
        if (!(obj instanceof LinkedList))
            return false;
        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((LinkedList<?>) obj).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
	}

	/**
	 * The Node class is an internal class that provides the Node that makes up a single unit of LinkedList.
	 * It has a data element stored, and the next and previous elements stored.
	 * 
	 * 
	 * @author Christina Liu 
	 *
	 */
	
	class Node<E> {
	        E data;
	        Node<E> next;
	        Node<E> prev;

	       /**
	        * Constructs a new Node object with the data as E element. 
	    	 	* @param the element E as the data stored in the node
	    	 	*/
	        
	        Node( E element) {
	            this.data = element;
	            next = null;	           
	        }	        

		    /**
		    	 * Constructs a new Node object with the data, the previous node and the next node. 
		    	 * @param the element E as the data, the node prev, and the node next stored in the current node constructor
		    	 */
	        
			Node(Node<E> prev, E element, Node<E> next) {
	            this.data = element;
	            this.next = next;
	            this.prev = prev;
	        }

			//sets the next node 
			public void setNext(Node arg0) {
				next = arg0;
			}

			/**
			 * Returns the node of the next node.
			 * @returns the node of the next node. 
			 */
			
			public Node<E> getNext() {
				return next;  
				}
			}
	
	/**
	 * The Itr class is an internal class that implements Iterator
	 * It is used in the LinkedList class iterator() method. 
	 * 
	 * 
	 * @author Christina Liu 
	 *
	 */	

	     class Itr<E> implements Iterator<E> {
	        private Node<E> current;
	        private Node<E> previous;

	        	/**
		     * Constructs a new Itr object 
		    	 */
		       
			public Itr() {
				super();
				this.current= (Node<E>) head;
			}
			
			/**
			 * Returns boolean 
			 * @returns true if there is a next node, false if otherwise
			 */
			
			public boolean hasNext() {
				if (current != null && current.next !=null) {
					return true;
				}
				return false;
			}
			
			/**
			 * Returns the next node
			 * @returns the node of the next node. 
			 */
			
			@Override
			public E next() {
				if (this.hasNext()) {
					if ( previous == null )
		            {
		                previous = current;
		                return previous.data;
		            }
		            E node = current.data;
		            current = current.next;
		            return current.data;
				}
				return null;
			}
	    }
}

	    

