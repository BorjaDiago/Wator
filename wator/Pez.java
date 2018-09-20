package wator;

class Numero{
	public static int numeroPeces;
}

public class Pez extends Thread{

	//true == hembra  || false == macho
	private boolean genero;
	private int numero;
	private Thread[][] mundo;
	
	public Pez(int numero, boolean g, Thread[][] m) {
		this.genero = g;
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
	//Método que asigna una nueva posición al hilo y comprueba si pelea o procrea
	public void mover() {
		int aleatorioI = (int) (Math.random()*20);
		int aleatorioK = (int) (Math.random()*20);
		if(mundo[aleatorioI][aleatorioK] == null) {
			mundo[aleatorioI][aleatorioK] = this;
		}
		else {
			if(mundo[aleatorioI][aleatorioK] instanceof Pez && ((Pez)mundo[aleatorioI][aleatorioK]).isGenero() == this.isGenero()) {
				pelea(mundo, aleatorioI, aleatorioK);
			}
			else if(mundo[aleatorioI][aleatorioK] instanceof Pez && ((Pez)mundo[aleatorioI][aleatorioK]).isGenero() != this.isGenero()){
				procrea(mundo, aleatorioI, aleatorioK);
			}
		}
	}
	//Método que crea un nuevo hilo Pez() asignando el "sexo" aleatoriamente
	private void procrea(Thread[][] mundo, int a, int b) {
		int genero =  (int) (Math.round(Math.random()));
		Pez pez = null;
		if(genero == 0) {
			pez = new Pez(Numero.numeroPeces+1, false, mundo);
			aumentarPeces();
			this.mover();
		}
		else if(genero != 0){
			pez = new Pez(Numero.numeroPeces+1, true, mundo);
			aumentarPeces();
			this.mover();
		}
	}
	//Metodo que interrumpe uno de los dos hilos de forma aleatoria 
	private void pelea(Thread[][] mundo, int a, int b) {
		int lucha =  (int) (Math.round(Math.random()));
		if(lucha == 0) {
			this.interrupt();
		}
		else {
			mundo[a][b].interrupt();
			mundo[a][b] = this;
		}
	}
	//Método sincronizado que aumenta la varible numeroPeces
	public synchronized void aumentarPeces() {
		Numero.numeroPeces++;
	}
	public String toString() {
		if(genero) {
			return "PH";
		}else {
			return "PM";
		}
	}
}
