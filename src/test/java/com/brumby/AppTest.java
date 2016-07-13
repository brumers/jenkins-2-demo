package com.brumby;


import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class AppTest {

    private Vertx vertx;
    private PersonDataSource dataSource = new HashmapPersonDataSource();

    @Before
    public void setup(TestContext context) {
        vertx = Vertx.vertx();
        App app = new App(dataSource);
        vertx.deployVerticle(app, context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }


    @Test
    public void should_add_new_person_to_map(TestContext context) {
        final Async async = context.async();
        vertx.createHttpClient().put(8080, "localhost", "/person/bob/smith",
                response -> {
                    context.assertEquals(200, response.statusCode());
                    context.assertNotNull(dataSource.getPeople("bob", "smith"));
                    context.assertEquals(0, dataSource.getPeople("bob", "smith").size());
                    async.complete();
                }).end();
    }
}
