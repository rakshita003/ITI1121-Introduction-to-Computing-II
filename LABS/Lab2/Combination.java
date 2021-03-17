public class Combination {
    int first,second,third;
    public Combination( int first, int second, int third ) {
             this.first=first;
             this.second=second;
             this.third=third;
    }

    public boolean equals( Combination other ) {
            if(other != null){
                if(first==other.first && second==other.second&& third==other.third)
                    return true;
                else 
                    return false;
            }
            else 
                return false;
    }

    public String toString() {
        return first +":"+second+":"+third;
    }
}