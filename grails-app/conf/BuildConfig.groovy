grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.repos.default = "crm"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn"
    repositories {
        grailsHome()
        mavenRepo "http://labs.technipelago.se/repo/crm-releases-local/"
        grailsCentral()
    }
    dependencies {
    }

    plugins {
        build(":tomcat:$grailsVersion",
                ":release:2.2.1") {
            export = false
        }
        runtime(":hibernate:$grailsVersion") {
            export = false
        }

        test(":spock:0.7") { export = false }
        test(":codenarc:0.17") { export = false }

        compile(":platform-core:1.0.RC5") { excludes 'resources' }

        compile "grails.crm:crm-core:latest.integration"
    }
}
