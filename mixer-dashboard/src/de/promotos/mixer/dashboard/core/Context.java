/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.core;

import com.mixer.api.resource.MixerUser;
import de.promotos.mixer.dashboard.scene.SceneFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Promotos
 */
public class Context {

    private Stage stage;
    private String userName;
    private MixerApiDelegate mixer;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Context() {
        mixer = new MixerApiDelegate();
    }
    
    public MixerUser identifyUser(String userName) throws ApiException {
        MixerUser user = mixer.identifyUser(userName);
        if (user != null) {
            this.userName = user.username;
        }
        return user;
    }
    
    public String getUsername() {
        return this.userName;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void showSelectUserScene() {
        showScene(new SceneFactory(this).selectUserScene());
    }

    public void showMainScene() {
        showScene(new SceneFactory(this).mainScene());
    }
    
    public void scheduleAtFixedRate(Runnable t, long init, long period, TimeUnit tu) {
        scheduler.scheduleAtFixedRate(t, init, period, tu);
    }
    
    public void shutdown() {
        scheduler.shutdown();
    }
    
    private void showScene(Parent parent) {
        final Scene s = new Scene(parent, 600, 400);
        stage.setScene(s);
    }
}
