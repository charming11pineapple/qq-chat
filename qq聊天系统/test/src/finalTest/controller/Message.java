package finalTest.controller;

import java.io.File;
import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 4895740302613218821L;
    private int from; //������id
    private int to;   //������id
    private int type; //��Ϣ����
    private String info;//��Ϣ��
    private File file;
 
    public Message() {
    }
 
    public Message(int from, int to, int type, String info) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.info = info;
    }
    
    public Message(int from, int to, int type, File file) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.file = file;
    }
 
    public int getFrom() {
        return from;
    }
 
    public void setFrom(int from) {
        this.from = from;
    }
 
    public int getTo() {
        return to;
    }
 
    public void setTo(int to) {
        this.to = to;
    }
 
    public int getType() {
        return type;
    }
 
    public void setType(int type) {
        this.type = type;
    }
 
    public String getInfo() {
        return info;
    }
 
    public void setInfo(String info) {
        this.info = info;
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	} 
    
}
