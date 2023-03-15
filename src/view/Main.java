package view;

import java.util.concurrent.Semaphore;

import Controller.corredor;

public class Main {

	public static void main(String[] args) {
		int permissao=1;
		Semaphore semaforo= new Semaphore(permissao);
		for(int idpessoa=1; idpessoa<=4;idpessoa++) {
			Thread tpessoa= new corredor(idpessoa,semaforo);
			tpessoa.start();
		}
	}

}
