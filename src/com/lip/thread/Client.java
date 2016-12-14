package com.lip.thread;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
	{

		/**
		 * Author: Lip 
		 * �ͻ���
		 */
		public static void main(String[] args)
			{
				Socket socket = null;
				BufferedReader br = null;
				PrintWriter pw = null;
				Scanner scanner = new Scanner(System.in);// �Ӽ��̶�ȡ
				try
					{
						// �����ͻ���socket
						String host="123.56.232.253";
						socket = new Socket(host, 22);
						// ��ȡ�ӿͻ��˷�������Ϣ
						br = new BufferedReader(new InputStreamReader(
								socket.getInputStream()));
						// д����Ϣ����������
						pw = new PrintWriter(
								new BufferedWriter(new OutputStreamWriter(
										socket.getOutputStream())));
						new ReadServerMessage(br);// �ӷ�������ȡ��Ϣ
						while (true)
							{
								String temp = scanner.nextLine();// �Ӽ��̶�ȡһ��
								pw.println(temp);// д��������
								pw.flush();
								if (temp.equals("q"))
									break;
							}
					}
				catch (Exception e)
					{
						e.printStackTrace();
					}
				finally
					{
						try
							{
								System.out.println("close......");
								br.close();
								pw.close();
								socket.close();
							}
						catch (IOException e)
							{
								e.printStackTrace();
							}
					}

			}

	}

class ReadServerMessage extends Thread//�ӷ�������ȡ��Ϣ
{
	BufferedReader bReader;
	public ReadServerMessage(BufferedReader br)
		{
			this.bReader = br;
			start();
		}

	public void run()
		{
			String str = "";
			while (true)//һֱ�ȴ��ŷ���������Ϣ
				{
					try
						{
							str = bReader.readLine();
							if(str==null)
							{
								System.out.println("δ�����Ϸ�����...");
								 bReader.close();
								break;
							}
							if (str.equals("q"))
								{
								  bReader.close();
								  break;
								}
						}
					catch (Exception e)
						{
							System.out.println("���ӷ������쳣:"+e.getMessage());
						}
					System.out.println("Server Message:" + str);
				}
		}
}
