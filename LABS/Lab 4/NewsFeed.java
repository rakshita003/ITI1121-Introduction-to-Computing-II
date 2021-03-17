/* *
 * Use static array for NewsFeed
 * with constant MAX_SIZE
 * */

public class NewsFeed {

    private Post[] messages;
    private int size;
    public static final int MAX_SIZE = 25;

    public NewsFeed() {
    	
		this.messages = new Post[MAX_SIZE];
		this.size = 0;
    }

    public void add(Post message) {
     
		if (size < MAX_SIZE)
		this.messages[this.size++] = message;
		
		
    }

    public Post get(int index) {
	     return messages[index];
    }

    public int size() {
	     return size;
    }

	  public void sort(){
			int i, j, argMin;
			Post tmp;
			for (i = 0; i < size - 1; i++) {
				argMin = i;
				for (j = i + 1; j < size(); j++) {
					if (messages[j].compareTo(messages[argMin]) < 0) {
						argMin = j;
					}
				}

  			tmp = messages[argMin];
  			messages[argMin] = messages[i];
  			messages[i] = tmp;
		  }

	  }

  	public NewsFeed getPhotoPost(){
  		
		  NewsFeed photo= new NewsFeed();

		  for(int i = 0; i < size(); i++){
			  if(messages[i].getClass().getName().equals("PhotoPost")){
			        photo.add(messages[i]);
			  }
		  }
		  return photo;
		  }
		   
  	

  	public NewsFeed plus(NewsFeed other){

		NewsFeed newFeeds = new NewsFeed();
		int i;
		for( i = 0; i < size; i++){
			newFeeds.add(messages[i]);
		}
		for(int  j = 0; j < other.size(); j++){
			newFeeds.add(other.messages[j]);
		}
	
		newFeeds.sort();
		return newFeeds;
  	  
  	}

}
