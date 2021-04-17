package WeekOne_GuessingGame;
/*.-|*|=^-^=|*|._.|*|=^-^=|*|._.|*|=^-^=|*|.__.|*|=^-^=|*|._.|*|=^-^=|*|._.|*|=^-^=|*|-.*\

      Programmer: Andy Beer
      Class: CS 145 OL2
      Date: 04/13/2021
      Lab 1 - Guessing Game V.2

      Purpose:

        Original program provides user with a guessing game and a game report when finished.
        Bug fixes include adding code to prevent MismatchException when inputing guesses, 
        avoiding issues with user error (when they input a letter instead of a number). 
        
        Incorrect inputs do not count against the player's score. New code starts at line
        148 with the inputInt().
        
\*.-|*|=^-^=|*|._.|*|=^-^=|*|._.|*|=^-^=|*|.__.|*|=^-^=|*|._.|*|=^-^=|*|._.|*|=^-^=|*|-.*/
 
import java.util.*; 

public class ABGuessingGameV2 // runs a guessing game, prints report  
{
    public static void main(String arg[]) 
	{  
        int totalGames = 0;             // initialize totalGames count
        int totalAttempts = 0;          // initialize totalAttempts
        int currentBest = 100;          // initialize currentBest
        boolean continueGame = true;    // initialize while loop test
        
        System.out.println("0=============================================0 ");
        System.out.println("|.5..7...32....89.....94.....12....3...76..26.| ");
        System.out.println("|..93. **=============================** .17..|");
        System.out.println("|==== ** Welcome to The Guessing Game! ** ====| ");
        System.out.println("|...31 **=============================** 49...| "); 
        System.out.println("|....2...86.........50....57........66...8....| ");
        System.out.println("0=============================================0 ");
        System.out.println();
        
        while (continueGame)
        {   // this loop runs the game, tracks the user input, and saves values for summary        
            String confirmedGame = playQuery();
            if(confirmedGame.toLowerCase().equals("y"))
            {   // playQuery() returns user confirmation and begins guessing game, or quits
                int gameAttempts = numberGame();        // runs game, saves int gameAttempts
                totalAttempts += gameAttempts;          // sums each set of gameAttempts
                currentBest = bestGame(gameAttempts, currentBest);// checks for best score
                totalGames++;                           // increments games played
            }    
            else if (confirmedGame.toLowerCase().equals("n"))
            {   // breaks while loop    
                System.out.println("Alright, here is your Game Report: ");
                continueGame = false;   //changed from break per assignment guidelines
            }
        }   // end game loop
        if ( totalGames > 0)
        {   // prevents gameSummary() from dividing by zero
            gameSummary(totalGames, totalAttempts, currentBest);
        }
        else
        {   // user did not play, no custom data exists. prints default info
            System.out.println();
            System.out.println("Total Games Played      : " + totalGames);
            System.out.println("Total Guesses           : " + totalAttempts);
            System.out.println("Average Guesses/Game    : 0 ");
            System.out.println("Best Game               : N/A ");
            System.out.println();
            System.out.println("Goodbye!");
        } 
    } // end main method
    
    public static String playQuery()
    {   // prompts user choice to play or quit guessing game.
        Scanner input = new Scanner(System.in); 
        System.out.println("I am thinking of a number between 1 and 100."); 
        System.out.println("You must guess the number I am thinking.");
        System.out.println("\nWould you like to play?\n\n(Y)es or (N)o.");
        while (true)
        {   // loop runs to prevent unusable user input
            String confirmedGame = input.next();
            System.out.println();   
            if(confirmedGame.toLowerCase().equals("y") || 
               confirmedGame.toLowerCase().equals("n")  )
            {   // input is usable, returns user input as confirmedGame 
                return confirmedGame;
            }
            else
            {   // input is unusable. prompts user to correct input, runs loop again
                System.out.println("I'm sorry, I didn't understand.");
                System.out.println("Please type \"y\" to play, or \"n\" to quit.");
            }
        }
    }   // end playQuery()
             
    public static int numberGame() 
	{   // generates random number, user attempts to guess, program records results 	 		
		//int number = 7;                          // line used when debugging
        int number = 1 + (int)(100 * Math.random());// generate number between 1 and 100
        int attemptsAllowed = 100;                  // allow user "unlimited" attempts
		int gameAttempts = 0;                       // initialize gameAttempts
        int i, guess;

		System.out.println("Excellent, a number has been chosen between 1 and 100.");
        System.out.println("Guess the number:"); 
		
		for (i = 1; i < attemptsAllowed; i++)  
        {   // loop checks user input against randomly generated number
            guess = inputInt();// User inputs their guess after prompt/clues
            gameAttempts++;         // counts number of attempts to get guess correctly
            if (number == guess) 
            {   // break loop, tell user they win 
                System.out.println();
                System.out.println("* * * * * * * * * * * * * * * * * * * * * **"); 
                System.out.println("* Congratulations! You guessed the number! *");
                System.out.println("* * * * * * * * * * * * * * * * * * * * * **");
                System.out.println();
                break;
            } 
			else if (number > guess) 
            {   // a clue is given after each incorrect attempt  
                System.out.println("The number is greater than " + guess); 
			} 
			else if (number < guess) 
            { 
                System.out.println("The number is less than " + guess); 
			} 
		} // end attempt iteration loop
	    return gameAttempts;
    } // end numberGame method
    
    public static int bestGame(int gameAttempts, int currentBest)
    {   // checks gameAttempts against current best game. Returns bestGame to main
        int bestGame = currentBest;     // default game score
        if (gameAttempts < bestGame)
        {   // reassigns best game if incoming gameAttempts are less than existing bestGame
            bestGame = gameAttempts;
        }
        return bestGame;    
    }   // end bestGame()

    public static void gameSummary( int totalGames, int totalAttempts, int bestGame)
    {   // after game loop completes, program prints a final report
        System.out.println();
        System.out.println("Total Games Played      : " + totalGames);
        System.out.println("Total Guesses           : " + totalAttempts);
        System.out.println("Average Guesses/Game    : " + totalAttempts/totalGames);
        System.out.println("Best Game               : " + bestGame + " guesses");
        System.out.println();
        System.out.println("Goodbye!");       
    }   // end gameSummary()
    
    public static int inputInt() 
    {   // verifies user input as a valid input
        Scanner input = new Scanner(System.in);
        int guessInt = 0;
        String guess = input.nextLine();
        while(true) 
        {   // tests String input for occurence of integers, returns valid entries
            Scanner intFinder = new Scanner (guess);
            if (intFinder.hasNextInt())
            {   // assigns integer value from string input
                guessInt = Integer.valueOf(intFinder.nextInt());
                break;
            }
            else
            {   // prevents NumberMismatchException by omitting input.nextInt()
                System.out.println("Invalid entry. Please enter a number between 1-100.");
                guess = input.nextLine();
            }
        }   // end while loop 
        return guessInt;
    }   // end inputInt()

}   // end class ABGuessingGame 
