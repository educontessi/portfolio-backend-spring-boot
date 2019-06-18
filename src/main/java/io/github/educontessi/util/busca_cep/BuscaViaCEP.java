package io.github.educontessi.util.busca_cep;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class BuscaViaCEP {

	private String uri = "http://viacep.com.br/ws/#CEP#/json/ ";

	public ObjetoViaCEP buscaCEP(String cep) {
		ObjetoViaCEP retorno = null;
		cep = cep.replace("-", "");
		try {
			URL url = new URL(this.uri.replace("#CEP#", cep));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "applicaiton/json");

			connection.setConnectTimeout(5000);
			connection.setDoInput(true);

			if (connection.getResponseCode() == 200) {
				InputStream inputStream = null;
				inputStream = (InputStream) connection.getContent();

				InputStreamReader in = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader buff = new BufferedReader(in);

				StringBuilder content = new StringBuilder("");
				String line;
				while ((line = buff.readLine()) != null) {
					content.append(line);
				}

				retorno = new Gson().fromJson(content.toString(), ObjetoViaCEP.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (retorno == null) {

		}

		return retorno;
	}

}
