package view;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Captor;

public class ThermostatWindow extends CaptorMonitorWindow{

    @FXML
    private Text valTemperature;

    @FXML
    private Slider slider;

    public ThermostatWindow(Captor captor) {
        super("Thermosta", "/fxml/ThermostatWindow.fxml", captor);
    }

    public void initialize() {
        //slider.valueProperty().bindBidirectional(getCaptor().getValue());
        //valTemperature.textProperty().bind(getCaptor().getValue().asString());
    }

    @FXML
    public void onChange() {

    }

    @FXML
    public void buttonExit() {
        Stage stage = (Stage) slider.getScene().getWindow();
        stage.close();
    }
}
