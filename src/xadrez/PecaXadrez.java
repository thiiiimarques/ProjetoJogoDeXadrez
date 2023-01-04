package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	private Cor cor;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		
		return PosicaoXadrez.converterPosicaoJogo(posicao);
	}
	
	protected boolean existePecaAdversaria(Posicao posicao) {
		
		PecaXadrez peca = (PecaXadrez) getTabuleiro().buscarPeca(posicao);
		
		return peca != null && peca.getCor() != cor;
	}


}
