package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class Account
{

public int ACCOUNTID;

public Account()
{}

public Account (int ACCOUNTID)
{
this.ACCOUNTID = ACCOUNTID;
}

static String resultToReturn;

public static String findAll(io.vertx.db2client.DB2Pool client) {
	
	client.query("SELECT ACCOUNTID FROM db2admin.Account_tl")
    		.execute(ar -> {
    			  if (ar.succeeded()) {
    				    RowSet<Row> result = ar.result();
    				    resultToReturn= "Got " + result.size() + " rows ";
    				    
    				  } else {
    					  resultToReturn= "Failure: " + ar.cause().getMessage();
    				  }});
	return resultToReturn;
}

private static Account from(Row row) {
    return new Account(row.getInteger("ACCOUNTID"));
}

}