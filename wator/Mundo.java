package wator;

import java.util.Arrays;

public class Mundo {
	static boolean sexo;
	static Thread[][] mundo;
	public static void main(String[] args) {
		mundo = new Thread[20][20];
		Pez[] peces = new Pez[100];
		//creo 100 peces 
		int sexoMax = 0;
		for(int i=0; i<peces.length; i++) {
			if(sexoMax < 50) {
				sexo = true;
				sexoMax++;
			}
			else {
				sexo = false;
			}
			Pez pez = new Pez((i+1),sexo, mundo);
			peces[i] = pez;
			Numero.numeroPeces ++;
			pez.start();
		}
		//creo 10 Tiburones
		Tiburon[] tiburones = new Tiburon[10];
		int maxSexo = 0;
		for(int i=0; i<tiburones.length; i++) {
			if(maxSexo < 5) {
				sexo = true;
				maxSexo++;
			}
			else {
				sexo = false;
			}
			Tiburon tibu = new Tiburon((i+1),sexo, mundo);
			Cantidad.numeroTiburones++;
			tiburones[i] = tibu;
			tibu.start();
		}
		//Asigno posiciones a los peces
		asignarPeces(peces);
		//Asigno posiciones a los tiburones
		asignarTiburones(tiburones);
		
		int temp = 1;
		while(temp > 0) {
			//muestro la posiciones en el tablero
			for(int i=0; i<mundo.length;i++) {
				System.out.println(Arrays.toString(mundo[i]));
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp++;
			System.out.println();
			System.err.println("----------------------Refrescando tablero----------------------");
			System.out.println();
		}
		System.out.println("Fin");
	}
	//Asigno posición a cada pez
	public static void asignarPeces(Pez[] peces) {
		for(int i = 0; i < peces.length; i++) {
			int aleatorioI = (int) (Math.random()*20);
			int aleatorioK = (int) (Math.random()*20);
			if(mundo[aleatorioI][aleatorioK] == null) {
				mundo[aleatorioI][aleatorioK] = peces[i];
			}
			else{
				i--;
			}
		}
	}
	//Asigno posición a cada tiburón
	public static void asignarTiburones(Tiburon[] tiburones) {
		for(int i = 0; i < tiburones.length; i++) {
			int aleatorioI = (int) (Math.random()*20);
			int aleatorioK = (int) (Math.random()*20);
			if(mundo[aleatorioI][aleatorioK] == null) {
				mundo[aleatorioI][aleatorioK] = tiburones[i];
			}
			else{
				i--;
			}
		}
	}
}