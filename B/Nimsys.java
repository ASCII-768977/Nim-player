
/* 
   COMP90041 Project1
   Student:Yuming Lin
   Login id:YUMINGL
   student num:883717
   email address:yumingl@student.unimelb.edu.au
   Semester 1, 2018, week4
   
   This class contains the control of different commands.
*/

import java.util.*;

public class Nimsys {
	
	static Scanner in = new Scanner(System.in);
	NimGame NG = new NimGame();
	public static NimPlayer[] users = new NimPlayer[100];
	private int index = 0;

	// The console judges the input instructions.
	private void control() {
		// The judgment of the input string.
		String input = in.nextLine();
		StringTokenizer st = new StringTokenizer(input, " |,");
		String firstCommand = st.nextToken();

		// judge the input commands.
		if (firstCommand.equals("addplayer")) {
			String name = st.nextToken();
			String givenName = st.nextToken();
			String familyName = st.nextToken();
			addPlayer(name, givenName, familyName);
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("removeplayer")) {
			if (st.hasMoreElements()) {
				String name = st.nextToken();
				removePlayer(name);
			} else {
				removePlayer(" ");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("editplayer")) {
			if (st.hasMoreElements()) {
				String name = st.nextToken();
				String givenName = st.nextToken();
				String familyName = st.nextToken();
				editPlayer(name, givenName, familyName);
			} else {
				System.out.println("Incorrect number of arguments supplied to command.");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("resetstats")) {
			if (st.hasMoreElements()) {
				String name = st.nextToken();
				resetStats(name);
			} else {
				resetStats(" ");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("displayplayer")) {
			if (st.hasMoreElements()) {
				String name = st.nextToken();
				displayPlayer(name);
			} else {
				displayPlayer(" ");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("rankings")) {
			if (st.hasMoreElements()) {
				String sequence = st.nextToken();
				rankings(sequence);
			} else {
				rankings(" ");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("startgame")) {
			if (st.hasMoreElements()) {
				String number1 = st.nextToken();
				String number2 = st.nextToken();
				String playerName1 = st.nextToken();
				String playerName2 = st.nextToken();
				int totalInput = Integer.parseInt(number1);
				int upperInput = Integer.parseInt(number2);
				int location1 = searchPlayer(playerName1);
				int location2 = searchPlayer(playerName2);
				if (location1 == -1 || location2 == -1) {
					System.out.println("One of the players does not exist.");
				} else if (location1 == location2) {
					System.out.println("The game needs two different players.");
				} else {
					startGame(totalInput, upperInput, location1, location2);
				}
			} else {
				System.out.println("Incorrect number of arguments supplied to command.");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("exit")) {
			exitGame();
		} else {
			System.out.println("'" + firstCommand + "' is not a valid command." + "\n");
			System.out.print("$");
			control();
		}
	}

	// Find out where the player is in the array.
	public int searchPlayer(String name) {
		/*
		 * number location means the index in the array to locate the user's
		 * position.
		 */
		int location = -1;
		for (int search = 0; search < index; search++) {
			if (users[search].getUserName().equals(name)) {
				location = search;
			}
		}
		return location;
	}

	// Add a player information.
	public void addPlayer(String name, String givenName, String familyName) {
		// search the name whether exist. If dosen's exist, then add.
		if (searchPlayer(name) != -1) {
			System.out.println("The player already exists.");
		} else {
			users[index] = new NimPlayer();
			users[index].setUserName(name);
			users[index].setGivenName(givenName);
			users[index].setFamilyName(familyName);
			users[index].setNumberOfGamesPlayed(0);
			users[index].setNumberOfGamesWon(0);
			index = index + 1;
		}
	}

	// Remove all players or specify players.
	public void removePlayer(String name) {
		// remove all the players.
		if (name.equals(" ")) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String confirm = in.nextLine();
			if (confirm.equals("y")) {
				for (int location = 0; location < index; location++) {
					users[location].setUserName("");
					users[location].setGivenName("");
					users[location].setFamilyName("");
					users[location].setNumberOfGamesPlayed(0);
					users[location].setNumberOfGamesWon(0);
				}
				index = 0;
			}
		} else {
			int location = searchPlayer(name);
			if (location == -1) {
				System.out.println("The player does not exist.");
			} else if (location == index | index == 1) {
				/*
				 * If the player is the last one in the array, initialize it
				 * directly.
				 */
				users[location].setUserName("");
				users[location].setGivenName("");
				users[location].setFamilyName("");
				users[location].setNumberOfGamesPlayed(0);
				users[location].setNumberOfGamesWon(0);
				index = index - 1;
			} else {
				/*
				 * If the player is not the last one, overwrite this with the
				 * last one.
				 */
				users[location].setUserName(users[index - 1].getUserName());
				users[location].setGivenName(users[index - 1].getGivenName());
				users[location].setFamilyName(users[index - 1].getFamilyName());
				users[location].setNumberOfGamesPlayed(users[index - 1].getNumberOfGamesPlayed());
				users[location].setNumberOfGamesWon(users[index - 1].getNumberOfGamesWon());
				index = index - 1;
			}
		}
	}

	// Edit a player's information.
	public void editPlayer(String name, String givenName, String familyName) {
		
		// search the name whether exist. If exist, then edit.
		int location = searchPlayer(name);
		if (location == -1) {
			System.out.println("The player does not exist.");
		} else {
			users[location].setGivenName(givenName);
			users[location].setFamilyName(familyName);
		}

	}

	// Reset all player data or specified player data.
	public void resetStats(String name) {
		// reset all user's statistics.
		if (name.equals(" ")) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String check = in.nextLine();
			if (check.equals("y")) {
				for (int location = 0; location < index; location++) {
					users[location].setNumberOfGamesPlayed(0);
					users[location].setNumberOfGamesWon(0);
				}
			} else {
				// search the name whether exist. If exist, then reset.
				int location = searchPlayer(name);
				if (location == -1) {
					System.out.println("The player does not exist.");
				} else if (location == index | index == 1) {
					users[location].setNumberOfGamesPlayed(0);
					users[location].setNumberOfGamesWon(0);
				}
			}
		}
	}

	// Show all player information or specified player information.
	public void displayPlayer(String name) {
		int location;
		if (name.equals(" ")) {
			String[] display = new String[100];
			// initialize the array.
			for (int initialization = 0; initialization < display.length; initialization++) {
				display[initialization] = "0";
			}

			// put name in it.
			for (int rank = 0; rank < index; rank++) {
				display[rank] = users[rank].getUserName();
			}

			// sort the array
			Arrays.sort(display);

			// display the users.
			for (int order = 0; order < 100; order++) {
				location = searchPlayer(display[order]);
				if (location == -1) {

				} else {
					System.out.println(users[location].getUserName() + "," + users[location].getFamilyName() + ","
							+ users[location].getGivenName() + "," + users[location].getNumberOfGamesPlayed()
							+ " games," + users[location].getNumberOfGamesWon() + " wins");
				}
			}
		} else {
			// search the name whether exist. If exist, then display.
			location = searchPlayer(name);
			if (location == -1) {
				System.out.println("The player does not exist.");
			} else
				System.out.println(users[location].getUserName() + "," + users[location].getFamilyName() + ","
						+ users[location].getGivenName() + "," + users[location].getNumberOfGamesPlayed() + " games,"
						+ users[location].getNumberOfGamesWon() + " wins");
		}
	}

	// Use default sorting or ascending or descending order.
	public void rankings(String sequence) {
		NimPlayer temp = new NimPlayer();
		users[index] = new NimPlayer();
		/*
		 * calculate the winrate.If number of games played is 0, then win rate
		 * is 0.
		 */
		for (int count = 0; count < index; count++) {
			if (users[count].getNumberOfGamesPlayed() == 0) {
				users[count].setWinrate(0);
			} else {
				users[count].setWinrate((double) users[count].getNumberOfGamesWon()
						/ (double) users[count].getNumberOfGamesPlayed() * 100);
			}
		}

		// use the default order which is descending order.
		if (sequence.equals(" ")) {
			for (int i = 0; i < index - 1; i++) {
				for (int j = i + 1; j < index; j++) {
					if (users[i].getWinrate() < users[j].getWinrate()) {
						// if the first winrate is smaller than the second one,
						// then exchange.
						temp = users[i];
						users[i] = users[j];
						users[j] = temp;
					} else if (users[i].getWinrate() == users[j].getWinrate()) {
						// if the winrate are same, then compare the username.
						int k = users[i].getUserName().compareTo(users[j].getUserName());
						if (k > 0) {
							temp = users[i];
							users[i] = users[j];
							users[j] = temp;
						}
					}
					for (int display = 0; display < index; display++) {
						String str = Math.round(users[display].getWinrate()) + "%";
						System.out.printf("%-4s %s %02d%s%s %s %s", str, "|", users[display].getNumberOfGamesPlayed(),
								" games ", "|", users[display].getFamilyName(), users[display].getGivenName());
						System.out.println();
					}
				}
			}

			// use descending order.
		} else if (sequence.equals("desc")) {
			for (int i = 0; i < index - 1; i++) {
				for (int j = i + 1; j < index; j++) {
					if (users[i].getWinrate() < users[j].getWinrate()) {
						// if the first winrate is smaller than the second one,
						// then exchange.
						temp = users[i];
						users[i] = users[j];
						users[j] = temp;
					} else if (users[i].getWinrate() == users[j].getWinrate()) {
						// if the winrate are same, then compare the username.
						int k = users[i].getUserName().compareTo(users[j].getUserName());
						if (k > 0) {
							temp = users[i];
							users[i] = users[j];
							users[j] = temp;
						}
					}
					for (int display = 0; display < index; display++) {
						String str = Math.round(users[display].getWinrate()) + "%";
						System.out.printf("%-4s %s %02d%s%s %s %s", str, "|", users[display].getNumberOfGamesPlayed(),
								" games ", "|", users[display].getFamilyName(), users[display].getGivenName());
						System.out.println();
					}
				}
			}

			// use ascending order
		} else if (sequence.equals("asc")) {
			for (int i = 0; i < index - 1; i++) {
				for (int j = i + 1; j < index; j++) {
					if (users[i].getWinrate() > users[j].getWinrate()) {
						// if the first winrate is bigger than the second one,
						// then exchange.
						temp = users[i];
						users[i] = users[j];
						users[j] = temp;
					} else if (users[i].getWinrate() == users[j].getWinrate()) {
						// if the winrate are same, then compare the username.
						int k = users[i].getUserName().compareTo(users[j].getUserName());
						if (k < 0) {
							temp = users[i];
							users[i] = users[j];
							users[j] = temp;
						}
					}
					for (int display = 0; display < index; display++) {
						String str = Math.round(users[display].getWinrate()) + "%";
						System.out.printf("%-4s %s %02d%s%s %s %s", str, "|", users[display].getNumberOfGamesPlayed(),
								" games ", "|", users[display].getFamilyName(), users[display].getGivenName());
						System.out.println();
					}
				}
			}
		}
	}

	// Exit the game.
	public void exitGame() {
		System.out.println();
		System.exit(0);
	}

	// Start the game.
	public void startGame(int total, int upper, int location1, int location2) {
		System.out.print("\n");
		NG.process(total, upper, location1, location2);
		NG.checkResult(location1, location2);
	}

	public static void main(String[] args) {
		Nimsys N = new Nimsys();
		System.out.println("Welcome to Nim");
		System.out.print("\n$");
		N.control();
	}
}
