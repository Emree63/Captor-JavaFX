package view;

import model.Captor;
import model.Observer;

public class CaptorMonitorWindow extends FXMLWindow implements Observer {


    private Captor captor;

    public CaptorMonitorWindow(String title, String pathFxmlRessource, Captor captor) {
        super(title, pathFxmlRessource);
        this.captor = captor;
        this.captor.add(this);
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

