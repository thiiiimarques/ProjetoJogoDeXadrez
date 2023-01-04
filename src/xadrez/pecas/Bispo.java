package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibilidadeDeMovimentos() {
		
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao posicaoAuxiliar = new Posicao(0,0);
		
		// Movimentação para diagonal esquerda(Noroeste) da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setValores(posicaoAuxiliar.getLinha() - 1, posicaoAuxiliar.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para diagonal direita(Nordeste) da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
				
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setValores(posicaoAuxiliar.getLinha() - 1, posicaoAuxiliar.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para diagonal baixo e esquerda(Sudoeste) da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1 , posicao.getColuna() - 1);
				
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setValores(posicaoAuxiliar.getLinha() + 1, posicaoAuxiliar.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para baixo e direita(Sudeste) da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		
		while(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			posicaoAuxiliar.setValores(posicaoAuxiliar.getLinha() + 1, posicaoAuxiliar.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		return matriz;
	}

}
