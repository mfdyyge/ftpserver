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
package org.apache.ftpserver;

import java.io.File;

/**
 * Ftp file upload/download/delete listener interface.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
interface FileListener {

    /**
     * User file upload notification.
     */
    void notifyUpload(final File file, final String sessionId);

    /**
     * User file download notification.
     */
    void notifyDownload(final File file, final String sessionId);

    /**
     * User file delete notification.
     */
    void notifyDelete(final File file, final String sessionId);
}
