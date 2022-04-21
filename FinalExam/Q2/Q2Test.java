public class Q2Test {

	public static void main(String[] args) {

		Queue integerQueue[]= new  LinkedQueue[3];
		integerQueue[0] = new  LinkedQueue();
		integerQueue[1] = new  LinkedQueue();
		integerQueue[2] = new  LinkedQueue();

		integerQueue[0].enqueue(0);		integerQueue[0].enqueue(1);
		integerQueue[1].enqueue(1);		integerQueue[1].enqueue(2);		integerQueue[1].enqueue(2);
		integerQueue[2].enqueue(2);		integerQueue[2].enqueue(3);		integerQueue[2].enqueue(3);
		integerQueue[2].enqueue(3);		integerQueue[2].enqueue(4);		

		System.out.println("---- First test: Integer Queue Arrays ----- ");
		System.out.println("Original Integer Queue[0]: " + integerQueue[0]);
		System.out.println("Original Integer Queue[1]: " + integerQueue[1]);
		System.out.println("Original Integer Queue[2]: " + integerQueue[2]);
		System.out.println("Merged Integer Queue: " + QueueMerger.merge(integerQueue)+"\n");

		
		integerQueue= new  LinkedQueue[3];
		integerQueue[0] = new  LinkedQueue();
		integerQueue[1] = new  LinkedQueue();
		integerQueue[2] = new  LinkedQueue();

		integerQueue[2].enqueue(2);		integerQueue[2].enqueue(3);		integerQueue[2].enqueue(3);
		integerQueue[2].enqueue(3);		integerQueue[2].enqueue(4);

		System.out.println("---- Second test: Integer Queue Arrays ----- ");
		System.out.println("Original Integer Queue[0]: " + integerQueue[0]);
		System.out.println("Original Integer Queue[1]: " + integerQueue[1]);
		System.out.println("Original Integer Queue[2]: " + integerQueue[2]);
		System.out.println("Merged Integer Queue: " + QueueMerger.merge(integerQueue)+"\n");


		LinkedQueue stringQueue[] = new LinkedQueue[1];
		stringQueue[0] = new LinkedQueue();
		
		stringQueue[0].enqueue("a");	stringQueue[0].enqueue("a");	stringQueue[0].enqueue("b");
		stringQueue[0].enqueue("b");	stringQueue[0].enqueue("b");	stringQueue[0].enqueue("d");
		stringQueue[0].enqueue("e");	stringQueue[0].enqueue("e");

		System.out.println("---- Third test: String Queue Array ----- ");
		System.out.println("Original String Queue[0]: " + stringQueue[0]);		
		System.out.println("Merged String Queue: " + QueueMerger.merge(stringQueue)+"\n");
		
	}
}
