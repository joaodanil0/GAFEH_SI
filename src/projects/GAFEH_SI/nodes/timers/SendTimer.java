package projects.GAFEH_SI.nodes.timers;

import java.util.Random;

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
	public Random random = new Random();
	
	public SendTimer(GAF gaf) {
		
		this.gaf = gaf;
	}
	
	@Override
	public void fire() {
		
		if(this.gaf.state == States.active) {
			
			double idMessage = this.gaf.ID*100000  + (random.nextInt(999) + random.nextGaussian());
			
			if(this.gaf.hasEnergy()) {
				GAF.dataPctsSentByHour++;
				Sink.pcktsSentByNetwork++;
				Sink.pcktsSenthour++;
				this.gaf.dataPctSent++;
				DataMessage msg = new DataMessage(this.gaf.ID, this.gaf.sinkDistance, idMessage, this.gaf.gridID);
				this.node.broadcast(msg);
				//System.out.println("Send in:     " + Sink.showTime() + " | Node: " + this.gaf.ID);
				this.gaf.battery.gastaEnergiaEnvio();
			}			
		}
	}

}
