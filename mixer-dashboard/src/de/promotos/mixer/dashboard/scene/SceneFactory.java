/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.scene;

import de.promotos.mixer.dashboard.core.Context;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Promotos
 */
public class SceneFactory {
    
    private static final Logger LOG = Logger.getLogger(SceneFactory.class.getName());
    private static final String MAIN_SCENE = "MainScene.fxml";
    
    private Context context;
    
    public SceneFactory(Context ctx) {
        this.context = ctx;
    }
    
    public Parent mainScene() {
        return loadSceneByName(MAIN_SCENE);
    }
    
    private Parent loadSceneByName(final String name) {
        try {
            LOG.log(Level.INFO, "Load scene {0}", name);
            
            FXMLLoader loader = new FXMLLoader(loadResourceByName(name));
            final Parent root = loader.load();

            ((Contextable) loader.getController()).setContext(context);

            return root;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        throw new IllegalStateException("Error loading the scene " + name);
    }
    
    private URL loadResourceByName(final String name) {
        return SceneFactory.class.getResource(name);
    }
}
