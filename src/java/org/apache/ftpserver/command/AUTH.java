// $Id$
/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ftpserver.command;

import org.apache.ftpserver.Command;
import org.apache.ftpserver.FtpRequestImpl;
import org.apache.ftpserver.FtpWriter;
import org.apache.ftpserver.RequestHandler;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.interfaces.IFtpConfig;

import java.io.IOException;

/**
 * This server supports explicit SSL support.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class AUTH implements Command {
    
    /**
     * Execute command
     */
    public void execute(RequestHandler handler,
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException, FtpException {
        
        // reset state variables
        request.resetState();
        
        // argument check
        if(!request.hasArgument()) {
            out.send(501, "AUTH", null);
            return;  
        }
        
        String authType = request.getArgument().toUpperCase();
        IFtpConfig fconfig = handler.getConfig();
        if(authType.equals("SSL")) {
            if(fconfig.getSocketFactory().getSSL() == null) {
                out.send(431, "AUTH", null);
            }
            else {
                out.send(234, "AUTH.SSL", null);
                try {
                    handler.createSecureSocket();
                }
                catch(FtpException ex) {
                    throw ex;
                }
                catch(Exception ex) {
                    fconfig.getLogger().warn("AUTH.execute()", ex);
                    throw new FtpException("AUTH.execute()", ex);
                }
            }
        }
        else {
            out.send(502, "AUTH", null);
        }
    }
}