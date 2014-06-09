package org.ups.sma.dao;

import com.google.gson.*;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.lang.reflect.Type;

/**
 * Created by Ben on 09/06/14.
 */
public class IEOAdapter implements JsonSerializer<InteractiveEnvironmentObject>, JsonDeserializer<InteractiveEnvironmentObject> {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE  = "INSTANCE";

    @Override
    public JsonElement serialize(final InteractiveEnvironmentObject src,final Type typeOfSrc,
                                 final JsonSerializationContext context) {

        JsonObject retValue = new JsonObject();
        String className = src.getClass().getName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement elem = context.serialize(src);
        retValue.add(INSTANCE, elem);
        return retValue;
    }

    @Override
    public InteractiveEnvironmentObject deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException  {
        JsonObject jsonObject =  json.getAsJsonObject();
        JsonElement prim =  jsonObject.get(CLASSNAME);
        String className = prim.getAsString();

        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
        return context.deserialize(jsonObject.get(INSTANCE), klass);
    }
}