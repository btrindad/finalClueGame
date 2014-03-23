package clueGame;

public class Solution {
	public String person;
	public String weapon;
	public String room;
	public Card personCard;
	public Card weaponCard;
	public Card roomCard;
	
	
	public Solution(String p, String w, String r) {
		person = p;
		weapon = w;
		room = r;
	}
	
	public Solution(Card p, Card w, Card r) {
		personCard = p;
		person = personCard.name;
		weaponCard = w;
		weapon = weaponCard.name;
		roomCard = r;
		room = roomCard.name;
	}

	@Override
	public String toString() {
		return "Solution [person=" + person + ", weapon=" + weapon + ", room="
				+ room + ", personCard=" + personCard + ", weaponCard="
				+ weaponCard + ", roomCard=" + roomCard + "]";
	}

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

	public Solution() {
		person = weapon = room = null;
	}

}
