package agentdemo.behavior;

import java.util.ArrayList;
import java.util.List;

public class BehaviorRegister {
    private final List<Display> behaviors = new ArrayList<>();

    public void register(String displayName, Behavior behavior) {
        behaviors.add(new Display(displayName, behavior));
    }

    public String[] getDisplayName() {
        return behaviors.stream()
                .map(d -> d.displayName)
                .toArray(String[]::new);
    }

    public Behavior getBehavior(int index) {
        return behaviors.get(index).behavior;
    }

    public Behavior getBehavior(String displayName) {
        for (Display d : behaviors) {
            if (d.displayName.equals(displayName)) {
                return d.behavior;
            }
        }
        return null;
    }

    public int size() {
        return behaviors.size();
    }

    static class Display {
        String displayName;
        Behavior behavior;

        public Display(String displayName, Behavior behavior) {
            this.displayName = displayName;
            this.behavior = behavior;
        }
    }
}
