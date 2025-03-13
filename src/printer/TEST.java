package printer;

import application.EKitaManager;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrintResolution;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TEST {
	
	private EKitaManager app;
	
	private Stage dialog;
	
	public TEST(EKitaManager app) {
		
		this.app = app;
		
		print(openPrintPage());
		
	}
	
	private void print(AnchorPane node) {
		
		PrinterJob job = PrinterJob.createPrinterJob();
		
		if(job != null && job.showPrintDialog(dialog)) {
			
			Printer printer = job.getPrinter();
			PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
			
			double width = node.getWidth();
			double height = node.getHeight();
			
			PrintResolution resolution = job.getJobSettings().getPrintResolution();
			
			width /= resolution.getFeedResolution();
			height /= resolution.getCrossFeedResolution();
			
			double scaleX = pageLayout.getPrintableWidth()/width/600;
			double scaleY = pageLayout.getPrintableHeight()/height/600;
			
			Scale scale = new Scale(scaleX, scaleY);
			
			node.getTransforms().add(scale);
			
			boolean success = job.printPage(pageLayout, node);
			if(success) {
				job.endJob();
			}
			
			node.getTransforms().remove(scale);
			
		}
		
	}
	
	private AnchorPane openPrintPage() {
		
		this.dialog = new Stage();
		this.app.printTestStage = dialog;
		dialog.initStyle(StageStyle.DECORATED);
		
		AnchorPane pane = null;
		FXMLLoader fxmlLoader = null;
		fxmlLoader = new FXMLLoader(this.getClass().getResource("/resources/fxml/print/TEST.fxml"));
		fxmlLoader.setResources(this.app.resourceBundle);
		try {
			pane = fxmlLoader.load();
		} catch (Exception ex) {
			this.app.log.LogError("can't load /resources/fxml/print/TEST.fxml", ex);
			this.app.displayErrorLayer().setErrorDetails("TEST - 001", "TEST.fxml kann nicht geladen werden!");
		}
		
		Scene scene = new Scene(pane);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.setTitle(this.app.APPLICATION_TITLE);
		dialog.getIcons().add(new Image(this.app.APPLICATION_ICON));
		dialog.show();
		
		return pane;
		
	}

}
