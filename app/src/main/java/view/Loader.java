package view;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Loader {
	
	 public static <X> Pane loadPane(final String path, final X controller) throws IOException {
	        final Pane pane;
	        var a = Loader.class.getResource(path);
	        final var loader = new FXMLLoader(a);
	        System.out.println(a);

	        loader.setController(controller);
	        pane = loader.load();
	        return pane;
	    }
	    
}
