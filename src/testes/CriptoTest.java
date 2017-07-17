package testes;


import java.nio.charset.StandardCharsets;

import org.junit.Test;


public class CriptoTest {
	
	int ord(String s){
		return s.length() > 0 ? (s.getBytes(StandardCharsets.UTF_8)[0] & 0xff) : 0;
	}
	
	int ordChar(char c){
		return c < 0x80 ? c: ord(Character.toString(c));
	}
	
	String decrypto(String source, String key){
		String result = "";
		Integer keyNdx;
		Integer keyOrd ; 
		String hexa;
		int hexaOrd;
		
		if(!source.equals("") && key.length() > 0){
			for(int i = 0; i < source.length(); i+=2){
				keyNdx = (i/2) % key.length();
				keyOrd = (int) key.charAt(keyNdx);
				hexa =  source.substring(i, i+2);
				hexaOrd = (256 + Integer.parseInt(hexa, 16) - keyOrd) % 256;				
				result += (char) hexaOrd;
			}
		}
		
		return result;
	}
	
	
	String crypt(String source, String key){
		String result = "";
		Integer sourceOrd;
		Integer keyNdx;
		Integer keyOrd ; 
		Integer atuOrd;
		String atuByte;
		
		if(!source.equals("") && key.length() > 0){
			for(int i = 0; i < source.length(); i++){
				sourceOrd = (int) source.charAt(i);
				keyNdx = i % key.length();
				keyOrd = (int) key.charAt(keyNdx);
				atuOrd = (256 + sourceOrd + keyOrd) % 256;
				atuByte  = String.format("%02x",atuOrd );
				result += atuByte;
			}
		}
		
		
		return result;
	}
	
	
	@Test
	public void testCripto(){
		
		Integer test = 1;
		String result = "";
		String cryptoKey = "LabClinic200610240957";
		
		result = decrypto("BFD6D2A8DE", cryptoKey);
		
		String teste = String.format("%08x",1234 );
		

		
		
		System.out.println(result.toUpperCase());
		
		
		
		
		
		
		
		
		
	}
}
