/*
 * In our version of the game the Solution class holds two primary purposes. First to hold
 * the final answer to the game. The player that correctly guesses that solution will be the
 * winner.
 * The second purpose is to be used as a container for a suggestion. Any time a player wants to
 * either take a guess to the solution or to make a suggestion, the Solution class will act
 * as a container for the three cards the player is guessing with. The attributes are kept
 * public so that other players can check the contents of a solution for the purpose of 
 * disproving it.
 */
package clueGame;

public class Solution {
	public String person;
	public String weapon;
	public String room;
	public Card personCard;
	public Card weaponCard;
	public Card roomCard;
	
	
	//constructor that takes in strings as the solution <-- consider deprecation
	public Solution(String p, String w, String r) {
		person = p;
		weapon = w;
		room = r;
	}
	
	//constructor that accepts card objects
	public Solution(Card p, Card w, Card r) {
		personCard = p;
		person = personCard.name;
		weaponCard = w;
		weapon = weaponCard.name;
		roomCard = r;
		room = roomCard.name;
	}

	//return formatted string for printing
	@Override
	public String toString() {
		return "Solution [person=" + person + ", weapon=" + weapon + ", room="
				+ room + ", personCard=" + personCard + ", weaponCard="
				+ weaponCard + ", roomCard=" + roomCard + "]";
	}

	
	/*
	 * hashCode and equals functions overwritten for use in data structures and testing
	 * equality
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result
				+ ((personCard == null) ? 0 : personCard.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result
				+ ((roomCard == null) ? 0 : roomCard.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
		result = prime * result
				+ ((weaponCard == null) ? 0 : weaponCard.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution other = (Solution) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (personCard == null) {
			if (other.personCard != null)
				return false;
		} else if (!personCard.equals(other.personCard))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (roomCard == null) {
			if (other.roomCard != null)
				return false;
		} else if (!roomCard.equals(other.roomCard))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		if (weaponCard == null) {
			if (other.weaponCard != null)
				return false;
		} else if (!weaponCard.equals(other.weaponCard))
			return false;
		return true;
	}

	//default constructor for empty solution
	public Solution() {
		person = weapon = room = null;
	}

}
