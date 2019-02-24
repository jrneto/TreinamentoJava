package com.ciandt.training.challenge1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaixaEletronico {
	 
	private Map<Integer, Integer> caixa = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> notas = new HashMap<Integer, Integer>();
	private double valorSaque;
	
	private final double VALOR_MAXIMO = 1000d;
	private final int QTD_NOTAS = 100;	 
	
	public CaixaEletronico() {
		iniciar();
	}
	
	private void iniciar() {
		caixa.put(2, QTD_NOTAS);
		caixa.put(5, QTD_NOTAS);
		caixa.put(10, QTD_NOTAS);
		caixa.put(20, QTD_NOTAS);
		caixa.put(50, QTD_NOTAS);
		caixa.put(100, QTD_NOTAS);
	}
	
	private void zerarNotas() {
		notas.put(2, 0);
		notas.put(5, 0);
		notas.put(10, 0);
		notas.put(20, 0);
		notas.put(50, 0);
		notas.put(100, 0); 
	}

	public void sacar(int valor) {
		zerarNotas();
		this.valorSaque = valor;
		
		if (!valorValido(valor)) {
			return;
		}
		
		while (valor > 0) {
			if (valor >= 100 && temNotaCaixa(100)) {
				valor -= 100;
				caixa.put(100, caixa.get(100) - 1);
				notas.put(100, notas.get(100) + 1);
				continue;
			}
			if (valor >= 50 && valor < 100 && temNotaCaixa(50)) {
				valor -= 50;
				caixa.put(50, caixa.get(50) - 1);
				notas.put(50, notas.get(50) + 1);
				continue;
			}
			if (valor >= 20 && valor < 50 && temNotaCaixa(20)) {
				valor -= 20;
				caixa.put(20, caixa.get(20) - 1);
				notas.put(20, notas.get(20) + 1);
				continue;
			}
			if (valor >= 10 && valor < 20 && temNotaCaixa(10)) {
				valor -= 10;
				caixa.put(10, caixa.get(10) - 1);
				notas.put(10, notas.get(10) + 1);
				continue;
			}
			if (valor >= 5 && valor < 10 && temNotaCaixa(5)) {
				valor -= 5;
				caixa.put(5, caixa.get(5) - 1);
				notas.put(5, notas.get(5) + 1);
				continue;
			}
			if (valor >= 2 && valor < 5 && temNotaCaixa(2)) {
				valor -= 2;
				caixa.put(2, caixa.get(2) - 1);
				notas.put(2, notas.get(2) + 1);
				continue;
			} else {
				valor = 0;
			}
		}
		
		visualizarNotasSacadas();
		System.out.format("Saque de %s efetuado!%n",valorSaque);
		System.out.println("Obrigado, volte sempre!");
	}
	
	private boolean valorValido(int valor) {
		if (valor > 0) {
		  if (valor > VALOR_MAXIMO) {
			  System.out.println("Não é permitido sacar mais do que R$" + VALOR_MAXIMO + "." );
			  return false;
		  } else {
			  if (!ehMultiplo(valor)) {
				  System.out.format("Valor %s inválido!", valor);
				  return false;
			  }
		  }
		} else {
			System.out.println("Valor deve ser maior que zero!");
			return false;
		}
		return true;
	}
	
	private boolean ehMultiplo(int valor) {
		for (Map.Entry<Integer, Integer> pair: caixa.entrySet()) {
			if (valor % pair.getKey() == 0) {
				return true;
			}
		}
		return false;
	}

	public void visualizarCaixa() {
		caixa.forEach((k, v) -> {
            System.out.format("Notas: %s, Quantidade: %d%n", k, v);
        });
	}
	
	public void visualizarNotasSacadas() {
		notas.forEach((k, v) -> {
			if (v > 0) {
	            System.out.format("%s de %d%n", v, k);
			}
        });
	}
	
	private boolean temNotaCaixa(int valor) {
		return caixa.get(valor) > 0;
	}
	
	public enum Notas {
	    DOIS(2),CINCO(5),DEZ(10),VINTE(20),CINQUENTA(50),CEM(100);
	 
	    public int valorNota;
	    Notas(int valor) {
	        valorNota = valor;
	    }
	}

	 
}
