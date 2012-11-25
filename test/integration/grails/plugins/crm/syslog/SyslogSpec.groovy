package grails.plugins.crm.syslog

import org.grails.plugin.platform.events.EventMessage

/**
 * Test syslog feature.
 */
class SyslogSpec extends grails.plugin.spock.IntegrationSpec {

    def grailsEventsPublisher

    def "test event logging"() {
        given:
        def count = CrmSyslog.count()

        when:
        grailsEventsPublisher.event(new EventMessage("userCreated", [username: "test", name: "Test User", ip: '127.0.0.1'], "crm", true)).waitFor(2000)

        then:
        CrmSyslog.count() == ++count

        when:
        grailsEventsPublisher.event(new EventMessage("userDeleted", [username: "test", name: "Test User"], "crm", true)).waitFor(2000)

        then:
        CrmSyslog.count() == ++count
    }
}
