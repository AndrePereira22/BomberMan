package Modelo;

public class Cronometro  {

	private int minuto = 0;
	private int segundo = 0;
	
	public Cronometro(int minuto, int segundo) {
		super();
		
		this.minuto = minuto;
		this.segundo = segundo;
		
	}
	public void run() {
		
		while(true) {
		
			try {
			segundo++;
			if (segundo == 59) {
				segundo = 0;
				minuto++;
				if (minuto == 59) {
					minuto = 0;
				}
			}
			Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public int getMinuto() {
		return minuto;
	}
	public int getSegundo() {
		return segundo;
	}
	
}