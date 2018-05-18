package projects.GAFEH_SI.nodes.messages;

import sinalgo.nodes.messages.Message;

public class DataMessage extends Message{
	
	/**
	 * Identification of origin node
	 */
	public int ID;
	
	/**
	 * Distance of current node to sink 
	 */
	public double distanceSink;
	
	/**
	 * Identification of packet
	 */
	public double idMessage;
	
	/**
	 * Identification which grid the message is
	 */
	public int gridMessage;
		
	public DataMessage(int ID, double distanceSink, double idMessage, int gridMessage) {
		this.ID = ID;
		this.distanceSink = distanceSink;
		this.idMessage = idMessage;
		this.gridMessage = gridMessage;
	}
	
	
	@Override
	public Message clone() {		
		return this;
	}

}
