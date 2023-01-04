package aplicacao;


import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Execucao {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {
			try {
				InterfacePartidaXadrez.limparTela();
				InterfacePartidaXadrez.imprimirTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.print("Posicao de Origem: ");
				PosicaoXadrez origem = InterfacePartidaXadrez.lerPosicaoXadrez(scan);
				
				boolean[][] corPossiveisMovimentos = partidaXadrez.corMovimentosPossiveis(origem);
				InterfacePartidaXadrez.limparTela();
				InterfacePartidaXadrez.imprimirTabuleiro(partidaXadrez.getPecas(), corPossiveisMovimentos);
				
				System.out.println();
				System.out.print("Posicao de Destino: ");
				PosicaoXadrez destino = InterfacePartidaXadrez.lerPosicaoXadrez(scan);
				
				PecaXadrez pecaCapturada = partidaXadrez.executarJogadaPartida(origem, destino);
				
				System.out.println();
			}
			catch (ExcecaoXadrez excecao) {
				System.out.println(excecao.getMessage());
				scan.nextLine();
			}
			catch (InputMismatchException excecao) {
				System.out.println(excecao.getMessage());
				scan.nextLine();
			}
			
		}
		
		

	}

}
