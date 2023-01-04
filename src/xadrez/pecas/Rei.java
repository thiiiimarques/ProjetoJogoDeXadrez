package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibilidadeDeMovimentos() {

		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao posicaoAuxiliar = new Posicao(0,0);
		
		// Movimentação para cima da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para baixo da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para esquerda da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para direita da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para noroeste da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para nordeste da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para sudeste da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(posicaoAuxiliar) && validarMovimentoPosicao(posicaoAuxiliar)) {
			matriz[posicaoAuxiliar.getLinha()][posicaoAuxiliar.getColuna()] = true;
		}
		
		// Movimentação para sudoeste da peça
		
		posicaoAuxiliar.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
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
