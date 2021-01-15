
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week4
    
    This class contains the main play method.
*/

public class NimGame {
	
	private int limitTaken, totalStones;
	// turn is used to determine who's turn.
	int turn = 0;
	// Can take the lower limit of stone
	private final static int LOWERLIMIT = 0;
	NimPlayer player1 = new NimPlayer();
	NimPlayer player2 = new NimPlayer();

	// check who wins by using turn number
	public void checkResult(int location1, int location2) {
		/*
		 * use turn's number to judge who is the winner.Then played number
		 * pluses one.
		 */
		if (turn == 1) {
			System.out.println("Game Over");
			System.out.println(player2.getFamilyName() + " " + player2.getGivenName() + " wins!");
			String format = Nimsys.in.nextLine();
			player1.setNumberOfGamesPlayed(player1.getNumberOfGamesPlayed() + 1);
			player2.setNumberOfGamesPlayed(player2.getNumberOfGamesPlayed() + 1);
			player2.setNumberOfGamesWon(player2.getNumberOfGamesWon() + 1);
		} else if (turn == 2) {
			System.out.println("Game Over");
			System.out.println(player1.getFamilyName() + " " + player1.getGivenName() + " wins!");
			String format = Nimsys.in.nextLine();
			player1.setNumberOfGamesPlayed(player1.getNumberOfGamesPlayed() + 1);
			player2.setNumberOfGamesPlayed(player2.getNumberOfGamesPlayed() + 1);
			player1.setNumberOfGamesWon(player1.getNumberOfGamesWon() + 1);
		}
	}

	// main playing method
	public void process(int totalStones, int limitTaken, int location1, int location2) {
		player1(location1);
		player2(location2);
		System.out.println("Initial stone count: " + totalStones);
		System.out.println("Maximum stone removal: " + limitTaken);
		System.out.println("Player 1: " + player1.getFamilyName() + " " + player1.getGivenName());
		System.out.println("Player 2: " + player2.getFamilyName() + " " + player2.getGivenName());
		System.out.print('\n');
		Loop: while (totalStones != LOWERLIMIT) {

			// print stones number
			System.out.printf(totalStones + " stones left:");
			for (int i = 0; i <= totalStones - 1; i++) {
				System.out.printf(" *");
			}

			// player1's turn
			System.out.println('\n' + player1.getFamilyName() + "'s turn - remove how many?");
			int stonesTaken1 = player1.setStonesTaken();

			// check wrong input.if number of stones taken is more than limit.
			if (stonesTaken1 > totalStones || stonesTaken1 > limitTaken || stonesTaken1 <= LOWERLIMIT) {
				if (totalStones >= limitTaken) {
					do {
						System.out.println();
						System.out.println("Invalid move. You must remove between 1 and " + limitTaken + " stones.");
						System.out.println();
						System.out.printf(totalStones + " stones left:");
						for (int i = 0; i <= totalStones - 1; i++) {
							System.out.printf(" *");
						}
						System.out
								.println('\n' + Nimsys.users[location1].getFamilyName() + "'s turn - remove how many?");
						stonesTaken1 = player1.setStonesTaken();
					} while (stonesTaken1 > limitTaken && totalStones >= limitTaken || stonesTaken1 <= LOWERLIMIT);
				}

				// check wrong input.if number of stones taken is more than
				// rest.
				else if (totalStones < limitTaken) {
					do {
						System.out.println();
						System.out.println("Invalid move. You must remove between 1 and " + totalStones + " stones.");
						System.out.println();
						System.out.printf(totalStones + " stones left:");
						for (int i = 0; i <= totalStones - 1; i++) {
							System.out.printf(" *");
						}
						System.out
								.println('\n' + Nimsys.users[location1].getFamilyName() + "'s turn - remove how many?");
						stonesTaken1 = player1.setStonesTaken();
					} while (stonesTaken1 > totalStones && totalStones < limitTaken || stonesTaken1 <= LOWERLIMIT);
				}
			}
			System.out.print('\n');

			// Calculate the remaining stones.
			totalStones = player1.removeStones(totalStones, stonesTaken1);
			turn = 1;

			// if stones are less than 0 then jump out of the loop
			if (totalStones <= LOWERLIMIT) {
				break Loop;
			}
			System.out.printf(totalStones + " stones left:");
			for (int i = 0; i <= totalStones - 1; i++) {
				System.out.printf(" *");
			}

			// player2's turn
			System.out.println('\n' + player2.getFamilyName() + "'s turn - remove how many?");
			int stonesTaken2 = player2.setStonesTaken();

			// check wrong input.if number of stones taken is more than limit.
			if (stonesTaken2 > totalStones || stonesTaken2 > limitTaken || stonesTaken2 <= LOWERLIMIT) {
				if (totalStones >= limitTaken) {
					do {
						System.out.println();
						System.out.println("Invalid move. You must remove between 1 and " + limitTaken + " stones.");
						System.out.println();
						System.out.printf(totalStones + " stones left:");
						for (int i = 0; i <= totalStones - 1; i++) {
							System.out.printf(" *");
						}
						System.out
								.println('\n' + Nimsys.users[location2].getFamilyName() + "'s turn - remove how many?");
						stonesTaken2 = player2.setStonesTaken();
					} while (stonesTaken2 > limitTaken && totalStones >= limitTaken || stonesTaken2 <= LOWERLIMIT);
				}

				// check wrong input.if number of stones taken is more than
				// rest.
				else if (totalStones < limitTaken) {
					do {
						System.out.println();
						System.out.println("Invalid move. You must remove between 1 and " + totalStones + " stones.");
						System.out.println();
						System.out.printf(totalStones + " stones left:");
						for (int i = 0; i <= totalStones - 1; i++) {
							System.out.printf(" *");
						}
						System.out
								.println('\n' + Nimsys.users[location2].getFamilyName() + "'s turn - remove how many?");
						stonesTaken2 = player2.setStonesTaken();
					} while (stonesTaken2 > totalStones && totalStones < limitTaken || stonesTaken2 <= LOWERLIMIT);
				}
			}
			System.out.print('\n');

			// Calculate the remaining stones.
			totalStones = player2.removeStones(totalStones, stonesTaken2);
			turn = 2;

			// if stones are less than 0 then jump out of the loop
			if (totalStones <= LOWERLIMIT) {
				break Loop;
			}
		}
	}

	// information of two players.
	public void player1(int location) {
		player1 = Nimsys.users[location];
	}

	public void player2(int location) {
		player2 = Nimsys.users[location];
	}

	public void stones(int totalStones, int limitTaken) {
		this.setLimitTaken(limitTaken);
		this.setTotalStones(totalStones);
	}

	public int getLimitTaken() {
		return limitTaken;
	}

	public void setLimitTaken(int limitTaken) {
		this.limitTaken = limitTaken;
	}

	public int getTotalStones() {
		return totalStones;
	}

	public void setTotalStones(int totalStones) {
		this.totalStones = totalStones;
	}
}