package wator;

class Cantidad {
	public static int numeroTiburones;
}

public class Tiburon extends Thread {

	private boolean genero;
	private int numero;
	private Thread[][] mundo;

	public Tiburon(int numero, boolean genero, Thread[][] m) {
		this.genero = genero;
		this.numero = numero;
		this.mundo = m;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Thread[][] getMundo() {
		return mundo;
	}

	public void setMundo(Thread[][] mundo) {
		this.mundo = mundo;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
				mover();
			} catch (InterruptedException e) {
			}
		}
		
	}
	//Método que asigna una nueva posición al hilo y comprueba si pelea, procrea o se come un hilo Pez()
	public void mover() {
		int aleatorioI = (int) (Math.random() * 20);
		int aleatorioK = (int) (Math.random() * 20);
		if (mundo[aleatorioI][aleatorioK] == null) {
			mundo[aleatorioI][aleatorioK] = this;
		} else {
			if (mundo[aleatorioI][aleatorioK] instanceof Tiburon && ((Tiburon) mundo[aleatorioI][aleatorioK]).isGenero() == this.isGenero()) {
				pelea(mundo, aleatorioI, aleatorioK);
			} else if (mundo[aleatorioI][aleatorioK] instanceof Tiburon && ((Tiburon) mundo[aleatorioI][aleatorioK]).isGenero() != this.isGenero()) {
				procrea(mundo, aleatorioI, aleatorioK);
			}
			else if(mundo[aleatorioI][aleatorioK] instanceof Pez) {
				mundo[aleatorioI][aleatorioK].interrupt();
				mundo[aleatorioI][aleatorioK] = this;
			}
		}
	}
	//Método que crea un nuevo hilo Tiburon() asignando el "sexo" aleatoriamente
	private void procrea(Thread[][] mundo, int a, int b) {
		int genero = (int) (Math.round(Math.random()));
		Tiburon tibu = null;
		if (genero == 0) {
			tibu = new Tiburon(Cantidad.numeroTiburones + 1, false, mundo);
			aumentarTiburones();
			this.mover();
		} else if (genero != 0) {
			tibu = new Tiburon(Cantidad.numeroTiburones + 1, true, mundo);
			aumentarTiburones();
			this.mover();
		}
	}
	//Metodo que interrumpe uno de los dos hilos de forma aleatoria 
	private void pelea(Thread[][] mundo, int a, int b) {
		int lucha = (int) (Math.round(Math.random()));
		if (lucha == 0) {
			this.interrupt();
		} else {
			mundo[a][b].interrupt();
			mundo[a][b] = this;
		}
	}
	//Método sincronizado que aumenta la varible numeroTiburones
	public synchronized void aumentarTiburones() {
		Cantidad.numeroTiburones++;
	}
	
	public String toString() {
		if(genero) {
			return "TH";
		}else {
			return "TM";
		}
	}
}
