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
package org.apache.ftpserver.interfaces;


/**
 * Ftp statistics listener interface.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
interface FtpStatisticsListener {

    /**
     * User file upload notification.
     */
    void notifyUpload();

    /**
     * User file download notification.
     */
    void notifyDownload();

    /**
     * User file delete notification.
     */
    void notifyDelete();

    /**
     * New user login notification.
     */
    void notifyLogin();

    /**
     * User logout notification.
     */
    void notifyLogout();

    /**
     * Connection open/close notification
     */
    void notifyConnection();

}