package util.stringcheck;

public class StringCheck {
	
	public static boolean containsIllegals(String toExamine) {
	    String[] arr = toExamine.split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2);
	    return arr.length > 1;
	}

}
