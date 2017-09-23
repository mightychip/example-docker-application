package ca.purpleowl.examples.spring.boot.docker.data.ws.resource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/example")
public class ExampleResource {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String example() {
        return "The example works!";
    }
}
