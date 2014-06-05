import java.util.Date;

import org.lightcouch.CouchDbClient;
import org.ups.sma.domain.Mode;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Control;


public class Main {

	public static void main(String[] args) {


		CouchDbClient dbClient = new CouchDbClient();
		
		Env myEnv = new Env();
		dbClient.save(myEnv);
		
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
