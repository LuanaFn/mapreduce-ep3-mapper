package br.com.ufabc.sistemasdistribuidos.ep3.mapper.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader {
	public static String read(String url) throws Exception {

        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String data;
        StringBuilder builder = new StringBuilder();
        
        while ((data = in.readLine()) != null)
            builder.append(data);
        in.close();
        
        return builder.toString();
    }
}
