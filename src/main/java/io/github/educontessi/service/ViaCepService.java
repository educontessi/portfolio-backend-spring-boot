package io.github.educontessi.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;

import io.github.educontessi.model.Bairro;
import io.github.educontessi.model.Cidade;
import io.github.educontessi.model.Estado;
import io.github.educontessi.model.Rua;
import io.github.educontessi.model.ViaCepJson;
import io.github.educontessi.model.ViaCepResposta;
import io.github.educontessi.repository.BairroRepository;
import io.github.educontessi.repository.CidadeRepository;
import io.github.educontessi.repository.EstadoRepository;
import io.github.educontessi.repository.RuaRepository;

@Service
public class ViaCepService {

	private final String URI = "http://viacep.com.br/ws/#CEP#/json/";

	@Autowired
	private RuaRepository ruaRepository;

	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public ViaCepResposta buscaEnderecoPorCep(String cep) {
		ViaCepJson viaCepJson = requisicaoViaCep(cep);
		ViaCepResposta resposta = null;
		if (viaCepJson != null && viaCepJson.isValid()) {
			resposta = new ViaCepResposta();
			resposta.setCep(viaCepJson.getCep());
			resposta.setEstado(getEstado(viaCepJson));
			resposta.setCidade(getCidade(viaCepJson, resposta.getEstado()));
			resposta.setBairro(getBairro(viaCepJson, resposta.getCidade()));
			resposta.setRua(getRua(viaCepJson, resposta.getCidade()));
		}
		return resposta;
	}

	protected ViaCepJson requisicaoViaCep(String cep) {
		ViaCepJson viaCepJson = null;
		cep = cep.replace("-", "");
		try {
			URL url = new URL(this.URI.replace("#CEP#", cep));
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
				viaCepJson = new Gson().fromJson(content.toString(), ViaCepJson.class);
			}
		} catch (Exception e) {
		}

		return viaCepJson;
	}

	private Estado getEstado(ViaCepJson viaCEP) {
		Optional<Estado> optionalEstado = estadoRepository.findByUf(viaCEP.getUf());
		return optionalEstado.isPresent() ? optionalEstado.get() : null;
	}

	private Cidade getCidade(ViaCepJson viaCEP, Estado estado) {
		Optional<Cidade> optionalCidade = cidadeRepository.findByIbge(Integer.parseInt(viaCEP.getIbge()));
		return optionalCidade.isPresent() ? optionalCidade.get() : incluirCidade(viaCEP, estado);
	}

	private Cidade incluirCidade(ViaCepJson viaCEP, Estado estado) {
		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setIbge(Integer.parseInt(viaCEP.getIbge()));
		cidade.setNome(viaCEP.getLocalidade());
		cidadeRepository.save(cidade);
		return cidade;
	}

	private Bairro getBairro(ViaCepJson viaCEP, Cidade cidade) {
		if (!StringUtils.isEmpty(viaCEP.getBairro())) {
			Optional<Bairro> optionalBairro = bairroRepository.findByNomeAndCidadeId(viaCEP.getBairro(),
					cidade.getId());
			return optionalBairro.isPresent() ? optionalBairro.get() : incluirBairro(viaCEP, cidade);
		}
		return null;
	}

	private Bairro incluirBairro(ViaCepJson viaCEP, Cidade cidade) {
		Bairro bairro = new Bairro();
		bairro.setCidade(cidade);
		bairro.setNome(viaCEP.getBairro());
		bairroRepository.save(bairro);
		return bairro;
	}

	private Rua getRua(ViaCepJson viaCEP, Cidade cidade) {
		if (!StringUtils.isEmpty(viaCEP.getBairro())) {
			Optional<Rua> optionalRua = ruaRepository.findByNomeAndCidadeId(viaCEP.getBairro(), cidade.getId());
			return optionalRua.isPresent() ? optionalRua.get() : incluirRua(viaCEP, cidade);
		}
		return null;
	}

	private Rua incluirRua(ViaCepJson viaCEP, Cidade cidade) {
		Rua rua = new Rua();
		rua.setCidade(cidade);
		rua.setNome(viaCEP.getLogradouro());
		ruaRepository.save(rua);
		return rua;
	}
}
