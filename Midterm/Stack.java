/**
 * COPYRIGHTED MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Guy-Vincent Jourdan
 * @author Mehrdad Sabetzadeh 
 */

public interface Stack<E> {
	boolean isEmpty();

	E push(E elem);

	E pop();

	E peek();
}