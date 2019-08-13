package br.com.ufabc.sistemasdistribuidos.ep3.mapper.dto;

import java.util.HashMap;
import java.util.List;

public class DtoReducer {
	String host;
	int port;
	HashMap<String, List<String>> linksMap;
	public DtoReducer(String host, int port, HashMap<String, List<String>> linksMap) {
		super();
		this.host = host;
		this.port = port;
		this.linksMap = linksMap;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public HashMap<String, List<String>> getLinksMap() {
		return linksMap;
	}
	public void setLinksMap(HashMap<String, List<String>> linksMap) {
		this.linksMap = linksMap;
	}
	public DtoReducer() {
		super();
		// TODO Auto-generated constructor stub
	}
}
