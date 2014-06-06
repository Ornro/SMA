package org.ups.sma.custom.impl.actions;

import org.ups.sma.custom.domain.environnement.objects.Box;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environement.EnvironmentManager;

import java.util.Stack;

public class Get extends Action {

    @Override
    public void execute(Agent a, InteractiveEnvironmentObject ieo){
        EnvironmentManager emanager = EnvironmentManager.getInstance();
        Env env = emanager.getFullEnvironment();
        Stack<InteractiveEnvironmentObject> objects = env.map.get(ieo.getLocation());
        if(objects.peek() instanceof Box) {
            Box box = (Box)objects.peek();
            a.getState().boxHolded = box;
            emanager.removeObject(box);
        }

        ieo.getAvailableActions().remove(this.getClass().getSimpleName());
    }
}
