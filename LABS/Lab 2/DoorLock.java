public class DoorLock {
    public static final int MAX_NUMBER_OF_ATTEMPTS = 3;
    private Combination combination;
    private boolean open;
    private boolean activated;
    private int numberOfAttempts;

    public DoorLock( Combination combination ) {
        this.combination = combination;
        open = false;
        activated = true;
        numberOfAttempts = 0;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isActivated() {
        return activated;
    }

// Notice that numberOfAttempts is compared to
// MAX_NUMBER_OF_ATTEMPTS only when its value has been
// incremented, Also, numberOfAttempts should be set to zero when
// activated is false. Problems related to the combined action of
// these two variables have caused problems for some students.

    public boolean open( Combination combination ) {
        if(this.isActivated() == true){
            numberOfAttempts ++;
       		if(numberOfAttempts == MAX_NUMBER_OF_ATTEMPTS) {
            		this.activated = false;
            	    numberOfAttempts = 0;
            	}
			if(combination == this.combination){
            	this.open = true;
            	
        	}
    	}
       
             	        	
    return activated;
    }

    public void activate( Combination c ) {
        if(c == this.combination)
            activated = true;
    }

}