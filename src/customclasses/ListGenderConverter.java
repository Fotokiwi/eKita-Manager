package customclasses;

import application.EKitaManager;
import javafx.util.StringConverter;

public class ListGenderConverter extends StringConverter<ListGender> {

	public ListGenderConverter(EKitaManager eKitaManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(ListGender object) {
		return object.getGender();
	}

	@Override
	public ListGender fromString(String string) {
		for(int i = 0; i < EKitaManager.getInstance().gender.size(); i++) {
			if(EKitaManager.getInstance().gender.get(i).getGender().equals(string))
				return EKitaManager.getInstance().gender.get(i);
		}
		return null;
	}

}
