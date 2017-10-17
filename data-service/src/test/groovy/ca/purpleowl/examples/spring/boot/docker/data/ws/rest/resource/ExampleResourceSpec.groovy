package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.resource

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Example
import ca.purpleowl.examples.spring.boot.docker.data.jpa.repository.ExampleRepository
import ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset.ExampleAsset
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

/**
 * This is a Spock Unit Test for the ExampleResource.  Spock goes a long way toward giving you the ability to do TRUE
 * unit tests, mocking absolutely everything except the unit under test.  Sometimes, I feel like JUnit falls short of
 * that.
 */
class ExampleResourceSpec extends Specification {
    def mockLogger
    def mockRepo
    ExampleResource exampleResource

    //Assigning values to private fields from outside is technically not allowed... but it makes testing easier.
    @SuppressWarnings("GroovyAccessibility")
    def setup() {
        mockLogger = Mock(Logger)
        mockRepo = Mock(ExampleRepository)
        exampleResource = new ExampleResource(mockRepo)
        exampleResource.logger = mockLogger
    }

    //Test getExample(id)
    def "get existing record by ID"() {
        given:
        mockRepo.findById(_ as Long) >> Optional.of(new Example(id: 1, message: "test"))

        when:
        def result = exampleResource.getExample("1")

        then:
        result != null
        result instanceof ResponseEntity<ExampleAsset>
        result.statusCode == HttpStatus.OK
        def payload = result.body
        payload instanceof ExampleAsset
        payload.id == 1
        payload.message == "test"
        1 * mockLogger.debug('Found Example with ID: 1')
    }

    def "get non-existing record by ID"() {
        given:
        mockRepo.findById(_ as Long) >> Optional.empty()

        when:
        def result = exampleResource.getExample("2")

        then:
        result != null
        result instanceof ResponseEntity<ExampleAsset>
        result.statusCode == HttpStatus.NOT_FOUND
        result.body == null
        1 * mockLogger.warn('Couldn\'t find Example with ID: 2')
    }

    def "provide bad ID"() {
        when:
        def result = exampleResource.getExample("A")

        then:
        result != null
        result instanceof ResponseEntity<ExampleAsset>
        result.statusCode == HttpStatus.BAD_REQUEST
        result.body == null
        1 * mockLogger.warn('An invalid ID of A was provided to getExample!', _ as NumberFormatException)
    }

    //Since we control the mock of the Repo, we have to manually kick out this exception, hence being
    //a "simulation."  Technically, I don't think this could happen at all, come to think of it...
    def "simulate provision of empty ID to getExample"() {
        given:
        mockRepo.findById(_ as Long) >> { throw new IllegalArgumentException("mocked") }

        when:
        def result = exampleResource.getExample("1")

        then:
        result != null
        result instanceof ResponseEntity<ExampleAsset>
        result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
        result.body == null
        1 * mockLogger.error('Wow!! Something bad happened in getExample!! mocked', _ as IllegalArgumentException)
    }
}
