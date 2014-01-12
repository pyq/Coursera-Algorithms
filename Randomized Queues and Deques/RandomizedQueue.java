import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
	//use array is much easier than linklist.
  //linklist implement:https://github.com/kprav/Java/blob/master/Queues/RandomizedQueue.java
  private Item[] a = (Item[]) new Object[1];
	private int N = 0;
	public RandomizedQueue()           // construct an empty randomized queue
   {
	   
   }
   public boolean isEmpty()           // is the queue empty?
   {
   		return N == 0;
   }
   public int size()                  // return the number of items on the queue
   {
   		return N;
   }
   private void resize(int max)
   {
   		Item[] temp = (Item[]) new Object[max];
   		for(int i = 0; i < N; i++)
   			temp[i] = a[i];
   		a = temp;
   }
   public void enqueue(Item item)     // add the item
   {
	    if (item == null) throw new java.lang.NullPointerException();
   		if(N == a.length) resize(2 * a.length);
   		a[N++] = item;
   }
   public Item dequeue()              // delete and return a random item
   {
	    if (isEmpty()) throw new java.util.NoSuchElementException();
   		int i = StdRandom.uniform(N);
   		Item item = a[i];
   		a[i] = a[--N];
   		if(N > 0 && N == a.length/4) resize(a.length/2);
   		return item; 
   }
   public Item sample()               // return (but do not delete) a random item
   {
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   Item item = a[StdRandom.uniform(N)];
   		return item;
   }
   public Iterator<Item> iterator()   // return an independent iterator over items in random order
   {
	   return new RandomizedQueueIterator();
   }
   
   private class RandomizedQueueIterator implements Iterator<Item> {
	   private int current = 0;
       private int[] shuffledIndexes = new int[N];
       
       public boolean hasNext() {
           if (current == 0) {
               for (int i = 0; i < N; i++)
                   shuffledIndexes[i] = i;
               StdRandom.shuffle(shuffledIndexes);
           }
           return current < N;
       }
       public Item next() {
           if (current == 0) {
               for (int i = 0; i < N; i++)
                   shuffledIndexes[i] = i;
               StdRandom.shuffle(shuffledIndexes);
           }
           if (current >= N || size() == 0) throw new java.util.NoSuchElementException();
           return a[shuffledIndexes[current++]];
       }
       public void remove() {
           throw new java.lang.UnsupportedOperationException();
       }
   }
   public static void main(String[] args){
	   RandomizedQueue<String> q = new RandomizedQueue<String>();
	   q.enqueue("1");
	   q.enqueue("2");
	   q.enqueue("3");
	   q.enqueue("4");
	  System.out.println(q.dequeue());
	  // System.out.println(q.removeFirst());
	  // System.out.println(q.removeFirst());
	   //System.out.println(q.removeFirst());
	  for(String s: q)
	   System.out.print(s);
   }
}
