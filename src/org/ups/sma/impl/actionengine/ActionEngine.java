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

	private int delay = 1000;
	
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

        synchronized (this){
            this.notify();
        }
	}

	@Override
	public void pause() {
		if(currentMode != Mode.AUTO) return;
		
		if(!isPlaying) return;
		
		isPlaying = false;
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

        synchronized (this){
	        this.notify();
        }
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

            if(isPlaying == false){
                try {
                    synchronized (this){
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }

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

            else if(currentMode == Mode.STEP){

                try {
                    synchronized (this){
                        this.wait();
                    }
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
