package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;

public class CaptorStationStub {

    private ObservableList<Captor> captors = FXCollections.observableArrayList();
    private ListProperty<Captor> groupe = new SimpleListProperty<>(captors);

    public CaptorStationStub() throws Exception {

        Captor captor1 = new CaptorBasic("France", new GenCPU());
        Captor captor2 = new CaptorBasic("Italie", new GenBoundedRandom(-10, 60));

        // CaptorArea
        Captor captorZone = new CaptorArea("Europe", new HashMap<>()) {{
            addCaptor(captor1, 20.0);
            addCaptor(captor2, 40.0);
        }};

        this.captors.add(captor1);
        this.captors.add(captor2);
        this.captors.add(captorZone);

    }

    public ObservableList<Captor> getGroupe() {
        return groupe.get();
    }

}
