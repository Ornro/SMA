package org.ups.sma.web;

import org.ups.sma.domain.Mode;
import org.ups.sma.impl.actionengine.ActionEngine;

import java.io.IOException;

/**
 * Created by Ben on 06/06/14.
 */
public class ActionEngineServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uri = request.getRequestURI();
        String methodToCall = uri.substring(uri.lastIndexOf("?") + 1);

        if (methodToCall.startsWith("play")){
            ActionEngine.getInstance().play();
        }else if (methodToCall.startsWith("pause")){
            ActionEngine.getInstance().pause();
        }else if (methodToCall.startsWith("run")){
            ActionEngine.getInstance().run();
        }else if (methodToCall.startsWith("setDelay")){
            ActionEngine.getInstance().setDelay(Integer.parseInt(methodToCall.substring(methodToCall.indexOf("&")+1)));
        }else if (methodToCall.startsWith("step")){
            ActionEngine.getInstance().step();
        } else if (methodToCall.startsWith("step")){
            ActionEngine.getInstance().step();
        } else if (methodToCall.startsWith("changeMode")){
            ActionEngine.getInstance().changeMode(Mode.valueOf(methodToCall.substring(methodToCall.indexOf("&") + 1)));
        }
    }
}
