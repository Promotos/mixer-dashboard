/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard;

import de.promotos.mixer.dashboard.core.Context;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Promotos
 */
public class MixerDashboardApplication extends Application {
    
    private static final Logger LOG = Logger.getLogger(MixerDashboardApplication.class.getName());
    
    private Context context;
    
    @Override
    public void start(Stage stage) {
        LOG.log(Level.INFO, "Application in start level.");
        context = new Context();
        context.setStage(stage);
        
        context.showSelectUserScene();
        
        stage.setTitle("mixer-dashboard");
        stage.show();
        LOG.log(Level.INFO, "Application show.");
    }

    @Override
    public void stop() throws Exception {
        context.shutdown();
        LOG.log(Level.INFO, "Application in stop level.");
        /* TODO: Required because mixer api left stuff open. */
        System.exit(0);
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
