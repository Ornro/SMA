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
import org.ups.sma.impl.agent.impl.Perceiver;
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


        /** Initialisation **/
        /*
        Size size = new Size();
        size.width = 100;
        size.height = 100;
        Map<Location,Stack<InteractiveEnvironmentObject>> map = new HashMap<Location, Stack<InteractiveEnvironmentObject>>();
*/
        //int nbAgents = Integer.parseInt(args[2]);
        int nbAgents = 10;
        Size sizeEnv = new Size(40,20);
        Size zoneStock = new Size(6,10);
        Size zoneDepot = new Size(6,10);
        Size zoneWall = new Size(13,sizeEnv.height);



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
        EnvironmentManager emanager = EnvironmentManager.initialize(sizeEnv);

        for(int i=0; i<sizeEnv.width; i++) {
            for(int j=0; j<sizeEnv.height; j++) {
                Location location = new Location(i,j);
                Default def = new Default(location);
                def.setType(Type.DEFAULT);
            }
        }

        Map<Location,Stack<InteractiveEnvironmentObject>> map = new HashMap<Location, Stack<InteractiveEnvironmentObject>>();
        Env env = emanager.getFullEnvironment();


        for(int i=0; i<zoneStock.width; i++) {
            for(int j=0; j<zoneStock.height; j++) {
                Location location = new Location(i,j+(env.size.height - zoneStock.height)/2);
                Take take = new Take(location);
                take.setType(Type.TAKE);
                Box box = new Box(location);
                box.setType(Type.BOX);
            }
        }

        for(int i=0; i<zoneDepot.width; i++) {
            for(int j=0; j<zoneDepot.height; j++) {
                Location location = new Location(i+(env.size.width - zoneDepot.width),j+(env.size.height - zoneStock.height)/2);
                Stock stock = new Stock(location);
                stock.setType(Type.STOCK);
            }
        }

        int yCorridor1 = 10;
        int yCorridor2 = 15;
        for(int i=0; i<zoneWall.height; i++) {
            if(i==yCorridor1 || i==yCorridor2) continue;

            for(int j=0;j<zoneWall.width;j++){
                Location location = new Location(j+(sizeEnv.width - zoneWall.width) / 2, i);
                Wall wall = new Wall(location);
                wall.setType(Type.WALL);
            }
        }

        List<Rule> rules = new ArrayList<Rule>();
        rules.add(new UpdateObjectiveRule());
        rules.add(new UpdateObjectiveRule2());
        rules.add(new UpdateWayPointRule());
        rules.add(new GetRule());
        rules.add(new DumpRule());
        rules.add(new MoveForwardRule());
        rules.add(new MoveForwardBlockedRule());
        rules.add(new CorridorStuckRule());
        rules.add(new CorridorFreeRule());
        rules.add(new CorridorEncounterRule());
        rules.add(new SurroundedByWallsRule());
        rules.add(new UpdateDepotWayRule());
        rules.add(new UpdateWrongDepotRule());
        rules.add(new UpdateStorageWayRule());
        rules.add(new UpdateWrongStorageRule());

        for(int i=0; i< nbAgents; i++) {
            Random rand = new Random();
            int x = rand.nextInt(sizeEnv.width-1);
            int y = rand.nextInt(sizeEnv.height-1);
            Location location = new Location(x,y);
            Stack<InteractiveEnvironmentObject> stack = env.map.get(location);
            while(!(stack.peek() instanceof Default)) {
                rand = new Random();
                x = rand.nextInt(sizeEnv.width-1);
                y = rand.nextInt(sizeEnv.height-1);
                location = new Location(x,y);
                stack = env.map.get(location);
            }

            State state = new State();
            int nb = (env.size.height - zoneStock.height)/2;
            state.storage = EnvironmentUtils.getRandomLocation(0, zoneStock.width, nb, nb + zoneStock.height);
            int n1 = (env.size.width - zoneDepot.width);
            int n2 = (env.size.height - zoneStock.height)/2;
            state.depot = EnvironmentUtils.getRandomLocation(n1, env.size.width, n2, n2 + zoneDepot.height);
            Filter perceptionFilter = new PerceptionFilter();
            Perceiver perceiver = new Perceiver(perceptionFilter);
            Decider decider = new Decider(rules);
            Filter rangeFilter = new RangeFilter();
            ArrayList<String> abilities = new ArrayList<String>();
            abilities.add("Dump");
            abilities.add("Get");
            abilities.add("Suicide");
            abilities.add("WalkOn");
            Effector effector = new Effector(rangeFilter, abilities);

            Agent agent = new Agent(state, effector, perceiver, decider, null, location);
            agent.setType(Type.AGENT);
        }

        ActionEngine.getInstance().launch();

    }
}