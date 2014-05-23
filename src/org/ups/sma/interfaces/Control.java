package org.ups.sma.interfaces;

import org.ups.sma.domain.Mode;

public interface Control {
	public void play();
	public void pause();
	public void changeMode(Mode newMode);
	public void step();
	public void resume();
}
