
public class StackeeTest {
	public static void main(String[] args) {
		Stackee<Integer> s = new Stackee<>();
		
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		
		
		/*
		 * 주석처리하면 EmptyException을 발생시킨다. 
		 * s.push(2);
		 * s.push(3);
		 * s.push(4);
		 * 
		 * */
		
		System.out.println(s);
		
		// peek은 EmptyException을 발생시킬 수 있다.
		System.out.println("peek : " + s.peek());
		System.out.println("pop : " + s.pop());
		System.out.println(s.pop());
		System.out.println(s);
		
	}
}
