
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week12
    
    This class saves the information of players.
*/

import java.io.Serializable;

public abstract class NimPlayer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName, givenName, familyName;
	private int gamesPlayed = 0, gamesWon = 0;
	private double winrate = 0;
	private String behaviour;

	public void player(String userName, String givenName, String familyName) {
		this.setUserName(userName);
		this.setGivenName(givenName);
		this.setFamilyName(familyName);
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

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int GamesWon) {
		this.gamesWon = GamesWon;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int GamesPlayed) {
		this.gamesPlayed = GamesPlayed;
	}

	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public abstract int setStonesTaken(int location, int restStones, int limitTaken);

	public abstract int removeStones(int restStones, int stonesTaken);

}