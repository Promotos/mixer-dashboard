/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.promotos.mixer.dashboard.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Promotos
 */
public class ApiException extends Throwable {
    
    private static final Logger LOG = Logger.getLogger(ApiException.class.getName());
    
    public ApiException(String message) {
        super(message);
        LOG.log(Level.WARNING, message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        LOG.log(Level.WARNING, message, cause);
    }

    public static ApiException createCommon(Throwable cause) throws ApiException {
        throw new ApiException("Mixer api error.", cause);
    }
    
    public static ApiException createTimeout(Throwable cause) throws ApiException {
        throw new ApiException("Mixer api timeout.", cause);
    }
    
}
