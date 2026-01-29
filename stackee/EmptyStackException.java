import java.util.*;


public class EmptyStackException extends RuntimeException {

	public EmptyStackException() {
		this("스택이 비어있습니다!!!");
	}
	
	public EmptyStackException(String message) {
		super(message);
	}
	
	
}
