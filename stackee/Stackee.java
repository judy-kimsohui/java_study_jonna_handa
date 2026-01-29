import java.util.*;


public class Stackee<E> {
	
	private int top;
	private Object[] elements;
	
	public Stackee() {
//		elements = new Object[10];
//		top = -1;
		this(10);
	}
	
	public Stackee(int size) {
		elements = new Object[10];
		top = -1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}

	public E peek() {
		if (isEmpty()) { throw new EmptyStackException(); }
		return (E)elements[top];
	}
	
	public E pop() {
//		E item = (E)elements[top];
		E item = peek();
		elements[top--] = null; // 삭제
		return item;
	}
	
	public boolean full() {
		return top == elements.length - 1;
	}
	
	public E push(E item) {
		
		// 배열이 꽉찬다면, 배열을 2배 단위로 늘린다 
		if (full()) {
			elements = Arrays.copyOf(elements, elements.length * 2);			
		}
		
		elements[++top] = item;
		return item;
	}

	@Override
	public String toString() {
	
		if (isEmpty()) {
			return "[]";
		}
		
		StringBuffer sb = new StringBuffer("[");
		sb.append(elements[0]);

		for (int i = 1; i <= top; i++) {
			sb.append(", ");
			sb.append(elements[i]);
		}
		sb.append("]");
		
		// return "Stackee [top=" + top + ", elements=" + Arrays.toString(elements) + "]";
		return sb.toString();	
	}
	
	

}
