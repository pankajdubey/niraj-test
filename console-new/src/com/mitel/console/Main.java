package com.mitel.console;

import com.mitel.console.home.ConsoleConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <code>ConsoleApplicationMain</code> is root class for new UI pages driven by
 * JavaFx.
 *
 * This class used to load the main home page layout Xml file "ConsoleHome.fxml"
 * as per released "Mitel Design Guidelines" for desktop applications.
 *
 * <p/>
 * <
 * code>FXML</code> is a XML document/file which used to define the layout of
 * the page (i.e. controls placement on a particular page) and further parsed by
 * the JavaFx Apie for conditional/dynamic manipulation.
 *
 *
 * @author dubey_p
 */
public class Main extends Application
        implements ConsoleConstants {

    private Stage stage;
//    private Rectangle rect;
    private FXMLLoader fXMLLoader;
    private static Main consoleApplication = null;
    private final Rectangle rect = new Rectangle(stageMinimumWidth, stageMinimumHeight);  
    
    public static Main getConsoleInstance() {
        return consoleApplication;
    }

    @Override
    public void start(Stage stage) throws Exception {

        consoleApplication = this;
        setStage(stage);

        VBox root;
        setXmlLoader(new FXMLLoader(getClass().getResource(HOME_LAYOUT)));
        root = (VBox) getXmlLoader().load();

        stage.setTitle(TITLE);//to be shown from windows toolbar
        Image icon = new Image(getClass().getResourceAsStream(APP_FAVICON));
        stage.getIcons().add(icon);//to be shown from windows toolbar

        
        rect.setArcHeight(20.0);
        rect.setArcWidth(20.0);
        root.setClip(rect);
        
        Scene scene = new Scene(root, stageMinimumWidth, stageMinimumHeight, Color.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);//removing default windows title and frame.
        
        stage.setScene(scene);

        stage.show();

    }

    public FXMLLoader getXmlLoader() {
        return fXMLLoader;
    }

    public void setXmlLoader(FXMLLoader fXMLLoader) {
        this.fXMLLoader = fXMLLoader;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
       
        
    }

    public Rectangle getRect() {
        return rect;
    }
}
