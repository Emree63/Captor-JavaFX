package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;

import java.util.concurrent.atomic.AtomicReference;

public class CaptorWindow {

    @FXML
    private TreeView<Captor> lvCaptors;
    @FXML
    private Label id;
    @FXML
    private TextField nom;
    @FXML
    private BorderPane master;
    @FXML
    private Button buttonChange;
    @FXML
    private VBox vBoxMaster;
    private CaptorStationStub captors = new CaptorStationStub();

    public CaptorWindow() throws Exception {
    }

    @FXML
    private void initialize() throws Exception {
        genTreeView();
        master.setVisible(false);
        AtomicReference<HBox> hbox = new AtomicReference<>(new HBox());
        VisitorCaptor visitorCaptor = new VisitorCaptor();
        lvCaptors.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (oldV != null) {
                id.textProperty().unbind();
                nom.textProperty().unbindBidirectional(oldV.getValue().getName());
                master.setVisible(false);
                vBoxMaster.getChildren().remove(hbox.get());


            }
            if (newV != null) {
                id.setText(newV.getValue().getId().toString());
                nom.textProperty().bindBidirectional(newV.getValue().getName());
                master.setVisible(true);
                try {
                    hbox.set(newV.getValue().details(visitorCaptor));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                vBoxMaster.getChildren().add(hbox.get());
            }
        });

        nom.textProperty().addListener((observable, oldValue, newValue) -> {
            // This code will be executed whenever the text in the text field changes
            lvCaptors.refresh();
        });
    }

    public void genTreeView() throws Exception {
        TreeItem<Captor> root = new TreeItem<>();
        root.setExpanded(true);
        VisitorCaptor visitorCaptor = new VisitorCaptor();
        for (Captor captor : captors.getGroupe()) {
            root.getChildren().add(captor.accept(visitorCaptor));
            //root.getChildren().add(toTreeItem(captor));
        }
        lvCaptors.setRoot(root);
        lvCaptors.setShowRoot(false);
    }

    /*
    public TreeItem<Captor> toTreeItem(Captor captor) throws Exception {
        TreeItem<Captor> itemCaptor = new TreeItem<>(captor);
        if (captor instanceof CaptorArea) {
            itemCaptor.setExpanded(true);
            for (Captor c : captor.getCaptors()) {
                itemCaptor.getChildren().add(toTreeItem(c));
            }
        }
        return itemCaptor;
    }*/

    public void openWindowImage(ActionEvent actionEvent) {
        openWindow(new ImageWindow(lvCaptors.getSelectionModel().getSelectedItem().getValue()));
    }

    public void openWindowThermostat(ActionEvent actionEvent) {
        openWindow(new ThermostatWindow(lvCaptors.getSelectionModel().getSelectedItem().getValue()));
    }

    public void openWindow(CaptorMonitorWindow type) {
        type.setResizable(false);
        type.show();
    }

    public void buttonExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void buttonAdd(ActionEvent actionEvent) throws Exception {
        captors.getGroupe().add(new CaptorBasic("New", new GenBoundedRandom(-30, 40)));
        genTreeView();
    }

    public void changeCaptor(ActionEvent actionEvent) {
        Captor selectedCaptor = lvCaptors.getSelectionModel().getSelectedItem().getValue();
        if (buttonChange.getText().equals("Start")) {
            buttonChange.setText("Stop");
            selectedCaptor.start();
        } else {
            buttonChange.setText("Start");
            selectedCaptor.stop();
        }
    }
}
