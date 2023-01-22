package model;

import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Map;

public class VisitorCaptor {

    public TreeItem<Captor> visit(CaptorArea captor) throws Exception {
        TreeItem<Captor> itemCaptor = new TreeItem<>(captor, new ImageView(new Image(getClass().getResourceAsStream("/images/multi_captor_icon.png"))));
        for (Captor c : captor.getCaptors()) {
            itemCaptor.getChildren().add(c.accept(this));
        }
        return itemCaptor;
    }

    public TreeItem<Captor> visit(CaptorBasic captor) {
        TreeItem<Captor> itemCaptor = new TreeItem<>(captor, new ImageView(new Image(getClass().getResourceAsStream("/images/captor_icon.png"))));
        return itemCaptor;
    }

    public HBox details(CaptorArea captor) throws Exception {
        HBox hbox = new HBox();
        TableView<Captor> tableView = new TableView<>();
        tableView.setPrefWidth(300);
        tableView.setPrefHeight(380);
        TableColumn<Captor, StringProperty> column = new TableColumn<>("Enfant");
        tableView.getColumns().add(column);
        column.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        for (Captor capteur : captor.getCaptors()) {
            tableView.getItems().add(capteur);
        }
        hbox.getChildren().add(tableView);
        return hbox;
    }

    public HBox details(CaptorBasic captor) {
        HBox hbox = new HBox();

        // For the strategy
        Text text = new Text();
        text.setText("STRATEGIE :");
        text.setStyle("-fx-font-weight: bold; -fx-fill: white;");
        hbox.getChildren().add(text);
        StrategyStub strategyStub = new StrategyStub();
        ComboBox<String> comboBox = new ComboBox<>();
        for (Map.Entry<String, GenStrategy> mesure : strategyStub.strategies.entrySet()) {
            comboBox.getItems().add(mesure.getKey());
            if (mesure.getValue().getClass() == captor.getGenStrategy().getClass()) {
                comboBox.setValue(mesure.getKey());
            }
        }
        comboBox.setOnAction(event -> {
            String selectedStrategy = comboBox.getValue();
            GenStrategy strategy = strategyStub.strategies.get(selectedStrategy);
            captor.setGenStrategy(strategy);
        });
        comboBox.setPrefWidth(150);
        hbox.getChildren().add(comboBox);

        // For the time
        Text text2 = new Text();
        text2.setText("TEMPS :");
        text2.setStyle("-fx-font-weight: bold; -fx-fill: white;");
        hbox.getChildren().add(text2);
        Spinner<Double> spinner = new Spinner<Double>();
        spinner.setEditable(false);
        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, captor.getTime(), 1));
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            captor.setTime(newValue.doubleValue());
            captor.stop();
            captor.start();
        });


        hbox.getChildren().add(spinner);


        return hbox;
    }


}
