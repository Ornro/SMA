package org.ups.sma.interfaces;

import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.Filter;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;

public interface EnvironmentInteractions {
    // changes a part or the whole environment
	public void change(Env newEnvironment);

    // replaces all matching objects by the one given. It location will be updated
    // automatically to match the location of the old one. Several instances of the
    // object will be used.
    public void replaceMatchingBy(InteractiveEnvironmentObject newObject, Filter filter);

}
