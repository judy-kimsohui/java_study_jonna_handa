
// 최소 힙

import java.util.NoSuchElementException;

public class Heap {

	int[] elements;
	int pos;
	public Heap() {
		elements = new int[10 + 1];
	}
	
	public String toString() {
		if (pos == 0) return "[]";
		
		StringBuffer sb = new StringBuffer("[");
		sb.append(elements[1]);
		for (int i = 2; i <= pos; i++) {
			sb.append(", ");
			sb.append(elements[i]);			
		}
		sb.append("]");
		return sb.toString();
	}
	
	public void offer(int data) {
		if (isFull()) {
			inc();
		}
		elements[++pos] = data;
		int idx = pos;
		while (idx > 1 && elements[idx/2] > elements[idx]) {
			swap(elements, idx/2, idx);
			idx = idx / 2;
		}
	}
	
	private boolean isFull() {
	    return pos == elements.length - 1;
	}

	private void inc() {
	    int[] newArr = new int[elements.length * 2];
	    System.arraycopy(elements, 0, newArr, 0, elements.length);
	    elements = newArr;
	}

	
	public int poll() {
		if (pos == 0) {
			throw new NoSuchElementException();
		}
		int result = elements[1];
		elements[1] = elements[pos];
		elements[pos--] = 0;
		
		heapify();
		return result;
	}
	
	private void heapify() {
		int idx = 1;
		while (idx*2 <= pos) {
			if (pos >= idx * 2 + 1) {
				if (elements[idx] <= elements[idx * 2] && elements[idx] <= elements[idx * 2 + 1]) {
					break;
				}				
			} else {
				if (elements[idx] <= elements[idx * 2]) {
					break;
				}								
			}
			int changeIdx = idx * 2;
			if (pos >= idx * 2 + 1) {
				changeIdx = elements[idx * 2] < elements[idx * 2 + 1] 
						? idx * 2 
						: idx * 2 + 1; 
			}
			swap(elements, idx, changeIdx);
			idx = changeIdx;
		}
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
