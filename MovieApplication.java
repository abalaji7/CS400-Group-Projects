// --== CS400 File Header Information ==--
// Name: Stephen Sarff
// Email: sarff@wisc.edu
// Team: NF
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.Scanner;
/**
 * 
 * @author Stephen Sarff
 *
 */
public class MovieApplication extends RedBlackTreeExtension<Movie>{
	public static void instructionsPrint() {
		System.out.println("COMMANDS");
		System.out.println("a -- add a movie to the database");
		System.out.println("g -- get a list of movies showing their runtime");
		System.out.println("r -- get a list of movies showing their review");
		System.out.println("w -- get a list of all of the watched movies");
		System.out.println("h -- to get a list of commands");
		System.out.println("p -- print a list of all the movies");
		System.out.println("b -- print a backwards list of all of the movies");
		System.out.println("q -- quit the program");
	}
	/**
	 * 
	 * @param Takes a scanner as an input
	 * @return returns the first character entered by the user
	 */
	public static char getInputChar(Scanner input) {
		char result = input.nextLine().trim().toLowerCase().charAt(0);
		return result;
	}
	/**
	 * Prints the runtime of all movies in the red black tree
	 * @param node
	 */
	public static void runtimePrint(Node<Movie> node) {
		if(node == null) return;
		runtimePrint(node.leftChild);
		System.out.println(node.data.getTitle() + " with a runtime of " + node.data.getRuntime());
		runtimePrint(node.rightChild);
	}
	/**
	 * Prints all reviews of the movies in the red black tree
	 * @param node
	 */
	public static void reviewPrint(Node<Movie> node) {
		if(node == null) return;
		reviewPrint(node.leftChild);
		System.out.println(node.data.getTitle() + " with a review of " + node.data.getRating());
		reviewPrint(node.rightChild);
	}
	/**
	 * Prints on the movies that have been watched
	 * @param node
	 */
	public static void watchedMoviesPrint(Node<Movie> node) {
		if(node == null) return;
		watchedMoviesPrint(node.leftChild);
		if(node.data.isWatched()) System.out.println(node.data.toString());
		watchedMoviesPrint(node.rightChild);
	}

	public static void main(String args[]) {
		RedBlackTreeExtension<Movie> movies = new RedBlackTreeExtension<Movie>();
		char caseChar;
		Scanner inputs = new Scanner(System.in);
		System.out.println("Here are some commands to help you get started:");
		instructionsPrint();
		caseChar = getInputChar(inputs);
		boolean quit = false;
		while(!quit) {
		switch(caseChar) { //Switches cases depending on the user input
			case 'a':
				System.out.println("To enter your movie use the following format:");
				System.out.println("Title,Rating,Year,Runtime,WatchedOrNot");
				String input = inputs.nextLine();
				String [] inputArray = input.trim().split(",");
				if (inputArray.length != 5) {
                                        System.out.println("The format was entered incorrectly use the format: Title,Rating,Year,Runtime,WatchedOrNot");
                                }
                                for(int i = 0; i < 5; i++) {
                                        inputArray[i] = inputArray[i].trim();
                                }
				try {
					if(inputArray[4].toLowerCase().equals("yes") || inputArray[4].toLowerCase().equals("true")) { //Checks for the user entering true or yes for the final parameter of the red black tree
						movies.insert(new Movie(inputArray[0],Double.parseDouble(inputArray[1]),Integer.parseInt(inputArray[2]), Double.parseDouble(inputArray[3]), true));
						System.out.println("Item successfully added press h for a list of commands");
						caseChar = getInputChar(inputs);
						break;
					}
					else {
						movies.insert(new Movie(inputArray[0],Double.parseDouble(inputArray[1]),Integer.parseInt(inputArray[2]), Double.parseDouble(inputArray[3]), false));
						System.out.println("Item successfully added press h for a list of commands");
						caseChar = getInputChar(inputs);
						break;
					}
				}
				catch(NumberFormatException e) { //Catches numbers being entered incorrectly
					System.out.println("You entered one of the numbers incorrectly, the rating and runtime are doubles, and the year is an int");
					break;
				} catch(IllegalArgumentException e1) {
                                        System.out.println("That rating is already in the tree, try changing the rating and adding it again");
                                        break;
                                }
			case 'g':
				runtimePrint(movies.root);
				caseChar = getInputChar(inputs);
				break;
			case 'r':
				reviewPrint(movies.root);
				caseChar = getInputChar(inputs);
				break;
			case 'w':
				watchedMoviesPrint(movies.root);
				caseChar = getInputChar(inputs);
				break;
			case 'h':
				instructionsPrint();
				caseChar = getInputChar(inputs);
				break;
			case 'p':
				movies.inOrderTraversal();
				caseChar = getInputChar(inputs);
				break;
			case 'b':
				movies.reverseOrderTraversal();
				caseChar = getInputChar(inputs);
				break;
			case 'q':
				quit = true;
				break;
			default:
				System.out.println("You entered an incorrection command, here is a list of all the commands");
				instructionsPrint();
				caseChar = getInputChar(inputs);
				break;
		}
		
		}
				
				
			
	}
	}