package xadrez;


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
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peça) {
		tabuleiro.colocarPecaTabuleiro(peça, new PosicaoXadrez(coluna, linha).converterPosicaoMatriz());
	}
	
	private void iniciarPartida() {
		colocarNovaPeca('b', 3 , new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e' , 1 , new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e' , 8 , new Rei(tabuleiro, Cor.PRETO));
	}
}
