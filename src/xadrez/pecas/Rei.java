package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean testarJogadaRoque(Posicao posicao) {
		
		PecaXadrez peca = (PecaXadrez) getTabuleiro().buscarPeca(posicao);
		return peca != null && peca instanceof Torre && peca.getCor() == getCor() && peca.getContagemMovimentos() == 0;
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
		
		// Possivel Movimento Especial no jogo de Xadrez - Roque
		
		if(getContagemMovimentos() == 0 && !partidaXadrez.getXeque()) {
			// Roque Pequeno - possibilidade de andar uma casa
			Posicao posicaoRoquePequeno = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if(testarJogadaRoque(posicaoRoquePequeno)) {
				Posicao posicao1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao posicao2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if(getTabuleiro().buscarPeca(posicao1) == null && getTabuleiro().buscarPeca(posicao2) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			
			// Roque Grande - possibilidade de andar uma casa
			Posicao posicaoRoqueGrande = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if(testarJogadaRoque(posicaoRoqueGrande)) {
				Posicao posicao1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao posicao2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao posicao3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if(getTabuleiro().buscarPeca(posicao1) == null && getTabuleiro().buscarPeca(posicao2) == null && getTabuleiro().buscarPeca(posicao3) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return matriz;
	}
	
	private boolean validarMovimentoPosicao(Posicao posicao) {
		
		PecaXadrez peca = (PecaXadrez) getTabuleiro().buscarPeca(posicao);
		return peca == null || peca.getCor() != getCor();
	}

}
