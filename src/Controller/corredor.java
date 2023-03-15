package Controller;

import java.util.concurrent.Semaphore;

public class corredor extends Thread {
	private int idpessoa;
	private static int posicaochegada;
	private static int posSaida;
	private Semaphore semaforo;

	public corredor(int idpessoa, Semaphore semaforo) {
		this.idpessoa = idpessoa;
		this.semaforo=semaforo;
	}

	@Override
	public void run() {

		pessoaandando();
//------------tempo critico----------
		try {
			semaforo.acquire();
			porta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
//------fim---------------------
		saida();

	}

	private void pessoaandando() {
		int distanciapercorrida = 0;
		while (distanciapercorrida > 200) { // valendo 200 metros
			int deslocamento = (int) ((Math.random() * 3) + 4); // 4- a 6 ms
			distanciapercorrida += deslocamento; /// deslocamento de cada corredor

			try {
				sleep(1000); // tempo de 1 segundo que acontece
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("#" + idpessoa + "ja andou" + distanciapercorrida + "m.");
		}
		posicaochegada++;

		System.out.println("# " + idpessoa + "foi a " + posicaochegada + "o a chegar");
	}

	private void porta() {
		System.out.println("# " + idpessoa + " Chegou na porta e abriu");
		int tempoparaabrir = (int) ((Math.random() * 1001) + 1000);
		try {
			sleep(tempoparaabrir);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saida() {
		posSaida++;
		System.out.println("# " + idpessoa + "saiu ");

	}
}
