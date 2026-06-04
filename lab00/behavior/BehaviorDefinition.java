package agentdemo.behavior;

import java.util.function.Supplier;

public class BehaviorDefinition {
    private final String key;
    private final String displayName;
    private final Supplier<Behavior> factor;


    public BehaviorDefinition(String key, String displayName, Supplier<Behavior> factor) {
        this.key = key;
        this.displayName = displayName;
        this.factor = factor;
    }

    public String getKey() { return key; }
    public String getDisplayName() { return displayName; }
    public Behavior create() { return factor.get(); }

    @Override
    public String toString() { return displayName; }
}
