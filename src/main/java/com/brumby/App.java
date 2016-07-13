package com.brumby;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * Hello world!
 */
public class App extends AbstractVerticle {

    private PersonDataSource dataSource;

    public App(PersonDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        Router router = Router.router(vertx);

        router.route("/")
                .handler(routingContext ->
                        routingContext
                                .response()
                                .putHeader("content-type", "text/html")
                                .end("<h1>Hello from my first Vert.x 3 application</h1>"));

        router.get("/person/:firstname/:surname")
                .handler(this::handleGetPerson);

        router.put("/person/:firstname/:surname")
                .handler(this::handlePutPerson);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", 8088), result -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }

    private void handlePutPerson(RoutingContext req) {
        String firstName = req.request().getParam("firstname");
        String surname = req.request().getParam("surname");
        dataSource.putPerson(firstName, surname, RandomUtils.nextInt(0, 99));
        req.response().end();

    }

    private void handleGetPerson(RoutingContext req) {
        String firstName = req.request().getParam("firstname");
        String surname = req.request().getParam("surname");
        List<Person> people = dataSource.getPeople(firstName, surname);
        req.response().end(Json.encodePrettily(people));
    }

    public static void main(String[] args) {
        PersonDataSource dataSource = new HashmapPersonDataSource();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new App(dataSource));
    }
}
