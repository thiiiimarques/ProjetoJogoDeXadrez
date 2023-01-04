package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	@Override
	public boolean[][] possibilidadeDeMovimentos() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao posicaoAuxiliar = new Posicao(0,0);
		
		// Movimentação em L para cima e esquerda da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação em L para cima e direita da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação em L para baixo e esquerda da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação em L para baixo e direita da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
				
		return matriz;

	}
	
	private boolean validarMovimentoPosicao(Posicao posicao) {
		
		PecaXadrez peca = (PecaXadrez) getTabuleiro().buscarPeca(posicao);
		return peca == null || peca.getCor() != getCor();
	}

}
