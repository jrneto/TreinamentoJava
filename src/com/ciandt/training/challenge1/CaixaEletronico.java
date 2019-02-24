package com.ciandt.training.challenge1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CaixaEletronico {
	 
	private Map<Notas, Integer> caixa = new HashMap<Notas, Integer>();
	private Map<Notas, Integer> notas = new HashMap<Notas, Integer>();
	private double valorSaque;
	
	private final double VALOR_MAXIMO = 10000d;
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
		System.out.println("##### Início Saque #####");
		System.out.println("Valor saque: " + valor);
		visualizarTotalCaixa();
		zerarNotas();
		this.valorSaque = valor;
		
		if (!valorValido(valor)) {
			return;
		} 
		 
		obterNotas(valor);
		
		visualizarNotasSacadas();
		System.out.format("Saque de %s efetuado!%n",valorSaque);
		System.out.println("Obrigado, volte sempre!");
		System.out.println("##### Fim Saque #####");
		
	}

	private void obterNotas(int valor) {  
		int qtdNotas; 		
		Notas maiorNota = Notas.getNota(obterMaiorNotaDisponivel(valor));
		while (valor > 0) {
			qtdNotas = 0;
			if (valor >= maiorNota.valorNota ) {
				qtdNotas = valor / maiorNota.valorNota; 
				caixa.put(maiorNota, caixa.get(maiorNota) - qtdNotas);
				notas.put(maiorNota, notas.get(maiorNota) + qtdNotas);
				valor %= maiorNota.valorNota;
			    
				if (caixa.get(maiorNota) < 0) {
					notas.put(maiorNota, notas.get(maiorNota) + caixa.get(maiorNota));
					valor += maiorNota.valorNota * (caixa.get(maiorNota) * -1);
					caixa.put(maiorNota, 0);					
				}
				
				if (valor > 0) {
					obterNotas(valor);
					return;
				} 
			}  
		} 
		 
	}
	
	private int obterMaiorNotaDisponivel(int valor) {
		
		if (temNota(Notas.CEM) && valor >= 100 ) {
			return 100;
		}
		if (temNota(Notas.CINQUENTA) && valor >= 50 ) {
			return 50;
		}
		if (temNota(Notas.VINTE) && valor >= 20 ) {
			return 20;
		}
		if (temNota(Notas.DEZ) && valor >= 10 ) {
			return 10;
		}
		if (temNota(Notas.CINCO) && valor >= 5) {
			if (valor % 5 == 0 || ((valor % 5) % 2 == 0) )
			return 5;
		}
		if (temNota(Notas.DOIS) && valor >= 2 ) {
			return 2;
		}
		
		return 0;
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
		int resto = 0;
		for (Map.Entry<Notas, Integer> pair: caixa.entrySet()) {
			resto = valor % pair.getKey().valorNota;
			if (resto == 0 || resto == 2 || resto == 5) {
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
	    
	    public static Optional<Notas> valueOf(int value) {
	        return Arrays.stream(values())
	            .filter(n -> n.valorNota == value)
	            .findFirst();
	    }
	    
	    public static Notas getNota(int index) {
	        for (Notas n : Notas.values()) {
	            if (n.valorNota == index) return n;
	        }
	        throw new IllegalArgumentException("Nota não existe!");
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
