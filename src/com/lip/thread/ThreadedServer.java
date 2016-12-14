package com.lip.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedServer extends Thread
	{

		private Socket socket = null;
		private BufferedReader br = null;
		private PrintWriter pw = null;
		private Scanner scanner = null;

		public ThreadedServer(Socket s)
			{
				socket = s;
				try
					{
						br = new BufferedReader(new InputStreamReader(
								socket.getInputStream()));
						pw = new PrintWriter(
								new BufferedWriter(new OutputStreamWriter(
										socket.getOutputStream())), true);
						scanner = new Scanner(System.in);// �Ӽ��̶�ȡ����
						start();
					}
				catch (Exception e)
					{

						e.printStackTrace();
					}
			}

		public void run()
			{
				new ReadClientMessage(br, socket);//��ȡ�ͻ�������
				while (true)//��ͻ��˷�������
					{
						try
							{
								pw.println(scanner.nextLine());
								pw.flush();
							}
						catch (Exception e)
							{
								try
									{
										br.close();
										pw.close();
										socket.close();
									}
								catch (IOException e1)
									{
										e1.printStackTrace();
									}
							}
					}
			}

	}

class ReadClientMessage extends Thread
	{
		BufferedReader bReader;
        Socket socket;
		public ReadClientMessage(BufferedReader br,Socket s)
			{
				this.bReader = br;
				this.socket=s;
				start();
			}

		public void run()
			{
				String str = "";
				while (true)
					{
						try
							{
								str = bReader.readLine();
								if (str.equals("q"))
									{
									  bReader.close();
									  socket.close();
									  break;
									}
							}
						catch (IOException e)
							{
								e.printStackTrace();
							}
						System.out.println("Client Message:" + str);
					}
			}
	}
