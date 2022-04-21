
// Name: Rakshita Mathur
// ID: 300215340
//=============================
public class LinkedDoublySkip<E> {
	
	//Node inner class
	public static class Node<T> {
		private T data;
		private Node<T> next, prevSkip;
		Node(T data, Node<T> next, Node<T> prevSkip) {
			this.data = data;
			this.next = next;
			this.prevSkip = prevSkip;
		}
		public T getData() { return data;}
		public Node<T> getNext(){ return next; }
		public Node<T> getPrevSkip(){ return prevSkip;}
	}

	private Node<E> head;
	private int size;

	public void add(E object) { // Q1.1 (8 marks)

		if (object==null)
			throw new IllegalStateException("Empty object can't be added!");

		Node<E> newNode = new Node<E>(object, null, null);
		//if list is empty
		if (head == null) {
			head = newNode;
			size++;
		}
		else { 
			//if it is the first element
			if (head.getData().equals(object)) {
				newNode.next = head;
				head.prevSkip = newNode;
				head = newNode;
				size++;
			}
			else {
				Node<E> current = head;
				while (current.getNext() != null) {
					if (current.getNext().getData().equals(object)) {
						newNode.next = current.getNext();
						newNode.prevSkip = current;
						current.getNext().prevSkip = newNode;
						current.next = newNode;
						size++;
						return;
					}
					current = current.getNext();
				}
				current.next = newNode;
				newNode.prevSkip = current;
				size++;
			}

		}	

	}

	public LinkedDoublySkip() { // Q1.2 (2 marks)
		head = null;
		size = 0;
	}

	public LinkedDoublySkip(E[] array) { // Q1.3 (5 marks)
		if (array == null)
			throw new NullPointerException("array cannot be null");

		for (int i = 0; i < array.length; i++) {
			add(array[i]);
		}

	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return head == null;
	}

	public E getElementAt(int index) { // Q1.4 (6 marks)
		if (index < 0 || index >=  size)
			throw new IllegalArgumentException("The index parameter is out of range");

		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;


	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if(head==null) return buffer.toString();

		Node<E> p=head;
		while(p.next!=null) {
				buffer.append(p.data);
				p=p.next;
				buffer.append(", ");
			}
		buffer.append(p.data);
		
		return buffer.toString();
	}

	//LinkedDoublyIterator inner class
	private class LinkedDoublyIterator implements Iterator<E> { // Q1.5 (9 marks)

		private Node<E> current;
		// ADD other instance variables here (if needed!!)
		private Node<E> prev;
 
        public LinkedDoublyIterator() {
			current = head;
			prev = null;
        }

        public E next() {

			//Add your code here
			if (current == null)
				throw new NoSuchElementException("No more elements");
		
			if (current.next != null){
				current = current.next;
				return current.data;
			} else {
				prev = current.prevSkip;
				current = null;
				if (prev.prevSkip != null)
				prev = prev.prevSkip;
				return prev.data;
			}
			
        }

		public boolean hasNext(){
			//Add your code here
			
			if (current == null)
				return false;
			 if (current.next != null)
				return true;
			if (current.prevSkip != null)
				return true;
			else
				return false;
			
		}

		
	}

	public Iterator<E> iterator() {
		return new LinkedDoublyIterator();
	}
}
