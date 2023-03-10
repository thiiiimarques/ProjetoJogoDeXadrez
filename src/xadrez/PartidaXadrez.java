package xadrez;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private int turno;
	private Cor jogador;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	private PecaXadrez jogadaEspecialEnPassant;
	private PecaXadrez promocaoPeao;
	
	private List<Peca> pecasTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	

	public PartidaXadrez() {
		
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		jogador = Cor.VERDE; // Pensando que o verde a cor Branca numa partida de Xadrez tradicional.
		iniciarPartida();
	}
	
	public Cor getJogador() {
		return jogador;
	}
	
	public int getTurno() {
		return turno;
	}
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequeMate;
	}
	
	public PecaXadrez getJogadaEspecialEnPassant() {
		return jogadaEspecialEnPassant;
	}
	
	public PecaXadrez getPromocaoPeao() {
		return promocaoPeao;
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
	
	public boolean[][] corMovimentosPossiveis(PosicaoXadrez corMovimentosPossiveis){
		
		Posicao posicao = corMovimentosPossiveis.converterPosicaoMatriz();
		validarPosicaoOrigem(posicao);
		return tabuleiro.buscarPeca(posicao).possibilidadeDeMovimentos();
	}
	
	
	public PecaXadrez executarJogadaPartida(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.converterPosicaoMatriz();
		Posicao destino = posicaoDestino.converterPosicaoMatriz();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = moverPeca(origem, destino);
		
		if(testarXeque(jogador)) {
			desmoverPeca(origem, destino, pecaCapturada);
			
			throw new ExcecaoXadrez("Voc?? n??o pode se colocar em Xeque-Mate");
		}
		
		PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.buscarPeca(destino);
		
		
		// Jogada Especial de Promocao do Peao
		promocaoPeao = null;
		if(pecaMovida instanceof Peao) {
			if( pecaMovida.getCor() == Cor.VERDE && destino.getLinha() == 0 || pecaMovida.getCor() == Cor.VERMELHO && destino.getLinha() == 7){
				promocaoPeao = (PecaXadrez) tabuleiro.buscarPeca(destino);
				promocaoPeao = substituirPecaPromovida("Q");
			}
		}
		
		
		xeque = (testarXeque(oponente(jogador))) ? true : false;
		
		if(testarXequeMate(oponente(jogador))) {
			xequeMate = true;
		}
		else {
			proximaJogada();
		}
		
		// Jogada Especial En Passant
		
		if(pecaMovida instanceof Peao && destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2) {
			jogadaEspecialEnPassant = pecaMovida;
		}
		else {
			jogadaEspecialEnPassant = null;
		}
		
		return (PecaXadrez) pecaCapturada;
	}
	
	public PecaXadrez substituirPecaPromovida(String tipo) {
		if(promocaoPeao == null) {
			throw new IllegalStateException("N??o ha pe??a para ser promovida neste momento");
		}
		if(!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("Q")) {
			return promocaoPeao;
		}
		
		Posicao posicaoPecaPromovida = promocaoPeao.getPosicaoXadrez().converterPosicaoMatriz();
		Peca peca = tabuleiro.removerPeca(posicaoPecaPromovida);
		pecasTabuleiro.remove(peca);
		
		PecaXadrez novaPeca = novaPecaPromovida(tipo, promocaoPeao.getCor());
		tabuleiro.colocarPecaTabuleiro(novaPeca, posicaoPecaPromovida);
		pecasTabuleiro.add(novaPeca);
		
		return novaPeca;
		
	}
	
	private PecaXadrez novaPecaPromovida(String tipo, Cor cor) {
		if (tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if (tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if (tipo.equals("T")) return new Torre(tabuleiro, cor);
		return new Rainha(tabuleiro, cor);
	}
	
	private Peca moverPeca(Posicao origem, Posicao destino) {
		PecaXadrez pecaRemovida = (PecaXadrez) tabuleiro.removerPeca(origem);
		pecaRemovida.adicionarMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(destino); // Uma possivel "comida" de pe??a caso haja alguma na posi????o de destino
		tabuleiro.colocarPecaTabuleiro(pecaRemovida, destino);
		
		if(pecaCapturada != null) {
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		
		// Movimento Especial Roque Pequeno
		
		if(pecaRemovida instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocarPecaTabuleiro(torre, destinoTorre);
			torre.adicionarMovimentos();
		}
		
		// Movimento Especial Roque Grande
		
		if(pecaRemovida instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.colocarPecaTabuleiro(torre, destinoTorre);
			torre.adicionarMovimentos();
		}
		
		
		//Movimento Especial En Passant
		
		if(pecaRemovida instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if(pecaRemovida.getCor() == Cor.VERDE) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				}
				else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasTabuleiro.remove(pecaCapturada);
			}
		}
		
		return pecaCapturada;
	}
	
	private void desmoverPeca(Posicao origem, Posicao destino, Peca pecaCapturada) {
		
		PecaXadrez pecaRemovida = (PecaXadrez) tabuleiro.removerPeca(destino);
		pecaRemovida.diminuirMovimentos();
		tabuleiro.colocarPecaTabuleiro(pecaRemovida, origem);
		
		if(pecaCapturada != null) {
			tabuleiro.colocarPecaTabuleiro(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
			
		}
		
		
		// Movimento Especial Roque Pequeno
		
		if(pecaRemovida instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.colocarPecaTabuleiro(torre, origemTorre);
			torre.diminuirMovimentos();
		}
		
		// Movimento Especial Roque Grande
		
		if(pecaRemovida instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.colocarPecaTabuleiro(torre, origemTorre);
			torre.diminuirMovimentos();
		}
		
		//Movimento Especial En Passant
		
		if(pecaRemovida instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == jogadaEspecialEnPassant) {
				PecaXadrez peao = (PecaXadrez) tabuleiro.removerPeca(destino);
				Posicao posicaoPeao;
				if(pecaRemovida.getCor() == Cor.VERDE) {
					posicaoPeao = new Posicao(3, destino.getColuna());
				}
				else {
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				
				tabuleiro.colocarPecaTabuleiro(peao, posicaoPeao);
				
			}
		}
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if(jogador != ((PecaXadrez)tabuleiro.buscarPeca(posicao)).getCor()) {
			throw new ExcecaoXadrez("Este pe??a ?? do outro Jogador! Presta aten????o, Carai!! =)");
		}
		if(!tabuleiro.existePeca(posicao)) {
			throw new ExcecaoXadrez("N??o existe pe??a na posicao de origem! Verifique a posi????o corretamente");
		}
		if (!tabuleiro.buscarPeca(posicao).existeMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existe movimentos possiveis para a pe??a escolhida!");
		}
		
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.buscarPeca(origem).possibilidadeDeMovimento(destino)) {
			throw new ExcecaoXadrez("A pe??a escolhida n??o pode se mover para a posicao de destino");
		}
	}
	
	 public void proximaJogada() {
		 turno++;
		 jogador = (jogador == Cor.VERDE) ? Cor.VERMELHO : Cor.VERDE;
		 
		/* if(jogador == Cor.VERDE)
			jogador = Cor.VERMELHO;     // outro modo de fazer a condi????o.
		 else 
			 jogador = Cor.VERDE;
		*/
	 }
	 
	 private Cor oponente(Cor cor) {
		 
		 return (cor == Cor.VERDE) ? Cor.VERMELHO : Cor.VERDE;
	 }
	 
	 private PecaXadrez rei(Cor cor) {
		 
		 List<Peca> listaAuxiliar = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
		 for(Peca peca : listaAuxiliar) {
			 if(peca instanceof Rei) {
				 return (PecaXadrez) peca;
			 }
		 }
		 
		 throw new IllegalStateException("N??o Existe no " + cor + "Rei no Tabuleiro");
	 }
	 
	 private boolean testarXeque(Cor cor) {
		 
		 Posicao posicaoRei = rei(cor).getPosicaoXadrez().converterPosicaoMatriz();
		 List<Peca> pecasOponentes = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
		 
		 for(Peca peca : pecasOponentes) {
			 boolean[][] matrizAuxiliar = peca.possibilidadeDeMovimentos();
			 if(matrizAuxiliar[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				 return true;
			 }
		 }
		 
		 return false;
	 }
	 
	 private boolean testarXequeMate(Cor cor) {
		 if(!testarXeque(cor)) {
			 return false;
		 }
		  
		 List<Peca> listaAuxiliar = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
		 for (Peca peca : listaAuxiliar) {
			 boolean[][] matriz = peca.possibilidadeDeMovimentos();
			 
			 for(int i = 0; i < matriz.length; i++) {
				 for(int j = 0; j < matriz.length; j++) {
					 if(matriz[i][j]) {
						 Posicao origem = ((PecaXadrez)peca).getPosicaoXadrez().converterPosicaoMatriz();
						 Posicao destino = new Posicao(i, j);
						 Peca pecaCapturada = moverPeca(origem, destino);
						 boolean testeXeque = testarXeque(cor);
						 desmoverPeca(origem, destino, pecaCapturada);
						 
						 if(!testeXeque) {
							 return false;
						 }
					 }
				 }
			 }
		 }
		 
		 return true;
	 }
	
	
	private void colocarNovaPeca(int coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPecaTabuleiro(peca, new PosicaoXadrez(coluna, linha).converterPosicaoMatriz());
		pecasTabuleiro.add(peca);
	}
	
	private void iniciarPartida() {

		colocarNovaPeca(ValoresColunasMatriz.a.valorColuna, 8, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.b.valorColuna, 8, new Cavalo(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 8, new Bispo(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 8, new Rainha(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 8, new Rei(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.f.valorColuna, 8, new Bispo(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.g.valorColuna, 8, new Cavalo(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.h.valorColuna, 8, new Torre(tabuleiro, Cor.VERDE));
		colocarNovaPeca(ValoresColunasMatriz.a.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.b.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.f.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.g.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));
		colocarNovaPeca(ValoresColunasMatriz.h.valorColuna, 7, new Peao(tabuleiro, Cor.VERDE, this));

		
		colocarNovaPeca(ValoresColunasMatriz.a.valorColuna, 1, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.b.valorColuna, 1, new Cavalo(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 1, new Bispo(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 1, new Rainha(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 1, new Rei(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.f.valorColuna, 1, new Bispo(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.g.valorColuna, 1, new Cavalo(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.h.valorColuna, 1, new Torre(tabuleiro, Cor.VERMELHO));
		colocarNovaPeca(ValoresColunasMatriz.a.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.b.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.c.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.d.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.e.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.f.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.g.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));
		colocarNovaPeca(ValoresColunasMatriz.h.valorColuna, 2, new Peao(tabuleiro, Cor.VERMELHO, this));

	}
}
