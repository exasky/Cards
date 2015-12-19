package data;

public enum Types {
	As, Deux, Trois, Quatre, Cinq, Six, Sept, Huit, Neuf, Dix, Valet, Dame, Roi;
	
	public static int getPos(Types type){
		Types[] values = Types.values();
		for(int i = 0; i< values.length; i++){
			if(values[i].equals(type)){
				return i;
			}
		}
		return -1;
	}
}
