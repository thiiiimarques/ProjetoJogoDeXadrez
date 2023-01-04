package tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}


	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
	public abstract boolean[][] possibilidadeDeMovimentos();
		
		
	public boolean possibilidadeDeMovimento(Posicao posicao) {
		
		 return possibilidadeDeMovimentos()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeMovimentoPossivel() {
		
		boolean[][] matriz = possibilidadeDeMovimentos();
		for(int i = 0; i<matriz.length; i++) {
			for(int j = 0; j<matriz.length; j++) {
				if(matriz[i][j] == false) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
}
