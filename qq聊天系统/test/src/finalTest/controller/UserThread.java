package finalTest.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import finalTest.model.ConOperate;
import finalTest.model.MySqlDAO;

class UserThread implements Runnable {
	private int fromId; // 客户端的id，唯一
	private int toId;//接收者id
	private Socket socket;
	private ArrayList<UserThread> vector; // 客户端处理线程的集合
	private ObjectInputStream oIn; // 输入流
	private ObjectOutputStream oOut; // 输出流
	private boolean flag = true; // 标记
	Connection con;// 该数据库的连接

	public UserThread(Socket socket, ArrayList<UserThread> vector) {
		this.socket = socket;
		this.vector = vector;
		vector.add(this); // 把当前线程也加入vector中
		try {
			con = MySqlDAO.getConnection("qq聊天系统");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void run() {
		try {
			// 1、构造输入输出流
			System.out.println("客户端：" + socket.getInetAddress().getHostAddress() + "已连接！");
//			oIn = new ObjectInputStream(socket.getInputStream());
			oOut = new ObjectOutputStream((socket.getOutputStream()));
			// 2、循环读取
			while (flag) {
				oIn = new ObjectInputStream(socket.getInputStream());
				// 读取消息对象
				Message message = (Message) oIn.readObject();
				// 获取消息类型，登录还是发送消息
				int type = message.getType();
				// 3、判断
				UserThread ut;
				switch (type) {
				case MessageType.TYPE_LOGIN:
					fromId = message.getFrom();
					toId = message.getTo();// 发送给谁
					break;
				// 如果是发送消息
				case MessageType.TYPE_SEND:
					ConOperate.saveMsg(con, message);//将该消息存入历史记录数据库				
					// 遍历vector，找到接收信息的客户端
					int size = vector.size();
					for (int i = 0; i < size; i++) {
						ut = vector.get(i);
						// 如果id相同，且不是自己，就把信息发给它
						if (toId == ut.fromId && fromId == ut.toId && ut != this) {						
							ut.oOut.writeObject(message); // 发送消息对象
						}
					}
					break;
				case MessageType.TYPE_DOC:
					// 遍历vector，找到接收信息的客户端
					int size1 = vector.size();
					for (int i = 0; i < size1; i++) {
						ut = vector.get(i);
						// 如果id相同，且不是自己，就把信息发给它
						if (toId == ut.fromId && fromId == ut.toId && ut != this) {						
							ut.oOut.writeObject(message); // 发送消息对象
						}
					}
					break;
				case MessageType.TYPE_PIC:
					// 遍历vector，找到接收信息的客户端
					int size2 = vector.size();
					for (int i = 0; i < size2; i++) {
						ut = vector.get(i);
						// 如果id相同，且不是自己，就把信息发给它
						if (toId == ut.fromId && fromId == ut.toId && ut != this) {						
							ut.oOut.writeObject(message); // 发送消息对象
						}
					}
					break;
				}

			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int getFromId() {
		return fromId;
	}
	
	public int getToId() {
		return toId;
	}

	public ObjectOutputStream getoOut() {
		return oOut;
	}
}
