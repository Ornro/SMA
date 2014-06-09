package org.ups.sma.impl.actionengine;

import java.util.ArrayList;

import org.ups.sma.domain.Mode;
import org.ups.sma.interfaces.ActionManager;
import org.ups.sma.interfaces.Actor;
import org.ups.sma.interfaces.Control;

public class ActionEngine implements ActionManager, Control, Runnable {

	private static volatile ActionEngine instance = null;
	
	private ArrayList<Actor> actorList = new ArrayList<Actor>();
	private Mode currentMode = Mode.AUTO;
	private boolean isPlaying = false;

	private int delay = 100;
	
	private Thread thread = null;
	
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

    public void launch(){
        thread = new Thread(this);
        thread.start();
    }
	
	@Override
	public void play() {
		if(currentMode != Mode.AUTO) return;
		
		if(isPlaying) return;
		
		isPlaying = true;
		
        thread.notify();
	}

	@Override
	public void pause() {
		if(currentMode != Mode.AUTO) return;
		
		if(!isPlaying) return;
		
		isPlaying = false;
		
		//thread.interrupt();
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void changeMode(Mode newMode) {
		if(newMode == currentMode) return;
		
		pause();
		
		currentMode = newMode;
	}

	@Override
	public void step() {
		if(currentMode != Mode.STEP) return;
		
		//notifyActors();
	    thread.notify();
    }

	@Override
	public void setDelay(int delay) {
		this.delay = delay;
	}

    public int getDelay(){ return this.delay; }
    public Mode getMode(){ return this.currentMode; }
    public boolean isPlaying(){ return this.isPlaying; }

	
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
		
		while(true){
			notifyActors();

            if(currentMode == Mode.AUTO){

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //Thread.currentThread().interrupt();
                    break;
                }
            }

            else {

                try {
                    thread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
		}
		
	}
	
	
	private void notifyActors(){
		for(int i=0;i<actorList.size();i++){
			actorList.get(i).act();
		}
	}
}
