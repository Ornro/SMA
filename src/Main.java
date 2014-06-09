import org.ups.sma.custom.impl.actions.WalkOn;
import org.ups.sma.domain.Action;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.custom.domain.environment.Type;
import org.ups.sma.custom.domain.environment.objects.Default;
import org.ups.sma.custom.domain.environment.objects.Stock;
import org.ups.sma.custom.domain.environment.objects.Take;
import org.ups.sma.custom.domain.environment.objects.Wall;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.environement.EnvironmentManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

        Action action = new WalkOn();
        System.out.println(action);


        /** Initialisation **/
        /*int width = Integer.parseInt(args[0]);
        int height =  Integer.parseInt(args[1]);
        Size size = new Size(width, height);*/
        Size sizeEnv = new Size(100, 100);

        //int nbAgents = Integer.parseInt(args[2]);
        Size zoneStock = new Size(30,50);
        Size zoneDepot = new Size(30,50);
        Size zoneWall = new Size(60,sizeEnv.height);

        int yCorridor1 = 10;
        int yCorridor2 = 50;


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
	}
}
