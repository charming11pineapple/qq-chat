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
	private int myId;//发送者的id
	private int toId;//接收者的id
	Socket socket;
	Client client;
	
	public Client(ChatBox chatBox, int myId, int toId) {
		this.chatBox = chatBox;
		this.myId = myId;
		this.toId = toId;
		try {
			// 创建客户端
			socket = new Socket("127.0.0.1", 8888);
			System.out.println("服务器连接成功！");
			}catch (Exception e) {
			e.printStackTrace();		
		}
		getMsg();
	}

    public Client() {

    }

    //发送消息
	public void sendMsg(String msg, int myqq, int friendqq) {
		try {
			// 构建输出流
			ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
			Message message = new Message();
			message.setTo(friendqq);
			// 谁发的，从自己这发
			message.setFrom(myqq);
			// 类型 发送消息
			message.setType(MessageType.TYPE_SEND);
			// 发送的内容
			message.setInfo(msg);
			/*----到此需要发送的消息 对象 封装完毕----*/
			// 发送给服务器
			oOut.writeObject(message);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	//发送文件
		public void sendMsg(File file, int myqq, int friendqq) {
			try {
				// 构建输出流
				ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
				Message message = new Message();
				message.setTo(friendqq);
				// 谁发的，从自己这发
				message.setFrom(myqq);
				// 类型 发送消息
				message.setType(MessageType.TYPE_DOC);
				// 发送的内容
				message.setFile(file);
				/*----到此需要发送的消息 对象 封装完毕----*/
				// 发送给服务器
				oOut.writeObject(message);
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		
	public void sendMsg(File file, int myqq, int friendqq, int type) {
		try {
			// 构建输出流
			ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
			Message message = new Message();
			message.setTo(friendqq);
			// 谁发的，从自己这发
			message.setFrom(myqq);
			// 类型 发送消息
			message.setType(type);
			// 发送的内容
			message.setFile(file);
			/*----到此需要发送的消息 对象 封装完毕----*/
			// 发送给服务器
			oOut.writeObject(message);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public void sendMsg(int myqq, int friendqq) {
		try {
			// 构建输出流
			ObjectOutputStream oOut = new ObjectOutputStream(socket.getOutputStream());
			Message message = new Message();
			message.setTo(friendqq);
			// 谁发的，从自己这发
			message.setFrom(myqq);
			// 类型 发送消息
			message.setType(MessageType.TYPE_LOGIN);
			/*----到此需要发送的消息 对象 封装完毕----*/
			// 发送给服务器
			oOut.writeObject(message);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	//接收消息
	public void getMsg() {
		ExecutorService es = Executors.newSingleThreadExecutor();// 单线程池
		try {
			//输入流
			ObjectInputStream oIn = new ObjectInputStream(socket.getInputStream());
			// 2、启动读取消息的线程
			es.execute(new readInfoThread(oIn, chatBox)); // 读取线程完成
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
