package projects.GAFEH_SI.nodes.timers;

import java.util.Random;

import projects.GAFEH_SI.CustomGlobal;
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
				this.gaf.dataPctsSentByHour++;
				Sink.pcktsSentByNetwork++;
				this.gaf.dataPctSent++;								
				Sink.pcktsSenthour[CustomGlobal.hora + 24*CustomGlobal.num_dias]++;	
				DataMessage msg = new DataMessage(this.gaf.ID, this.gaf.sinkDistance, idMessage, this.gaf.gridID, CustomGlobal.hora + 24*CustomGlobal.num_dias);
				this.node.broadcast(msg);
				this.gaf.battery.gastaEnergiaEnvio();
			}			
		}
	}

}
