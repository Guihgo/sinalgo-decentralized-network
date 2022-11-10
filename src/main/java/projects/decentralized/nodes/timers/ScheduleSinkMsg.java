package projects.decentralized.nodes.timers;

import sinalgo.nodes.timers.Timer;
import lombok.AllArgsConstructor;

import projects.decentralized.nodes.messages.SinkMsg;

@AllArgsConstructor
public class ScheduleSinkMsg extends Timer {

    private SinkMsg msg = null;

    @Override
    public void fire() {
        this.getTargetNode().broadcast(this.msg);
    }
    
}
