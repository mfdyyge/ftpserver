/* ====================================================================
 * Copyright 2002 - 2004
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
 *
 *
 * $Id$
 */

package org.apache.ftpserver.gui;

import java.net.URL;
import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.JTree;

/**
 * Displays <a href="about.html">index.html</a> page. Help panel.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
class FtpAboutPanel extends PluginPanel {
    public static final String INDEX_PAGE = "org/apache/ftpserver/gui/help/index.html";

    /**
     * Constructor.
     */
    public FtpAboutPanel(CommonHandler commonHandler, JTree tree) {
        super(commonHandler, tree);
        setLayout(new BorderLayout());
        Component frame = commonHandler.getTopFrame();
        URL home = getClass().getClassLoader().getResource(INDEX_PAGE);
        MiniBrowserPane browser = new MiniBrowserPane(frame, home, true, true);
        add(browser, BorderLayout.CENTER);
    }

}