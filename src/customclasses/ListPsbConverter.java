package customclasses;

import javafx.collections.ObservableList;
import javafx.util.StringConverter;

public class ListPsbConverter extends StringConverter<ListPersonensorgeberechtigte> {
	
	private ObservableList<ListPersonensorgeberechtigte> list;

	public ListPsbConverter() {
		
	}

	public ListPsbConverter(ObservableList<ListPersonensorgeberechtigte> list) {
		this.list = list;
	}

	@Override
	public String toString(ListPersonensorgeberechtigte object) {
		return object.getName();
	}

	@Override
	public ListPersonensorgeberechtigte fromString(String string) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(string))
				return list.get(i);
		}
		return null;
	}
}
