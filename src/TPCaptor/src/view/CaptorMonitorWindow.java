package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Captor;
import model.Observer;

public class CaptorMonitorWindow extends FXMLWindow implements Observer {

    @FXML
    protected Label labelTemp;
    @FXML
    protected Label nom;
    private Captor captor;

    public CaptorMonitorWindow(String title, String pathFxmlRessource, Captor captor) {
        super(title, pathFxmlRessource);
        this.captor = captor;
        this.captor.attach(this);
    }

    public Captor getCaptor() {
        return this.captor;
    }

    public void setCaptor(Captor captor) {
        this.captor = captor;
    }


    @Override
    public void update() {
    }
}

