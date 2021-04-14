public class UniquifiableArrayStack<E> extends ArrayStack<E> {

	public Stack<E> uniquify() {
	  if ( isEmpty()){
		return new UniquifiableArrayStack<>();
	  }

	Stack<E> reverse = new UniquifiableArrayStack<>();
	Stack<E> result = new UniquifiableArrayStack<>();

	while ( ! isEmpty()){
		reverse.push(pop());
	}

	E elem = reverse.pop();

	result.push(elem);
	push(elem);

	while( !reverse.isEmpty() ){
		elem = reverse.pop();
		if(!result.peek().equals(elem)){
			result.push(elem);
		}
		push(elem);
	}
	
	  return result;
	}

}
