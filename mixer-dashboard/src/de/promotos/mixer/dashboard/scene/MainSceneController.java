/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.scene;

import com.mixer.api.resource.MixerUser;
import de.promotos.mixer.dashboard.core.ApiException;
import de.promotos.mixer.dashboard.core.Context;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Promotos
 */
public class MainSceneController implements Initializable, Contextable {

    private Context context;
    
    @FXML
    private TabPane tpMain;
    @FXML
    private Label lbUsername;
    @FXML
    private Label lbExperience;
    @FXML
    private Label lbSparks;
    @FXML
    private Label lbOnline;
    @FXML
    private Tab pnlChannel;
    @FXML
    private AnchorPane pnlChat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
        context.scheduleAtFixedRate(() -> {
            try {
                final MixerUser user = context.identifyUser(context.getUsername());
                Platform.runLater(() -> {
                    final Logger LOG = Logger.getLogger(MainSceneController.class.getName());
                    LOG.log(Level.FINE, "Backgound update ui information.");
                    final NumberFormat num = DecimalFormat.getInstance();

                    /* Top Panel */
                    lbUsername.setText(user.username);
                    lbUsername.setTooltip(new Tooltip( String.format("email: %s", user.email != null ? user.email : "n/a")));
                    lbOnline.setText( String.format("Channel: %s", user.channel.online ? "online" : "offline"));
                    lbExperience.setText( String.format("Experience: %s", num.format(user.experience)) );
                    lbSparks.setText( String.format("Sparks: %s", num.format(user.sparks)) );
                });
            } catch (ApiException ex) {
                throw new IllegalStateException(ex);
            }            
        }, 1, 10, TimeUnit.SECONDS);
    }

}
