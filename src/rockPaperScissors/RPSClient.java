/**
 * 
 */
package rockPaperScissors;

import java.util.InputMismatchException;
import java.util.Scanner;
import rockPaperScissors.domain.Game;

/**
 * @author
 *
 */
public class RPSClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InputMismatchException {
		int totalPlayers;
		boolean haveHumanPlayer;
		try (Scanner input = new Scanner(System.in)) {
				try {
					System.out.println("多少玩家要一起玩？請輸入數字：");
					totalPlayers = input.nextInt();
					System.out.println("您要一起玩嗎？[true] 要一起玩，[false] 電腦自己玩就好。");
					haveHumanPlayer = input.nextBoolean();
					Game game = new Game(totalPlayers, haveHumanPlayer);
					game.play();
				} catch (InputMismatchException ex) {
					System.out.println("Invalid input!");
				}
		}
	}
}
