package sample.helloworld

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

class HelloClientSpec extends Specification {
    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer =
            ApplicationContext.run(EmbeddedServer)

    @Shared
    HelloClient client = embeddedServer
            .applicationContext
            .getBean(HelloClient)


    void "test hello world response"() {
        expect:
        client.hello().blockingGet() == "Hello World"
    }

}