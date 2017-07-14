package testes;


import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TesteRest {

	public void test() {
		Client client = Client.create();
		JSONObject solicitacao = new JSONObject();

		//WebResource webResource = client.resource("http://devmedianotesapi.azurewebsites.net/api/");
		// System.out.println(webResource.path("Notes").get(String.class));
		// JSONObject json = new
		// JSONObject((webResource.path("Notes").get(String.class)));
		
		solicitacao.put("nome", "Paulo Fernandes");
		solicitacao.put("tipo_exame", "CNH-R");
		solicitacao.put("cpf", "12345678909");
		solicitacao.put("data_nascimento", "2017-08-07");
		solicitacao.put("email", "paulo.fernandes@psychemedics.com.br");
		solicitacao.put("celular", "11997975974");
		solicitacao.put("vencimento_cnh", "2018-01-30");
		solicitacao.put("numero_cnh", "1234567890");
		solicitacao.put("numero_declaracao", "CNJ12345678");
		solicitacao.put("forma_pagamento", "B");
		
		JSONObject notaFiscal = new JSONObject();
		notaFiscal.put("cpf", "12345678909");
		notaFiscal.put("nome", "Paulo Fernandes");
		notaFiscal.put("email", "paulo.fernandes@psychemedics.com.br");
		notaFiscal.put("cep", "05022-001");
		notaFiscal.put("logradouro", "Av Pompeia");
		notaFiscal.put("numero", "1380");
		notaFiscal.put("complemento", "Apto 32");
		notaFiscal.put("bairro", "Vila Pompeia");
		notaFiscal.put("cidade", "São Paulo");
		notaFiscal.put("uf", "SP");
		
		solicitacao.put("nota_fiscal", notaFiscal);
		

		WebResource resource = client.resource("https://api.exametoxicologico.com.br/ws/v1/create");
		ClientResponse response =  resource.header("Psychemedics-Code", "psy-teste")
				.header("Psychemedics-Pass", "6c5c10956c2bbe505bb8c42201efe5ef")
				.accept("application/json")
				.type("application/json")
				.post(ClientResponse.class, solicitacao.toString());
		
		
		
		String saida = response.getEntity(String.class);
		JSONObject saidaJson = new JSONObject(saida);
		System.out.println(saidaJson.toString());
		
		
		//String stringJson = webResource.path("Notes").get(String.class);

		/*try {
			json = new JSONArray(stringJson);

			System.out.println(json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
