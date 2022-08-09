package dao;

public class MelonThread extends Thread {
	String data;
	// data == ♪
	
	public MelonThread(String data) {
		this.data = data;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 8; i++) {
			if (i % 2 != 0) {
				System.out.print(data);
				try {sleep(500);} catch (InterruptedException e) {}
				} else {
					System.out.print("♩");
					try {sleep(500);} catch (InterruptedException e) {}
				}
			}
	}
	
	
	
}
