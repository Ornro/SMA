package org.ups.sma.web;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.ups.sma.domain.Mode;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.bootstrap.Bootstrap;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ben on 06/06/14.
 */
public class ActionEngineServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Bootstrap.getInstance();

        String methodToCall = request.getQueryString();
        ActionEngine actEngine = ActionEngine.getInstance();

        if (methodToCall.startsWith("play")){
            actEngine.play();
        }else if (methodToCall.startsWith("pause")){
            actEngine.pause();
        }else if (methodToCall.startsWith("delay")){
            actEngine.setDelay(Integer.parseInt(methodToCall.substring(methodToCall.indexOf("=") + 1)));
        }else if (methodToCall.startsWith("step")){
            actEngine.step();
        } else if (methodToCall.startsWith("step")){
            actEngine.step();
        } else if (methodToCall.startsWith("mode")){
            actEngine.changeMode(Mode.valueOf(methodToCall.substring(methodToCall.indexOf("=") + 1)));
        } else if (methodToCall.startsWith("currentSet")){
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write(getSet(actEngine.getMode(), actEngine.isPlaying(), actEngine.getDelay()).toString());
        }
    }

    private JsonObject getSet(Mode mode, boolean isPlaying, int delay){
        Gson gson = new Gson();
        JsonObject jso = new JsonObject();
        String state = "undefined";
        jso.add("mode", gson.toJsonTree(mode.toString().toLowerCase()));
        if (isPlaying) {
            state = "play";
        } else state = "pause";
        jso.add("state", gson.toJsonTree(state));
        jso.add("delay", gson.toJsonTree(delay));

        return jso;
    }
}
