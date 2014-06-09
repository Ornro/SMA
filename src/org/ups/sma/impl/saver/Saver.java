package org.ups.sma.impl.saver;

import com.google.gson.*;
import org.lightcouch.CouchDbClient;
import org.ups.sma.dao.EnvAdapter;
import org.ups.sma.dao.IEOAdapter;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.impl.environment.EnvironmentManager;

/**
 * Created by Ben on 29/05/14.
 */
public class Saver {


    public static boolean saveCurrentStatus(String name){

        CouchDbClient dbClient = new CouchDbClient();
        if(dbClient.contains(name)){
            return false;
        }

        EnvironmentManager envManager = EnvironmentManager.getInstance();
        Env env = envManager.getFullEnvironment();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(InteractiveEnvironmentObject.class, new IEOAdapter());
        builder.registerTypeAdapter(Env.class, new EnvAdapter());

        Gson gson = builder.create();
        String ts = gson.toJson(env,Env.class);
        JsonObject elem = new JsonParser().parse(ts).getAsJsonObject();
        elem.add("_id",gson.toJsonTree(name));

        dbClient.save(elem);
        return true;
    }

    public static Env load(String name){

        CouchDbClient dbClient = new CouchDbClient();
        if(!dbClient.contains(name)){
            return null;
        }

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(InteractiveEnvironmentObject.class, new IEOAdapter());
        builder.registerTypeAdapter(Env.class, new EnvAdapter());
        dbClient.setGsonBuilder(builder);

        Env env = dbClient.find(Env.class,name);

        return env;
    }

}
