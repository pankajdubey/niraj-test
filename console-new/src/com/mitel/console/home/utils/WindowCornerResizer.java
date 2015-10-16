package com.mitel.console.home.utils;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Simple icon for the bottom right of a window to support resizing.
 *
 * @author dubey_p
 */
public class WindowCornerResizer {

    private double dragOffsetX, dragOffsetY;

    public WindowCornerResizer(final ImageView windowResizer, final Stage stage,
            final double stageMinimumWidth, final double stageMinimumHeight) {
        windowResizer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                dragOffsetX = (stage.getX() + stage.getWidth()) - e.getScreenX();
                dragOffsetY = (stage.getY() + stage.getHeight()) - e.getScreenY();
                e.consume();
            }
        });
        windowResizer.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                ObservableList<Screen> screens = Screen.getScreensForRectangle(
                        stage.getX(), stage.getY(), 1, 1);
                final Screen screen;
                if (screens.size() > 0) {
                    screen = Screen.getScreensForRectangle(stage.getX(),
                            stage.getY(), 1, 1).get(0);
                } else {
                    screen = Screen.getScreensForRectangle(0, 0, 1, 1).get(0);
                }
                Rectangle2D visualBounds = screen.getVisualBounds();
                double maxX = Math.min(visualBounds.getMaxX(), e.getScreenX() + dragOffsetX);
                double maxY = Math.min(visualBounds.getMaxY(), e.getScreenY() - dragOffsetY);
                stage.setWidth(Math.max(stageMinimumWidth, maxX - stage.getX()));
                stage.setHeight(Math.max(stageMinimumHeight, maxY - stage.getY()));
                e.consume();
            }
        });
    }
}
