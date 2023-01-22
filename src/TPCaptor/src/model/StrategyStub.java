package model;

import java.util.HashMap;
import java.util.Map;

public class StrategyStub {

    public Map<String, GenStrategy> strategies =new HashMap<String,GenStrategy>();

    public StrategyStub(){
        strategies.put("Cpu",new GenCPU());
        strategies.put("Random",new GenBoundedRandom(-30,40));
    }

}
