package controller;

import java.util.Arrays;
import java.util.Scanner;

import model.Criptografia;

public class Controller {

	@SuppressWarnings("unused")
	private String msgCripto = "";
	private int[] chave = new int[5];
	Criptografia teste = new Criptografia();

	/**
	 * Apresenta as op��es dispon�veis e recebe como entrada 	
	 * a op��o desejada.
	 */
	public void inicio() {

		@SuppressWarnings("resource")
		Scanner ler = new Scanner(System.in);
		String opcao = "";
		boolean i = false;

		do {

			System.out.println("\n      ##Crpiptografia / Descriptografia##");
			System.out.println("\t|-----------------------------|");
			System.out.println("\t| Op��o 1 - Criptografar      |");
			System.out.println("\t| Op��o 2 - Descriptografar   |");
			System.out.println("\t| Op��o 3 - Sair	      |");
			System.out.println("\t|-----------------------------|");
			System.out.println(" Escolha uma op��o: ");

			opcao = ler.next();
			ler.nextLine(); // -->important
			System.out.println();
			switch (opcao) {
			case "1":
				System.out.println("Digite o texto que deseja criptografar:\n");
			
				String msg = ler.nextLine();
				msgCripto = msg;
				msg = criptografar(msg);
				System.out.println(msg);
				break;
			case "2":
				System.out.println("\nDigite o texto que deseja descriptografar:\n");
				msg = "";
				msg = ler.next();
				System.out.println("\nDigite a chave de 5 bytes: ");

				int j = 0;
				do {
					try {

						System.out.println(j + 1 + "�");
						int num = ler.nextInt();
						System.out.println("\n");
						if (num >= 0) {
							chave[j] = num;
							j++;

						} else {
							System.out.println("\nDigite um n�mero positivo!\n");
						}

					} catch (Exception e) {
						System.out.println("Digite apenas n�meros");
						ler.nextLine(); // -->important
						System.out.println();
					}
				} while (j < 5);
				msg = descriptografar(msg, chave);
				System.out.println(msg);
				break;

			case "3":
				i = true;
				System.out.println("\nPrograma finalizado!\n");
				break;

			default:
				System.out.println("\nOp��o in�lida! Tente novamente.\n");
				break;
			}
			
		} while (i != true);
		
	}

	/**
	 * Invoca o m�todo 'encrypt' da classe 'Criptografia',<br>
	 * onde est�o as regras de criptografia.
	 * @param word
	 * @return
	 */
	public String criptografar(String word) {

		Criptografia crypto = new Criptografia();

		String txtCriptografado = crypto.encrypt(word);
		String chave = Arrays.toString(crypto.getChaveCripto());

		return "\nTexto criptografado: " + txtCriptografado + "\n" + "Chave: " + chave + "\n";
	}

	/**
	 * Invoca o m�todo 'decrypt' da classe 'Criptografia'<br>
	 * onde est�o as regras de descriptografia.
	 * @param word
	 * @param chave
	 * @return
	 */
	public String descriptografar(String word, int[] chave) {
		Criptografia crypto = new Criptografia();

		String txtDescriptografado = crypto.decrypt(word, chave);

		return "Texto original: " + txtDescriptografado;
	}

}
