package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.*;
import javax.inject.*;
import javax.annotation.PostConstruct;
import io.vertx.sqlclient.PoolOptions;
import io.smallrye.mutiny.Multi;
import io.vertx.db2client.*;
import io.vertx.sqlclient.*;

@Path("/helloAMEEN")
public class AccountResource {

    @Inject
    io.vertx.db2client.DB2Pool client;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        gettingStarted();
		return "JI";

    }

	@Inject
@ConfigProperty(name = "myapp.schema.create", defaultValue = "false") 
boolean schemaCreate;

@PostConstruct
void config() {
    if (schemaCreate) {
        initdb();
    }
}

private void initdb() {
    // TODO
}

public void gettingStarted() {

    // Connect options
    DB2ConnectOptions connectOptions = new DB2ConnectOptions()
      .setPort(50000)
      .setHost("localhost")
      .setDatabase("AHABIBDB")
      .setUser("db2admin")
      .setPassword("password12");

    // Pool options
    PoolOptions poolOptions = new PoolOptions()
      .setMaxSize(5);

    // Create the client pool
    DB2Pool client = DB2Pool.pool(connectOptions, poolOptions);

    // A simple query
    client
      .query("Select * from db2admin.Account_tl")
      .execute(ar -> {
      if (ar.succeeded()) {
        RowSet<Row> result = ar.result();
        System.out.println("Got " + result.size() + " rows ");
      } else {
        System.out.println("Failure: " + ar.cause().getMessage());
      }

      // Now close the pool
      client.close();
    });
  }

}