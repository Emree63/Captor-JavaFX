package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class FXMLWindow extends Stage {

    public FXMLWindow(String title, String pathFxmlRessource) {
        try {
            //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pathFxmlRessource)));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathFxmlRessource));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            setTitle(title);
            setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
