package org.ups.sma.impl.bootstrap;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.custom.domain.environment.objects.Default;
import org.ups.sma.custom.domain.environment.objects.Stock;
import org.ups.sma.custom.domain.environment.objects.Take;
import org.ups.sma.custom.domain.environment.objects.Wall;
import org.ups.sma.custom.impl.actions.WalkOn;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.environement.EnvironmentManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by nath on 09/06/14.
 */
public class Bootstrap {

    private static volatile Bootstrap instance;

    public static Bootstrap getInstance(){
        if (instance == null)
        {
            synchronized (Bootstrap.class) {
                if (instance == null)
                    instance = new Bootstrap();
            }
        }
        return instance;
    }

    private Bootstrap(){


        Action action = new WalkOn();
        System.out.println(action);


        /** Initialisation **/
        /*int width = Integer.parseInt(args[0]);
        int height =  Integer.parseInt(args[1]);
        Size size = new Size(width, height);*/
        Size sizeEnv = new Size(30, 10);

        //int nbAgents = Integer.parseInt(args[2]);
        Size zoneStock = new Size(5,5);
        Size zoneDepot = new Size(5,5);
        Size zoneWall = new Size(10,sizeEnv.height);

        int yCorridor1 = 6;
        int yCorridor2 = 9;


        Map<Location,Stack<InteractiveEnvironmentObject>> map = new HashMap<Location, Stack<InteractiveEnvironmentObject>>();
        Env env = new Env(sizeEnv, map);
        EnvironmentManager emanager = EnvironmentManager.getInstance();
        emanager.setEnvironment(env);
        for(int i=0; i<env.size.width; i++) {
            for(int j=0; j<env.size.height; j++) {
                Location location = new Location(i,j);
                Default def = new Default(location, new ArrayList<String>());
                def.setType(Type.DEFAULT);
                Stack<InteractiveEnvironmentObject> stack = new Stack<InteractiveEnvironmentObject>();
                stack.push(def);
                env.map.put(location, stack);
            }
        }

        for(int i=0; i<zoneStock.width; i++) {
            for(int j=0; j<zoneStock.height; j++) {
                Location location = new Location(i,j+(env.size.height - zoneStock.height)/2);
                ArrayList<String> actions = new ArrayList<String>();
                actions.add("Get");
                Take take = new Take(location, actions);
                take.setType(Type.TAKE);
                Stack<InteractiveEnvironmentObject> stack = new Stack<InteractiveEnvironmentObject>();
                stack.push(take);
                env.map.put(location, stack);
            }
        }

        for(int i=0; i<zoneDepot.width; i++) {
            for(int j=0; j<zoneDepot.height; j++) {
                Location location = new Location(i+(env.size.width - zoneDepot.width),j+(env.size.height - zoneStock.height)/2);
                ArrayList<String> actions = new ArrayList<String>();
                actions.add("Dump");
                Stock stock = new Stock(location, actions);
                stock.setType(Type.STOCK);
                Stack<InteractiveEnvironmentObject> stack = new Stack<InteractiveEnvironmentObject>();
                stack.push(stock);
                env.map.put(location, stack);
            }
        }

        for(int i=0; i<zoneWall.height; i++) {
            if(i==yCorridor1 || i==yCorridor2) continue;

            for(int j=0;j<zoneWall.width;j++){
                Location location = new Location(i+(sizeEnv.width - zoneWall.width) / 2, j);
                Wall wall = new Wall(location, new ArrayList<String>());
                wall.setType(Type.WALL);
                Stack<InteractiveEnvironmentObject> stack = new Stack<InteractiveEnvironmentObject>();
                stack.push(wall);
                env.map.put(location, stack);
            }
        }

        System.out.println(EnvironmentManager.getInstance().getFullEnvironment());

        ActionEngine.getInstance().launch();

    }
}