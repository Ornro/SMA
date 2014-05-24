package org.ups.sma.impl.ActionEngine;

import java.util.ArrayList;

import org.ups.sma.domain.Mode;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Control;

public class ActionEngine implements ActionManager, Control, Runnable {

	private static volatile ActionEngine instance = null;
	
	private ArrayList<Actor> actorList = new ArrayList<Actor>();
	private Mode currentMode = Mode.AUTO;

	
	private ActionEngine(){ }
	
	public final static ActionEngine getInstance(){
		if(instance == null){
			synchronized (ActionEngine.class) {
				if(instance == null){
					instance = new ActionEngine();
				}
			}
		}
		return instance;
	}
	
	
	@Override
	public void play() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void changeMode(Mode newMode) {
		currentMode = newMode;
	}

	@Override
	public void step() {
		if(currentMode != Mode.STEP) return;
		
		notifyActors();
	}

	
	@Override
	public void register(Actor actor) {
		actorList.add(actor);
	}

	@Override
	public void unregister(Actor actor) {
		for(int i=0;i<actorList.size();i++){
			if(actorList.get(i).equals(actor)){
				actorList.remove(i);
				break;
			}
		}
	}

	@Override
	public void run() {
		
	}
	
	
	private void notifyActors(){
		for(int i=0;i<actorList.size();i++){
			actorList.get(i).act();
		}
	}

}
