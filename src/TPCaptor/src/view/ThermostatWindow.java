package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Captor;

import java.util.Map;

public class ThermostatWindow extends CaptorMonitorWindow {

    @FXML
    private Slider slider;

    public ThermostatWindow(Captor captor) {
        super("Thermostat", "/fxml/ThermostatWindow.fxml", captor);
        nom.textProperty().bind(this.getCaptor().getName());
        update();
    }

    public void update() {
        slider.valueProperty().bindBidirectional(getCaptor().getValue());
        labelTemp.setText(String.format("%.2f°C", this.getCaptor().getValue().doubleValue()));
    }

    @FXML
    public void onChange() {
        getCaptor().setValue(slider.getValue());
    }

    @FXML
    public void buttonExit() {
        Stage stage = (Stage) slider.getScene().getWindow();
        stage.close();
    }
}
