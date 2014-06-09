package org.ups.sma.dao;

import org.lightcouch.CouchDbClient;
import org.ups.sma.custom.domain.environment.Size;

import java.util.Map;
import java.util.Stack;

/**
 * Created by Ben on 29/05/14.
 */
public abstract class DAO {
    class SavableEnv{
        public Size size;
        public Map<String,Stack<String>> map;
    }
    CouchDbClient dbClient;

    DAO(){
        dbClient = new CouchDbClient();
    }

   /*  public SavableEnv convert(Env env){
       SavableEnv senv = new SavableEnv();
        senv.size = env.size;
        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> set = env.map.entrySet();
        for (Map.Entry<Location,Stack<InteractiveEnvironmentObject>> entry : set){
            Location l = entry.getKey();
            Stack<InteractiveEnvironmentObject> envObject = entry.getValue();

        }

    }*/

    public void destroy(){
        dbClient.shutdown();
    }


}
