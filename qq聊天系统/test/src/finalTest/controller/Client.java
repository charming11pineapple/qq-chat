package finalTest.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.temporal.JulianFields;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import finalTest.view.ChatBox;
import finalTest.view.Login;

public class Client extends Login {
	ChatBox chatBox;
	private int myId;//�����ߵ�id
	private int toId;//�����ߵ�id
	Socket socket;
	Client client;
	
	public Client(ChatBox chatBox, int myId, int toId) {
		this.chatBox = chatBox;
		this.myId = myId;
		this.toId = toId;
		try {
			// �����ͻ���
			socket = new Socket("127.0.0.1", 8888);
			System.out.println("���������ӳɹ���");
			}catch (Exception e) {
			e.printStackTrace();		
		}
		getMsg();
	}

    public Client() {

    }

    //������Ϣ
	public void sendMsg(String msg, int myqq, int friendqq) {
		try {
			// ���������
			ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
			Message message = new Message();
			message.setTo(friendqq);
			// ˭���ģ����Լ��ⷢ
			message.setFrom(myqq);
			// ���� ������Ϣ
			message.setType(MessageType.TYPE_SEND);
			// ���͵�����
			message.setInfo(msg);
			/*----������Ҫ���͵���Ϣ ���� ��װ���----*/
			// ���͸�������
			oOut.writeObject(message);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	//�����ļ�
		public void sendMsg(File file, int myqq, int friendqq) {
			try {
				// ���������
				ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
				Message message = new Message();
				message.setTo(friendqq);
				// ˭���ģ����Լ��ⷢ
				message.setFrom(myqq);
				// ���� ������Ϣ
				message.setType(MessageType.TYPE_DOC);
				// ���͵�����
				message.setFile(file);
				/*----������Ҫ���͵���Ϣ ���� ��װ���----*/
				// ���͸�������
				oOut.writeObject(message);
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		
	public void sendMsg(File file, int myqq, int friendqq, int type) {
		try {
			// ���������
			ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
			Message message = new Message();
			message.setTo(friendqq);
			// ˭���ģ����Լ��ⷢ
			message.setFrom(myqq);
			// ���� ������Ϣ
			message.setType(type);
			// ���͵�����
			message.setFile(file);
			/*----������Ҫ���͵���Ϣ ���� ��װ���----*/
			// ���͸�������
			oOut.writeObject(message);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public void sendMsg(int myqq, int friendqq) {
		try {
			// ���������
			ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
			Message message = new Message();
			message.setTo(friendqq);
			// ˭���ģ����Լ��ⷢ
			message.setFrom(myqq);
			// ���� ������Ϣ
			message.setType(MessageType.TYPE_LOGIN);
			/*----������Ҫ���͵���Ϣ ���� ��װ���----*/
			// ���͸�������
			oOut.writeObject(message);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	//������Ϣ
	public void getMsg() {
		ExecutorService es = Executors.newSingleThreadExecutor();// ���̳߳�
		try {
			//������
			ObjectInputStream oIn = new ObjectInputStream(socket.getInputStream());
			// 2��������ȡ��Ϣ���߳�
			es.execute(new readInfoThread(oIn, chatBox)); // ��ȡ�߳����
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}	
	
}
