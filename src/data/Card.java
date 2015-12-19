package data;

public class Card {
	public Colors color;
	public Types type;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Card)){
			return false;
		}
		return color.equals(((Card) obj).color) && type.equals(((Card) obj).type);
	}
	
	@Override
	public String toString() {
		return type.name() + " de " + color.name();
	}
}
