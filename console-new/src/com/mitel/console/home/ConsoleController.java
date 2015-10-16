package com.mitel.console.home;

import com.mitel.console.Main;
import com.mitel.console.home.utils.BasicWindowButtons;
import com.mitel.console.home.utils.Utility;
import com.mitel.console.home.utils.WindowCornerResizer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * As JavaFx is based on MVC design pattern.
 *
 * Controller class is used as a primary administrator object which is used to
 * manage all the UI controls on this particular scene i.e. all the
 * conditional/dynamic manipulation of controls as well as any checks/validation
 * managed by this class only.
 *
 * @author dubey_p
 */
public class ConsoleController implements
        IConsoleController, ConsoleConstants {

    private BasicWindowButtons windowButtons;
    private Main appInstance =
            Main.getConsoleInstance();
    @FXML
    private Pane topBarNotificCount;
    @FXML
    private BorderPane header;
    @FXML
    private TextField searchBox;
    @FXML
    private ToolBar topToolbar;
    @FXML
    private ImageView windowResizer;

    /**
     * <code>initialize</code> default method executed to handle initialization
     * tasks at time of XML load.
     */
    @FXML
    void initialize() {

        topBarNotificCount.getChildren().add(Utility.addNotification("5"));
        this.addDoubleClickEvent(header);
        
        //adding appName label to topbar
        Utility.createTopBar(topToolbar, TITLE);

        //adding customized buttons on top bar (minimize, maximize & close)
        windowButtons = new BasicWindowButtons(appInstance);
        windowButtons.addEvents(topToolbar);
        this.setWindowButtons(windowButtons);

        // adding resizable button at bottom right corner
        new WindowCornerResizer(windowResizer, appInstance.getStage(),
                stageMinimumWidth, stageMinimumHeight);
    }

    @FXML
    public void removeSearchBoxFocus() {
        //TODO
        /* Need to handle remove focus from textfield in some better way
         * as of now working as per suggestions from community
         * https://community.oracle.com/thread/2501041         */
        searchBox.getParent().requestFocus();
    }

    @Override
    public ConsoleController getController() {
        return this;
    }

    public ToolBar getTopToolbar() {
        return topToolbar;
    }

    public void setTopToolbar(ToolBar topToolbar) {
        this.topToolbar = topToolbar;
    }

    public BasicWindowButtons getWindowButtons() {
        return windowButtons;
    }

    public void setWindowButtons(BasicWindowButtons windowButtons) {
        this.windowButtons = windowButtons;
    }

    /**
     * To add maximize, resizing on double click of full blue bars having logo
     * and search-box.
     *
     * @param header
     */
    private void addDoubleClickEvent(Node header) {
        header.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    getWindowButtons().toogleMaximized();
                }
            }
        });
    }
}
