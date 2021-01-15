
/* 
   COMP90041 Project1
   Student:Yuming Lin
   Login id:YUMINGL
   student num:883717
   email address:yumingl@student.unimelb.edu.au
   Semester 1, 2018, week4
*/

import java.util.Scanner;

public class Nimsys {
	Scanner in = new Scanner(System.in);
	int limitTaken, totalStones, stonesTaken;
	//turn is used to determine who's turn.
	int turn = 0;
	//Can take the lower limit of stone
	int lowerLimit = 0;
	String playerName1, playerName2, answer;

	// set the upper bound of stones
	public void setLimitTaken() {
		System.out.println('\n' + "Please enter upper bound of stone removal:");
		limitTaken = in.nextInt();
		// check wrong input
		if (limitTaken <= lowerLimit) {
			do {
				limitTaken = in.nextInt();
			} while (limitTaken <= lowerLimit);
		}
	}

	// set the initial number of stones
	public void setTotalStones() {
		System.out.println('\n' + "Please enter initial number of stones:");
		totalStones = in.nextInt();
		// check wrong input
		if (totalStones == lowerLimit || totalStones < limitTaken) {
			do {
				totalStones = in.nextInt();
			} while (totalStones == lowerLimit || totalStones < limitTaken);
		}
	}

	// input the number of the removed stones
	public int setRemoveStone() {
		stonesTaken = in.nextInt();
		// check wrong input
		if (stonesTaken < lowerLimit || stonesTaken > limitTaken) {
			do {
				stonesTaken = in.nextInt();
			} while (stonesTaken < lowerLimit || stonesTaken > limitTaken);
		}
		return stonesTaken;
	}

	// set player's name
	public void setPlayerName() {
		System.out.println('\n' + "Please enter Player 1's name:");
		playerName1 = in.nextLine();
		System.out.println('\n' + "Please enter Player 2's name:");
		playerName2 = in.nextLine();
	}

	// check who wins by using turn number
	public int checkResult() {
		if (turn == 1) {
			System.out.println("Game Over");
			System.out.println(playerName2 + " wins!");
		} else if (turn == 2) {
			System.out.println("Game Over");
			System.out.println(playerName1 + " wins!");
		}
		return turn;
	}

	// main playing method
	public int process() {
		Loop: while (totalStones != lowerLimit) {
			System.out.print('\n');

			// print stones number
			System.out.printf(totalStones + " stones left:");
			for (int i = 0; i <= totalStones - 1; i++) {
				System.out.printf(" *");
			}
			// player1's turn
			System.out.println('\n' + playerName1 + "'s turn - remove how many?");
			stonesTaken = setRemoveStone();
			System.out.print('\n');

			// Calculate the remaining stones.
			if (stonesTaken <= totalStones || stonesTaken > lowerLimit) {
				totalStones = totalStones - stonesTaken;
				turn = 1;
			}
			// if stones are less than 0 then jump out of the loop
			if (totalStones <= lowerLimit) {
				break Loop;
			}

			System.out.printf(totalStones + " stones left:");
			for (int i = 0; i <= totalStones - 1; i++) {
				System.out.printf(" *");
			}
			System.out.println('\n' + playerName2 + "'s turn - remove how many?");
			stonesTaken = setRemoveStone();

			if (stonesTaken <= totalStones || stonesTaken > lowerLimit) {
				totalStones = totalStones - stonesTaken;
				turn = 2;
			}
			if (totalStones <= lowerLimit) {
				break Loop;
			}
		}
		return totalStones;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Nim");
		Nimsys play = new Nimsys();
		NimPlayer player1 = new NimPlayer();
		NimPlayer player2 = new NimPlayer();
		play.setPlayerName();
		player1.PlayerName(play.playerName1);
		player1.getPlayerName();
		player1.RemoveStones(play.stonesTaken);
		player1.getRemoveStones();
		player2.PlayerName(play.playerName2);
		player2.getPlayerName();
		player2.RemoveStones(play.stonesTaken);
		player2.getRemoveStones();

		// whether to play again
		do {
			play.setLimitTaken();
			play.setTotalStones();
			play.process();
			play.checkResult();
			System.out.printf('\n' + "Do you want to play again (Y/N):");
			play.answer = play.in.next();
		} while (play.answer.equals("Y"));
	}

}
