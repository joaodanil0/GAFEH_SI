package projects.GAFEH_SI.nodes.timers;

import projects.GAFEH_SI.nodes.messages.DataMessage;
import projects.GAFEH_SI.nodes.nodeImplementations.GAF;
import projects.GAFEH_SI.nodes.nodeImplementations.Sink;
import projects.GAFEH_SI.nodes.nodeImplementations.GAF.States;
import sinalgo.nodes.timers.Timer;

public class SendTimer extends Timer {
	
	/**
	 * The instance of the node that call the timer
	 */
	public GAF gaf;
	
	public SendTimer(GAF gaf) {
		
		this.gaf = gaf;
	}
	
	@Override
	public void fire() {
		
		if(this.gaf.state == States.active) {
			
			int idMessage = Integer.parseInt(this.gaf.ID + "" + this.gaf.dataPctSent++);
			
			if(this.gaf.hasEnergy()) {
				GAF.dataPctsSentByHour++;
				Sink.pcktsSentByNetwork++;
				Sink.pcktsSenthour++;
				DataMessage msg = new DataMessage(this.gaf.ID, this.gaf.sinkDistance, idMessage, this.gaf.gridID);
				this.node.broadcast(msg);
				this.gaf.battery.gastaEnergiaEnvio();
			}			
		}
	}

}
