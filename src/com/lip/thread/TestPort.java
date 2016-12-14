package com.lip.thread;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPort {
	public static String host = "123.56.232.253";

	public static void main(String[] args) {
		ExecutorService multiJob = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 10000; i++) {
			multiJob.submit(new PortRunable(host, i));
		}
		multiJob.shutdown();
	}

	public static void isPortOn(String host, int i) {
		try {
			Socket socket = new Socket(host, i);
		} catch (UnknownHostException e) {
			System.out.println(String.format("������������...{%s}", i, e.getMessage()));
		} catch (IOException e) {
			System.out.println(String.format("�˿�%sδ��...{%s}", i, e.getMessage()));
		}
	}

}

class PortRunable implements Runnable {
	String host;
	int port;

	public PortRunable(String _host, int _port) {
		this.host = _host;
		this.port = _port;
	}

	@Override
	public void run() {
		try {
			Socket socket = new Socket(host, port);
			System.out.println(String.format("***************�˿�%s��***************", port));
		} catch (UnknownHostException e) {
			System.out.println(String.format("������������...{%s}", port, e.getMessage()));
		} catch (IOException e) {
			System.out.println(String.format("�˿�%sδ��...{%s}", port, e.getMessage()));
		}
	}
}
