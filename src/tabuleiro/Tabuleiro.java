package tabuleiro;

public class Tabuleiro {

	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new ExcecaoTabuleiro("Erro na criação do tabuleiro! Você precisa de numeros positivos para se criar um Tabuleiro de jogo");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
		
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca buscarPeca(int linha, int coluna) {
		if (!existePosicao(linha, coluna)) {
			throw new ExcecaoTabuleiro("Essa posição não existe dentro deste tabuleiro!");
		}
		return pecas[linha][coluna];
	}
	
	public Peca buscarPeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new ExcecaoTabuleiro("Essa posição não existe dentro deste tabuleiro!");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPecaTabuleiro(Peca peca, Posicao posicao) {
		if(existePeca(posicao)) {
			throw new ExcecaoTabuleiro("Oxe, já existe uma peça nesta posição!! A peça está na posição: " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) { 
		if(!existePosicao(posicao)) {
			throw new ExcecaoTabuleiro("Essa posição não existe dentro deste tabuleiro!");
		}
		
		if (buscarPeca(posicao) == null) {
			return null;
		}
		
		Peca auxiliar = buscarPeca(posicao);
		auxiliar.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		
		return auxiliar;
	}
	
	private boolean existePosicao(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean existePosicao(Posicao posicao) {
		return existePosicao(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean existePeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new ExcecaoTabuleiro("Essa posição não existe dentro deste tabuleiro!");
		}
		return buscarPeca(posicao) != null;
	}
	
}
