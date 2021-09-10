package model;

import java.util.Random;

/**
 * 
 * @author David
 *
 */
public class Criptografia {

	private String msgCripto = "";
	private int[] chaveCripto = new int[5];
	private String[] letras = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	@SuppressWarnings("unused")
	private String chaveTexto = " ";

	int colum = 20;
	int rows = 3;
	private int[][] matrizCripto;

	/**
	 * Recebe uma String por parâmetro e a retorna criptografada.
	 * 
	 * @param msg
	 * @return
	 */
	public String encrypt(String msg) {

		matrizCripto = new int[4][msg.length()];
		int cont = 0;
		msgCripto = msg;
		boolean encontrou = false;

		String[] mensagem = msg.split("");

		for (int i = 0; i <= 26 && cont <= (mensagem.length - 1); i++) {
			if (letras[i].compareTo(mensagem[cont].toUpperCase()) == 0) {

				matrizCripto[0][cont] = i;
				encontrou = true;
			}
			if (encontrou != false) {
				cont++;
				encontrou = false;
				i = -1;
			}
		}

		int[] chave = gerarChave();
		cont = 0;

		for (int i = 0; cont <= (mensagem.length - 1); i++) {

			if (i < 5) {
				matrizCripto[1][cont] = chave[i];
			} else {
				i = -1;
				cont--;
			}
			cont++;
		}

		msg = criptografar();
		return msg;
	}

	/**
	 * Responsável por gerar a chave para a descritografia, 
	 * retorna um array contendo 5 posições preenchidas, a geração
	 * se dá de forma aleatória.
	 * @return
	 */
	private int[] gerarChave() {

		int[] chave = new int[5];
		Random gerador = new Random();

		for (int i = 0; i < chave.length; i++) {
			int num = gerador.nextInt(27);

			for (int j = 0; j < chave.length; j++) {
				if (num == chave[i]) {
					num = gerador.nextInt(27);
				}
			}
			chave[i] = num;
			chaveCripto[i] = chave[i];
		}

		return chave;
	}

	/**
	 * Método privado que realiza a criptografia, é invocado no
	 * método público 'encrypt' desta classe.
	 * @return
	 */
	private String criptografar() {

		@SuppressWarnings("unused")
		String soma = "";
		int result = 0;
		for (int i = 0; i < msgCripto.length(); i++) {
			if (matrizCripto[0][i] == 26) {
				result = matrizCripto[1][i];
			} else {
				result = matrizCripto[0][i] + matrizCripto[1][i];
				if (result > 26) {
					result = result - 26;
				}
			}

			matrizCripto[2][i] = result;
			soma += matrizCripto[2][i] + ", ";
		}
		String textoCriptografado = "";
		for (int i = 0; i <= msgCripto.length() - 1; i++) {
			textoCriptografado += letras[matrizCripto[2][i]];
		}
		return textoCriptografado;
	}

	/**
	 * Realiza a descriptografia do texto recebido no parâmetro,<br>
	 * utiliza a chave recebida por parâmetro para isso.
	 * @param textoCriptografado
	 * @param chave
	 * @return
	 */
	public String decrypt(String textoCriptografado, int[] chave) {

		String[] text = textoCriptografado.split("");
		int[][] descrypt = new int[3][text.length];
		int cont = 0;
		boolean encontrou = false;

		for (int i = 0; i <= 26 && cont <= text.length - 1; i++) {

			if (letras[i].compareTo(text[cont]) == 0) {
				descrypt[0][cont] = i;
				encontrou = true;
			}

			if (encontrou != false) {
				cont++;

				encontrou = false;
				i = -1;
			}
		}

		cont = 0;
		for (int i = 0; cont <= (textoCriptografado.length() - 1); i++) {

			if (i < 5) {
				descrypt[1][cont] = chave[i];
			} else {
				i = -1;
				cont--;
			}
			cont++;
		}

		for (int i = 0; i < textoCriptografado.length(); i++) {

			int result = descrypt[0][i] + 26;
			if (result > 26) {
				result = (result - descrypt[1][i]) - 26;
				descrypt[2][i] = result;
			} else {
				descrypt[2][i] = descrypt[0][i] - descrypt[1][i];
			}
		}

		String original = "";
		for (int i = 0; i < textoCriptografado.length(); i++) {
			int posi = descrypt[2][i];
			if (posi < 0) {

				posi = 26 + posi;
			}

			original += letras[posi];
		}
		return original;
	}

	/**
	 * Retorna o texto criptografado
	 * @return
	 */
	public String getMsgCripto() {
		return msgCripto;
	}

	/**
	 * @param msgCripto
	 */
	public void setMsgCripto(String msgCripto) {
		this.msgCripto = msgCripto;
	}

	/**
	 * Retorna a chave gerada para a descriptografia.
	 * @return
	 */
	public int[] getChaveCripto() {
		return (int[]) chaveCripto;
	}

	/**
	 * @param chaveCripto
	 */
	public void setChaveCripto(int[] chaveCripto) {
		this.chaveCripto = (int[]) chaveCripto;
	}

}
