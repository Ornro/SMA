package org.ups.sma.impl.bootstrap;

import org.ups.sma.custom.domain.agent.State;
import org.ups.sma.custom.domain.agent.rules.*;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.custom.domain.environment.objects.*;
import org.ups.sma.custom.impl.actions.WalkOn;
import org.ups.sma.custom.impl.environment.EnvironmentUtils;
import org.ups.sma.custom.impl.agent.PerceptionFilter;
import org.ups.sma.custom.impl.agent.RangeFilter;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.Filter;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.impl.Decider;
import org.ups.sma.impl.agent.impl.Effector;
import org.ups.sma.impl.agent.impl.Perciever;
import org.ups.sma.impl.environment.EnvironmentManager;

import java.util.*;

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
        /*
        Size size = new Size();
        size.width = 100;
        size.height = 100;
        Map<Location,Stack<InteractiveEnvironmentObject>> map = new HashMap<Location, Stack<InteractiveEnvironmentObject>>();
*/
        //int nbAgents = Integer.parseInt(args[2]);
        int nbAgents = 1000;
        Size sizeEnv = new Size(100,100);
        Size zoneStock = new Size(30,50);
        Size zoneDepot = new Size(30,50);
        Size zoneWall = new Size(60,sizeEnv.height);


 /*
        List<String> actions = new ArrayList<String>();
        actions.add("Dump");
        actions.add("Get");
        InteractiveEnvironmentObject object = new Default(new Location(10,10),actions);
        InteractiveEnvironmentObject object2 = new Default(new Location(10,10),actions);

        Stack<InteractiveEnvironmentObject> stack = new Stack<InteractiveEnvironmentObject>();
        stack.push(object);
        stack.push(object2);

        map.put(new Location(10,10),stack);
        map.put(new Location(20,20),stack);

        Env env = new Env();
        env.size = size;
        env.map = map;

        EnvironmentManager envM = EnvironmentManager.initialize(env);
        Env env2 = Saver.load("toto");
        System.out.println(env2);
*/
        Map<Location,Stack<InteractiveEnvironmentObject>> map = new HashMap<Location, Stack<InteractiveEnvironmentObject>>();
        Env env = new Env(sizeEnv, map);
        EnvironmentManager emanager = EnvironmentManager.initialize(env);
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
                Take take = new Take(location, null);
                take.setType(Type.TAKE);
                Box box = new Box(location, actions);
                box.setType(Type.BOX);
                emanager.addObject(location, take);
                emanager.addObject(location, box);
            }
        }

        for(int i=0; i<zoneDepot.width; i++) {
            for(int j=0; j<zoneDepot.height; j++) {
                Location location = new Location(i+(env.size.width - zoneDepot.width),j+(env.size.height - zoneStock.height)/2);
                ArrayList<String> actions = new ArrayList<String>();
                actions.add("Dump");
                Stock stock = new Stock(location, actions);
                stock.setType(Type.STOCK);
                emanager.addObject(location, stock);
            }
        }

        int yCorridor1 = 10;
        int yCorridor2 = 50;
        for(int i=0; i<zoneWall.height; i++) {
            if(i==yCorridor1 || i==yCorridor2) continue;

            for(int j=0;j<zoneWall.width;j++){
                Location location = new Location(j+(sizeEnv.width - zoneWall.width) / 2, i);
                Wall wall = new Wall(location, new ArrayList<String>());
                wall.setType(Type.WALL);
                emanager.addObject(location, wall);
            }
        }

        List<Rule> rules = new ArrayList<Rule>();
        rules.add(new CorridorEncounterRule());
        rules.add(new CorridorFreeRule());
        rules.add(new CorridorStuckRule());
        rules.add(new DumpRule());
        rules.add(new GetRule());
        rules.add(new MoveForwardBlockedRule());
        rules.add(new MoveForwardRule());
        rules.add(new SurroundedByWallsRule());
        rules.add(new UpdateObjectiveRule());
        rules.add(new UpdateObjectiveRule2());
        rules.add(new UpdateWayPointRule());

        for(int i=0; i< nbAgents; i++) {
            Random rand = new Random();
            int x = rand.nextInt(sizeEnv.width-1);
            int y = rand.nextInt(sizeEnv.height-1);
            Location location = new Location(x,y);
            Stack<InteractiveEnvironmentObject> stack = env.map.get(location);
            if(!(stack.peek() instanceof Default)) continue;

            State state = new State();
            int nb = (env.size.height - zoneStock.height)/2;
            state.storage = EnvironmentUtils.getRandomLocation(0, zoneStock.width, nb, nb + zoneStock.height);
            int n1 = (env.size.width - zoneDepot.width);
            int n2 = (env.size.height - zoneStock.height)/2;
            state.depot = EnvironmentUtils.getRandomLocation(n1, env.size.width, n2, n2 + zoneDepot.height);
            Filter perceptionFilter = new PerceptionFilter();
            Perciever perciever = new Perciever(perceptionFilter);
            Decider decider = new Decider(rules);
            Filter rangeFilter = new RangeFilter();
            ArrayList<String> abilities = new ArrayList<String>();
            abilities.add("Dump");
            abilities.add("Get");
            abilities.add("Suicide");
            abilities.add("WalkOn");
            Effector effector = new Effector(rangeFilter, abilities);

            Agent agent = new Agent(state, effector, perciever, decider, null);

            stack.push(agent);
            env.map.put(location,stack);
        }

        ActionEngine.getInstance().launch();

    }
}