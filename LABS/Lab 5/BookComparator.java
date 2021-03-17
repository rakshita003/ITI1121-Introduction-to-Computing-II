import java.util.Comparator;



public class BookComparator implements Comparator<Book> {

    public int compare(Book b1, Book b2){
        int author = b1.getAuthor().compareTo(b2.getAuthor());
        if(author != 0 ){
            return author;

        }
        int title = b1.getTitle().compareTo(b2.getTitle());
        if(title != 0){
            return title;
        }
        return b1.getYear()-b2.getYear();
    
    }

}
   
         

