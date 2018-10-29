package br.edu.ifcvideira.utils;

import java.awt.Color;

public class Cor {
	public static Color corMaisClara(Color color, float quantia) {
	    int r = (int) ((color.getRed() * (1 - quantia) / 255 + quantia) * 255);
	    int g = (int) ((color.getGreen() * (1 - quantia) / 255 + quantia) * 255);
	    int b = (int) ((color.getBlue() * (1 - quantia) / 255 + quantia) * 255);
	    return new Color(r, g, b);
	  }
}
