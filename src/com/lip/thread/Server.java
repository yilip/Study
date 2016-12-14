package com.lip.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
	{

		/**
		 * Author:Lip Desc:SSD8_exercise1
		 */
		public static int PORT = 22;
		public static String HOST = "127.0.0.1";

		public static void main(String[] args)
			{
				ServerSocket serverSocket = null;
				Socket socket = null;
				try
					{
						serverSocket = new ServerSocket(PORT);
						// �ȴ�����,������һֱ�ȴ�
						while (true)
							{
								System.out.println("Waiting Client");
								socket = serverSocket.accept();// ��������
								System.out.println("Client Conect!");
								new ThreadedServer(socket);
							}
					}
				catch (Exception e)
					{
						try
							{
								socket.close();
							}
						catch (IOException e1)
							{
								e1.printStackTrace();
							}
					}
				finally
					{
                       try
						{
							serverSocket.close();
						}
					catch (IOException e)
						{
							e.printStackTrace();
						}
					}

			}

	}
