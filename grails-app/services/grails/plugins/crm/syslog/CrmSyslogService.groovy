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

import grails.events.Listener

/**
 * Syslog service.
 * TODO Implement DSL and dynamic routing!
 */
class CrmSyslogService {

    @Listener(namespace = 'crm', topic = 'userCreated')
    def logCreatedUsers(user) {
        save("crmUser.created", CrmSyslog.LEVEL_INFO, "username=[${user.username}], name=[${user.name}], IP=[${user.ip}]")
    }

    @Listener(namespace = 'crm', topic = 'userDeleted')
    def logDeletedUsers(user) {
        save("crmUser.deleted", CrmSyslog.LEVEL_INFO, "username=[${user.username}], name=[${user.name}]")
    }

    @Listener(namespace = 'crm', topic = 'tenantCreated')
    def logCreatedTenants(tenant) {
        save("crmTenant.created", CrmSyslog.LEVEL_INFO, "tenant=[#${tenant.id} ${tenant.name}], username=[${tenant.account.user.username}]")
    }

    @Listener(namespace = 'crm', topic = 'tenantDeleted')
    def logDeletedTenants(tenant) {
        save("crmTenant.deleted", CrmSyslog.LEVEL_INFO, "tenant=[#${tenant.id} ${tenant.name}], username=[${tenant.account.user.username}]")
    }

    private CrmSyslog save(String event, String level, String msg) {
        new CrmSyslog(event: event, level: level, message: msg).save(failOnError: true, flush: true)
    }
}
