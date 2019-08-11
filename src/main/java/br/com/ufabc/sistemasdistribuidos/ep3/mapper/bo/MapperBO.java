package br.com.ufabc.sistemasdistribuidos.ep3.mapper.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ufabc.sistemasdistribuidos.ep3.mapper.service.HTMLLinkExtractor;
import br.com.ufabc.sistemasdistribuidos.ep3.mapper.service.URLReader;

public class MapperBO {
	public MapperBO() {
		
	}
	
	public void recebeUrls(String lista) {
		List<String> urls = new ArrayList<String>(Arrays.asList(lista.split(",")));
		HashMap<String, List<String>> linksMap = new HashMap<String, List<String>>();
		
		for(int i=0; i< urls.size(); i++) {
			try {
				HTMLLinkExtractor extractor = new HTMLLinkExtractor();
				List<String> links = extractor.grabHTMLLinks(urls.get(i));
				
				linksMap.put(urls.get(i), links);
				
				System.out.println("Conteúdo processado da url "+urls.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
