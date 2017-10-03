package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.resource;

import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.BaseEntity;
import ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset.BaseAsset;

/**
 * This is the base class for the REST Resources.  Any common methods will get placed here.
 *
 * @param <A> - The REST Asset, which the JSON model is built from.
 * @param <B> - The JPA Entity, which gets stored in and retrieved from the storage mechanism.
 */
abstract class AbstractSimpleResource<A extends BaseAsset, B extends BaseEntity> {

    static final String CREATE_ENTITY_SUCCESS_MSG = "Created a new %s in the storage mechanism with ID %d";
    static final String CREATE_ENTITY_FAILURE_MSG = "Failed to create a new %s in the storage mechanism";
    static final String DELETE_BY_ID_FAILURE_MSG = "Couldn't find or delete %s with ID: %s";
    static final String DELETE_BY_ID_SUCCESS_MSG = "Found and deleted %s with ID: %s";
    static final String FIND_BY_ID_FAILURE_MSG = "Couldn't find %s with ID: %s";
    static final String FIND_BY_ID_SUCCESS_MSG = "Found %s with ID: %s";
    static final String GENERIC_BAD_REQUEST_MSG = "Bad request against %s";
    static final String INVALID_ID_MSG = "An invalid ID of %s was provided to %s!"; //We provide the method because logging at that granularity impacts performance.
    static final String INVALID_UPDATE_MSG = "An attempt was made to update a non-existent %s with ID %s";
    static final String LIST_ENTITIES_SUCCESS_MSG = "Found %d %s entities to return!";
    static final String LIST_ENTITIES_FAILURE_MSG = "No %s entities were found to return!";
    static final String UNEXPECTED_EXCEPTION_MSG = "Wow!! Something bad happened in %s!! %s";

    abstract A convertToAsset(B model);
    abstract B convertToEntity(A asset);
}
