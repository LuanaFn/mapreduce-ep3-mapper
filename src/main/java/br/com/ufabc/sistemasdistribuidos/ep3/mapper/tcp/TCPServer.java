package br.com.ufabc.sistemasdistribuidos.ep3.mapper.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.ufabc.sistemasdistribuidos.ep3.mapper.bo.MapperBO;

public class TCPServer {
	private ServerSocket server;
	private MapperBO mapperbo;

	public TCPServer(String ipAddress) throws Exception {
		mapperbo = new MapperBO();

		if (ipAddress != null && !ipAddress.isEmpty())
			this.server = new ServerSocket(8081, 1, InetAddress.getByName(ipAddress));
		else
			this.server = new ServerSocket(8081, 1, InetAddress.getLocalHost());
	}

	private void listen() throws Exception {
		String data = null;
		Socket client = this.server.accept();
		String clientAddress = client.getInetAddress().getHostAddress();
		System.out.println("\r\nNew connection from " + clientAddress);

		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		StringBuilder builder = new StringBuilder();
		while ((data = in.readLine()) != null) {
			System.out.println("\r\nMessage from " + clientAddress + ": " + data);

			builder.append(data);
		}
		mapperbo.recebeUrls(builder.toString());
	}

	public InetAddress getSocketAddress() {
		return this.server.getInetAddress();
	}

	public int getPort() {
		return this.server.getLocalPort();
	}

	public static void main(String[] args) throws Exception {
		System.out.println("MAPPER");
		TCPServer app;

//		if (args.length > 0)
			//ip 
			app = new TCPServer("ec2-34-230-52-12.compute-1.amazonaws.com");
//		else
//			app = new TCPServer(null);

		System.out.println(
				"\r\nRunning Server: " + "Host=" + app.getSocketAddress().getHostAddress() + " Port=" + app.getPort());

		while (true)
			app.listen();
	}
}
