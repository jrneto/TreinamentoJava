package com.ciandt.training.challenge1; 
 

public class Main {
 
	public static void main(String[] args) {
		CaixaEletronico caixaEletronico = new CaixaEletronico();
		
		try {
			caixaEletronico.visualizarTotalCaixa();
			System.out.println("Informe o valor do saque: ");
			
			caixaEletronico.sacar(110);
			caixaEletronico.visualizarCaixa();
			 
		} 
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
