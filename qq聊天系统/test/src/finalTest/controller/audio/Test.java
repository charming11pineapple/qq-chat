package finalTest.controller.audio;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        //�½�¼���̣߳�������test.wav�ļ���
        AudioRecorder audioRecorder = new AudioRecorder("test.wav");
        audioRecorder.start();
        Thread.sleep(5000);//����������¼����ʱ��
        audioRecorder.stopRecording();
        audioRecorder.play("test.wav");//�����ļ�·��������Ƶ
    }
}
