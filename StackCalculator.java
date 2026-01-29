import java.util.*;


public class StackCalculator {
	
	// 연산자의 우선순위
	private static Map<Character, Integer> priority = new HashMap<>();
	static {
		priority.put('*', 2);
		priority.put('/', 2);
		priority.put('+', 1);
		priority.put('-', 1); // 우선순위가 낮음
		priority.put('(', 0);			
	}

	public static void main(String[] args) {
		
		String Post = makePost("(6+5*(2-8)/2)");
		System.out.println(Post);
		calPost(Post); // "6528-*2/+"
	}
	
	// 후위 표현식 작성
	private static String makePost(String data) {
		StringBuffer postfix = new StringBuffer();

		// 연산자 스택
		Stack<Character> op = new Stack<>();
		
		// 계산식 확인
		for (int i = 0; i < data.length(); i++) {
			char tmp = data.charAt(i);
			
			// 숫자라면
			if (tmp >= '0' && tmp <= '9') {
				postfix.append(tmp);
			} else if (tmp == '(') {
				// 여는 골호라면, op 스택에 푸시
				op.push(tmp);
			} else if (tmp == ')') {
				// 닫는 괄호라면, 여는 괄호를 꺼낼 때까지 op를 꺼내야함
				while (op.peek() != '(') {
					postfix.append(op.pop());
				}
				op.pop();
			} else {
				// 연산자가 나온 경우
				if (op.empty()) {
					op.push(tmp);
				} else {
					// 연산자가 들어가있는 상태 
					while (!op.empty() && priority.get(op.peek()) >= priority.get(tmp)) {
						postfix.append(op.pop());
					}
					op.push(tmp);
				}
			}
		}
		
		while (!op.isEmpty()) {
			postfix.append(op.pop());
		}		
		return postfix.toString();
	}

	// 후위 표현식에 따른 계산
	private static void calPost(String expression) {
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0, size = expression.length(); i < size; i++) {
			char temp = expression.charAt(i);
			
			// 숫자라면 (피연산자)
			if (Character.isDigit(temp)) {
				int k = temp - '0';
				// temp & 15 비트 연산자
				stack.push(k);
			} else {
				int val2 = stack.pop();
				int val1 = stack.pop();
				int result = 0;
				
				switch (temp) {
				case '+':
					result = val1 + val2;
					break;
				case '-':
					result = val1 - val2;
					break;
				case '*':
					result = val1 * val2;
					break;
				case '/':
					result = val1 / val2;
					break;
				}
				
				stack.push(result);
			}
		}
		System.out.println(stack.pop());
	}

}
