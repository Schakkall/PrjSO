package com.so.projeto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * GUILHERME BORONI PEREIRA 
 * JOSÉ EURIQUE C. RIBEIRO JR
 */

public class Main {

	static String lote = "entrada2.txt";

	public static void main(String[] args) {
		Memory m = filaProcessos(lote);
		FIFO(m);
		OTM(m);
		LRU(m);
	}

	/**
	 * OS DOIS PRIMEIROS ALGORITMOS UTILIZAM COMO QUADROS UM ARRAY, O SEGUNDO
	 * UTILIZA A LISTA
	 */

	public static void OTM(Memory m) {
		m.reset();
		int pagefault = 0;
		int refPos = 0;
		for (Integer i : m.getReferencias()) {
			if (!m.contains(i)) {
				int maisDistantePos = 0;
				int maisDistante = 0;
				Boolean replaceValue = false;
				for (int cont = 0; cont < m.quadros.length; cont++) {
					if (m.quadros[cont] == null) {
						m.quadros[cont] = i;
						replaceValue = false;
						break;
					} else {
						Integer distancia = m.getDistance(m.quadros[cont], refPos);
						if (distancia > maisDistante) {
							maisDistante = distancia;
							maisDistantePos = cont;
							replaceValue = true;
						}
					}
				}
				if (replaceValue)
					m.quadros[maisDistantePos] = i;
				pagefault++;
			}
			refPos++;
		}
		System.out.println("OTM " + pagefault);
	}

	public static void FIFO(Memory m) {
		m.reset();
		int pagefault = 0;
		int quadroPos = 0;
		for (Integer i : m.getReferencias()) {
			if (!m.contains(i)) {
				pagefault++;
				m.quadros[quadroPos] = i;
				quadroPos++;
				if (quadroPos > m.getSize()) {
					quadroPos = 0;
				}
			}
		}
		System.out.println("FIFO " + pagefault);
	}

	public static void LRU(Memory m) {
		m.reset();
		int pagefault = 0;
		int quadroPos = 0;
		for (Integer i : m.getReferencias()) {
			if (!m.containsInList(i)) {
				pagefault++;
				if (m.isFull()) {
					m.listQuadros.remove(0);
					m.listQuadros.add(i);
				} else {
					m.listQuadros.add(i);
				}
			} else {
				quadroPos = m.getPosition(i);
				m.listQuadros.remove(quadroPos);
				m.listQuadros.add(i);
			}
		}
		System.out.println("LRU " + pagefault);
	}

	public static Memory filaProcessos(String arquivo) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			Memory m = new Memory(Integer.valueOf(linha));
			while (br.ready()) {
				linha = br.readLine();
				m.addReferencia(Integer.valueOf(linha));
			}
			br.close();
			return m;
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

}
