package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.resource;

import ca.purpleowl.examples.spring.boot.docker.data.annotations.JSONDELETE;
import ca.purpleowl.examples.spring.boot.docker.data.annotations.JSONGET;
import ca.purpleowl.examples.spring.boot.docker.data.annotations.JSONPOST;
import ca.purpleowl.examples.spring.boot.docker.data.annotations.JSONPUT;
import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Example;
import ca.purpleowl.examples.spring.boot.docker.data.jpa.repository.ExampleRepository;
import ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset.ExampleAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

/**
 * This is a simple REST service.  At this stage in my series of posts, we're just going to implement some basic stuff.
 *
 * This Resource allows the user to Create, Read, Update and Delete Example entities from the underlying storage
 * mechanism with little knowledge of the mechanism itself.  Entities from the {@link ExampleRepository} are marshalled
 * and unmarshalled to/from JSON to communicate with the client.
 *
 * More than anything, this serves as a very simple example of a REST Resource/Controller.  We also return
 * {@link ResponseEntity} objects to allow us to specify status codes, indicating bad requests, internal errors, etc.
 */
@RestController
@Scope("request")
@RequestMapping("/example")
public class ExampleResource extends AbstractSimpleResource<ExampleAsset, Example> {
    private static Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    private final ExampleRepository exampleRepo;

    /**
     * Any Autowiring should happen at the constructor level to allow for easier testing.  Also, IntelliJ will do that
     * ugly yellow highlighting if you don't do this.  If making your code testable isn't enough, hopefully being
     * annoyed by IDE warnings is!!
     *
     * @param exampleRepo - An {@link ExampleRepository} provided by Spring's IoC mechanism.  (No, not the International Olympics Committee)
     */
    @Autowired
    public ExampleResource(ExampleRepository exampleRepo) {
        this.exampleRepo = exampleRepo;
    }

