package br.edu.ifcvideira.utils;

import java.awt.Color;
import java.util.prefs.Preferences;

public class Preferencias {
	static Preferences preferencias = Preferences.userNodeForPackage(br.edu.ifcvideira.utils.Preferencias.class);
	
	public static void setImagem(String caminhoImagem) {
		preferencias.put("Imagem", caminhoImagem);
	}
	
	public static String getImagem() {
		return preferencias.get("Imagem", "");
	}
	
	public static void setR(int r) {
		preferencias.putInt("R", r);
	}

	public static int getR() {
		return preferencias.getInt("R", 118);
	}
	
	public static void setG(int g) {
		preferencias.putInt("G", g);
	}

	public static int getG() {
		return preferencias.getInt("G", 184);
	}
	
	public static void setB(int b) {
		preferencias.putInt("B", b);
	}

	public static int getB() {
		return preferencias.getInt("B", 184);
	}
}
