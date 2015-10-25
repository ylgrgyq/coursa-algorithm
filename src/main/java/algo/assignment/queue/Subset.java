package algo.assignment.queue;

/**
 * Created on 15/10/25.
 * Author: ylgrgyq
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[1]);
        String[] sequence = args[2].split(" ");
        RandomizedQueue<String> q = new RandomizedQueue<>();
        for (String s : sequence){
            q.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(q.dequeue());
        }
    }
}
