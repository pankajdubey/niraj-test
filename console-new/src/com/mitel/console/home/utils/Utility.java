
package com.mitel.console.home.utils;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Utility class for various support
 *
 * @author dubey_p
 */
public class Utility {

    /**
     * Method used for showing pending notification on top bar red circle
     *
     * @param number
     * @return <code>Node</code>
     */
    public static Node addNotification(String number) {
        // StackPane used to keep the circle and label on top of each other
        StackPane baseCircle = new StackPane();
        Label lab = new Label(number);
        lab.setStyle("-fx-text-fill:white");
        Circle circle = new Circle(8, Color.rgb(200, 0, 0, .9));
        circle.setStrokeWidth(2.0);
        circle.setStyle("-fx-background-insets: 0 0 -1 0, 10, 1, 2;");
        circle.setSmooth(true);
        baseCircle.getChildren().addAll(circle, lab);
        return baseCircle;
    }

    /**
     * Creating top tool bar with application title, customized buttons
     * (minimize, maximize & close)
     */
    public static void createTopBar(ToolBar toolBar, String TITLE) {
        toolBar.getItems().add(getRegion());
        toolBar.getItems().add(getTitleLabel(TITLE));
        toolBar.getItems().add(getRegion());
    }

    /**
     * Spacer to center the title label.
     *
     * @return Region
     */
    public static Region getRegion() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    /**
     * Application Title label
     *
     * @return Label
     */
    public static Label getTitleLabel(String TITLE) {
        final Label appTitleLabel = new Label(TITLE);
        appTitleLabel.setId("app-title");
        appTitleLabel.setPrefWidth(280);
        return appTitleLabel;
    }
}
