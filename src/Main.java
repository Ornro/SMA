import java.util.Date;

import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Mode;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.impl.actionengine.ActionEngine;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Control;


public class Main {

	public static void main(String[] args) {

		
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


        String uri = "blabla?toto";
        System.out.println(uri.substring(uri.lastIndexOf("?")+1));


	}

}
