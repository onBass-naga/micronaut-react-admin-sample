package sample.helloworld;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.annotation.security.PermitAll;

@Controller("/hello")
public class HelloController {

    @Get(produces = MediaType.TEXT_PLAIN)
    @PermitAll
    public String index() {
        return "Hello World";
    }
}