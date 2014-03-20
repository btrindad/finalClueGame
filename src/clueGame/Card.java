package clueGame;

public class Card {
	// Both public for now
	public String name;
	public CardType cardType;

	public Card(String name, String type) {
		this.name = name.toUpperCase();
		type.toUpperCase();

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

	public Card() {
		name = null;
		cardType = null;
	}
}