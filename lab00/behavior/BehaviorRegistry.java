package agentdemo.behavior;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BehaviorRegistry {
    private final Map<String, BehaviorDefinition> behaviors = new LinkedHashMap<>();

    public static BehaviorRegistry defaultRegistry() {
        BehaviorRegistry registry = new BehaviorRegistry();
        registry.register(new BehaviorDefinition(
                "random",
                "随机",
                RandomMoveBehavior::new));
        registry.register(new BehaviorDefinition(
                "chase",
                "追逐",
                ChaseBehavior::new));
        registry.register(new BehaviorDefinition(
                "avoid",
                "回避",
                AvoidNearestBehavior::new));
        registry.register(new BehaviorDefinition(
                "patrol",
                "巡逻",
                PatrolBehavior::new));
        return registry;
    }

    public void register(BehaviorDefinition behavior) { behaviors.put(behavior.getKey(), behavior); }

    public Behavior create(String key) {
        BehaviorDefinition behavior = behaviors.get(key);
        if (behavior == null) {
            throw new IllegalArgumentException("Unknown behavior: " + key);
        }
        return behavior.create();
    }

    public List<BehaviorDefinition> definitions() { return List.copyOf(behaviors.values()); }

    public BehaviorDefinition find(String key) { return behaviors.get(key); }
}
