package projects.decentralized.nodes.nodeImplementations;

import java.awt.Graphics;

import sinalgo.exception.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;
import sinalgo.tools.logging.Logging;

// import javax.swing.*;

import java.awt.*;

public class Sensor extends Router {

    private Logging log = Logging.getLogger();

    @Override
    public void handleMessages(Inbox inbox) {
        while (inbox.hasNext()) {
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
        this.name = "Sensor";
        this.log.logln("[Sensor]\tID: " + this.getID());

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
        Color color = Color.BLUE;
        String sinkNode = "";
        if(this.lastSinkMessage != null) {
            color = Color.GREEN;
            sinkNode = Long.toString(this.lastSinkMessage.getFrom().getID());
        }
        this.setColor(color);
        super.drawNodeAsDiskWithText(g, pt, highlight, sinkNode, 10, Color.RED);
    }

}
