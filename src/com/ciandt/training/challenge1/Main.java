package com.ciandt.training.challenge1; 
 

public class Main {
 
	public static void main(String[] args) {
		CaixaEletronico caixaEletronico = new CaixaEletronico();
		
		try {
			caixaEletronico.visualizarTotalCaixa();
			System.out.println("Informe o valor do saque: ");
			
			caixaEletronico.sacar(2);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(4);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(5);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(6);
			caixaEletronico.visualizarCaixa();			
			caixaEletronico.sacar(7);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(8);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(9);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(10);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(12);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(17);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(57);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(698);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(9998);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(2222);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(4465);
			caixaEletronico.visualizarCaixa();
			caixaEletronico.sacar(1317);
			caixaEletronico.visualizarCaixa();
		} 
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
