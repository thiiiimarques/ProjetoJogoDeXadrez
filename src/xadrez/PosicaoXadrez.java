package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {
	
	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna < 'a' || linha < 0 || coluna > 'h' || linha > 8) {
			throw new ExcecaoXadrez(" Erro de posição no Jogo, É valido posições até a linha 8 e coluna h");
		}
		
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posicao converterPosicaoMatriz() {
		return new Posicao(0 + (linha-1) , coluna - 'a');
		// como ele fez uma operação de diminuir uma letra por outra letra?
	}
	
	protected static PosicaoXadrez converterPosicaoJogo(Posicao posicao) {
		return new PosicaoXadrez( (char) ('a' - posicao.getColuna()) , 0 + (posicao.getLinha()-1));
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
}
