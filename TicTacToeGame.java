import java.util.Scanner;

public class TicTacToeGame {
	
	// Global variables
	public static char [][] shape = new char [3][3];
	public static char player = 'X';
	
	public static void main(String[] args) {
		initShape();
		gameShape();
		nextRound();
	}
	
		// To sign each element with space char. We will need this in comparison later
	public static void initShape() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				shape[i][j] = ' '; // 00 = ' ' , 01 = ' ' , 02 = ' ' - 10 = ' ' , 11 = ' ' , 12 = ' ' - 20 = ' ' , 21 = ' ' , 22 = ' '
			}
		}
	}
	
	// Print the game's shape
	public static void gameShape() {
		System.out.println("-------------");
		for(int i = 0; i < 3; i++) {
			System.out.print("| ");
			for(int j = 0; j < 3; j++) {
				System.out.print(shape[i][j] + " | ");
			}
		System.out.println();
		System.out.println("-------------");
		}
	}
	
	// Set X or O, switch player and continue to next rounds
	public static void fullShape(int row, int col) {
		if (shape[row][col] == ' ') {
			shape [row][col] = player;
			gameShape();
			if (checkWinner() == true) {
				System.out.println(player + " player won");
				System.exit(0);
			}
			
			// Check if there is no one won and the shape completed then will print draw
			if (checkDraw() == true && !checkWinner()) {
				System.out.println("Draw");
				System.exit(0);
			}
			if(player == 'X')
				player = 'O';
			else
				player = 'X';
			nextRound();
		}
		else
			System.out.println("Already taken");
			nextRound();
		}
	
	// Take row and column from the player - inputs 
	public static void nextRound() {
		try {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a row (0, 1, or 2) for player " + player + ": ");
		int row = input.nextInt();
		System.out.print("Enter a column (0, 1, or 2) for player " + player + ": ");
		int col = input.nextInt();
		
		// Check user input
		if (row >= 0 && row < 3) {
			if (col >= 0 && col < 3)
			fullShape(row, col);
			else {
				System.out.println("Please enter a column (0, 1, or 2): ");
				nextRound();
				}
		}
		else {
			System.out.println("Please enter a row (0, 1, or 2): ");	
			nextRound();
		}
		}
		catch(Exception e) {
			System.out.println("Please enter (0, 1, or 2) only.");
			nextRound();
		}
	}	
	// Check if the player won
	public static boolean checkWinner() {
		return (checkRow() || checkColumn() || checkSlash());
	}
	// Check if there is completed row from one player
	public static boolean checkRow() {
		for(int i = 0; i < 3; i++) {
			if(check(shape[i][0],shape[i][1],shape[i][2]) == true)
				return true;
		}
			return false;
	}
	// Check if there is completed column from one player
	public static boolean checkColumn() {
		for(int i = 0; i < 3; i++) {
			if(check(shape[0][i],shape[1][i],shape[2][i]) == true)
				return true;
		}
			return false;
	}
	// Check if there is completed slash from one player
	public static boolean checkSlash() {
			return (check(shape[0][0], shape[1][1], shape[2][2]) == true || check(shape[0][2], shape[1][1], shape[2][0]) == true);
	}
	
	// Check if the shape is completed with X and O
	public static boolean checkDraw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (shape[i][j] == ' ')
					return false;
			}
		}
	return true;
	}
	
	// Check if the entered values are the same in each shape
	public static boolean check(char x1, char x2, char x3) {
		return (x1 == x2 && x2 == x3 && x1 != ' ');
	}
}
