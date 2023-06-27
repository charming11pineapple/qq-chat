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
	private int fromId; // �ͻ��˵�id��Ψһ
	private int toId;//������id
	private Socket socket;
	private ArrayList<UserThread> vector; // �ͻ��˴����̵߳ļ���
	private ObjectInputStream oIn; // ������
	private ObjectOutputStream oOut; // �����
	private boolean flag = true; // ���
	Connection con;// �����ݿ������

	public UserThread(Socket socket, ArrayList<UserThread> vector) {
		this.socket = socket;
		this.vector = vector;
		vector.add(this); // �ѵ�ǰ�߳�Ҳ����vector��
		try {
			con = MySqlDAO.getConnection("qq����ϵͳ");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void run() {
		try {
			// 1���������������
			System.out.println("�ͻ��ˣ�" + socket.getInetAddress().getHostAddress() + "�����ӣ�");
//			oIn = new ObjectInputStream(socket.getInputStream());
			oOut = new ObjectOutputStream((socket.getOutputStream()));
			// 2��ѭ����ȡ
			while (flag) {
				oIn = new ObjectInputStream(socket.getInputStream());
				// ��ȡ��Ϣ����
				Message message = (Message) oIn.readObject();
				// ��ȡ��Ϣ���ͣ���¼���Ƿ�����Ϣ
				int type = message.getType();
				// 3���ж�
				UserThread ut;
				switch (type) {
				case MessageType.TYPE_LOGIN:
					fromId = message.getFrom();
					toId = message.getTo();// ���͸�˭
					break;
				// ����Ƿ�����Ϣ
				case MessageType.TYPE_SEND:
					ConOperate.saveMsg(con, message);//������Ϣ������ʷ��¼���ݿ�				
					// ����vector���ҵ�������Ϣ�Ŀͻ���
					int size = vector.size();
					for (int i = 0; i < size; i++) {
						ut = vector.get(i);
						// ���id��ͬ���Ҳ����Լ����Ͱ���Ϣ������
						if (toId == ut.fromId && fromId == ut.toId && ut != this) {						
							ut.oOut.writeObject(message); // ������Ϣ����
						}
					}
					break;
				case MessageType.TYPE_DOC:
					// ����vector���ҵ�������Ϣ�Ŀͻ���
					int size1 = vector.size();
					for (int i = 0; i < size1; i++) {
						ut = vector.get(i);
						// ���id��ͬ���Ҳ����Լ����Ͱ���Ϣ������
						if (toId == ut.fromId && fromId == ut.toId && ut != this) {						
							ut.oOut.writeObject(message); // ������Ϣ����
						}
					}
					break;
				case MessageType.TYPE_PIC:
					// ����vector���ҵ�������Ϣ�Ŀͻ���
					int size2 = vector.size();
					for (int i = 0; i < size2; i++) {
						ut = vector.get(i);
						// ���id��ͬ���Ҳ����Լ����Ͱ���Ϣ������
						if (toId == ut.fromId && fromId == ut.toId && ut != this) {						
							ut.oOut.writeObject(message); // ������Ϣ����
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
