package clueGame;

public class Card {
	// Both public for now
	public String name;
	public CardType cardType;

	public Card(String name, String type) {
		this.name = name.toUpperCase();
		type = type.toUpperCase();

		switch (type) {
		case "ROOM":
			cardType = CardType.ROOM;
			break;
		case "PERSON":
			cardType = CardType.PERSON;
			break;
		case "WEAPON":
			cardType = CardType.WEAPON;
			break;
		}
	}
	
	public Card(String name, CardType type) {
		this.name = name.toUpperCase();
		cardType = type;
	}

	public Card() {
		name = null;
		cardType = null;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Card))
			return false;
		Card other = (Card) obj;
		if (cardType != other.cardType)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}

}