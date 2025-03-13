package customclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListBetreuungszeit {
	
	private String pattern = "yyyy-MM-dd";
	private DateTimeFormatter dateFormatter = null;
	
	private int zid;
	private int pid;
	private LocalDate beginn;
	private LocalDate ende;
	private String beginnOutput;
	private String endeOutput;
	private int stunden;
	
	public ListBetreuungszeit(int id, int pid, String beginn, String ende, int stunden) {
		this.zid = id;
		this.pid = pid;
		this.dateFormatter = DateTimeFormatter.ofPattern(this.pattern);
	    if(id == 0) {
	    	this.beginnOutput = beginn;
	    } else {
	    	this.beginn = LocalDate.parse(beginn, dateFormatter);
		    this.ende = LocalDate.parse(ende, dateFormatter);
			this.stunden = stunden;
			
			this.beginnOutput = this.beginn.getDayOfMonth() + "." + this.beginn.getMonthValue() + "." + this.beginn.getYear();
			this.endeOutput = this.ende.getDayOfMonth() + "." + this.ende.getMonthValue() + "." + this.ende.getYear();
	    }
		
	}
	
	public int getZid() {
		return this.zid;
	}
	
	public int getPid() {
		return this.pid;
	}
	
	public LocalDate getBeginnRaw() {
		return this.beginn;
	}
	
	public LocalDate getEndeRaw() {
		return this.ende;
	}
	
	public String getBeginnData() {
		return this.beginn.toString();
	}
	
	public String getEndeData() {
		return this.ende.toString();
	}
	
	public String getBeginn() {
		return this.beginnOutput;
	}
	
	public String getEnde() {
		return this.endeOutput;
	}
	
	public int getStunden() {
		return this.stunden;
	}
	
	public String getFormatted() {
		if(zid == 0) {
			return this.beginnOutput; 
		} else {
			return this.beginnOutput + "  bis  " + this.endeOutput + "  -  " + this.stunden + "h"; 		
		}		
	}	
	
	public void setStunden(int stunden) {
		this.stunden = stunden;
	}
	
	public void setBeginn(LocalDate beginn) {
		this.beginn = beginn;
		this.beginnOutput = this.beginn.getDayOfMonth() + "." + this.beginn.getMonthValue() + "." + this.beginn.getYear();
	}
	
	public void setEnde(LocalDate ende) {
		this.ende = ende;
		this.endeOutput = this.ende.getDayOfMonth() + "." + this.ende.getMonthValue() + "." + this.ende.getYear();
	}

}
