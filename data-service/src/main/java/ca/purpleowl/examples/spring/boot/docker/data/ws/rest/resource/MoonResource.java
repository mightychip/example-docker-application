package ca.purpleowl.examples.spring.boot.docker.data.ws.rest.resource;

import ca.purpleowl.examples.spring.boot.docker.data.annotations.JSONGET;
import ca.purpleowl.examples.spring.boot.docker.data.annotations.JSONPOST;
import ca.purpleowl.examples.spring.boot.docker.data.annotations.JSONPUT;
import ca.purpleowl.examples.spring.boot.docker.data.jpa.entity.Moon;
import ca.purpleowl.examples.spring.boot.docker.data.jpa.repository.MoonRepository;
import ca.purpleowl.examples.spring.boot.docker.data.ws.rest.asset.MoonAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//TODO JavaDoc me!!!
@RestController
@Scope("request")
@RequestMapping("/moon")
public class MoonResource extends AbstractSimpleResource<MoonAsset, Moon> {
    //This is left not final so that we can tinker with it in tests.  There's
    //probably a better way to do this with autowiring, but this is fine enough
    //for now.
    private static Logger logger = LoggerFactory.getLogger(MoonResource.class);
    private final MoonRepository moonRepo;

    //FIXME Hey, it looks like there's a bunch of boilerplate stuff here that could be removed.  You know what that means!!


    @SuppressWarnings("MVCPathVariableInspection")
    @JSONGET(path = "/{id}")
    public ResponseEntity<MoonAsset> getMoon(@PathVariable("id") String id) {
        //This is kinda dangerous and you should sanitize your input and such,
        //but I'm lazy right now.  I'll make a second pass at this later. This
        //is also an example and a toy, not enterprise-level goodness.
        try {
            Optional<Moon> result = moonRepo.findByMoonId(Long.parseLong(id));

            if(result.isPresent()) {
                //200?
                logger.debug(String.format(FIND_BY_ID_SUCCESS_MSG,
                                           Moon.class.getSimpleName(),
                                           id));

                return ResponseEntity.status(HttpStatus.OK)
                                     .body(convertToAsset(result.get()));
            } else {
                //404
                logger.warn(String.format(FIND_BY_ID_FAILURE_MSG,
                                          Moon.class.getSimpleName(),
                                          id));

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .build();
            }
        } catch(NumberFormatException nfe) {
            logger.warn(String.format(INVALID_ID_MSG,
                                      id,
                                      "getMoon"),
                        nfe);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        }
        //TODO Consider if this is actually a good approach...
        catch(Exception e) {
            logger.error(String.format(UNEXPECTED_EXCEPTION_MSG,
                                       "getMoon",
                                       e.getMessage()),
                         e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .build();
        }
    }

    @JSONPOST
    public ResponseEntity<Long> createMoon(@RequestBody MoonAsset asset) {
        if(asset == null || asset.getId() != null) {
            logger.warn(String.format(GENERIC_BAD_REQUEST_MSG, "createMoon"));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        }

        Moon entity = convertToEntity(asset);

        entity = moonRepo.save(entity);

        if(entity.getId() != null) {
            logger.debug(String.format(CREATE_ENTITY_SUCCESS_MSG, Moon.class.getSimpleName(), entity.getId()));

            //We still pas them back the Moon ID they gave us... nobody needs to know the real moon ID, right??
            //TODO Better make sure of this.
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(entity.getMoonId());
        } else {
            logger.error(String.format(CREATE_ENTITY_FAILURE_MSG, Moon.class.getSimpleName()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .build();
        }
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @JSONPUT(path = "/{id}")
    public ResponseEntity<Long> updateMoon(@PathVariable("id") String id, @RequestBody MoonAsset asset) {
        if(asset == null || asset.getId() != null) {
            logger.warn(GENERIC_BAD_REQUEST_MSG,
                        "updateMoon");
        }

        try {
            Long moonId = Long.parseLong(id);

            if(moonRepo.existsByMoonId(moonId)) {
                //TODO Should probably make a method to update entity from asset!!
                Optional<Moon> originalMoon = moonRepo.findByMoonId(moonId);
                Moon newMoon = convertToEntity(asset);
                //whatever... this will be fixed when i refactor...
                //noinspection ConstantConditions
                newMoon.setId(originalMoon.get().getId());
                moonRepo.save(newMoon);

                return ResponseEntity.status(HttpStatus.OK)
                                     .body(moonId);
            } else {
                logger.warn(String.format(INVALID_ID_MSG,
                                          Moon.class.getSimpleName(),
                                          id));

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .build();
            }
        } catch(NumberFormatException nfe) {
            logger.warn(String.format(INVALID_ID_MSG,
                                      id,
                                      "updateMoon"),
                        nfe);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .build();
        } catch(Exception e) {
            logger.error(String.format(UNEXPECTED_EXCEPTION_MSG,
                                       "updateMoon",
                                       e.getMessage()),
                         e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .build();
        }
    }

    //No deletes... you cannot destroy moons... you cannot destroy planets.
    //Don't add bad data pls.  This prevents accidents and malicious use.

    @Autowired
    public MoonResource(MoonRepository moonRepo) {
        this.moonRepo = moonRepo;
    }

    @Override
    MoonAsset convertToAsset(Moon model) {
        return new MoonAsset(model.getMoonId(),
                             model.getMoonName(),
                             model.getSystemId(),
                            (model.getPlanet() == null ? null : model.getPlanet().getPlanetId()));
    }

    @Override
    Moon convertToEntity(MoonAsset asset) {
        Moon returnMe = new Moon().setMoonId(asset.getId())
                                  .setMoonName(asset.getName())
                                  .setSystemId(asset.getSystemId());

        //TODO Add lookup for planet, throw error if moon has no planet... add plumbing to allow that if necessary.
        //For now........ moons can have a null planet.  ROGUE MOON!!!!
        returnMe.setPlanet(null);

        return returnMe;
    }
}
