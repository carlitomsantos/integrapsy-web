package testes;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;

public class CriptoTest {
	
	int ordUTF8(char a){
		int result = (int) a;
		
		if(result > 0x7f){
			int primeiroByte = result;
			
		}
		
		
		return result;
	}
	
	String cryotV2(String source, String key, Boolean encode){
		
		Integer keyTam = key.length();
		Integer ndx = 1;
		Integer keyNdx;
		short keyOrd;
		Integer sourceOrd;
		Integer atuOrd;
		String atuByte;
		String result = "";
		
		if(!source.equals("") && !key.equals("")){
			while(source.charAt(ndx) != ' '){
				if(encode){
					sourceOrd = (int) source.charAt(ndx);
					keyNdx = (ndx % keyTam) + 1;
				}else{
				
				}
			}
		}
		
		
		return "";
	}
	
	@Test
	public void testCripto(){
		
		Integer test = 1;
		
		System.out.println((int) 'A');
		
		
		
		
		
		
		
	}
}
