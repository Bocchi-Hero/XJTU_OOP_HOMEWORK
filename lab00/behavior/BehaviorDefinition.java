package agentdemo.behavior;

import java.util.function.Supplier;

public class BehaviorDefinition {
    private final String key;
    private final String displayName;
    private final Supplier<Behavior> factory;


    public BehaviorDefinition(String key, String displayName, Supplier<Behavior> factory) {
        this.key = key;
        this.displayName = displayName;
        this.factory = factory;
    }

    public String getKey() { return key; }
    public String getDisplayName() { return displayName; }
    public Behavior create() { return factory.get(); }

    @Override
    public String toString() { return displayName; }
}
