package jantarfilosofos;

import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {

	private final int id;
	private final String nome;
	private Estado estado;
	private final Semaphore semaforo = new Semaphore(0);

	public Filosofo(int id, String nome) {
		this.id = id;
		this.nome = nome;
		estado = Estado.PENSANDO;
	}

	public void run() {
		try {
			while (true) {
				imprime();
				switch (estado) {
				case PENSANDO:
					pensando();
					JantarFilosofos.mutex.acquire();
					estado = Estado.FAMINTO;
					break;

				case FAMINTO:
					verifica(this);

					JantarFilosofos.mutex.release();
					semaforo.acquire();
					estado = Estado.COMENDO;
					break;

				case COMENDO:
					comendo();
					JantarFilosofos.mutex.acquire();
					estado = Estado.PENSANDO;
					verifica(esquerda());
					verifica(direita());
					JantarFilosofos.mutex.release();
					break;
				}
			}
		} catch (InterruptedException e) {
		}
	}

	private void pensando() {
		try {
			Thread.sleep((long) Math.round(Math.random() * 5000));
		} catch (InterruptedException e) {
		}
	}

	private void comendo() {
		try {
			Thread.sleep((long) Math.round(Math.random() * 5000));
		} catch (InterruptedException e) {
		}
	}

	public Filosofo esquerda() {
		return JantarFilosofos.filosofos.get(id == 0 ? JantarFilosofos.quantidade - 1 : id - 1);
	}

	public Filosofo direita() {
		return JantarFilosofos.filosofos.get((id + 1) % JantarFilosofos.quantidade);
	}

	private static void verifica(Filosofo filosofo) {
		if (filosofo.esquerda().estado != Estado.COMENDO && filosofo.estado == Estado.FAMINTO
				&& filosofo.direita().estado != Estado.COMENDO) {
			filosofo.estado = Estado.COMENDO;
			filosofo.semaforo.release();
		}
	}

	private void imprime() {
		System.out.println("(" + (id + 1) + ") " + "O FILÓSOFO " + nome + " ESTÁ " + estado.getDescricao());
	}
}
