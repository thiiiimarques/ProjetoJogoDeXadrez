package xadrez;


import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		
		tabuleiro = new Tabuleiro(8,8);
		iniciarPartida();
	}
	
	public PecaXadrez[][] getPecas(){
		
		PecaXadrez[][] matrizPartida = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		 for(int i=0; i<tabuleiro.getLinhas(); i++) {
			 for(int j=0; j<tabuleiro.getColunas(); j++) {
				 matrizPartida[i][j] = (PecaXadrez) tabuleiro.buscarPeca(i, j);				 
			 }
		 }
		 
		 return matrizPartida;
	}
	
	public PecaXadrez executarJogadaPartida(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.converterPosicaoMatriz();
		Posicao destino = posicaoDestino.converterPosicaoMatriz();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = moverPeca(origem, destino);
		
		return (PecaXadrez) pecaCapturada;
	}
	
	private Peca moverPeca(Posicao origem, Posicao destino) {
		Peca pecaRemovida = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino); // Uma possivel "comida" de peça caso haja alguma na posição de destino
		tabuleiro.colocarPecaTabuleiro(pecaRemovida, destino);
		
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posicao de origem! Verifique a posição corretamente");
		}
		if (!tabuleiro.buscarPeca(posicao).existeMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existe movimentos possiveis para a peça escolhida!");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.buscarPeca(origem).possibilidadeDeMovimento(destino)) {
			throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posicao de destino");
		}
	}
	
	
	private void colocarNovaPeca(int coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPecaTabuleiro(peca, new PosicaoXadrez(coluna, linha).converterPosicaoMatriz());
	}
	
	private void iniciarPartida() {
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 8, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 8, new Rei(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 8, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 7, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 7, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 7, new Torre(tabuleiro, Cor.VERDE));
		
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 1, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 1, new Rei(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 1, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 2, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 2, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 2, new Torre(tabuleiro, Cor.VERMELHO));
	}
}
