package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {
	
	private int coluna;
	private int linha;
	
	public PosicaoXadrez(int coluna, int linha) {
		if(coluna < 0 || linha < 0 || coluna > 8 || linha > 8) {
			throw new ExcecaoXadrez(" Erro de posição no Jogo, É valido posições até a linha 8 e coluna h");
		}
		
		this.coluna = coluna;
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posicao converterPosicaoMatriz() {
		return new Posicao((linha-1) , coluna );
		
	}
	
	protected static PosicaoXadrez converterPosicaoJogo(Posicao posicao) {
		return new PosicaoXadrez(posicao.getColuna() , (posicao.getLinha()+1));
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
}
