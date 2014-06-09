package org.ups.sma.dao;

import com.google.gson.*;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Ben on 09/06/14.
 */
public class EnvAdapter implements JsonSerializer<Env>, JsonDeserializer<Env> {
    private static final String SIZE = "SIZE";
    private static final String MAP  = "MAP";

    @Override
    public Env deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        Env env = new Env();
        JsonObject jsonObject =  jsonElement.getAsJsonObject();

        env.size = context.deserialize(jsonObject.get(SIZE),env.size.getClass());
        JsonObject map = jsonObject.get(MAP).getAsJsonObject();
        Set<Map.Entry<String,JsonElement>> set = map.entrySet();

        for (Map.Entry<String,JsonElement> entry : set){
            Location l =  context.deserialize(new JsonParser().parse(entry.getKey()), Location.class);
            Stack<InteractiveEnvironmentObject> stack = new Stack<InteractiveEnvironmentObject>();
            JsonArray arr = entry.getValue().getAsJsonArray();
            Iterator<JsonElement> it = arr.iterator();
            while (it.hasNext()){
                JsonElement elem = it.next();
                stack.push((InteractiveEnvironmentObject) context.deserialize(elem,InteractiveEnvironmentObject.class));
            }
            env.map.put(l,stack);
        }

        return env;

    }

    @Override
    public JsonElement serialize(Env env, Type type, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.add(SIZE,context.serialize(env.size));
        JsonObject map = new JsonObject();

        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> set = env.map.entrySet();

        for (Map.Entry<Location,Stack<InteractiveEnvironmentObject>> entry : set){

            String l = context.serialize(entry.getKey()).toString();
            JsonArray stack = new JsonArray();
            for (InteractiveEnvironmentObject ieo : entry.getValue()){
                stack.add(context.serialize(ieo,InteractiveEnvironmentObject.class));
            }
            map.add(l,stack);
        }
        obj.add(MAP,map);

        return obj;
    }
}
