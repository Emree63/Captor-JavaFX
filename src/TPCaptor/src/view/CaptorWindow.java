package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.Captor;
import model.CaptorStationStub;
import model.VisitorCaptor;

public class CaptorWindow {

    @FXML
    private TreeView<Captor> lvCaptors;
    @FXML
    private Label id;
    @FXML
    private TextField nom;
    @FXML
    private BorderPane master;
    private CaptorStationStub captors = new CaptorStationStub();

    public CaptorWindow() throws Exception {
    }

    @FXML
    private void initialize() throws Exception {
        TreeItem<Captor> root = new TreeItem<>();
        root.setExpanded(true);
        VisitorCaptor visitorCaptor = new VisitorCaptor();
        for (Captor captor : captors.getGroupe()) {
            root.getChildren().add(captor.accept(visitorCaptor));
            //root.getChildren().add(toTreeItem(captor));
        }
        lvCaptors.setRoot(root);
        lvCaptors.setShowRoot(false);
        master.setVisible(false);

        lvCaptors.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if(oldV != null) {
                id.textProperty().unbind();
                nom.textProperty().unbindBidirectional(oldV.getValue().getName());
                master.setVisible(false);
            }
            if(newV != null) {
                id.setText(newV.getValue().getId().toString());
                nom.textProperty().bindBidirectional(newV.getValue().getName());
                master.setVisible(true);
            }
        });

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
        Captor selectedCaptor = lvCaptors.getSelectionModel().getSelectedItem().getValue();
        ImageWindow imageWindow = new ImageWindow(selectedCaptor);
        imageWindow.setResizable(false);
        imageWindow.show();
    }

    public void openWindowThermosta(ActionEvent actionEvent) {
        Captor selectedCaptor = lvCaptors.getSelectionModel().getSelectedItem().getValue();
        ThermostatWindow thermostaWindow = new ThermostatWindow(selectedCaptor);
        thermostaWindow.setResizable(false);
        thermostaWindow.show();
    }

    public void buttonExit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
