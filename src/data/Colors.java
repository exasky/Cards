package data;

public enum Colors {
	Trefle, Pique, Coeur, Carreau;
	
	public static int getPos(Colors color){
		Colors[] values = Colors.values();
		for(int i = 0; i< values.length; i++){
			if(values[i].equals(color)){
				return i;
			}
		}
		return -1;
	}
}
