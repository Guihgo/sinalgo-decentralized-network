package projects.decentralized.nodes.nodeImplementations;

import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.logging.Logging;

import projects.decentralized.nodes.messages.SinkMsg;
import projects.decentralized.nodes.timers.ScheduleSinkMsg;

public abstract class Router extends Node {
    public String name = "";
    public Node nextNode = null; /* SinkNode <== Node A (nextNode) <== Node B (this) */
    public SinkMsg lastSinkMessage = null;


    public Boolean processSinkMessage(Message message) {
        if (!(message instanceof SinkMsg))
            return false;

        Boolean mustForwardMsg = false;

        SinkMsg sinkMsg = (SinkMsg) message;

        // if(sinkMsg.getHop().equals(this)) {
        // mustForwardMsg = false;
        // } else { /* Message to know routing */

        if(this.getID() == sinkMsg.getFrom().getID()) {
            return true;
        }

        if (this.nextNode == null) { /*
                                 * If never used
                                 * this node (new in
                                 * the routing)
                                 */
            this.nextNode = sinkMsg.getHop();
            mustForwardMsg = true;
        }

        if (this.lastSinkMessage != null) {
            
            /*
             * if sink node is different OR
             * if is a new message from same
             * sink node
             * => forward
             */
            mustForwardMsg |= this.lastSinkMessage.getFrom().getID() != (sinkMsg.getFrom().getID())
                    || this.lastSinkMessage.getSequenceId() < sinkMsg.getSequenceId();
                    // this.lastSinkMessage.getFrom().equals(sinkMsg.getFrom()) &&
            Logging.getLogger().logln("[" + this.name + "][" + this.getID() + "]\tlastSinkMessage.getFrom().getID(): "+this.lastSinkMessage.getFrom().getID()+" | sinkMsg.getFrom().getID(): "+ sinkMsg.getFrom().getID());
        }

        this.lastSinkMessage = sinkMsg.clone();
        if (mustForwardMsg) {
            Logging.getLogger().logln("["+this.name+"][" + this.getID() + "]\treceived a sink message from Sink["
                    + sinkMsg.getFrom().getID() + "]: " + sinkMsg.getSequenceId());

            sinkMsg.setHop(this);
            (new ScheduleSinkMsg(sinkMsg)).startRelative(1, this);
        }

        return true;

    }
}