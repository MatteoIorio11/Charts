package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Loader {
	
	 public static <X> Pane loadPane(final String path, final X controller) throws IOException {
	        final Pane pane;
	        final var loader = new FXMLLoader(Loader.class.getResource(path));
	        loader.setController(controller);
	        pane = loader.load();
	        return pane;
	    }
}