    /**
     * Get a list of all {@link Example} entities storage in the persistent storage mechanism.
     *
     * @return A {@link ResponseEntity} containing an appropriate HTTP Status and - if present - any entities found.
     */
    @JSONGET
    public ResponseEntity<ExampleAsset[]> getExamples() {
        Long count = exampleRepo.count();

        if(count > 0) {
            Iterator<Example> result = exampleRepo.findAll().iterator();

            ExampleAsset[] assets = new ExampleAsset[count.intValue()];
            int i = 0;

            while (result.hasNext()) assets[i++] = convertToAsset(result.next());

            logger.debug(LIST_ENTITIES_SUCCESS_MSG,
                         count,
                         Example.class.getSimpleName());

            return ResponseEntity.status(HttpStatus.OK)
                                 .body(assets);
        } else {
            logger.warn(LIST_ENTITIES_FAILURE_MSG,
                        Example.class.getSimpleName());

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ExampleAsset[0]);
        }
    }

    /**
     * Provide a numeric ID as a path argument, get an {@link Example} entity represented in JSON by an {@link ExampleAsset} if
     * it exists under that ID.  If one doesn't exist under that ID, receive a 404.  If the provided ID was non-numeric
     * or no ID was provided at all, receive a 400.
     *
     * @param id - A numeric value representing the ID of the {@link Example} entity you wish to retrieve.
     * @return A {@link ResponseEntity} object containing the appropriate HTTP Status and possibly the desired entity.
     */
    @JSONGET(path = "/{id}")
    public ResponseEntity<ExampleAsset> getExample(@PathVariable(name = "id") String id) {
        try {
            Optional<Example> result = exampleRepo.findById(Long.parseLong(id));

            if(result.isPresent()) {
                logger.debug(FIND_BY_ID_SUCCESS_MSG,
                             Example.class.getSimpleName(),
                             id);

                return ResponseEntity.status(HttpStatus.OK)
                                     .body(convertToAsset(result.get()));
            } else {
                logger.warn(FIND_BY_ID_FAILURE_MSG,
                            Example.class.getSimpleName(),
                            id);

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .build();
            }
        } catch(NumberFormatException nfe) {
            logger.warn(INVALID_ID_MSG,
                        id,
                        "getExample");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        } catch(IllegalArgumentException iae) {
            //This really shouldn't happen... so issue a 500.
            logger.error(UNEXPECTED_EXCEPTION_MSG,
                         "getExample",
                         iae.getMessage());

            iae.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .build();
        }
    }

    /**
     * Provide a {@link ExampleAsset} and have it saved to the storage mechanism.  Successful saves result in a {@link ResponseEntity}
     * carrying the ID of the stored data.  Forgetting to provide an {@link ExampleAsset} result in a 400.  If the save
     * doesn't work, then the result is obviously a 500.  The ID of the Asset provided must be null.
     *
     * TODO Find a way to ignore the ID here... do we ignore it on this end or is it configuration of ExampleAsset???
     *
     * @param asset - A {@link ExampleAsset} populated the way you'd like it to be stored.  Note that the ID will be ignored.
     * @return A {@link ResponseEntity} with an appropriate status code and - if applicable - the ID of the stored data.
     */
    @JSONPOST
    public ResponseEntity<Long> createExample(@RequestBody ExampleAsset asset) {
        if(asset == null || asset.getId() != null) {
            logger.warn(GENERIC_BAD_REQUEST_MSG,
                        "createExample");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        }

        Example entity = convertToEntity(asset);

        entity = exampleRepo.save(entity);

        if(entity.getId() != null) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(entity.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .build();
        }
    }

    /**
     * Update an existing {@link Example} by the provided ID, using the contents of the provided {@link ExampleAsset}.
     * A {@link ResponseEntity} is returned,
     *
     * If you forget to provide the {@link ExampleAsset}.
     * @param id - A {@link Long} value representing the ID of the desired {@link Example}.
     * @param asset - A {@link ExampleAsset} object populated to update the existing {@link Example}.
     * @return A {@link ResponseEntity} containing the ID of the updated {@link Example} if applicable, and an appropriate HTTP Status code.
     */
    @JSONPUT(path = "/{id}")
    public ResponseEntity<Long> updateExample(@PathVariable("id") String id,
                                              @RequestBody ExampleAsset asset) {
        if(asset == null) {
            logger.warn(GENERIC_BAD_REQUEST_MSG,
                        "updateExample");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        }

        try {
            if(exampleRepo.existsById(Long.parseLong(id))) {
                Example entity = convertToEntity(asset).setId(Long.parseLong(id));

                entity = exampleRepo.save(entity);

                return ResponseEntity.status(HttpStatus.OK)
                                     .body(entity.getId());
            } else {
                logger.warn(INVALID_UPDATE_MSG,
                            Example.class.getSimpleName(),
                            id);

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .build();
            }
        } catch(NumberFormatException nfe) {
            logger.warn(INVALID_ID_MSG,
                        id,
                        "updateExample");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        }
    }

    /**
     * Delete the {@link Example} with the provided ID.  If no {@link Example} exists with this ID, provide a 404.
     *
     * @param id - A {@link Long} value representing the ID of the desired {@link Example}
     * @return A {@link ResponseEntity} containing only the appropriate HTTP Status code.
     */
    @JSONDELETE(path = "/{id}")
    public ResponseEntity deleteExample(@PathVariable("id") String id) {
        try {
            Long longId = Long.parseLong(id);

            if(exampleRepo.existsById(longId)) {
                exampleRepo.deleteById(longId);

                logger.debug(DELETE_BY_ID_SUCCESS_MSG,
                             Example.class.getSimpleName());

                return ResponseEntity.status(HttpStatus.OK)
                                     .build();
            } else {
                logger.warn(DELETE_BY_ID_FAILURE_MSG,
                            Example.class.getSimpleName(),
                            id);

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .build();
            }
        } catch(NumberFormatException nfe) {
            logger.error(INVALID_ID_MSG,
                         id,
                         "deleteExample");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        }
    }

    @Override
    ExampleAsset convertToAsset(Example model) {
        return new ExampleAsset().setId(model.getId())
                                 .setMessage(model.getMessage());
    }

    @Override
    Example convertToEntity(ExampleAsset asset) {
        return new Example().setId(asset.getId())
                            .setMessage(asset.getMessage());
    }
}
