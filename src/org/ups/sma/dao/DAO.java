package org.ups.sma.dao;

import org.lightcouch.CouchDbClient;

/**
 * Created by Ben on 29/05/14.
 */
public abstract class DAO {
    CouchDbClient dbClient;

    DAO(){
        dbClient = new CouchDbClient();
    }

    public void destroy(){
        dbClient.shutdown();
    }


}
