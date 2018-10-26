package sample.helloworld

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

class HelloControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    HttpClient client = HttpClient.create(embeddedServer.URL)

    private def retrieve = { HttpRequest request -> client.toBlocking().retrieve(request) }

    void "test hello world response"() {
        expect:
        retrieve(HttpRequest.GET('/hello')) == "Hello World"
    }
}
