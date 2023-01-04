package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] possibilidadeDeMovimentos() {

		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao posicaoAuxiliar = new Posicao(0,0);
		
		// Movimentação para cima da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna());
		
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setLinha(posicaoAuxiliar.getLinha() - 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para esquerda da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha(), posicao.getColuna() - 1);
				
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setColuna(posicaoAuxiliar.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para direita da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha(), posicao.getColuna() + 1);
				
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setColuna(posicaoAuxiliar.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para baixo da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna());
		
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setLinha(posicaoAuxiliar.getLinha() + 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		return matriz;
	}

}
