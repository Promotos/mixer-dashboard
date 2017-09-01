/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.scene;

import de.promotos.mixer.dashboard.core.Context;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Promotos
 */
public class MainSceneController implements Initializable, Contextable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnHelloWorldClicked(ActionEvent event) {
        System.out.println("Hello World Clicked!!!!");
    }

    @Override
    public void setContext(Context context) {
        System.out.println("Set context");
    }
    
}
