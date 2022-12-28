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
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca('d', 1, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.VERDE));
		colocarNovaPeca('e', 5, new Torre(tabuleiro, Cor.VERDE));
		
		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.VERMELHO));
	}
}
