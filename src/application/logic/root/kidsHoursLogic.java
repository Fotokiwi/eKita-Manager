package application.logic.root;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import application.EKitaManager;
import customclasses.Betreuungszeit;
import customclasses.ListBetreuungszeit;
import customclasses.PersonKid;
import database.mysql.MySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class kidsHoursLogic {

	// Reference to the main application
	private EKitaManager app;
	
	public ObservableList<ListBetreuungszeit> zeiten = null;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public kidsHoursLogic(EKitaManager app) {
		
		this.app = app;
		
	}

	public ObservableList<ListBetreuungszeit> generateBetreuungszeitenList(int pid) {
		
		zeiten = FXCollections.observableArrayList();
		
		zeiten.add(new ListBetreuungszeit(0, 0, "< Neue Betreuungszeit hinzufügen >", "", 0));
		
		String[] loginData = this.app.settings.getMySQLData(this.app.settings.getServerType());
		
		MySQL dbconn = new MySQL(app);
		if(!dbconn.connect(loginData[1], 3306, loginData[0], loginData[2], loginData[3]))
			return zeiten;
		
		ResultSet rs = null;
		
		rs = dbconn.select("SELECT * FROM `Betreuungszeitindex` WHERE `PID`=" + pid + ";");
				
		try {
			if (!rs.isBeforeFirst() ) {    
				dbconn.disconnect();
				dbconn = null;
				return zeiten;
			} else {
				while(rs.next()) {
					zeiten.add(new ListBetreuungszeit(rs.getInt("ZID"), rs.getInt("PID"), rs.getString("Beginn"), rs.getString("Ende"), rs.getInt("Stunden")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.app.log.LogError("mysql error generateBetreuungszeitenList", e);
			this.app.displayErrorLayer().setErrorDetails("11 - 001", "Die Betreuungszeitenliste konnte nicht geladen werden.");
		}

		dbconn.disconnect();
		dbconn = null;
		return zeiten;
	}
	
	public ObservableList<ListBetreuungszeit> generateBetreuungszeitenList(PersonKid kidData) {
		
		zeiten = null;
		zeiten = FXCollections.observableArrayList();
		
		List<Betreuungszeit> liste = kidData.getBetreuungszeiten();
		
		zeiten.add(new ListBetreuungszeit(0, 0, "< Neue Betreuungszeit hinzufügen >", "", 0));
		
		for(int i = 0; i < liste.size(); i++) {
			
			zeiten.add(new ListBetreuungszeit(liste.get(i).getZid(), kidData.getPID(), liste.get(i).getBeginn().toString(), liste.get(i).getEnde().toString(), liste.get(i).getStunden()));
			
		}
		
		return zeiten;
		
	}
	
	public boolean analyseDateRedundancy(int newDataZID, LocalDate newDataBegin, LocalDate newDataEnd, ObservableList<ListBetreuungszeit> observableList, boolean autoedit) {
		
		LocalDate newDateBegin = newDataBegin;
		LocalDate newDateEnd = newDataEnd;
		List<ListBetreuungszeit> oldDates = observableList;
		
		for(int i = 1; i < oldDates.size(); i++) {

			if(newDataZID != oldDates.get(i).getZid()) {
				
				if((newDateBegin.isBefore(oldDates.get(i).getEndeRaw()) || newDateBegin.isEqual(oldDates.get(i).getEndeRaw())) && newDateBegin.isAfter(oldDates.get(i).getBeginnRaw()) && (newDateEnd.isAfter(oldDates.get(i).getEndeRaw()) || newDateEnd.isEqual(oldDates.get(i).getEndeRaw()))) {
					
					if(autoedit) {
						ListBetreuungszeit tmpZeit = oldDates.get(i);
						LocalDate tmpDate = newDateBegin.minusDays(1);
						tmpZeit.setEnde(tmpDate);
						this.app.kidsHoursController.lefts.set(i, tmpZeit);
						return true;
					}
					//System.out.println("Neues Datum liegt im Bereich des Alten! (Ende)");
					return false;
					
				}
				if((newDateEnd.isAfter(oldDates.get(i).getBeginnRaw()) || newDateEnd.isEqual(oldDates.get(i).getBeginnRaw())) && newDateEnd.isBefore(oldDates.get(i).getEndeRaw()) && (newDateBegin.isBefore(oldDates.get(i).getBeginnRaw()) || newDateBegin.isEqual(oldDates.get(i).getBeginnRaw()))) {
					
					if(autoedit) {
						ListBetreuungszeit tmpZeit = oldDates.get(i);
						LocalDate tmpDate = newDateEnd.plusDays(1);
						tmpZeit.setBeginn(tmpDate);
						this.app.kidsHoursController.lefts.set(i, tmpZeit);
						return true;
					}
					//System.out.println("Neues Datum liegt im Bereich des Alten! (Anfang)");
					return false;
					
				}
				if((newDateBegin.isBefore(oldDates.get(i).getBeginnRaw()) || newDateBegin.isEqual(oldDates.get(i).getBeginnRaw())) && (newDateEnd.isAfter(oldDates.get(i).getEndeRaw()) || newDateEnd.isEqual(oldDates.get(i).getEndeRaw()))) {
					
					if(autoedit) {
						this.app.kidsHoursController.lefts.remove(i);
						return true;
					}
					
					//System.out.println("Neues Datum überschreibt das Alte vollständig!");
					return false;
					
				}
				if(newDateBegin.isAfter(oldDates.get(i).getBeginnRaw()) && newDateEnd.isBefore(oldDates.get(i).getEndeRaw())) {
					
					if(autoedit) {	
						ListBetreuungszeit firstTime = new ListBetreuungszeit(oldDates.get(i).getZid(), oldDates.get(i).getPid(), oldDates.get(i).getBeginnData(), newDateBegin.minusDays(1).toString(), oldDates.get(i).getStunden());
						ListBetreuungszeit lastTime = new ListBetreuungszeit(oldDates.get(i).getZid(), oldDates.get(i).getPid(), newDateEnd.plusDays(1).toString(), oldDates.get(i).getEndeData(), oldDates.get(i).getStunden());

						//System.out.println(firstTime.getFormatted());
						//System.out.println(lastTime.getFormatted());
						
						this.app.kidsHoursController.lefts.set(i, firstTime);
						this.app.kidsHoursController.lefts.add(lastTime);
						return true;
					}
					
					//System.out.println("Neues Datum teilt das Alte auf!");
					return false;
					
				}
			} else {
				//System.out.println("Eintrag wird nicht mit sich selbst geprüft!");
			}
						
		}		
		
		return true;
		
	}

}
