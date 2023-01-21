package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

import java.util.List;
import java.util.UUID;

public abstract class Captor extends Observable {

    private UUID id;
    private StringProperty name;
    private double value;
    private double time;

    public Captor(String name) {
        this.id = UUID.randomUUID();
        this.name = new SimpleStringProperty(name);
        this.time = 4;
    }

    public UUID getId() {
        return this.id;
    }

    public StringProperty getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public abstract double getTemperature();

    public abstract List<Captor> getCaptors() throws Exception;

    public abstract GenStrategy getGenStrategy() throws Exception;

    public abstract void setGenStrategy(GenStrategy genStrategy) throws Exception;

    public abstract void addCaptor(Captor captor, double weight) throws Exception;

    @Override
    public String toString() {
        return this.name.getValue();
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        this.notifyAllObservers();
    }

    public abstract TreeItem<Captor> accept(VisitorCaptor visitorCaptor) throws Exception;
}
