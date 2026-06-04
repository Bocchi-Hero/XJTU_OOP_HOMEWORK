package agentdemo.behavior;

import agentdemo.model.Agent;
import agentdemo.model.World;

/**
 * 行为策略接口（Demo 版本）
 * 仅用于演示“策略模式 + 多态”的最基本思想。
 */
public interface Behavior {

    String key();
    String displayName();
    /**
     * 每一帧由 Agent 调用，用于更新自身状态
     *
     * @param self  当前智能体
     * @param world 所在世界环境
     */

    void update(Agent self, World world, double dt);
}