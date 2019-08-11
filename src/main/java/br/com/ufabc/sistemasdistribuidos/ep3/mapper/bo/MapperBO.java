package br.com.ufabc.sistemasdistribuidos.ep3.mapper.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperBO {
	public MapperBO() {
		
	}
	
	public void recebeUrls(String lista) {
		List<String> urls = new ArrayList<String>(Arrays.asList(lista.split(",")));
	}
}
