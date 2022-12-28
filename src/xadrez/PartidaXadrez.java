package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		
		tabuleiro = new Tabuleiro(8,8);
		iniciarPartida();
	}
	
	public PecaXadrez[][] getPecas(){
		
		PecaXadrez[][] matrizPartida = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		 for(int i=0; i<tabuleiro.getLinhas(); i++) {
			 for(int j=0; j<tabuleiro.getColunas(); j++) {
				 matrizPartida[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
				 // Tabuleiro não é uma subclasse de peça ou peçaXadrez (peçaXadrez que é filha de peça), porque preciso do downcasting aqui?
			 }
		 }
		 
		 return matrizPartida;
	}
	
	private void iniciarPartida() {
		tabuleiro.colocarPecaTabuleiro(new Torre(tabuleiro, Cor.BRANCO), new Posicao(0, 1));
		tabuleiro.colocarPecaTabuleiro(new Rei(tabuleiro, Cor.BRANCO), new Posicao(0, 4));
		tabuleiro.colocarPecaTabuleiro(new Rei(tabuleiro, Cor.PRETO), new Posicao(7, 4));
	}
}
