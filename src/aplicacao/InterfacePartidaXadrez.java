package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.ValoresColunasMatriz;

public class InterfacePartidaXadrez {
	
	// Codigo sobre as cores no console tirados do stackoverflow
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";

	
	// Codigo tirado do stackoverflow : https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner scan) {
		
		try {
		
			String posicaoEscolhida = scan.nextLine();
			
			int coluna = ValoresColunasMatriz.valueOf(posicaoEscolhida.substring(0, 1)).valorColuna;
			int linha = Integer.parseInt(posicaoEscolhida.substring(1));
			
			return new PosicaoXadrez(coluna, linha);
		}
		catch(RuntimeException excecao) {
			throw new InputMismatchException("Error para ler a posição. Valide se o valor esta entre a1-a8 até h1-h8!");
		}
	}
	
	public static void imprimirPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> pecasCapturadas) {
		imprimirTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		imprimirPecasCapturadas(pecasCapturadas);
		System.out.println();
		
		if(!partidaXadrez.getXequeMate()) {
			System.out.println("Turno: " + partidaXadrez.getTurno());			
			System.out.println("Aguardando o Jogador: " + partidaXadrez.getJogador());
			
			if(partidaXadrez.getXequeMate()) {
				System.out.print(ANSI_YELLOW);
				System.out.println("XEQUE!!!");
				System.out.print(ANSI_RESET);
			}
		}
		else {
			System.out.print(ANSI_YELLOW);
			System.out.println("XEQUE-MATE!!!");
			System.out.println("O vencedor é o: " + partidaXadrez.getJogador());
			System.out.print(ANSI_RESET);
		}
	
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
		
		for(int i=0; i<pecas.length; i++) {
			System.out.print((i+1) + " ");
			for(int j=0; j<pecas.length; j++) {
				imprimirPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		
		System.out.println("  a  b  c  d  e  f  g  h");
		
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] corPossiveisMovimentos) {
		
		for(int i=0; i<pecas.length; i++) {
			System.out.print((i+1) + " ");
			for(int j=0; j<pecas.length; j++) {
				imprimirPeca(pecas[i][j], corPossiveisMovimentos[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("  a  b  c  d  e  f  g  h");
		
	}
	
	private static void imprimirPeca(PecaXadrez peca, boolean corFundoMovimento) {
		if(corFundoMovimento) {
			System.out.print(ANSI_YELLOW_BACKGROUND);
		}
		
		if( peca == null) {
			System.out.print("-" + ANSI_RESET);
		}
		else {
			if(peca.getCor() == Cor.VERMELHO) {
			System.out.print(ANSI_RED + peca + ANSI_RESET);
			}
			else {
				System.out.print(ANSI_GREEN + peca + ANSI_RESET);
			}
		}
		
		System.out.print("  ");
	}
	
	private static void imprimirPecasCapturadas(List<PecaXadrez> pecasCapturadas) {
		
		List<PecaXadrez> pecasVerdes = pecasCapturadas.stream().filter(x -> x.getCor() == Cor.VERDE).collect(Collectors.toList());
		List<PecaXadrez> pecasVermelhas = pecasCapturadas.stream().filter(x -> x.getCor() == Cor.VERMELHO).collect(Collectors.toList());
		
		System.out.println("Peças capturadas: ");
		System.out.println();
		System.out.print(ANSI_GREEN);
		System.out.print("Verdes: ");
		System.out.println(Arrays.toString(pecasVerdes.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print(ANSI_RED);
		System.out.print("Vermelhas: ");
		System.out.println(Arrays.toString(pecasVermelhas.toArray()));
		System.out.print(ANSI_RESET);
	}

}
