package model;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VisitorCaptor {

    public TreeItem<Captor> visit(CaptorArea captor) throws Exception {
        TreeItem<Captor> itemCaptor = new TreeItem<>(captor, new ImageView(new Image(getClass().getResourceAsStream("/images/multi_captor_icon.png"))));
        itemCaptor.setExpanded(true);
        for (Captor c : captor.getCaptors()) {
            itemCaptor.getChildren().add(c.accept(this));
        }
        return itemCaptor;
    }

    public TreeItem<Captor> visit(CaptorBasic captor) {
        TreeItem<Captor> itemCaptor = new TreeItem<>(captor, new ImageView(new Image(getClass().getResourceAsStream("/images/captor_icon.png"))));
        return itemCaptor;
    }


}
