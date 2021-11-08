import java.util.*;

public class Selection<E extends Comparable<E>> {

    int k;
    ArrayList<E> input;

    public Selection(int k, ArrayList<E> list) {
        this.k = k;
        input = list;
    }

    // Algorithm 1B
    // The book wanted an array, so I used the Arrays class
    public E algo1() {
        if (input.isEmpty()) {
            throw new NoSuchElementException();
        }
        Object[] arr = input.subList(0, k).toArray();
        Arrays.sort(arr, Collections.reverseOrder());
        ListIterator<E> list = input.listIterator(k);

        while (list.hasNext()) {
            int index = k - 1;
            E element = list.next();
            if (element.compareTo((E) arr[index]) > 0) { // Very first comparison to kth element
                arr[index] = element;
                index--;
            }
            while (index >= 0 && element.compareTo((E) arr[index]) > 0) { // Other comparisons if any
                E temp = (E) arr[index];
                arr[index] = element;
                arr[index + 1] = temp;
                index--;
            }
        }
        return (E) arr[k - 1];
    }

    // Algorithm 6A
    // Uses Professor Sekharan's version of MaxHeap
    public E algo2() {
        if (input.isEmpty()) {
            throw new NoSuchElementException();
        }
        MaxHeap<E> heap = new MaxHeap<>();
        heap.buildHeap(input);
        for (int i = 0; i < k - 1; i++) {
            heap.removeHeap();
        }
        return heap.findMax();
    }

    // Algorithm 6B
    public E algo3() {
        if (input.isEmpty()) {
            throw new NoSuchElementException();
        }
        PriorityQueue<E> minHeap = new PriorityQueue<>(input.subList(0, k));
        ListIterator<E> list = input.listIterator(k);
        if (!minHeap.isEmpty()) {
            while(list.hasNext()) {
                E element = list.next();
                assert minHeap.peek() != null;
                if (element.compareTo(minHeap.peek()) > 0) {
                    minHeap.poll();
                    minHeap.add(element);
                }
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        // Data Construction
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10000000; i++) {
            list.add(rand.nextInt());
        }
        Selection<Integer> sel = new Selection<>(1000000, list);

        long initialTime;

        // Algo1 test
//        initialTime = System.currentTimeMillis();
//        System.out.println(sel.algo1());
//        System.out.println(System.currentTimeMillis() - initialTime + " milliseconds");
        // Algo2 test
        initialTime = System.currentTimeMillis();
        System.out.println(sel.algo2());
        System.out.println(System.currentTimeMillis() - initialTime + " milliseconds");
        // Algo3 test
        initialTime = System.currentTimeMillis();
        System.out.println(sel.algo3());
        System.out.println(System.currentTimeMillis() - initialTime + " milliseconds");
    }

}
