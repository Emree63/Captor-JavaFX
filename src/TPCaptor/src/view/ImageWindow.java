package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Captor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ImageWindow extends CaptorMonitorWindow {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label labelTemp;

    private static NavigableMap<Double, String> Images = new TreeMap<>();

    public ImageWindow(Captor captor) {
        super("Image", "/fxml/ImageWindow.fxml", captor);
        Images.put(25.0, "soleil");
        Images.put(0.0, "nuage");
        nom.textProperty().bind(this.getCaptor().getName());
        Images.put(-Double.MAX_VALUE, "neige");
        update();
    }

    @FXML
    public void buttonExit() {
        Stage stage = (Stage) image.getScene().getWindow();
        stage.close();
    }

    public void update() {
        Map.Entry<Double, String> entry = Images.floorEntry(this.getCaptor().getValue());
        if (entry != null && entry.getValue() != null) {
            image.setImage(new Image("/images/" + entry.getValue() + ".jpg"));
        }
        labelTemp.setText(String.format("%.2f°C", this.getCaptor().getValue()));
    }

}
