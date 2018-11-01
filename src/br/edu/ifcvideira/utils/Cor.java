package br.edu.ifcvideira.utils;

import java.awt.Color;

public class Cor {
	public static Color corMaisClara(Color cor, float quantia) {
	    int r = (int) ((cor.getRed() * (1 - quantia) / 255 + quantia) * 255);
	    int g = (int) ((cor.getGreen() * (1 - quantia) / 255 + quantia) * 255);
	    int b = (int) ((cor.getBlue() * (1 - quantia) / 255 + quantia) * 255);
	    return new Color(r, g, b);
	}
}
