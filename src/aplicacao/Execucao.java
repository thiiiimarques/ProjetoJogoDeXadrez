package aplicacao;


import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Execucao {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {
			InterfacePartidaXadrez.imprimirTabuleiro(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Posicao de Origem: ");
			PosicaoXadrez origem = InterfacePartidaXadrez.lerPosicaoXadrez(scan);
			System.out.println();
			System.out.print("Posicao de Destino: ");
			PosicaoXadrez destino = InterfacePartidaXadrez.lerPosicaoXadrez(scan);
			
			PecaXadrez pecaCapturada = partidaXadrez.executarJogadaPartida(origem, destino);
			
			System.out.println();
		}
		
		

	}

}
