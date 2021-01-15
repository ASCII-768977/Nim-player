
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week12
    
    This class contains the main play method.
*/

public class NimGame {

	private int limitTaken, totalStones;
	// turn is used to determine who's turn.
	int turn = 2;
	NimPlayer player = new NimHumanPlayer();
	NimPlayer playerAI = new NimAIPlayer();

	// check who wins by using turn number
	public void checkResult(int location1, int location2) {
		/*
		 * use turn's number to judge who is the winner.Then played number
		 * pluses one.
		 */
		if (turn % Nimsys.TWO != Nimsys.ZERO) {
			System.out.println("Game Over");
			System.out.println(
					Nimsys.users[location2].getFamilyName() + " " 
								+ Nimsys.users[location2].getGivenName() + " wins!");
			Nimsys.users[location1]
					.setGamesPlayed(Nimsys.users[location1].getGamesPlayed() + Nimsys.ONE);
			Nimsys.users[location2]
					.setGamesPlayed(Nimsys.users[location2].getGamesPlayed() + Nimsys.ONE);
			Nimsys.users[location2].setGamesWon(Nimsys.users[location2].getGamesWon() 
							+ Nimsys.ONE);
			turn = Nimsys.TWO;
		} else {
			System.out.println("Game Over");
			System.out.println(
					Nimsys.users[location1].getFamilyName() + " " 
								+ Nimsys.users[location1].getGivenName() + " wins!");
			Nimsys.users[location1]
					.setGamesPlayed(Nimsys.users[location1].getGamesPlayed() + Nimsys.ONE);
			Nimsys.users[location2]
					.setGamesPlayed(Nimsys.users[location2].getGamesPlayed() + Nimsys.ONE);
			Nimsys.users[location1].setGamesWon(Nimsys.users[location1].getGamesWon() 
							+ Nimsys.ONE);
			turn = Nimsys.TWO;
		}
	}

	public void turn(int totalStones, int limitTaken, int location1, int location2) {
		System.out.println("Initial stone count: " + totalStones);
		System.out.println("Maximum stone removal: " + limitTaken);
		System.out.println(
				"Player 1: " + Nimsys.users[location1].getFamilyName() + " " 
							+ Nimsys.users[location1].getGivenName());
		System.out.println(
				"Player 2: " + Nimsys.users[location2].getFamilyName() + " " 
							+ Nimsys.users[location2].getGivenName());
		System.out.print('\n');
		// if totalStones is not zero then begin.
		while (totalStones != Nimsys.ZERO) {
			totalStones = process(totalStones, limitTaken, location1);
			if (totalStones != Nimsys.ZERO) {
				totalStones = process(totalStones, limitTaken, location2);
			}
		}
	}

	// main playing method
	public int process(int totalStones, int limitTaken, int location) {
		// print stones number
		System.out.printf(totalStones + " stones left:");
		for (int i = Nimsys.ZERO; i <= totalStones - Nimsys.ONE; i++) {
			System.out.printf(" *");
		}
		int stonesTaken;
		// player's turn. check if the player is human or AI.
		if (Nimsys.users[location].getBehaviour().equals("Human")) {
			System.out.println('\n' + Nimsys.users[location].getFamilyName() 
							+ "'s turn - remove how many?");
			stonesTaken = player.setStonesTaken(location, totalStones, limitTaken);
		} else {
			System.out.println('\n' + Nimsys.users[location].getFamilyName() 
							+ "'s turn - remove how many?");
			stonesTaken = playerAI.setStonesTaken(location, totalStones, limitTaken);
		}

		// check wrong input number.such as "0","-1".or stonesTaken is more than
		// totalStones or limitTaken.
		while ((stonesTaken > limitTaken && totalStones >= limitTaken)
				|| (stonesTaken > totalStones && totalStones < limitTaken) 
				|| stonesTaken <= Nimsys.ZERO) {
			try {
				if (stonesTaken > totalStones || stonesTaken > limitTaken 
								|| stonesTaken <= Nimsys.ZERO) {
					throw new InvalidMoveException();
				}
			} catch (InvalidMoveException e) {
				System.out.println();
				if (totalStones >= limitTaken) {
					System.out.println(e.getMessage() + " You must remove between 1 and " 
								+ limitTaken + " stones.");
					System.out.println();
					System.out.printf(totalStones + " stones left:");
					for (int i = Nimsys.ZERO; i <= totalStones - Nimsys.ONE; i++) {
						System.out.printf(" *");
					}
					// get how many stones are taken
					System.out.println('\n' + Nimsys.users[location].getFamilyName() 
									+ "'s turn - remove how many?");
					stonesTaken = player.setStonesTaken(location, totalStones, limitTaken);
				} else if (totalStones < limitTaken) {
					System.out.println(e.getMessage() + " You must remove between 1 and " 
								+ totalStones + " stones.");
					System.out.println();
					System.out.printf(totalStones + " stones left:");
					for (int i = Nimsys.ZERO; i <= totalStones - Nimsys.ONE; i++) {
						System.out.printf(" *");
					}
					// get how many stones are taken
					System.out.println('\n' + Nimsys.users[location].getFamilyName() 
									+ "'s turn - remove how many?");
					stonesTaken = player.setStonesTaken(location, totalStones, limitTaken);
				}
			}
		}

		System.out.print('\n');
		// calculate the remaining stones
		if (Nimsys.users[location].getBehaviour().equals("Human")) {
			// Calculate the remaining stones.
			totalStones = player.removeStones(totalStones, stonesTaken);
		} else {
			totalStones = playerAI.removeStones(totalStones, stonesTaken);
		}
		turn = turn + Nimsys.ONE;
		return totalStones;
	}

	public void playerAI(int location) {
		playerAI = Nimsys.users[location];
	}

	public void player(int location) {
		player = Nimsys.users[location];
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