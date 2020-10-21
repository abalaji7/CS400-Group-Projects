// --== CS400 File Header Information ==--
// Name: Karim Matouk
// Email: matouk@wisc.edu
// Team: Testimg
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RedBlackTreeTest {
  
  @Test
  public void testMovieObjectCreation() {
    Movie movie = new Movie("The Empty Man", 5.0, 2020, 2.17, true);
    
    if(!movie.getTitle().equals("The Empty Man")) {
      fail("Movie object not created correctly");
    }
    if(movie.getRating() != 5.0) {
      fail("Movie object not created correctly");
    }
    if(movie.getYear() != 2020) {
      fail("Movie object not created correctly");
    }
    if(movie.getRuntime() != 2.17) {
      fail("Movie object not created correctly");
    }
    if(!movie.isWatched()) {
      fail("Movie object not created correctly");
    }
    
  }
  
  @Test
  public void testMovieObjectManipulation() {
    Movie movie = new Movie("The Empty Man", 5.0, 2020, 2.17, true);
    movie.setTitle("365 Days");
    movie.setRating(8.6);
    movie.setRuntime(1.56);
    movie.setWatched(false);
    
    if(!movie.getTitle().equals("365 Days")) {
      fail("Movie setters not working correctly");
    }
    if(movie.getRating() != 8.6) {
      fail("Movie setters not working correctly");
    }
    if(movie.getYear() != 2020) {
      fail("Movie setters not working correctly");
    }
    if(movie.getRuntime() != 1.56) {
      fail("Movie setters not working correctly");
    }
    if(movie.isWatched()) {
      fail("Movie setters not working correctly");
    }
  }
  
  @Test
  public void testCreatingLibraryWithRoot() {
   RBTMovie Library = new RBTMovie(); 
   Movie movie = new Movie("The Empty Man", 5.0, 2020, 2.17, true);
   Library.insert(movie);
   if(Library.root.data != movie) {
     fail("Book not set as root"); // if root of new Library is not movie test failed.
   }
  }
  
  @Test
  public void testAddingRightChild() {
    Movie movie = new Movie("The Empty Man", 5.0, 2020,  2.17, true);
    Movie movie2 = new Movie("365 Days", 8.6, 2020, 1.56, false);
    RBTMovie Library = new RBTMovie(); 
    Library.insert(movie);
    Library.insert(movie2);
    
    if(Library.root.data != movie) {
      fail("Book not set as root"); // if root of new Library is not movie test failed.
    }
    if(Library.root.rightChild.data != movie2) {
      fail("Book2 not set as rightCild"); // if right child is not movie 2 test failed
    }
  }
  
  @Test
  public void testAddingLeftChild() {
    Movie movie = new Movie("365 Days", 8.6, 2020, 1.56, false);
    Movie movie2 = new Movie("The Empty Man", 5.0, 2020, 2.17, true);
   
    RBTMovie Library = new RBTMovie(); 
    Library.insert(movie);
    Library.insert(movie2);
    
    if(Library.root.data != movie) {
      fail("Book not set as root"); // if root of new Library is not movie test failed.
    }
    if(Library.root.leftChild.data != movie2) {
      fail("Book2 not set as leftCild"); // if left child is not movie2 test failed
    }
  }  

}