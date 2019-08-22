package br.com.ufabc.sistemasdistribuidos.ep3.mapper.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ufabc.sistemasdistribuidos.ep3.mapper.dto.DtoCoordenador;
import br.com.ufabc.sistemasdistribuidos.ep3.mapper.dto.DtoReducer;
import br.com.ufabc.sistemasdistribuidos.ep3.mapper.service.HTMLLinkExtractor;
import br.com.ufabc.sistemasdistribuidos.ep3.mapper.service.URLReader;
import br.com.ufabc.sistemasdistribuidos.ep3.mapper.tcp.TCPClient;

public class MapperBO {
	public MapperBO() {

	}

	public void recebeUrls(String lista) {

		// traduz o dto que o coordenador mandou
		DtoCoordenador dtoCoord = new DtoCoordenador();

		ObjectMapper mapper = new ObjectMapper();
		try {
			dtoCoord = mapper.readValue(lista, DtoCoordenador.class);
			lista = dtoCoord.getUrls();

		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String> urls = new ArrayList<String>(Arrays.asList(lista.split(",")));
		HashMap<String, List<String>> linksMap = new HashMap<String, List<String>>();

		for (int i = 0; i < urls.size(); i++) {
			try {
				HTMLLinkExtractor extractor = new HTMLLinkExtractor();
				List<String> links = extractor.grabHTMLLinks(URLReader.read(urls.get(i)));

				linksMap.put(urls.get(i), links);

				System.out.println("Conte�do processado da url " + urls.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// tenta repassar a informa��o pro reducer
		try {
			DtoReducer dtoReducer = new DtoReducer();
			dtoReducer.setHost(dtoCoord.getHost());
			dtoReducer.setPort(dtoCoord.getPort());
			dtoReducer.setLinksMap(linksMap);
			
			TCPClient client = new TCPClient("http://ec2-34-229-109-240.compute-1.amazonaws.com/", 8082);

			client.enviaMensagem(mapper.writeValueAsString(dtoReducer));
			client.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
