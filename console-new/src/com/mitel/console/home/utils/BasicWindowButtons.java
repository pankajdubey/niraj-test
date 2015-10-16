package com.mitel.console.home.utils;

import com.mitel.console.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
/**
 * Horizontal box with customized 3 small buttons for window close, minimize and
 * maximize. Separate object for keeping all the three button icons similar for
 * all windows across application
 *
 * @author dubey_p
 */
public class BasicWindowButtons extends HBox {

    private Stage stage;
    private Rectangle2D backupWindowBounds = null;
     Rectangle rect=null;
    private boolean maximized = false;
    private double mouseDragOffsetX = 0;
    private double mouseDragOffsetY = 0;

    public BasicWindowButtons(final Main appInstance) {
        super(4);
        
        this.stage = appInstance.getStage();
        this.rect = appInstance.getRect();
        // create buttons
        Button closeBtn = new Button();
        closeBtn.setId("window-close");
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        Button minBtn = new Button();
        minBtn.setId("window-min");
        minBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*
                 * TODO
                 * Since the iconifying from task bar is not yet working 
                 * reference to issue at JavaFx Jira, below thread about Fx bug
                 * https://community.oracle.com/thread/2553358
                 * 
                 * May be some JNI (Java Native Interface) code will solve this
                 */
                stage.setIconified(true);
            }
        });
        Button maxBtn = new Button();
        maxBtn.setId("window-max");
        maxBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                toogleMaximized();
            }
        });
        getChildren().addAll(minBtn, maxBtn, closeBtn);
    }

    public void toogleMaximized() {
        final Screen screen = Screen.getScreensForRectangle(Math.abs(stage.getX()),
                Math.abs(stage.getY()), 1, 1).get(0);
        if (maximized) {
            maximized = false;
            if (backupWindowBounds != null) {
                stage.setX(backupWindowBounds.getMinX());
                stage.setY(backupWindowBounds.getMinY());
                stage.setWidth(backupWindowBounds.getWidth());
                stage.setHeight(backupWindowBounds.getHeight());
                rect.setWidth(backupWindowBounds.getWidth());
                rect.setHeight(backupWindowBounds.getHeight());
                
                System.out.println("rect->"+rect.getWidth());
                System.out.println("rect->"+rect.getHeight());
                System.out.println("rect->"+rect.getArcHeight());
                System.out.println("rect->"+rect.getArcWidth());
            }
        } else {
            maximized = true;
            backupWindowBounds = new Rectangle2D(stage.getX(), stage.getY(),
                    stage.getWidth(), stage.getHeight());
            stage.setX(screen.getVisualBounds().getMinX());
            stage.setY(screen.getVisualBounds().getMinY());
            stage.setWidth(screen.getVisualBounds().getWidth());
            stage.setHeight(screen.getVisualBounds().getHeight());
            rect.setWidth(screen.getVisualBounds().getWidth());
            rect.setHeight(screen.getVisualBounds().getHeight());
                
             System.out.println("rect-1>"+rect.getWidth());
                System.out.println("rect-1>"+rect.getHeight());
                System.out.println("rect-1>"+rect.getArcHeight());
                System.out.println("rect-1>"+rect.getArcWidth());
        }
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void addEvents(ToolBar toolBar) {
        toolBar.getItems().add(this);
        // add window header double clicking
        toolBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    toogleMaximized();
                }
            }
        });
        // add window dragging
        toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseDragOffsetX = event.getSceneX();
                mouseDragOffsetY = event.getSceneY();
            }
        });
        toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!isMaximized()) {
                    stage.setX(event.getScreenX() - mouseDragOffsetX);
                    stage.setY(event.getScreenY() - mouseDragOffsetY);
                }
            }
        });
    }
}
