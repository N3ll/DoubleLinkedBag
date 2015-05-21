public class DoublyLinkedBag<T> implements BagI<T> {

	private Node<T> header;
	private Node<T> trailer;
	private int size;

	// doubly linked bag with sentinels - the header and trailer will be always
	// null
	public DoublyLinkedBag() {
		header = new Node<T>(null, null, null);
		trailer = new Node<T>(null, null, null);
		header.next = trailer;
		trailer.previous = header;

		size = 0;
	}

	@Override
	public int getCurrentSize() {
		return size;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean add(T newEntry) {
		if (isFull())
			return false;
		Node<T> toAdd = new Node<T>(newEntry, header.next, header);
		header.next.previous = toAdd;
		header.next = toAdd;
		size++;
		return true;
	}

	@Override
	public T remove() {
		if (isEmpty())
			return null;
		
		int rand = (int) (Math.random() * size);
		
		if (rand == 0) {
			rand++;
		}

		Node<T> temp = header;
		Node<T> result = null;

		for (int i = 0; i < rand; i++) {
			temp = temp.next;
		}
		result = temp;
		temp.previous.next = temp.next;
		temp.next.previous = temp.previous;
		size--;
		return result.getData();
	}

	@Override
	public boolean remove(T anEntry) {
		if (isEmpty())
			return false;

		Node<T> temp = header.next;

		for (int i = 1; i < size; i++) {
			if (temp.data.equals(anEntry)) {
				Node<T> tempPrevious = temp.previous;
				temp.previous = temp.next;
				temp.next = tempPrevious;
				size--;
				return true;
			} else {
				temp = temp.next;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		header.next = trailer;
		trailer.previous = header;
		size = 0;

	}

	@Override
	public int getFrequencyOf(T anEntry) {
		int count = 0;

		Node<T> temp = header.next;

		for (int i = 1; i < size; i++) {
			if (temp.data.equals(anEntry)) {
				count++;
			}
			temp = temp.next;
		}
		return count;
	}

	@Override
	public boolean contains(T anEntry) {
		if (isEmpty())
			return false;

		Node<T> temp = header.next;

		for (int i = 1; i < size; i++) {
			if (temp.data.equals(anEntry)) {
				return true;
			} else {
				temp = temp.next;
			}
		}
		return false;
	}

	@Override
	public T[] toArray() {
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[size]; // unchecked cast

		int index = 0;
		Node<T> currentNode = header.next;
		while (currentNode.data != null) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		}

		return result;
	}

	private static class Node<T> {

		// all fields are visible to the outer class
		private T data; // entry in bag
		private Node<T> next; // link to next node
		private Node<T> previous; // link to the previous node

		public Node(T data, Node<T> next, Node<T> previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

		public T getData() {
			return data;
		}

	} // end Node<T> class

}
