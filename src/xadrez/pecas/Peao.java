package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibilidadeDeMovimentos() {
		
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao posicaoAuxiliar = new Posicao(0,0);
		
		if(getCor() == Cor.VERDE) {
			posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}
			
			posicaoAuxiliar.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao posicaoAuxiliar2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar) && getTabuleiro().existePosicao(posicaoAuxiliar2) && !getTabuleiro().existePeca(posicaoAuxiliar2) && getContagemMovimentos() == 0) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}
			
			posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}
			
			posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}		
			
		}
		else {
			
			posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}
			
			posicaoAuxiliar.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao posicaoAuxiliar2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && !getTabuleiro().existePeca(posicaoAuxiliar) && getTabuleiro().existePosicao(posicaoAuxiliar2) && !getTabuleiro().existePeca(posicaoAuxiliar)) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}
			
			posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}
			
			posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().existePosicao(posicaoAuxiliar) && existePecaAdversaria(posicaoAuxiliar)) {
				matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
			}
			
		}
		
		
		return matriz;
	}


}
