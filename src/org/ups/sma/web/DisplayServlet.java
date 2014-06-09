package org.ups.sma.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.impl.bootstrap.Bootstrap;
import org.ups.sma.impl.environment.EnvironmentManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Ben on 06/06/14.
 */
public class DisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Bootstrap.getInstance();

        EnvironmentManager emanager = EnvironmentManager.getInstance();

        response.setContentType("application/json");
        response.getWriter().write(getInterpretation(emanager.getFullEnvironment()).toString());
    }

    private JsonObject getInterpretation(Env environment){
        Gson gson = new Gson();
        JsonObject jso = new JsonObject();
        jso.add("size", gson.toJsonTree(environment.size));

        JsonArray map = new JsonArray();
        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> set = environment.map.entrySet();
        for( Map.Entry<Location, Stack<InteractiveEnvironmentObject>> entry : set ){
            JsonObject currentObj = new JsonObject();
            currentObj.add("type", gson.toJsonTree(convertToType(entry.getValue()).peek()));
            currentObj.addProperty("x", entry.getKey().x);
            currentObj.addProperty("y", entry.getKey().y);
            map.add(currentObj);
        }
        jso.add("map", map);

        return jso;
    }

    private Stack<Type> convertToType(Stack<InteractiveEnvironmentObject> stack){
        Stack<Type> ts = new Stack<Type>();
        Iterator<InteractiveEnvironmentObject> it =  stack.iterator();
        while (it.hasNext()){
            ts.push(it.next().getType());
        }

        return ts;
    }
}
