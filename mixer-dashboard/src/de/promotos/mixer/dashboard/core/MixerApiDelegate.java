/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.core;

import com.mixer.api.MixerAPI;
import com.mixer.api.resource.MixerUser;
import com.mixer.api.response.users.UserSearchResponse;
import com.mixer.api.services.impl.UsersService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Promotos
 */
public class MixerApiDelegate {

    private static final Logger LOG = Logger.getLogger(MixerApiDelegate.class.getName());
    
    public MixerUser identifyUser(String user) throws ApiException {
        if (user == null || user.length() == 0) {
            throw new ApiException("User name must not be empty.");
        }
        final MixerAPI mixer = new MixerAPI();
        try {
            UserSearchResponse response = mixer.use(UsersService.class).search(user).get(10, TimeUnit.SECONDS);
            if (response.size() > 1) {
                LOG.log(Level.INFO, String.format("%s user names matches \"%s\"", response.size(), user));
                throw new ApiException( String.format("User name \"%s\" is not unique.", user) );
            } else if( response.isEmpty()) {
                throw new ApiException( String.format("User name \"%s\" could not be found.", user) );
            }
            return response.get(0);
        } catch (InterruptedException | ExecutionException ex) {
            throw ApiException.createCommon(ex);
        } catch (TimeoutException ex) {
            throw ApiException.createTimeout(ex);
        }
    }

}
