package utils;

/**
 * 
 * @author Carlito
 * 
 *         Classe responsável em realizar a criptografia e descritografia das
 *         senhas de usuarios do labclinic
 */

public class CryptoUtil {

	private static String KEY = "LabClinic200610240957";

	/**
	 * 
	 * @param source
	 * @return senhaCriptografada
	 * 
	 *         Método Recebe o valor e retorna o valor criptografado
	 */

	public static String crypt(String source) {
		String result = "";
		Integer sourceOrd;
		Integer keyNdx;
		Integer keyOrd;
		Integer atuOrd;
		String atuByte;

		if (!source.equals("") && KEY.length() > 0) {
			for (int i = 0; i < source.length(); i++) {
				sourceOrd = (int) source.charAt(i);
				keyNdx = i % KEY.length();
				keyOrd = (int) KEY.charAt(keyNdx);
				atuOrd = (256 + sourceOrd + keyOrd) % 256;
				atuByte = String.format("%02x", atuOrd);
				result += atuByte;
			}
		}

		return result.toUpperCase();
	}

	/**
	 * 
	 * @param source
	 * @return senhaDescriptografada
	 * 
	 * Método Recebe a senha criptografada e devolve descriptografada
	 */
	public static String decrypt(String source){
		String result = "";
		Integer keyNdx;
		Integer keyOrd ; 
		String hexa;
		int hexaOrd;
		
		if(!source.equals("") && KEY.length() > 0){
			for(int i = 0; i < source.length(); i+=2){
				keyNdx = (i/2) % KEY.length();
				keyOrd = (int) KEY.charAt(keyNdx);
				hexa =  source.substring(i, i+2);
				hexaOrd = (256 + Integer.parseInt(hexa, 16) - keyOrd) % 256;				
				result += (char) hexaOrd;
			}
		}
		
		return result;
	}
}
