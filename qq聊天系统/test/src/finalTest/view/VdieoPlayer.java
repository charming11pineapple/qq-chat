package finalTest.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VdieoPlayer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //�����ַ�����ʽ��ý��Դ·��
        String url = "file:///D:/wd.mp4";
        //����ý�����
        Media media=new Media(url);
        //����ý�岥����
        MediaPlayer mPlayer=new MediaPlayer(media);
        //����ý�岥����ͼ
        MediaView mView=new MediaView(mPlayer);
        //����ý�岥����ͼ�Ŀ�Ⱥ͸߶�
        mView.setFitWidth(350);
        mView.setFitHeight(180);
        //���Ű�ť
        Button pBut=new Button(">");
        //�ж��Ƿ�Ҫ�󲥷���Ƶ
        pBut.setOnAction(e->
        {
            if(pBut.getText().equals(">"))
            {
                //��ʼ���ţ������İ�ť�ϵ�����
                mPlayer.play();
                pBut.setText("||");
            }
            else
            {
                //����ֹͣ�������İ�ť�ϵ�����
                mPlayer.pause();
                pBut.setText(">");
            }
        });
        //�������²��Ű�ť
        Button rBut=new Button("<<");
        //���ص���㲥��
        rBut.setOnAction(e->mPlayer.seek(Duration.ZERO));
        //����������
        Slider sVol=new Slider();
        //���û���������С��ȡ���ѡ���
        sVol.setMinWidth(30);
        sVol.setPrefWidth(150);
        /*
        Ĭ�ϻ������Ŀ̶�ֵΪ100���������û�����һ��ʼ���ڵ�ֵ��50��
        Ҳ����˵�����������м�λ��
         */
        sVol.setValue(50);
        //������������100���������ĳ��ȣ��õ��İٷ����벥��������������һ��
        mPlayer.volumeProperty().bind(sVol.valueProperty().divide(100));
        HBox hB=new HBox(10);
        hB.setAlignment(Pos.CENTER);
        Label vol=new Label("����");
        hB.getChildren().addAll(pBut,rBut,vol,sVol);
        BorderPane bPane=new BorderPane();
        bPane.setCenter(mView);
        bPane.setBottom(hB);
        Scene scene=new Scene(bPane);
        primaryStage.setTitle("��Ƶ������");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
