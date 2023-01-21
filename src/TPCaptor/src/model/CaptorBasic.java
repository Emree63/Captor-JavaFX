package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;

public class CaptorBasic extends Captor implements Runnable {

    private GenStrategy genStrategie;

    public CaptorBasic(String name, GenStrategy genStrategie) {
        super(name);
        this.genStrategie = genStrategie;
        setValue(this.getTemperature());
        run();
    }

    @Override
    public double getTemperature() {
        return this.genStrategie.generate();
    }

    @Override
    public List<Captor> getCaptors() throws Exception {
        throw new Exception("Impossible de retourner la liste des capteurs");
    }

    @Override
    public GenStrategy getGenStrategy() {
        return this.genStrategie;
    }

    @Override
    public void setGenStrategy(GenStrategy genStrategie) {
        this.genStrategie = genStrategie;
    }

    @Override
    public void addCaptor(Captor captor, double weight) throws Exception {
        throw new Exception("Impossible d'ajouter un capteur dans cette classe");
    }

    @Override
    public void run() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.seconds(this.getTime()), event -> {
                    if (getGenStrategy() != null) {
                        this.setValue(this.getGenStrategy().generate());
                    }
                }
                ));

        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    @Override
    public TreeItem<Captor> accept(VisitorCaptor visitorCaptor) {
        return visitorCaptor.visit(this);
    }
}
