package util.servercheck;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import application.EKitaManager;

public class ServerCheck implements Runnable {
	
	private TextField serverURL;
	private String ipNet;
	
	public ServerCheck(String ipNet, TextField serverURL) {
        this.serverURL = serverURL;
        this.ipNet = ipNet;
    }
	
	@Override public void run() {
		//String ipNet = "10.0.100.110";
		String[] ipSegments = ipNet.split("\\.");
		EKitaManager.getInstance().log.LogDebug("" + ipSegments.length);
		for(int i = 1; i < 255; i++) {
			if(Thread.currentThread().isInterrupted()) {
				break;
			}
			URL url = null;
			try {
				url = new URL("http://" + ipSegments[0] + "." + ipSegments[1] + "." + ipSegments[2] + "." + i + "/utility/servercheck.php");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection conn = null;
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(500);
				conn.setReadTimeout(500);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
					final String id = ipSegments[0] + "." + ipSegments[1] + "." + ipSegments[2] + "." + i;
					Platform.runLater(new Runnable(){
                        @Override
                        public void run() {                        	
                        	serverURL.setText(id);
                        }
					});
					break;
				} else {
					final String id = ipSegments[0] + "." + ipSegments[1] + "." + ipSegments[2] + "." + i;
					Platform.runLater(new Runnable(){
                        @Override
                        public void run() {                        	
                        	serverURL.setText(id);
                        }
					});
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();					
				final String id = ipSegments[0] + "." + ipSegments[1] + "." + ipSegments[2] + "." + i;
				Platform.runLater(new Runnable(){
                    @Override
                    public void run() {                        	
                    	serverURL.setText(id);
                    }
				});
			}
		}
		//final String id = "Server nicht gefunden!";
	}
}