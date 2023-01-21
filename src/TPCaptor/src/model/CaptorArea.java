package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;

import java.util.*;

public class CaptorArea extends Captor {

    Map<Captor, Double> captors = new HashMap<>();

    public CaptorArea(String name, Map<Captor, Double> captors) {
        super(name);
        this.captors = captors;
    }

    @Override
    public GenStrategy getGenStrategy() throws Exception {
        throw new Exception("Impossible de renvoyer la strategie");
    }

    @Override
    public void setGenStrategy(GenStrategy genStrategy) throws Exception {
        throw new Exception("Impossible de renvoyer la strategie");
    }

    @Override
    public double getTemperature() {
        double temperatureSum = 0.0;
        double weightSum = 0.0;

        for (Map.Entry<Captor, Double> entry : captors.entrySet()) {
            Captor captor = entry.getKey();
            double weight = entry.getValue();

            temperatureSum += captor.getTemperature() * weight;
            weightSum += weight;
        }

        return temperatureSum / weightSum;
    }

    @Override
    public List<Captor> getCaptors() throws Exception {
        List<Captor> captor = new ArrayList<>();
        for (Map.Entry<Captor, Double> entry : captors.entrySet()) {
            captor.add(entry.getKey());
        }
        return captor;
    }

    @Override
    public void addCaptor(Captor captor, double weight) throws Exception {
        captors.put(captor, weight);
    }

    @Override
    public TreeItem<Captor> accept(VisitorCaptor visitorCaptor) throws Exception {
        return visitorCaptor.visit(this);
    }

}
