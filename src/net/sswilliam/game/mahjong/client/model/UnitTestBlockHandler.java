package net.sswilliam.game.mahjong.client.model;

public class UnitTestBlockHandler implements IResponseHandler {

	private boolean bufferIsEmpty = true;
	private final Object slock = new Object();
	private byte[] buffer = null;
	public byte[] waitForResponse(){
		while(bufferIsEmpty)
		{	
			try {
				Thread.currentThread().sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return buffer;
	}
	@Override
	public void handle(byte[] response) {
		// TODO Auto-generated method stub
		
	}

}
