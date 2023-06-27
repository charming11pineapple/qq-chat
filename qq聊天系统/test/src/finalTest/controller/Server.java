package finalTest.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	public static void main(String[] args) {
        //����ͻ��˴�����߳�
        ArrayList<UserThread> vector = new ArrayList<UserThread> ();
        //�̶���С���̳߳أ���������ͬ�ͻ���
        ExecutorService es = Executors.newFixedThreadPool(10);
        //�����������˵�Socket
        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("�����������������ڵȴ�����...");
            while(true){
                //���ܿͻ��˵�Socket����û�У���������
                Socket socket = server.accept();
                //ÿ��һ���ͻ��ˣ�����һ���̴߳�����
                UserThread user = new UserThread(socket,vector);
                es.execute(user);  //�����߳�
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
