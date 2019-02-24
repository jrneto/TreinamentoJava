package com.ciandt.training.challenge1; 
 

public class Main {

	public static void main(String[] args) {
		CaixaEletronico caixaEletronico = new CaixaEletronico();
		
		try {
			System.out.println("Informe o valor do saque: ");
			
			caixaEletronico.sacar(57);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(100);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(500);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(662);
			caixaEletronico.visualizarCaixa();
		} 
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
