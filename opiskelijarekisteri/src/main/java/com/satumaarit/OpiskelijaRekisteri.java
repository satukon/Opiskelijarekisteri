package com.satumaarit;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Pääluokka
 * @author satu
 */
public class OpiskelijaRekisteri extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("OpiskelijaRekisteriFXML"));
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(725);
        stage.setTitle("Opiskelijarekisteri");
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OpiskelijaRekisteri.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * 
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        launch(args);

    }

}
