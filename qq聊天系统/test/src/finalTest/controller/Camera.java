package finalTest.controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Camera {
	private String filePreStr; // Ĭ��ǰ׺��ѡ��洢·�����磺 D��\\��
    private String defName = "cameraImg";  // Ĭ�Ͻ�ͼ����
    static int serialNum = 0;  //��ͼ���ƺ���������ۼ�
    private String imageFormat; // ͼ���ļ��ĸ�ʽ
    private String defaultImageFormat = "png"; //��ͼ��׺
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //��ȡȫ��Ļ�Ŀ�߳ߴ������
 
    public Camera() {
        filePreStr = defName;
        imageFormat = defaultImageFormat;
    }
 
    public Camera(String s, String format) {
        filePreStr = s;
        imageFormat = format;
    }
 
    public void snapShot() {
        try {
            // *** ���Ĵ��� *** ������Ļ��һ��BufferedImage����screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
            serialNum++;
            // �����ļ�ǰ׺�������ļ���ʽ�������Զ������ļ���
            String name = filePreStr + String.valueOf(serialNum) + "." + imageFormat;
            File f = new File(name);
            System.out.print("Save File " + name);
            // ��screenshot����д��ͼ���ļ�
            ImageIO.write(screenshot, imageFormat, f);
            System.out.print("..Finished!\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public String getPath() {
    	return filePreStr + String.valueOf(serialNum) + "." + imageFormat;
    }
    // ����֮�󣬼��ɽ�ȫ��Ļ��ͼ���浽ָ����Ŀ¼����<br>// ���ǰ��ҳ�������ѡ��ߴ���߼���������̨������ʵ������ѡ���ͼ����ʹ�С�Ľ�ͼ<br>
    public static void main(String[] args) {
        Camera cam = new Camera("d:\\getPic\\����", "png");
        cam.snapShot();
    }
}
