import java.util.Date;

import org.json.JSONObject;
import org.lightcouch.CouchDbClient;
import org.ups.sma.domain.Mode;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Control;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class Main {

	public static void main(String[] args) {


		CouchDbClient dbClient = new CouchDbClient();
		Env myEnv = new Env();
		Gson gson = dbClient.getGson();
		JsonObject jso = new JsonObject();
		
		jso.add("_id",gson.toJsonTree("currentEnv"));
		jso.add("env", gson.toJsonTree(myEnv));
		dbClient.save(jso);
		
		// Test de l'actionEngine
		
		ActionManager am = ActionEngine.getInstance();
		
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
		ctrl.step();
		
		
	}

}
