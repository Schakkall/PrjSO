package com.so.projeto2;

import java.util.ArrayList;

public class Memory {

	private ArrayList<Integer> referencias;

	public Integer[] quadros;

	public Memory(Integer nrreferencias) {
		quadros = new Integer[nrreferencias];
	}

	public ArrayList<Integer> getReferencias() {
		if (referencias == null)
			referencias = new ArrayList<Integer>();
		return referencias;
	}

	public void setreferencias(ArrayList<Integer> referencias) {
		this.referencias = referencias;
	}

	public int getSize() {
		return this.quadros.length - 1;
	}

	public void addReferencia(Integer i) {
		getReferencias().add(i);
	}

	public void printreferencias() {
		for (Integer integer : referencias) {
			System.out.println(integer);
		}
	}

	public Boolean contains(Integer value) {
		for (Integer integer : quadros) {
			if (integer != null && integer.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		quadros = new Integer[quadros.length];

	}

	public Integer getDistance(Integer value, Integer start) {
		int distance = Integer.MAX_VALUE;
		for (int cont = start; cont < getReferencias().size(); cont++) {
			if (value.equals(getReferencias().get(cont))){
				return cont-start;
			}
		}

		return distance;
	}

}
