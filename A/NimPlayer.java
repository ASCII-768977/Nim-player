
/* 
    COMP90041 Project1
    Student:Yuming Lin
    Login id:YUMINGL
    student num:883717
    email address:yumingl@student.unimelb.edu.au
    Semester 1, 2018, week4
*/

public class NimPlayer {
	public String playerName;
	public int stonesTaken;

	// store name and removestones
	public void PlayerName(String playerName) {
		this.playerName = new String(playerName);
	}

	public void RemoveStones(int removedNumber) {
		this.stonesTaken = removedNumber;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getRemoveStones() {
		return stonesTaken;
	}

}
