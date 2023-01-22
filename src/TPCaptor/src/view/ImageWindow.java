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
    private static NavigableMap<Double, Image> Images = new TreeMap<>();

    public ImageWindow(Captor captor) {
        super("Image", "/fxml/ImageWindow.fxml", captor);
        Images.put(25.0, new Image("/images/soleil.jpg"));
        Images.put(0.0, new Image("/images/nuage.jpg"));
        Images.put(-Double.MAX_VALUE, new Image("/images/neige.jpg"));
        nom.textProperty().bind(this.getCaptor().getName());
        update();
    }

    @FXML
    public void buttonExit() {
        Stage stage = (Stage) image.getScene().getWindow();
        stage.close();
    }

    public void update() {
        Map.Entry<Double, Image> entry = Images.floorEntry(this.getCaptor().getValue().doubleValue());
        if (entry != null && entry.getValue() != null) {
            image.setImage(entry.getValue());
        }
        labelTemp.setText(String.format("%.2f°C", this.getCaptor().getValue().doubleValue()));
    }

}
