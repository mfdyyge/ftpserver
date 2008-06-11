/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */  

package org.apache.ftpserver.ftplet;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This ftplet calls other ftplet methods and returns appropriate return value.
 */
public class DefaultFtpletContainer implements FtpletContainer {
    
    private final Logger LOG = LoggerFactory.getLogger(DefaultFtpletContainer.class);
    
    private Map<String, Ftplet> ftplets = new ConcurrentHashMap<String, Ftplet>();

    public void dispose() {
        
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {
            try {
                entry.getValue().destroy();
            }
            catch(Exception ex) {
                LOG.error(entry.getKey() + " :: FtpletHandler.destroy()", ex);
            }
        }
        ftplets.clear();
    }
    
    public void addFtplet(String name, Ftplet ftplet) {
        if(getFtplet(name) != null) {
            throw new IllegalArgumentException("Ftplet with name \"" + name + "\" already registred with container");
        }
        
        ftplets.put(name, ftplet);
    }

    public Ftplet removeFtplet(String name) {
        Ftplet ftplet = ftplets.get(name);
        
        if(ftplet != null) {
            ftplets.remove(name);
            return ftplet;
        } else {
            return null;
        }
    }
    
    /**
     * Get Ftplet for the given name.
     */
    public Ftplet getFtplet(String name) {
        if(name == null) {
            return null;
        }
       
        return ftplets.get(name);
    }
    
    /**
     * Destroy all ftplets.
     */
    public void destroy() {
        dispose();
    }
    
    /**
     * Call ftplet onConnect.
     */
    public FtpletEnum onConnect(FtpSession session) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {
            retVal = entry.getValue().onConnect(session);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onDisconnect.
     */
    public FtpletEnum onDisconnect(FtpSession session) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onDisconnect(session);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }
    
    /**
     * Call ftplet onLogin.
     */
    public FtpletEnum onLogin(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onLogin(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /** 
     * Call ftplet onDeleteStart.
     */
    public FtpletEnum onDeleteStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onDeleteStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    
    /**
     * Call ftplet onDeleteEnd.
     */
    public FtpletEnum onDeleteEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onDeleteEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onUploadStart.
     */
    public FtpletEnum onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onUploadStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onUploadEnd.
     */
    public FtpletEnum onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onUploadEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onDownloadStart.
     */
    public FtpletEnum onDownloadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onDownloadStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onDownloadEnd.
     */
    public FtpletEnum onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onDownloadEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onRmdirStart.
     */
    public FtpletEnum onRmdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onRmdirStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onRmdirEnd.
     */
    public FtpletEnum onRmdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onRmdirEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onMkdirStart.
     */
    public FtpletEnum onMkdirStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onMkdirStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /** 
     * Call ftplet onMkdirEnd.
     */
    public FtpletEnum onMkdirEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onMkdirEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }
    
    /**
     * Call ftplet onAppendStart.
     */
    public FtpletEnum onAppendStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onAppendStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onAppendEnd.
     */
    public FtpletEnum onAppendEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onAppendEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onUploadUniqueStart.
     */
    public FtpletEnum onUploadUniqueStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onUploadUniqueStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }
    
    /**
     * Call ftplet onUploadUniqueEnd.
     */
    public FtpletEnum onUploadUniqueEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onUploadUniqueEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    /**
     * Call ftplet onRenameStart.
     */
    public FtpletEnum onRenameStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onRenameStart(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }
    
    /**
     * Call ftplet onRenameEnd.
     */
    public FtpletEnum onRenameEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onRenameEnd(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }
    
    /**
     * Call ftplet onSite.
     */
    public FtpletEnum onSite(FtpSession session, FtpRequest request) throws FtpException, IOException {
        FtpletEnum retVal = FtpletEnum.RET_DEFAULT;
        for(Entry<String, Ftplet> entry : ftplets.entrySet()) {

            retVal = entry.getValue().onSite(session, request);
            if(retVal == null) {
                retVal = FtpletEnum.RET_DEFAULT;
            }
            
            // proceed only if the return value is FtpletEnum.RET_DEFAULT
            if(retVal != FtpletEnum.RET_DEFAULT) {
                break;
            }
        }
        return retVal;
    }

    public void init(FtpletContext ftpletContext) throws FtpException {
        // dummy, forced by Ftplet API       
    }

    /**
     * @see FtpletContainer#getFtplets()
     */
    public Map<String, Ftplet> getFtplets() {
        return ftplets;
    }

    /**
     * @see FtpletContainer#setFtplets(Map)
     */
    public void setFtplets(Map<String, Ftplet> ftplets) {
        this.ftplets = ftplets;
    }


}