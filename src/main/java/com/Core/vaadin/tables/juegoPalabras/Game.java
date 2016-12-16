package com.Core.vaadin.tables.juegoPalabras;

import java.util.Collection;
import java.util.Random;

import com.vaadin.ui.Notification;

public class Game {
	
	
	private final int value;
	private String currentLetter = null;
	private int contadorDeLetra = 0;
	private int primo = 0;
	private static Random r = new Random();
	
	
	private String abecedario = "AAAAAAAAA" + "BB" + "CCC" + "DDDDD"
			+ "EEEEEEEEEEEEE" + "FFF" + "GGG" + "HHHHHHH" + "IIIIIII" + "J"
			+ "K" + "KKKKK" + "LLLLL" + "MMM" + "NNNNNNN" + "OOOOOOOO" + "PP"
			+ "Q" + "RRRRRR" + "SSSSSSS" + "TTTTTTTTTT" + "UUUUUUU" + "V"
			+ "WWW" + "X" + "YY" + "Z";
	
	public Game(int value) {
		this.value = value;
		r.setSeed(System.currentTimeMillis());
	}
	
	public int getSize() {
		return value;
	}
	
	
	public int random(int x) {
		
		return (1 + r.nextInt(x)) -1 ;
	}
	
	public boolean over() {
		return contadorDeLetra >= value * value;
	}
	
	public String nextLetter() {
		currentLetter = "" +  abecedario.charAt(random(abecedario.length()));
		
		contadorDeLetra ++;
		return currentLetter;
	}
	public String getCurrentLetter() {
		return currentLetter;
	}
	
	public Collection<String> getWords(String line) {
		return Diccionario.getWords(line);
	}
	
	public int getScore(Collection<String> words) {
		int score = 0;
		for(String word : words) {
			score += word.length();
		}
		return score;
	}
	
	public int getPrimo() { return primo;}
	
	public boolean getNumeroPrimo() {
		int num = random(100);
		for(int f=2; f<num; f++) {
			if(num % f == 0) {
				return false;
			}else {
				primo = num;
			}
		}
		return true;
	}
}
