package vo.action;

public enum ActionType {
	BACKGROUND,
	CHARACTER,
	TEXT;
	
	public static boolean isActionType(String data){
		for(ActionType actionType : values()){
			if(actionType.name().equals(data)) return true;
		}
		return false;
	}
}
