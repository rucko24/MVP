package com.Core.vaadin.tables.juegoPalabras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.server.VaadinServlet;

public class Diccionario {

	private static boolean found = false;

	public static boolean existe(String word) {

		word = word.toLowerCase();

		try {
			BufferedReader br = new BufferedReader(
					new FileReader(VaadinServlet.getCurrent().getServletContext().getRealPath("")+"/en_US.dic"));
				
			String line = "";
			line = br.readLine();

			while (line != null) {
				if (line.contains("/")) { // alguna palabras tienes metadata,if
											// so
					line = line.split("/")[0]; // ignoramos metadatos
				}
				if (line.equals(word)) {
					found = true;
					break;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return found;
	}

	public static Collection<String> getWords(String line) {
		
		ArrayList<String> list = new ArrayList<String>();

		for (int begin = 0; begin <= line.length() - 2; begin++) {
			for (int end = line.length(); end >= begin; end--) {

				String sSubString = line.substring(begin, end);

				if (existe(sSubString)) {
					list.add(sSubString);
					break;
				}
			}
		}

		return list;
	}
}
