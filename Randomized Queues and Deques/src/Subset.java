
public class Subset {
	public static void main(String[] args){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			String s = StdIn.readString();
			q.enqueue(s);
		}
		int k = Integer.parseInt(args[0]);
        //int k = 3;
		for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
	}
}
