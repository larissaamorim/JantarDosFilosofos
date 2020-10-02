package jantarfilosofos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class JantarFilosofos {

	public static final int quantidade = 5;
	public static final Semaphore mutex = new Semaphore(1);
	public static final List<String> nomes = new ArrayList<String>();
	public static final List<Filosofo> filosofos = new ArrayList<Filosofo>();

	public static void main(String[] args) {

		nomes.add("RENÉ DESCARTES");
		nomes.add("PLATÃO");
		nomes.add("GEORG HEGEL");
		nomes.add("SÃO TOMÁS DE AQUINO");
		nomes.add("LUDWIG WITTGENSTEIN");

		for (int i = 0; i < quantidade; i++) {
			filosofos.add(new Filosofo(i, nomes.get(i)));
		}

		for (Thread t : filosofos) {
			t.start();
		}

	}

}
