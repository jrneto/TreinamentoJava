package com.ciandt.training.challenge1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class CaixaEletronico {
	 
	private Map<Cedula, Integer> caixa = new TreeMap<Cedula, Integer>();
	private Map<Cedula, Integer> cedulasSacadas = new TreeMap<Cedula, Integer>();
	private double valorSaque;
	
	private final double VALOR_MAXIMO = 10000d;
	private final int QTD_NOTAS = 100;	 
	
	public CaixaEletronico() {
		iniciar();
	}
	
	private void iniciar() {
		caixa.put(Cedula.DOIS, QTD_NOTAS);
		caixa.put(Cedula.CINCO, QTD_NOTAS);
		caixa.put(Cedula.DEZ, QTD_NOTAS);
		caixa.put(Cedula.VINTE, QTD_NOTAS);
		caixa.put(Cedula.CINQUENTA, QTD_NOTAS);
		caixa.put(Cedula.CEM, QTD_NOTAS);
	}
	
	private void zerarCedulasSacadas() {
		cedulasSacadas.put(Cedula.DOIS, 0);
		cedulasSacadas.put(Cedula.CINCO, 0);
		cedulasSacadas.put(Cedula.DEZ, 0);
		cedulasSacadas.put(Cedula.VINTE, 0);
		cedulasSacadas.put(Cedula.CINQUENTA, 0);
		cedulasSacadas.put(Cedula.CEM, 0); 
	}

	public void sacar(int valor) { 
		System.out.println("##### Início Saque #####");
		System.out.println("Valor saque: " + valor);
		visualizarTotalCaixa();
		zerarCedulasSacadas();
		this.valorSaque = valor;
		
		if (!valorValido(valor)) {
			return;
		} 
		 
		obterCedulas(valor);
		
		visualizarCedulasSacadas();
		System.out.format("Saque de %s efetuado!%n",valorSaque);
		System.out.println("Obrigado, volte sempre!");
		System.out.println("##### Fim Saque #####");
		
	}

	private void obterCedulas(int valor) {  
		int qtdCedula; 		
		Cedula maiorCedula = Cedula.getCedula(obterCedulaDisponivel(valor));
		while (valor > 0) {
			qtdCedula = 0;
			if (valor >= maiorCedula.valorCedula ) {
				qtdCedula = valor / maiorCedula.valorCedula; 
				valor %= maiorCedula.valorCedula;
				
				caixa.put(maiorCedula, caixa.get(maiorCedula) - qtdCedula);
				cedulasSacadas.put(maiorCedula, cedulasSacadas.get(maiorCedula) + qtdCedula);
								
				if (caixa.get(maiorCedula) < 0) {
					cedulasSacadas.put(maiorCedula, cedulasSacadas.get(maiorCedula) + caixa.get(maiorCedula));
					valor += maiorCedula.valorCedula * (caixa.get(maiorCedula) * -1);
					caixa.put(maiorCedula, 0);					
				}
				
				if (valor > 0) {
					obterCedulas(valor);
					return;
				} 
			}  
		} 
		 
	}
	
	 
	
	private int obterCedulaDisponivel(int valor) {
		
		if (temCedula(Cedula.CEM) && valor >= 100 ) {
			return 100;
		}
		if (temCedula(Cedula.CINQUENTA) && valor >= 50 ) {			
			return 50;		
		}
		if (temCedula(Cedula.VINTE) && valor >= 20 ) {
			return 20;
		}
		if (temCedula(Cedula.DEZ) && valor >= 10 ) {
			return 10;
		}
		if (temCedula(Cedula.CINCO) && valor >= 5) {
			if (valor % 5 == 0 || ((valor % 5) % 2 == 0) ) {
				return 5;
			}				
		}
		if (temCedula(Cedula.DOIS) && valor >= 2 ) {
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
		for (Map.Entry<Cedula, Integer> pair: caixa.entrySet()) {
			resto = valor % pair.getKey().valorCedula;
			if (resto == 0 || resto == 2 || resto == 5) {
				return true;
			}
		}
		return false;
	}

	public void visualizarCaixa() {
		System.out.println("##### Cédulas em caixa #####");
		caixa.forEach((k, v) -> {
            System.out.format("Cédula: %s, Quantidade: %d%n", k, v);
        });
		System.out.println("##### fim caixa #####");
	}
	
	public void visualizarCedulasSacadas() {
		cedulasSacadas.forEach((k, v) -> {
			if (v > 0) {
	            System.out.format("%s cédula de %d%n", v, k.valorCedula);
			}
        });
	}
	
	private boolean temCedula(Cedula nota) {
		return caixa.get(nota) > 0;
	}
	
	public enum Cedula {
	    DOIS(2),CINCO(5),DEZ(10),VINTE(20),CINQUENTA(50),CEM(100);
	 
	    public int valorCedula;
	    Cedula(int valor) {
	        valorCedula = valor;
	    }
	    
	    public static Optional<Cedula> valueOf(int value) {
	        return Arrays.stream(values())
	            .filter(n -> n.valorCedula == value)
	            .findFirst();
	    }
	    
	    public static Cedula getCedula(int index) {
	        for (Cedula n : Cedula.values()) {
	            if (n.valorCedula == index) return n;
	        }
	        throw new IllegalArgumentException("Nota não existe!");
	     }
	     
	}

	public int valorCaixa() {
		//return caixa.values().stream().mapToInt(Integer::intValue).sum();
		int valor = 0;
		for (Map.Entry<Cedula, Integer> pair: caixa.entrySet()) {
			valor += pair.getKey().valorCedula * pair.getValue();
		}
		 return valor;
	}
	
	public void visualizarTotalCaixa() {
		System.out.println("Valor total em caixa: " + valorCaixa());
	}
}
