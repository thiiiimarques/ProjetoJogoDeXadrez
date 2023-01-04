package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{
	
	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
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
			
			// Jogada Especial En Passante peca Branca
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().existePosicao(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().buscarPeca(esquerda) == partidaXadrez.getJogadaEspecialEnPassant()) {
					matriz[esquerda.getLinha() -1][esquerda.getColuna()] =  true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().existePosicao(direita) && existePecaAdversaria(direita) && getTabuleiro().buscarPeca(direita) == partidaXadrez.getJogadaEspecialEnPassant()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] =  true;
				}
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
			
			// Jogada Especial En Passante peca Preta
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().existePosicao(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().buscarPeca(esquerda) == partidaXadrez.getJogadaEspecialEnPassant()) {
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] =  true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().existePosicao(direita) && existePecaAdversaria(direita) && getTabuleiro().buscarPeca(direita) == partidaXadrez.getJogadaEspecialEnPassant()) {
					matriz[direita.getLinha() + 1][direita.getColuna()] =  true;
				}
			}
			
		}
		
		
		return matriz;
	}


}
