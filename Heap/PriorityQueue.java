
import java.util.Comparator;
import java.util.PriorityQueue;

public class PQTest {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(4);
		pq.offer(2);
		pq.offer(7);
		pq.offer(1);
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());

//		pq = new PriorityQueue<>(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				return o2 - o1;
//			}
//		});
		pq = new PriorityQueue<>(
			(o1, o2) -> o2 - o1
		);
		
	}
}
