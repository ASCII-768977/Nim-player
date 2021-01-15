
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week4
    
    This class saves the information of players.
*/

public class NimPlayer {

	private String userName, givenName, familyName;
	private int numberOfGamesPlayed = 0, numberOfGamesWon = 0;
	private double winrate = 0;

	public void player(String userName, String givenName, String familyName) {
		this.setUserName(userName);
		this.setGivenName(givenName);
		this.setFamilyName(familyName);
	}

	public int setStonesTaken() {
		int stonesTaken = Nimsys.in.nextInt();
		return stonesTaken;
	}

	public int removeStones(int restStones, int stonesTaken) {
		return restStones - stonesTaken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public int getNumberOfGamesWon() {
		return numberOfGamesWon;
	}

	public void setNumberOfGamesWon(int numberOfGamesWon) {
		this.numberOfGamesWon = numberOfGamesWon;
	}

	public int getNumberOfGamesPlayed() {
		return numberOfGamesPlayed;
	}

	public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
		this.numberOfGamesPlayed = numberOfGamesPlayed;
	}

	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}
}