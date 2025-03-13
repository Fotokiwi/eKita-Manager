package customclasses;

import application.EKitaManager;
import javafx.util.StringConverter;

public class ListGroupConverter extends StringConverter<ListGroup> {

	public ListGroupConverter(EKitaManager eKitaManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(ListGroup object) {
		return object.getGroupName();
	}

	@Override
	public ListGroup fromString(String string) {
		for(int i = 0; i < EKitaManager.getInstance().groups.size(); i++) {
			if(EKitaManager.getInstance().groups.get(i).getGroupName().equals(string))
				return EKitaManager.getInstance().groups.get(i);
		}
		return null;
	}
}
