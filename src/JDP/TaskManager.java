package JDP;

import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TaskManager {
	public static void main(String[] args){
	    final SplashScreen splash = SplashScreen.getSplashScreen();
	    try {
			splash.setImageURL(new URL("sunny.jpg"));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
		new MainFrame();
	}
}
