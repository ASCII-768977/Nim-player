
/* 
   COMP90041 Project1
   Student:Yuming Lin
   Login id:YUMINGL
   student num:883717
   email address:yumingl@student.unimelb.edu.au
   Semester 1, 2018, week12
   
   This class contains the control of different commands.
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Nimsys {

	static Scanner in = new Scanner(System.in);
	NimGame nimgame = new NimGame();
	public static NimPlayer[] users = new NimPlayer[100];
	private int index = 0;
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int ONEHUNDRED = 100;
	public static final int NEGATIVEONE = -1;

	// The console judges the input instructions.
	private void control() {

		// The judgment of the input string.
		String input = in.nextLine();
		// if there is no input command.
		if (input.equals("")) {
			System.out.println("' ' is not a valid command." + "\n");
			System.out.print("$");
			control();
		}

		StringTokenizer st = new StringTokenizer(input, " |,");
		String firstCommand = st.nextToken();

		// judge the input commands.
		if (firstCommand.equals("addplayer")) {
			/*
			 * if input such as addplayer z. then throw an exception. if the
			 * input is addplayer a,s,d,f. then the user is a,s,d.
			 */
			try {
				if (st.countTokens() < THREE) {
					throw new InvalidArgumentsException();
				} else if (st.countTokens() > THREE) {
					String name = st.nextToken();
					String givenName = st.nextToken();
					String familyName = st.nextToken();
					addPlayer(name, givenName, familyName);
					System.out.print("\n" + "$");
					control();
				} else {
					String name = st.nextToken();
					String givenName = st.nextToken();
					String familyName = st.nextToken();
					addPlayer(name, givenName, familyName);
					System.out.print("\n" + "$");
					control();
				}
			} catch (InvalidArgumentsException e) {
				System.out.println(e.getMessage());
				System.out.print("\n" + "$");
				control();
			}
		} else if (firstCommand.equals("addaiplayer")) {
			/*
			 * if input such as "addplayer z". then throw an exception. if the
			 * input is "addplayer a,s,d,f". then the user is "a,s,d".
			 */
			try {
				if (st.countTokens() < THREE) {
					throw new InvalidArgumentsException();
				} else if (st.countTokens() > THREE) {
					String name = st.nextToken();
					String givenName = st.nextToken();
					String familyName = st.nextToken();
					addPlayer(name, givenName, familyName);
					System.out.print("\n" + "$");
					control();
				} else {
					String name = st.nextToken();
					String givenName = st.nextToken();
					String familyName = st.nextToken();
					addAIPlayer(name, givenName, familyName);
					System.out.print("\n" + "$");
					control();
				}
			} catch (InvalidArgumentsException e) {
				System.out.println(e.getMessage());
				System.out.print("\n" + "$");
				control();
			}
		} else if (firstCommand.equals("removeplayer")) {
			// if input is "removeplayer a,s,d". then execute "removeplayer a".
			if (st.hasMoreElements()) {
				if (st.countTokens() > ONE) {
					String name = st.nextToken();
					removePlayer(name);
				} else {
					String name = st.nextToken();
					removePlayer(name);
				}
			} else {
				removePlayer(" ");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("editplayer")) {
			try {
				if (st.countTokens() != THREE) {
					throw new InvalidArgumentsException();
				} else {
					if (st.hasMoreElements()) {
						String name = st.nextToken();
						String givenName = st.nextToken();
						String familyName = st.nextToken();
						editPlayer(name, givenName, familyName);
					}
					System.out.print("\n" + "$");
					control();
				}
			} catch (InvalidArgumentsException e) {
				System.out.println(e.getMessage());
				System.out.print("\n" + "$");
				control();
			}
		} else if (firstCommand.equals("resetstats")) {
			// if input is "resetstats a,s,d". then execute "resetstats a".
			if (st.hasMoreElements()) {
				if (st.countTokens() > ONE) {
					String name = st.nextToken();
					resetStats(name);
				} else {
					String name = st.nextToken();
					resetStats(name);
				}
			} else {
				resetStats(" ");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("displayplayer")) {
			/*
			 * if input is "displayplayer a,s,d". then execute "displayplayer a"
			 * .
			 */
			if (st.hasMoreElements()) {
				if (st.countTokens() > ONE) {
					String name = st.nextToken();
					displayPlayer(name);
				} else {
					String name = st.nextToken();
					displayPlayer(name);
				}
			} else {
				displayPlayer(" ");
			}
			System.out.print("\n" + "$");
			control();
		} else if (firstCommand.equals("rankings")) {
			// check wrong input.
			try {
				if (st.countTokens() > ONE) {
					throw new InvalidArgumentsException();
				} else if (st.countTokens() == ONE) {
					String sequence = st.nextToken();
					rankings(sequence);
				} else {
					rankings(" ");
				}
				System.out.print("\n" + "$");
				control();
			} catch (InvalidArgumentsException e) {
				System.out.println(e.getMessage());
				System.out.print("\n" + "$");
				control();
			}
		} else if (firstCommand.equals("startgame")) {
			// check wrong input of arguments such as "startgame 5,3,a,z,z"
			try {
				if (st.countTokens() != FOUR) {
					throw new InvalidArgumentsException();
				} else if (st.countTokens() == FOUR) {
					String number1 = st.nextToken();
					String number2 = st.nextToken();
					String playerName1 = st.nextToken();
					String playerName2 = st.nextToken();
					int totalInput = Integer.parseInt(number1);
					int upperInput = Integer.parseInt(number2);
					int location1 = searchPlayer(playerName1);
					int location2 = searchPlayer(playerName2);
					/*
					 * incorrect input of totalstones and upperstones.such as
					 * "startgame a,s,d,f","startgame 3,10,a,z",
					 * "startgame -3,-4,a,z"
					 */
					if (totalInput < upperInput) {
						throw new InvalidArgumentsException();
					} else if (totalInput <= 0 || upperInput <= 0) {
						throw new InvalidArgumentsException();
					}
					// if the player dose not exist or two players are same.
					if (location1 == NEGATIVEONE || location2 == NEGATIVEONE) {
						System.out.println("One of the players does not exist.");
					} else if (location1 == location2) {
						System.out.println("The game needs two different players.");
					} else {
						startGame(totalInput, upperInput, location1, location2);
						System.out.print("\n" + "$");
						control();
					}
				}
			} catch (InvalidArgumentsException e) {
				System.out.println(e.getMessage());
				System.out.print("\n" + "$");
				control();
			} catch (Exception e) {
				System.out.println("Incorrect number of arguments supplied to command.");
				System.out.print("\n" + "$");
				control();
			}
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
		int location = NEGATIVEONE;
		for (int search = ZERO; search < index; search++) {
			if (users[search].getUserName().equals(name)) {
				location = search;
			}
		}
		return location;
	}

	// Add a player information.
	public void addPlayer(String name, String givenName, String familyName) {
		// search the name whether exist. If dosen's exist, then add.
		if (searchPlayer(name) != NEGATIVEONE) {
			System.out.println("The player already exists.");
		} else {
			users[index] = new NimHumanPlayer();
			users[index].setUserName(name);
			users[index].setGivenName(givenName);
			users[index].setFamilyName(familyName);
			users[index].setGamesPlayed(ZERO);
			users[index].setGamesWon(ZERO);
			users[index].setBehaviour("Human");
			index = index + ONE;
		}
	}

	// Add a player information.
	public void addAIPlayer(String name, String givenName, String familyName) {
		// search the name whether exist. If dosen's exist, then add.
		if (searchPlayer(name) != NEGATIVEONE) {
			System.out.println("The player already exists.");
		} else {
			users[index] = new NimAIPlayer();
			users[index].setUserName(name);
			users[index].setGivenName(givenName);
			users[index].setFamilyName(familyName);
			users[index].setGamesPlayed(ZERO);
			users[index].setGamesWon(ZERO);
			users[index].setBehaviour("AI");
			index = index + ONE;
		}
	}

	// Remove all players or specify players.
	public void removePlayer(String name) {
		// remove all the players.
		if (name.equals(" ")) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String confirm = in.nextLine();
			if (confirm.equals("y")) {
				for (int location = ZERO; location < index; location++) {
					users[location].setUserName("");
					users[location].setGivenName("");
					users[location].setFamilyName("");
					users[location].setGamesPlayed(ZERO);
					users[location].setGamesWon(ZERO);
				}
				index = ZERO;
			}
		} else {
			int location = searchPlayer(name);
			if (location == NEGATIVEONE) {
				System.out.println("The player does not exist.");
			} else if (location == index | index == ONE) {
				/*
				 * If the player is the last one in the array, initialize it
				 * directly.
				 */
				users[location].setUserName("");
				users[location].setGivenName("");
				users[location].setFamilyName("");
				users[location].setGamesPlayed(ZERO);
				users[location].setGamesWon(ZERO);
				index = index - ONE;
			} else {
				/*
				 * If the player is not the last one, overwrite this with the
				 * last one.
				 */
				users[location].setUserName(users[index - ONE].getUserName());
				users[location].setGivenName(users[index - ONE].getGivenName());
				users[location].setFamilyName(users[index - ONE].getFamilyName());
				users[location].setGamesPlayed(users[index - ONE].getGamesPlayed());
				users[location].setGamesWon(users[index - ONE].getGamesWon());
				index = index - ONE;
			}
		}
	}

	// Edit a player's information.
	public void editPlayer(String name, String givenName, String familyName) {
		// search the name whether exist. If exist, then edit.
		int location = searchPlayer(name);
		if (location == NEGATIVEONE) {
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
				for (int location = ZERO; location < index; location++) {
					users[location].setGamesPlayed(ZERO);
					users[location].setGamesWon(ZERO);
				}
			}
		} else {
			// search the name whether exist. If exist, then reset.
			int location = searchPlayer(name);
			if (location == NEGATIVEONE) {
				System.out.println("The player does not exist.");
			} else {
				users[location].setGamesPlayed(ZERO);
				users[location].setGamesWon(ZERO);
			}
		}
	}

	// Show all player information or specified player information.
	public void displayPlayer(String name) {
		int location;
		if (name.equals(" ")) {
			String[] display = new String[100];
			// initialize the array.
			for (int initial = ZERO; initial < display.length; initial++) {
				display[initial] = "0";
			}

			// put name in it.
			for (int rank = ZERO; rank < index; rank++) {
				display[rank] = users[rank].getUserName();
			}

			// sort the array
			Arrays.sort(display);

			// display the users.
			for (int order = ZERO; order < ONEHUNDRED; order++) {
				location = searchPlayer(display[order]);
				if (location == NEGATIVEONE) {

				} else {
					System.out.println(users[location].getUserName() + "," 
							+ users[location].getFamilyName() + ","
							+ users[location].getGivenName() + "," 
							+ users[location].getGamesPlayed() + " games,"
							+ users[location].getGamesWon() + " wins");
				}
			}
		} else {
			// search the name whether exist. If exist, then display.
			location = searchPlayer(name);
			if (location == NEGATIVEONE) {
				System.out.println("The player does not exist.");
			} else {
				System.out.println(users[location].getUserName() + "," 
						+ users[location].getFamilyName() + ","
						+ users[location].getGivenName() + "," 
						+ users[location].getGamesPlayed() + " games,"
						+ users[location].getGamesWon() + " wins");
			}
		}
	}

	// Use default sorting or ascending or descending order.
	public void rankings(String sequence) {
		NimPlayer temp;
		/*
		 * calculate the winrate.If number of games played is 0, then win rate
		 * is 0.
		 */
		for (int count = ZERO; count < index; count++) {
			if (users[count].getGamesPlayed() == ZERO) {
				users[count].setWinrate(ZERO);
			} else {
				users[count].setWinrate(
						(double) users[count].getGamesWon() / 
						(double) users[count].getGamesPlayed() * ONEHUNDRED);
			}
		}

		// use the default order which is descending order.
		if (sequence.equals(" ")) {
			for (int i = ZERO; i < index - ONE; i++) {
				for (int j = i + ONE; j < index; j++) {
					if (users[i].getWinrate() < users[j].getWinrate()) {
						// if the first winrate is smaller than the second one,
						// then exchange.
						temp = users[i];
						users[i] = users[j];
						users[j] = temp;
					} else if (users[i].getWinrate() == users[j].getWinrate()) {
						// if the winrate are same, then compare the username.
						int k = users[i].getUserName().compareTo(users[j].getUserName());
						if (k > ZERO) {
							temp = users[i];
							users[i] = users[j];
							users[j] = temp;
						}
					}
				}
			}

			// use descending order.
		} else if (sequence.equals("desc")) {
			for (int i = ZERO; i < index - ONE; i++) {
				for (int j = i + ONE; j < index; j++) {
					if (users[i].getWinrate() < users[j].getWinrate()) {
						// if the first winrate is smaller than the second one,
						// then exchange.
						temp = users[i];
						users[i] = users[j];
						users[j] = temp;
					} else if (users[i].getWinrate() == users[j].getWinrate()) {
						// if the winrate are same, then compare the username.
						int k = users[i].getUserName().compareTo(users[j].getUserName());
						if (k > ZERO) {
							temp = users[i];
							users[i] = users[j];
							users[j] = temp;
						}
					}
				}
			}

			// use ascending order
		} else if (sequence.equals("asc")) {
			for (int i = ZERO; i < index - ONE; i++) {
				for (int j = i + ONE; j < index; j++) {
					if (users[i].getWinrate() > users[j].getWinrate()) {
						// if the first winrate is bigger than the second one,
						// then exchange.
						temp = users[i];
						users[i] = users[j];
						users[j] = temp;
					} else if (users[i].getWinrate() == users[j].getWinrate()) {
						// if the winrate are same, then compare the username.
						int k = users[i].getUserName().compareTo(users[j].getUserName());
						if (k < ZERO) {
							temp = users[i];
							users[i] = users[j];
							users[j] = temp;
						}
					}
				}
			}
		} else {
			// input is not asc or desc.
			try {
				throw new InvalidArgumentsException();
			} catch (InvalidArgumentsException e) {
				System.out.println(e.getMessage());
				System.out.print("\n" + "$");
				control();
			}
		}
		for (int display = ZERO; display < index; display++) {
			String str = Math.round(users[display].getWinrate()) + "%";
			System.out.printf("%-4s %s %02d%s%s %s %s", str, "|", 
					users[display].getGamesPlayed(), " games ", "|",
					users[display].getFamilyName(), users[display].getGivenName());
			System.out.println();
		}
	}

	// Exit the game.
	public void exitGame() {
		write();
		System.out.println();
		System.exit(0);
	}

	// Start the game.
	public void startGame(int total, int upper, int location1, int location2) {
		System.out.print("\n");
		nimgame.turn(total, upper, location1, location2);
		nimgame.checkResult(location1, location2);
	}

	// read the file "players.dat".
	public void read() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("players.dat"));
			String temp = bufferedReader.readLine();
			if (temp != null) {
				index = Integer.parseInt(temp);
				for (int read = ZERO; read < index; read++) {
					String attribute = bufferedReader.readLine();
					if (attribute.equals("class NimHumanPlayer")) {
						users[read] = new NimHumanPlayer();
						users[read].setUserName(bufferedReader.readLine());
						users[read].setGivenName(bufferedReader.readLine());
						users[read].setFamilyName(bufferedReader.readLine());
						users[read].setGamesPlayed(Integer.parseInt(bufferedReader.readLine()));
						users[read].setGamesWon(Integer.parseInt(bufferedReader.readLine()));
						users[read].setBehaviour(bufferedReader.readLine());
					} else if (attribute.equals("class NimAIPlayer")) {
						users[read] = new NimAIPlayer();
						users[read].setUserName(bufferedReader.readLine());
						users[read].setGivenName(bufferedReader.readLine());
						users[read].setFamilyName(bufferedReader.readLine());
						users[read].setGamesPlayed(bufferedReader.read());
						users[read].setGamesWon(bufferedReader.read());
						users[read].setBehaviour(bufferedReader.readLine());
					}
				}
				bufferedReader.close();
			}
		} catch (IOException e) {
		}
	}

	// write the file "players.dat".
	public void write() {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileOutputStream("players.dat"));
		} catch (IOException e) {

		}
		printWriter.println(index);
		for (int write = ZERO; write < index; write++) {
			printWriter.println(users[write].getClass());
			printWriter.println(users[write].getUserName());
			printWriter.println(users[write].getGivenName());
			printWriter.println(users[write].getFamilyName());
			printWriter.println(users[write].getGamesPlayed());
			printWriter.println(users[write].getGamesWon());
			printWriter.println(users[write].getBehaviour());
		}
		printWriter.close();
	}

	public static void main(String[] args) {
		Nimsys Nimsys = new Nimsys();
		Nimsys.read();
		System.out.println("Welcome to Nim");
		System.out.print("\n$");
		Nimsys.control();
	}
}
