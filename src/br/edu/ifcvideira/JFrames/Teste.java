package br.edu.ifcvideira.JFrames;

import br.edu.ifcvideira.Classes.*;
import br.edu.ifcvideira.DAOs.*;

public class Teste {
	public static void main(String[] args) {
		Cliente cl = new Cliente();
		ClienteDao dao = new ClienteDao();
		
		cl.setCodigo(1);
		cl.setCpf("dsasasda");
		cl.setNome("dsdassad");
		try {
			dao.CadastrarCliente(cl);
		}catch(Exception e){
			System.out.println("Erro:  " + e);
		}
	}
}
