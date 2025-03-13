package customclasses;

import application.EKitaManager;
import javafx.util.StringConverter;

public class ListDoctorsConverter extends StringConverter<ListDoctors> {

	public ListDoctorsConverter(EKitaManager eKitaManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(ListDoctors object) {
		return object.getDisplayName();
	}

	@Override
	public ListDoctors fromString(String string) {
		for(int i = 0; i < EKitaManager.getInstance().doctors.size(); i++) {
			if(EKitaManager.getInstance().doctors.get(i).getDisplayName().equals(string))
				return EKitaManager.getInstance().doctors.get(i);
		}
		return null;
	}
}
