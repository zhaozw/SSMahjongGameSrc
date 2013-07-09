package net.sswilliam.game.mahjong.client;


public class UnitTestBlockHandler implements IResponseHandler {

	private boolean bufferIsEmpty = true;
	private final Object slock = new Object();
	private byte[] buffer = null;
	private byte handleFlag = Byte.MIN_VALUE;
	public void setFlag(byte flag){
		synchronized (slock) {
			
			this.handleFlag = flag;
			bufferIsEmpty = true;
			System.out.println("set buffer is empty true");
			buffer = null;
		}
	}
	public byte[] waitForResponse(){
		
		while(true)
		{	
			try {
				Thread.currentThread().sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(!bufferIsEmpty){
//				System.out.println("check");
				break;
			}
		}
		return buffer;
	}
	@Override
	public void handle(byte[] response) {
		// TODO Auto-generated method stub
		synchronized (slock) {
			if(response != null && response.length > 0){
				System.out.println(response[0] == handleFlag);
				System.out.println(response[0]);
				System.out.println(handleFlag);
				if(response[0] == handleFlag){
					System.out.println("received");
					bufferIsEmpty = false;
					System.out.println("set buffer is empty false");
					buffer = response;
					
				}else{
					System.out.println("not the right package discarded");
				}
			}
		}
		
		
	}

}
