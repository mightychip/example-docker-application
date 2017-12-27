package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.resource

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Moon
import ca.purpleowl.examples.spring.boot.docker.data.jpa.repository.MoonRepository
import ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset.MoonAsset
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class MoonResourceSpec extends Specification {
    def mockLogger
    def mockRepo
    MoonResource fixture

    @SuppressWarnings("GroovyAccessibility")//I think this'll become a problem as of Java 9 or something like that?
    def setup() {
        mockLogger = Mock(Logger)
        mockRepo = Mock(MoonRepository)
        fixture = new MoonResource(mockRepo)
        fixture.logger = mockLogger //This is what gets the warning.
    }

    //Test getMoon(id)

    def "get existing Moon by MoonId"() {
        given:
        mockRepo.findByMoonId(_ as Long) >> Optional.of(new Moon(id: 1L, moonId: 420L, moonName: "Moon Name", planet: null, systemId: 42L))

        when:
        def result = fixture.getMoon("1")

        then:
        result != null
        result instanceof ResponseEntity<MoonAsset>
        result.statusCode == HttpStatus.OK
        def payload = result.body
        payload instanceof MoonAsset
        payload.id == 420L
        payload.name == "Moon Name"
        1 * mockLogger.debug('Found Moon with ID: 1')
    }
}
