package com.ciandt.training.challenge1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaixaEletronico {
	 
	private Map<Notas, Integer> caixa = new HashMap<Notas, Integer>();
	private Map<Notas, Integer> notas = new HashMap<Notas, Integer>();
	private double valorSaque;
	
	private final double VALOR_MAXIMO = 1000d;
	private final int QTD_NOTAS = 100;	 
	
	public CaixaEletronico() {
		iniciar();
	}
	
	private void iniciar() {
		caixa.put(Notas.DOIS, QTD_NOTAS);
		caixa.put(Notas.CINCO, QTD_NOTAS);
		caixa.put(Notas.DEZ, QTD_NOTAS);
		caixa.put(Notas.VINTE, QTD_NOTAS);
		caixa.put(Notas.CINQUENTA, QTD_NOTAS);
		caixa.put(Notas.CEM, QTD_NOTAS);
	}
	
	private void zerarNotas() {
		notas.put(Notas.DOIS, 0);
		notas.put(Notas.CINCO, 0);
		notas.put(Notas.DEZ, 0);
		notas.put(Notas.VINTE, 0);
		notas.put(Notas.CINQUENTA, 0);
		notas.put(Notas.CEM, 0); 
	}

	public void sacar(int valor) {
		zerarNotas();
		this.valorSaque = valor;
		
		if (!valorValido(valor)) {
			return;
		}
		
		while (valor > 0) {
			if (valor >= 100 && temNota(Notas.CEM)) {
				valor -= 100;
				caixa.put(Notas.CEM, caixa.get(Notas.CEM) - 1);
				notas.put(Notas.CEM, notas.get(Notas.CEM) + 1);
				continue;
			}
			if (valor >= 50 && valor < 100 && temNota(Notas.CINQUENTA)) {
				valor -= 50;
				caixa.put(Notas.CINQUENTA, caixa.get(Notas.CINQUENTA) - 1);
				notas.put(Notas.CINQUENTA, notas.get(Notas.CINQUENTA) + 1);
				continue;
			}
			if (valor >= 20 && valor < 50 && temNota(Notas.VINTE)) {
				valor -= 20;
				caixa.put(Notas.VINTE, caixa.get(Notas.VINTE) - 1);
				notas.put(Notas.VINTE, notas.get(Notas.VINTE) + 1);
				continue;
			}
			if (valor >= 10 && valor < 20 && temNota(Notas.DEZ)) {
				valor -= 10;
				caixa.put(Notas.DEZ, caixa.get(Notas.DEZ) - 1);
				notas.put(Notas.DEZ, notas.get(Notas.DEZ) + 1);
				continue;
			}
			if (valor >= 5 && valor < 10 && temNota(Notas.CINCO)) {
				valor -= 5;
				caixa.put(Notas.CINCO, caixa.get(Notas.CINCO) - 1);
				notas.put(Notas.CINCO, notas.get(Notas.CINCO) + 1);
				continue;
			}
			if (valor >= 2 && valor < 5 && temNota(Notas.DOIS)) {
				valor -= 2;
				caixa.put(Notas.DOIS, caixa.get(Notas.DOIS) - 1);
				notas.put(Notas.DOIS, notas.get(Notas.DOIS) + 1);
				continue;
			} else {
				valor = 0;
			}
		}
		
		visualizarNotasSacadas();
		System.out.format("Saque de %s efetuado!%n",valorSaque);
		System.out.println("Obrigado, volte sempre!");
		visualizarTotalCaixa();
	}
	
	private boolean valorValido(int valor) {
		if (valor > 0) {
		  if (valor > VALOR_MAXIMO) {
			  System.out.println("Não é permitido sacar mais do que R$" + VALOR_MAXIMO + "." );
			  return false;
		  } else {
			  if (valor > valorCaixa()) {
				  System.out.println("Não há dinheiro suficiente no caixa!");
				  return false;
			  }
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
		for (Map.Entry<Notas, Integer> pair: caixa.entrySet()) {
			if (valor % pair.getKey().valorNota == 0) {
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
	            System.out.format("%s de %d%n", v, k.valorNota);
			}
        });
	}
	
	private boolean temNota(Notas nota) {
		return caixa.get(nota) > 0;
	}
	
	public enum Notas {
	    DOIS(2),CINCO(5),DEZ(10),VINTE(20),CINQUENTA(50),CEM(100);
	 
	    public int valorNota;
	    Notas(int valor) {
	        valorNota = valor;
	    }
	}

	public int valorCaixa() {
		//return caixa.values().stream().mapToInt(Integer::intValue).sum();
		int valor = 0;
		for (Map.Entry<Notas, Integer> pair: caixa.entrySet()) {
			valor += pair.getKey().valorNota * pair.getValue();
		}
		 return valor;
	}
	
	public void visualizarTotalCaixa() {
		System.out.println("Valor total em caixa: " + valorCaixa());
	}
}
