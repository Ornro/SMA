import org.ups.sma.custom.domain.agent.State;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.custom.domain.environment.objects.*;
import org.ups.sma.custom.impl.agent.Decide;
import org.ups.sma.custom.impl.agent.Perceive;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.impl.Act;
import org.ups.sma.impl.agent.interfaces.Decider;
import org.ups.sma.impl.agent.interfaces.Effector;
import org.ups.sma.impl.agent.interfaces.Perciever;
import org.ups.sma.impl.environement.EnvironmentManager;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		/*CouchDbClient dbClient = new CouchDbClient();
		Env myEnv = new Env();
		Gson gson = dbClient.getGson();
		JsonObject jso = new JsonObject();
		
		jso.add("_id",gson.toJsonTree("currentEnv"));
		jso.add("env", gson.toJsonTree(myEnv));
		dbClient.save(jso);*/
		
		// Test de l'actionEngine
		
		/*ActionManager am = ActionEngine.getInstance();
		
		Control ctrl = ActionEngine.getInstance();
		
		am.register(new Actor() {
			public void act() {
				System.out.println("Hello, I'm acting: " + (new Date()));
			}
		});
		
		System.out.println("Go to play");
		ctrl.play();
		System.out.println("Play called");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Go to pause");
		ctrl.pause();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Go to step by step");
		ctrl.changeMode(Mode.STEP);

		ctrl.step();
		ctrl.step();
		ctrl.step();*/

        /*Mode mode = Mode.AUTO;
        boolean isPlaying = true;
        int delay = 10;

        Gson gson = new Gson();
        JsonObject jso = new JsonObject();
        String state = "undefined";
        jso.add("mode", gson.toJsonTree(mode.toString().toLowerCase()));
        if (isPlaying) {
            state = "play";
        } else state = "pause";
        jso.add("state", gson.toJsonTree(state));
        jso.add("delay", gson.toJsonTree(delay));

        System.out.println(jso.toString());*/

        /**Action action = new WalkOn();
        System.out.println(action);*/



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

        for(int i=0; i< nbAgents; i++) {
            Random rand = new Random();
            int x = rand.nextInt(sizeEnv.width-1);
            int y = rand.nextInt(sizeEnv.height-1);
            Location location = new Location(x,y);
            Stack<InteractiveEnvironmentObject> stack = env.map.get(location);
            if(!(stack.peek() instanceof Default)) continue;

            State state = new State();
            int nb = (env.size.height - zoneStock.height)/2;
            state.storage = getRandomLocation(0, zoneStock.width, nb, nb + zoneStock.height);
            int n1 = (env.size.width - zoneDepot.width);
            int n2 = (env.size.height - zoneStock.height)/2;
            state.depot = getRandomLocation(n1, env.size.width, n2, n2 + zoneDepot.height);
            Filter perceptionFilter = new PerceptionFilter();
            Perciever perciever = new Perceive(perceptionFilter);
            Decider decider = new Decide();
            Effector effector = new Act();
            ArrayList<String> abilities = new ArrayList<String>();
            abilities.add("Dump");
            abilities.add("Get");
            abilities.add("Suicide");
            abilities.add("WalkOn");
            Filter rangeFilter = new RangeFilter();
            Agent agent = new Agent(state, effector, perciever, decider, null , abilities, rangeFilter);

            stack.push(agent);
            env.map.put(location,stack);
        }
    }

    public static Location getRandomLocation(int x1, int x2, int y1, int y2) {
        Random rand = new Random();
        int randomX = rand.nextInt((x2 - x1) + 1) + x1;
        int randomY = rand.nextInt((y2 - y1) + 1) + y1;

        return new Location(randomX, randomY);
    }
}
