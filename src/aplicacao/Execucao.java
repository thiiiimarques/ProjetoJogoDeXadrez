package aplicacao;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Execucao {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> pecasCapturadas =  new ArrayList<>();
		
		while(!partidaXadrez.getXequeMate()) {
			try {
				InterfacePartidaXadrez.limparTela();
				InterfacePartidaXadrez.imprimirPartida(partidaXadrez, pecasCapturadas);
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
				
				if(pecaCapturada != null) {
					pecasCapturadas.add(pecaCapturada);
				}
				
				if(partidaXadrez.getPromocaoPeao() != null) {
					System.out.println("Wow! Peão promovido, digite a Letra da peça que você quer promovê-lo (B/C/T/Q): ");
					String tipoPromocao = scan.nextLine().toUpperCase();
					while(!tipoPromocao.equals("B") && !tipoPromocao.equals("C") && !tipoPromocao.equals("T") && !tipoPromocao.equals("Q")) {
						System.out.println("Opa, valor escolhido invalido! Digite a Letra da peça que você quer promovê-lo (B/C/T/Q): ");
						tipoPromocao = scan.nextLine().toUpperCase();
					}
					partidaXadrez.substituirPecaPromovida(tipoPromocao);
				}
				
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
		
		InterfacePartidaXadrez.limparTela();
		InterfacePartidaXadrez.imprimirPartida(partidaXadrez, pecasCapturadas);
		
		

	}

}
