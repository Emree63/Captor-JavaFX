package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CaptorBasic;
import model.GenCPU;
import view.FXMLWindow;
import view.ImageWindow;

import java.util.Objects;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        /*FXMLWindow fxmlWindow = new FXMLWindow("/fxml/CaptorWindow.fxml", "Home");
        fxmlWindow.show();*/

        Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CaptorWindow.fxml")));
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.show();

    }


}
