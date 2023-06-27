package finalTest.controller.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AudioRecorder extends Thread {

    private static TargetDataLine mic;
    private String audioName;

    public AudioRecorder(String audioName) {
        this.audioName = audioName;
    }

    @Override
    public  void run() {
        initRecording();
        statRecording();
    }

    private void initRecording() {

        System.out.println("��ʼ¼��.....");

        try {
            //define audio format
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

            mic = (TargetDataLine) AudioSystem.getLine(info);
            mic.open();

            System.out.println("¼����......");
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }

    }

    private void statRecording() {
        try {
            mic.start();
            AudioInputStream audioInputStream = new AudioInputStream(mic);
            File f = new File(audioName);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, f);
            System.out.println("¼���ļ��洢.....");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void stopRecording() {
        mic.stop();
        mic.close();
        System.out.println("¼������....");
    }

    /**
     * pcm��Ƶ����
     * @param file �ļ�·��
     */
    public void play(String file){
        try {
            System.out.println("��ʼ����.....");
            FileInputStream fis = new FileInputStream(file);
            AudioFormat.Encoding encoding =  new AudioFormat.Encoding("PCM_SIGNED");
            //�����ʽ�������ʣ�ÿ��������λ����������֡�����ֽڣ���֡�����Ƿ�big-endian�ֽ�˳��洢
            AudioFormat format = new AudioFormat(encoding,44100, 16, 2, 4, 44100 ,false);
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            auline.start();
            byte[] b = new byte[256];
            try {
                while(fis.read(b)>0) {
                    auline.write(b, 0, b.length);
                }
                auline.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
