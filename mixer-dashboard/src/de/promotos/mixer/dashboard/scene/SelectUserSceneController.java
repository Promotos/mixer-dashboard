/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.scene;

import de.promotos.mixer.dashboard.core.ApiException;
import de.promotos.mixer.dashboard.core.Context;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author Promotos
 */
public class SelectUserSceneController implements Initializable, Contextable {

    private Context context;
    
    @FXML
    private Label lblMessage;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnSelect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSelectClicked(ActionEvent event) {
        try {
            btnSelect.setDisable(true);
            lblMessage.setText("");
            lblMessage.setTooltip(null);
            
            final String username = txtUsername.getText();
            
            if (context.identifyUser(username) != null) {
                context.showMainScene();
            } else {
                throw new IllegalStateException("Api result -> null");
            }
            
        } catch (ApiException ex) {
            lblMessage.setText(ex.getMessage());
            lblMessage.setTooltip(new Tooltip(ex.getMessage()));
            txtUsername.requestFocus();
        } finally {
            btnSelect.setDisable(false);            
        }
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
    
}
