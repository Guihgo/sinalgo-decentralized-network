package projects.decentralized.nodes.messages;

import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SinkMsg extends Message{

    private Integer sequenceId;
    private Integer ttl;
    private Node from;
    private Node to;
    private Node hop;
    private Integer salts;
    private String payload;

    // public Msg(Integer id,
    //             Integer ttl,
    //             Node from,
    //             Node to,
    //             Node hop,
    //             Integer salts,
    //             String payload) {
    //     this.id = id;
    //     this.ttl = ttl;
    //     this.from = from;
    //     this.to = to;
    //     this.hop = hop;
    //     this.salts = salts;
    //     this.payload = payload;
    // }

    @Override
    public SinkMsg clone() {
        return new SinkMsg(this.sequenceId, this.ttl, this.from, this.to, this.hop, this.salts, this.payload);
    }
    
}
