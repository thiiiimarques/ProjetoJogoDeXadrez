package aplicacao;


import xadrez.PartidaXadrez;

public class Execucao {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		InterfacePartidaXadrez.imprimirTabuleiro(partidaXadrez.getPecas());
		

	}

}
