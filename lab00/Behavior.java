package agentdemo;

/**
 * 行为策略接口（Demo 版本）
 * 仅用于演示“策略模式 + 多态”的最基本思想。
 */
public interface Behavior {

    /**
     * 每一帧由 Agent 调用，用于更新自身状态
     *
     * @param self  当前智能体
     * @param world 所在世界环境
     */
    void update(Agent self, World world);

    /**
     * 返回行为名称（用于调试或显示）
     */
    default String name() {
        return getClass().getSimpleName();
    }
}