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
package org.apache.ftpserver.gui;

import javax.swing.JPanel;

import org.apache.ftpserver.interfaces.IFtpConfig;

/**
 * This is the base class of all the ftp panels.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
abstract class PluginPanel extends JPanel {

    private PluginPanelContainer container;
    
    /**
     * Constructor - set the container.
     */
    public PluginPanel(PluginPanelContainer container) {
        this.container = container;
    }
    
    /**
     * Get the container.
     */
    public PluginPanelContainer getContainer() {
        return container;
    }
    
    /**
     * Refresh the panel with the new ftp config
     */
    public abstract void refresh(IFtpConfig ftpConfig);
    
    
    /**
     * Can the plugin panel be displayed.
     */
    public abstract boolean canBeDisplayed();
}
