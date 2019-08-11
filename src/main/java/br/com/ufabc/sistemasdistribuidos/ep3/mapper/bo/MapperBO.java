package br.com.ufabc.sistemasdistribuidos.ep3.mapper.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ufabc.sistemasdistribuidos.ep3.mapper.service.URLReader;

public class MapperBO {
	public MapperBO() {
		
	}
	
	public void recebeUrls(String lista) {
		List<String> urls = new ArrayList<String>(Arrays.asList(lista.split(",")));
		List<String> conteudos = new ArrayList<String>();
		
		for(int i=0; i< urls.size(); i++) {
			try {
				conteudos.add(URLReader.read(urls.get(i)));
				
				System.out.println("Conteúdo recebido: ");
				System.out.println(conteudos.get(conteudos.size()-1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
