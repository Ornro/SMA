package org.ups.sma.impl.saver;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.lightcouch.CouchDbClient;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.dao.EnvAdapter;
import org.ups.sma.dao.IEOAdapter;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.environement.EnvironmentManager;

import java.io.InputStream;

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
