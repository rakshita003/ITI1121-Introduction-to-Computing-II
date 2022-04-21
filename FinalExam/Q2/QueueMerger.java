// Name: Rakshita Mathur
// ID: 300215340
//=============================
public class QueueMerger {

	public static Queue merge(Queue q[]) {
		
		//add your code here
		Queue newQueue = new LinkedQueue();
		int length  = q.length;
		boolean[]  barr = new boolean[length];
		boolean check = false;
		for(int i=0; i<length; i++){
			barr[i]= true;
			check = check||barr[i];
		}
		while(check){
			for(int i=0; i<length; i++){
				if(!q[i].isEmpty()){
					newQueue.enqueue(q[i].dequeue());
				}
				else{
					barr[i] = false;
				}
			}
			check = false;
			for(int i=0; i<length; i++){
				check = check||barr[i];
			}
		}
		
		return newQueue; 
	}
}
