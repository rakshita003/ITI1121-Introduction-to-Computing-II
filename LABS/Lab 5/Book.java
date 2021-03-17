public class Book {

    // Your variables declaration here
    private String author;

    private String title;

    private int year;


    public Book (String author, String title, int year) {
        // Your code here
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        // Your code here
        return author;
    }

    public String getTitle() {
        // Your code here
        return title;
    }

    public int getYear() {
        // Your code here
        return year;
    }

    public boolean equals(Object other) {
        // Your code here
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        final Book obj = (Book) other;
        if ((this.author == null) ? (obj.author != null) : !this.author.equals(obj.author)){
            return false;
        }
        if ((this.title == null) ? (obj.title != null) : !this.title.equals(obj.title)){
            return false;
        }
        if (this.year != obj.year) {
            return false;
            
        }

        return true;
        
    }

    public String toString() {
        // Your code here
        String string;
		string = getAuthor() +":" +getTitle() + "("+getYear()+")"; 
		return string;
    }
}