package org.ups.sma.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.environement.EnvironmentManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Ben on 06/06/14.
 */
public class DisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EnvironmentManager emanager = EnvironmentManager.getInstance();
        //response.setContentType("application/json");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       // out.write(getInterpretation(emanager.getFullEnvironment()).toString());
        out.println("<HTML>");
        out.println("<HEAD><TITLE> Titre </TITLE></HEAD>");
        out.println("<BODY>");
        out.println("Ma première servlet");
        out.println("</BODY>");
        out.println("</HTML>");
        out.close();
    }

    private JsonObject getInterpretation(Env environment){
        Gson gson = new Gson();
        JsonObject jso = new JsonObject();
        jso.add("size", gson.toJsonTree(environment.size));
        Map<String,Stack<Type>> graphicMap = new HashMap<String, Stack<Type>>();

        Set<Map.Entry<Location,Stack<InteractiveEnvironmentObject>>> set = environment.map.entrySet();
        for( Map.Entry<Location, Stack<InteractiveEnvironmentObject>> entry : set ){
            graphicMap.put(entry.getKey().x+"@"+entry.getKey().y,convertToType(entry.getValue()));
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
