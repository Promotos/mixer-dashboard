/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.scene;

import com.google.common.util.concurrent.Futures;
import com.mixer.api.MixerAPI;
import com.mixer.api.resource.MixerUser;
import com.mixer.api.response.users.UserSearchResponse;
import com.mixer.api.services.impl.UsersService;
import com.mixer.api.util.ResponseHandler;
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

        
        // Construct an instance of the Mixer API such that we can query certain
// endpoints for data.
        MixerAPI mixer = new MixerAPI();

// Invoke the `UsersService.class` in order to access the methods within
// that service.  Then, assign a callback using Guava's FutureCallback
// class so we can act on the response.
        Futures.addCallback(mixer.use(UsersService.class).search("Promoto"), new ResponseHandler<UserSearchResponse>() {
            // Set up a handler for the response
            @Override
            public void onSuccess(UserSearchResponse response) {
                for (MixerUser user : response) {
                    System.out.println(user.username + ", online:" + user.channel.online);
                }
            }
        });

    }

    @Override
    public void setContext(Context context) {
    }

}
