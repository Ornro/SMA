import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.custom.domain.environment.Size;
import org.ups.sma.custom.domain.environment.objects.Default;
import org.ups.sma.custom.impl.actions.WalkOn;
import org.ups.sma.dao.EnvAdapter;
import org.ups.sma.dao.IEOAdapter;
import org.ups.sma.domain.Action;
<<<<<<< HEAD
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
=======
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.environement.EnvironmentManager;
import org.ups.sma.impl.saver.Saver;

import java.util.*;
>>>>>>> save + rule enfine

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

        /**Action action = new WalkOn();
        System.out.println(action);*/



        Size size = new Size();
        size.width = 100;
        size.height = 100;
        Map<Location,Stack<InteractiveEnvironmentObject>> map = new HashMap<Location, Stack<InteractiveEnvironmentObject>>();

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

    }
}
