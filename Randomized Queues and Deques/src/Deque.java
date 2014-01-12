import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
   
   private class Node
   {
   		Item item;
   		Node next;
   		Node prev;
   }
   private Node first;
   private Node last;
   private int N;
   public Deque()                     // construct an empty deque
   {
   		first = last = null;
   		N = 0;
   }
   public boolean isEmpty()           // is the deque empty?
   {
   		if(first == null)
   			return true;
   		else
   			return false;
   		//return first == null;
   }
   public int size()                  // return the number of items on the deque
   {
   		return N;
   }
   public void addFirst(Item item)    // insert the item at the front
   {
   		if(item == null)
   			throw new java.lang.NullPointerException();
   		Node oldfirst = first;
   		if(isEmpty())
   		{
   	   		first = new Node();
   	   		first.item = item;
   	   		first.prev = null;
   	   		first.next = oldfirst;
   			last = first;
   		}
   		else
   		{
   	   		first = new Node();
   	   		first.item = item;
   	   		first.prev = null;
   	   		first.next = oldfirst;
   			oldfirst.prev = first;
   		}
   		N++;
   }
   public void addLast(Item item)     // insert the item at the end
   {
	   if(item == null)
		   throw new java.lang.NullPointerException();
	   Node oldlast = last;
	   last = new Node();
	   last.item = item;
	   last.next = null;
	   last.prev = oldlast;
	   if(isEmpty())
		   first = last;
	   else
		   oldlast.next = last;
	   N++;
   }
   public Item removeFirst()          // delete and return the item at the front
   {
	   if(isEmpty())
		   throw new java.util.NoSuchElementException();
	   Item item = first.item;
	   first = first.next;
	   if(first !=null)
		   first.prev = null;
	   else
		   last = null;
	   N--;
	   return item;
   }
   public Item removeLast()           // delete and return the item at the end
   {
	   if(isEmpty())
		   throw new java.util.NoSuchElementException();
	   Item item = last.item;
	   last = last.prev;
	   if(last != null)
		   last.next = null;
	   else
		   first = null;
	   N--;
	   return item;
   }
   public Iterator<Item> iterator()   // return an iterator over items in order from front to end
   {
	   return new ListIterator();
   }
   
   private class ListIterator implements Iterator<Item>
   {
       private Node current = first;
       public boolean hasNext()
       {  return current != null;  }
       public void remove() 
       {
    	   throw new java.lang.UnsupportedOperationException();
       }
       public Item next()
       {
    	   if(current == null)
    		   throw new java.util.NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
        } 
    }
   
   public static void main(String[] args){
	   Deque<String> q = new Deque<String>();
	   q.addLast("B");
	   q.addFirst("A");
	   q.addFirst("c");
	   q.addLast("b");
	  // System.out.println(q.removeLast());
	  // System.out.println(q.removeFirst());
	  // System.out.println(q.removeFirst());
	   //System.out.println(q.removeFirst());
	  for(String s: q)
	   System.out.print(s);
   }
}