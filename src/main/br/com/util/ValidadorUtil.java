package br.com.util;

public class ValidadorUtil {

	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public static boolean isValidCNPJ(String cnpj) {
		if (cnpj == null) {
			return false;
		}
		if (cnpj.length() == 18) {
			cnpj = cnpj.replace(".", "");
			cnpj = cnpj.replace("/", "");
			cnpj = cnpj.replace("-", "");
		}

		if (cnpj.length() != 14) {
			return false;
		}

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	public static boolean isValidCPF(String cpf) {
		if (cpf == null) {
			return false;
		}
		if (cpf.length() == 14) {
			cpf = cpf.replace(".", "");
			cpf = cpf.replace("-", "");
		}

		if (cpf.length() != 11) {
			return false;
		}

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	public static void main(String[] args) {
		System.out.printf("CPF Valido:%s \n", ValidadorUtil.isValidCPF("998.526.161-53"));
		System.out.printf("CNPJ Valido:%s \n", ValidadorUtil.isValidCNPJ("70.185.247/0001-05"));
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
