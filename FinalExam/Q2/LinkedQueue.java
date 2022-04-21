public class LinkedQueue implements Queue {

    private static class Elem {
        private Object value;
        private Elem next;
        private Elem(Object value, Elem next) {
            this.value = value;
            this.next = next;
        }
    }

    private Elem front;
    private Elem rear;

    public LinkedQueue(){
        front = rear = null;
    }

    public  boolean isEmpty(){
       return front == null ;       
    }

    public  void enqueue(Object newElement){

        if(newElement == null) {
            throw new NullPointerException("no null object in my queue !");
        }
        
        Elem newElem;
        newElem = new Elem(newElement,null);
        if(isEmpty()) {
            front = newElem;
            rear = newElem;
        } else {
            rear.next = newElem;
            rear = newElem;
        }
    }


    public Object dequeue() {

        if(isEmpty()) {
            throw new IllegalStateException("Dequeue method called on an empty queue");
        }

        Object returnedValue;
        returnedValue = front.value;

        if(front.next == null) {   
            front = rear = null;
        } else {
            front = front.next;
        }
        return returnedValue;
    }

    public String toString() {
        
        String result = "Front -> [";
        if(!isEmpty()){
            Elem p = front;
            result += p.value;
            while(p.next != null){
                p = p.next;
                result += ", " + p.value;
            }
        }
        result += "] <- Rear";
        return result;
    }

}
