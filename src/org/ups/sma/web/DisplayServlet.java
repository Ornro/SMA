package org.ups.sma.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.custom.domain.environnement.Type;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

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

    }

    private JsonObject getInterpretation(Env environment){
        Gson gson = new Gson();
        JsonObject jso = new JsonObject();
        jso.add("size", gson.toJsonTree(environment.size));
        Map<Location,Stack<Type>> graphicMap = new HashMap<Location, Stack<Type>>();

        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> set = environment.map.entrySet();
        for( Map.Entry<Location, Stack<InteractiveEnvironmentObject>> entry : set ){
            graphicMap.put(entry.getKey(),convertToType(entry.getValue()));
        }
        jso.add("map",gson.toJsonTree(graphicMap));

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
