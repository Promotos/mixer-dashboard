/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard;

import de.promotos.mixer.dashboard.core.Context;
import de.promotos.mixer.dashboard.scene.SceneFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Promotos
 */
public class MixerDashboardApplication extends Application {
    
    @Override
    public void start(Stage stage) {
        final Context context = new Context();
        final Scene scene = new Scene(new SceneFactory(context).mainScene(), 600, 400);

        stage.setTitle("mixer-dashboard");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
