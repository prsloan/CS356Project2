
public enum PositiveWords {
	INSTANCE;
	
	private String[] positives = { "Happy" , "Joyful" , "Good"
									,"Excited", "Jolly", "Great", "Awesome" , "Fantastic",
									"Ducky" , " Wonderful ", "beautiful", "nice", "friendly","glorious"
								};
	
	
	private PositiveWords(){
		
	}
	
	
	public boolean isPositive(String message){
		for (String s: positives){
			if (message.contains(s)){
				return true;
			}
		}
		return false;
	}
	
}
