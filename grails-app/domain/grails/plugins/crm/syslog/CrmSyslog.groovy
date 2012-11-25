/*
 * Copyright (c) 2012 Goran Ehrsson.
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

package grails.plugins.crm.syslog

class CrmSyslog {

    public static final String LEVEL_INFO = "info"
    public static final String LEVEL_WARNING = "warning"
    public static final String LEVEL_ERROR = "error"

    Date dateCreated
    String event
    String level
    String message

    static constraints = {
        event(maxSize: 64, blank: false)
        level(maxSize: 8, blank: false, inList: [LEVEL_INFO, LEVEL_WARNING, LEVEL_ERROR])
        message(maxSize: 2000, blank: false)
    }

    static mapping = {
        version false
    }

    String toString() {
        level + ' ' + event + ' ' + dateCreated.toString()
    }
}
