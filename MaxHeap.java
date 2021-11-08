
import java.util.*;
public class MaxHeap<E extends Comparable<E>> extends ArrayList<E>   {
    // construct an empty Heap using ArrayList
    // with root at index 0.
    // don't use binary tree for implementing the heap.
    public MaxHeap(){

        super();

    }

    public MaxHeap(Collection<? extends E> c){

        super(c);

    }
    // returns max value
    public E findMax() {
        return  get(0);
    }

    public void buildHeap() {

        for (int i=size()/2;i>=0;i--)
            percolateDown(i);


    }

    //same as remove and add done back to back
    public void replaceMax (E val) {
        set(0,val);
        percolateDown(0);
    }
    public void percolateDown(int location) {
        E left=null;
        E right=null;

        if (!isEmpty()) {

            int k = size()-1;
            int c1=2*location+1;

            while (c1<=k) {
                left=get(c1);
                int c2 =2*location+2;
                if (c2<=k)  { //1
                    right=get(c2);
                    if (left.compareTo(right)>=0) {
                        if (left.compareTo(get(location))>0)
                            swap(location,c1);
                        location=c1;
                    }
                    else if (right.compareTo(get(location))>0) {
                        swap(location,c2);
                        location=c2;
                    }
                } //1
                // no right child
                else {
                    if (left.compareTo(get(location))>0)
                        swap(location,c1);
                    break;
                }
                //  c1=2*location+1;
                if (c1!=2*location+1) c1=2*location+1;
                else break;
            }
        }


    }

    // adds a new value to the heap at the end of the Heap and 
    // adjusts values up to the root to ensure Max heap property is satisfied.
    // parent of node at i is given by the formula (i-1)/2
    // throw appropriate exception
    public void addHeap(E val) {
        add(val);
        int k=size()-1;
        while (k>=0) {
            int j =(k-1)/2;
            // when k=0, j becomes 0 at which point you should stop and hence the check for j!=k
            if (j>=0 && j!=k) {
                if (val.compareTo(get(j))>0)
                    swap(k,j);
                k=j;
            }
            else break;
        }
    }

    public void swap(int k1, int k2) {
        E temp = get(k1);
        set(k1,get(k2));
        set(k2,temp);
    }

    //returns the max value at the root of the heap by swapping the last value 
    // and percolating the value down from the root to preserve max heap property
    // children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    // bounds of the Heap index, namely, 0 ... size()-1.
    // throw appropriate exception
    public E removeHeap() {

        E left=null;
        E right=null;

        if (!isEmpty()) {
            E max=findMax();
            int k=size()-1;
            set(0,get(k));
            remove(k);
            k=k-1;
            int p=0;
            int c1=2*p+1;
            while (c1<=k) {
                left=get(c1);
                int c2 =2*p+2;
                if (c2<=k)  { //1
                    right=get(c2);
                    if (left.compareTo(right)>=0) {
                        if (left.compareTo(get(p))>0)
                            swap(p,c1);
                        p=c1;
                    }
                    else if (right.compareTo(get(p))>0) {
                        swap(p,c2);
                        p=c2;
                    }
                } //1
                // no right child
                else {
                    if (left.compareTo(get(p))>0)
                        swap(p,c1);
                    break;
                }
                // c1=2*p+1;
                if (c1!=2*p+1) c1=2*p+1;
                else break;
            }
            return max;
        }
        else throw new NoSuchElementException();
    }

    // takes a list of items E and builds the heap and then prints 
    // decreasing values of E with calls to removeHeap().  
    public void heapSort(List<E> list){
        buildHeap(list);
        while (!isEmpty())
            System.out.println(removeHeap());
    }

    // merges the other maxheap with this maxheap to produce a new maxHeap.  
    public void heapMerge(MaxHeap<E> other){
        // the easiest but not the most efficient way is this
        // you keep this heap the same
        // use removeHeap() from the other to add that item via addHeap() to this heap
        // repeat the step above
        while (!other.isEmpty())
            addHeap(other.removeHeap());

    }

    //takes a list of items E and builds the heap by calls to addHeap(..)
    public void buildHeap(List<E> list) {

        ListIterator<E> li = list.listIterator();
        while (li.hasNext())
            addHeap(li.next());

    }

    public static void main(String[] args) {

        MaxHeap<Integer>mh = new MaxHeap();
        Random r = new Random();
        //  MaxHeap<Integer> mh2 = new MaxHeap();
        for (int i=0;i<100;i++)
            mh.add(r.nextInt(500));
        mh.buildHeap();
        
      
      /*  
        Integer[] num = {5,2,9,7,8,15,22,17,14,3,10};
        List<Integer> li = Arrays.asList(num);
        MaxHeap<Integer>mh = new MaxHeap(li);
         mh.buildHeap();*/



        //  Integer[] num2 ={1,2,3,18,20};
        // List<Integer> list2 = Arrays.asList(num2);
        //  mh1.buildHeap(list1);
        // mh2.buildHeap(list2);
        // mh1.heapMerge(mh2);*/
        while(!mh.isEmpty())
            System.out.println(mh.removeHeap());



        // mh.buildHeap(list);
        // mh.heapSort(list);
        // mh.removeHeap();

    }


}