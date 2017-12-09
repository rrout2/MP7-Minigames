import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
	
	private int[][] board;
	private int turn;
	private static Scanner scanner;

	public TicTacToe() {
		board = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
		turn = 1;
	}
	
	public int getTurn() {
		return turn == 1 ? 1 : 2;
	}
	
	public boolean move(final int x, final int y) {
		if (board[y][x] != 0) {
			return false;
		} else {
			board[y][x] = turn;
			turn *= -1;
			return true;
		}
	}
	
	public int win() {
		if (Math.abs(Arrays.stream(board[0]).sum()) == 3 
				|| Math.abs(board[0][0] + board[1][0] + board[2][0]) == 3) {
			return board[0][0];
		} else if (Math.abs(Arrays.stream(board[1]).sum()) == 3
				|| Math.abs(board[0][1] + board[1][1] + board[2][1]) == 3
				|| Math.abs(board[0][0] + board[1][1] + board[2][2]) == 3
				|| Math.abs(board[0][2] + board[1][1] + board[2][0]) == 3) {
			return board[1][1];
		} else if (Math.abs(Arrays.stream(board[2]).sum()) == 3
				|| Math.abs(board[0][2] + board[1][2] + board[2][2]) == 3) {
			return board[2][2];
		}
				
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) {
					return 0;
				}
			}
		}
		
		return 2;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public static void main(String args[]) {
		scanner = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		while (game.win() == 0) {
			System.out.println("Player " + game.getTurn() +  ", choose a column for your first move.");
			int x = getXY();
			System.out.println("Player " + game.getTurn() +  ", choose a row for your first move.");
			int y = getXY();
			while(!game.move(x,y)) {
				System.out.println("That is not a valid move. Please select an empty square.");
				printBoard(game.getBoard());
				System.out.println("Player " + game.getTurn() +  ", column a row for your first move.");
				x = getXY();
				System.out.println("Player " + game.getTurn() +  ", row a column for your first move.");
				y = getXY();
			}
			
			printBoard(game.getBoard());
						
			/*if(game.win() != 0)
				break;
			System.out.println("Player 2, choose a column for your first move.");
			x = getXY();
			System.out.println("Player 2, choose a row for your first move.");
			y = getXY();
			while(!game.move(x,y)) {
				System.out.println("That is not a valid move. Please select an empty square.");
				printBoard(game.getBoard());
				System.out.println("Player 2, choose a column for your first move.");
				x = getXY();
				System.out.println("Player 2, choose a row for your first move.");
				y = getXY();
			}*/
			
			//printBoard(game.getBoard());
		}
		if (game.win() == 1) {
			System.out.println("Player 1 wins!");
		} else if (game.win() == -1) {
			System.out.println("Player 2 wins!");
		} else {
			System.out.println("Draw!");
		}
		
		scanner.close();
	}
	
	private static int getXY() {
		String input = scanner.nextLine();
		while(!input.equals("1") && !input.equals("2") && !input.equals("3")) {
			System.out.println("Please enter a number between 1 and 3");
			scanner.next();
		}
		return Integer.parseInt(input) - 1;
	}
	
	private static void printBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				switch (board[i][j]) {
					case 0:
						System.out.print("- ");
						break;
					case 1:
						System.out.print("X ");
						break;
					case -1:
						System.out.print("O ");
						break;
					default:
						break;
				}
			}
			System.out.println();
		}
	}

}

