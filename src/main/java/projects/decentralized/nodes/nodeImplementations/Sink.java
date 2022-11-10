package projects.decentralized.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;

import sinalgo.exception.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.messages.Message;
import sinalgo.nodes.messages.Inbox;
import sinalgo.tools.logging.Logging;

import projects.decentralized.nodes.messages.SinkMsg;
import projects.decentralized.nodes.timers.ScheduleSinkMsg;

public class Sink extends Router {

    private Logging log = Logging.getLogger();

    private Integer sequenceId = 0;

    @Override
    public void handleMessages(Inbox inbox) {
        while(inbox.hasNext()) {
            Message message = inbox.next();
            this.processSinkMessage(message);
        }
    }

    @Override
    public void preStep() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        this.name = "Sink";
        this.log.logln("[Sink]\tID: " + this.getID());        
    }

    @NodePopupMethod(menuText = "Start new Routing")
    public void _startRouting() {
        this.startRouting(this);
    }

    public void startRouting(Sink fromNode) {
        SinkMsg sinkMsg = new SinkMsg(this.sequenceId, -1, fromNode, null, fromNode, -1, null);
        this.sequenceId++;
        this.log.logln("[Sink]\tStarting new routing from Sink["+this.getID()+"]: "+ sinkMsg.getSequenceId());
        (new ScheduleSinkMsg(sinkMsg)).startRelative(1, this);
    }

    @Override
    public void neighborhoodChange() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postStep() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkRequirements() throws WrongConfigurationException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics g, PositionTransformation pt, boolean highlight) {
        this.setColor(Color.RED);
        super.drawNodeAsSquareWithText(g, pt, highlight, Long.toString(this.getID()), 10, Color.WHITE);
    }


    
}
