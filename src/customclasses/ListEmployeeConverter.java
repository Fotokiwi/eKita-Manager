package customclasses;

import application.EKitaManager;
import javafx.util.StringConverter;

public class ListEmployeeConverter extends StringConverter<ListEmployee> {

	public ListEmployeeConverter(EKitaManager eKitaManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(ListEmployee object) {
		return object.getFullName();
	}

	@Override
	public ListEmployee fromString(String string) {
		for(int i = 0; i < EKitaManager.getInstance().employees.size(); i++) {
			if(EKitaManager.getInstance().employees.get(i).getFullName().equals(string))
				return EKitaManager.getInstance().employees.get(i);
		}
		return null;
	}

}
