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
        //创建字符串格式的媒体源路径
        String url = "file:///D:/wd.mp4";
        //创建媒体对象
        Media media=new Media(url);
        //创建媒体播放器
        MediaPlayer mPlayer=new MediaPlayer(media);
        //创建媒体播放视图
        MediaView mView=new MediaView(mPlayer);
        //设置媒体播放视图的宽度和高度
        mView.setFitWidth(350);
        mView.setFitHeight(180);
        //播放按钮
        Button pBut=new Button(">");
        //判断是否要求播放视频
        pBut.setOnAction(e->
        {
            if(pBut.getText().equals(">"))
            {
                //开始播放，并更改按钮上的文字
                mPlayer.play();
                pBut.setText("||");
            }
            else
            {
                //播放停止，并更改按钮上的文字
                mPlayer.pause();
                pBut.setText(">");
            }
        });
        //创建重新播放按钮
        Button rBut=new Button("<<");
        //返回到起点播放
        rBut.setOnAction(e->mPlayer.seek(Duration.ZERO));
        //创建滑动条
        Slider sVol=new Slider();
        //设置滑动条的最小宽度、首选宽度
        sVol.setMinWidth(30);
        sVol.setPrefWidth(150);
        /*
        默认滑动条的刻度值为100，这里设置滑动条一开始所在的值在50，
        也就是说滑动条处于中间位置
         */
        sVol.setValue(50);
        //将滑动条除以100（滑动条的长度）得到的百分率与播放器的音量绑定在一起
        mPlayer.volumeProperty().bind(sVol.valueProperty().divide(100));
        HBox hB=new HBox(10);
        hB.setAlignment(Pos.CENTER);
        Label vol=new Label("音量");
        hB.getChildren().addAll(pBut,rBut,vol,sVol);
        BorderPane bPane=new BorderPane();
        bPane.setCenter(mView);
        bPane.setBottom(hB);
        Scene scene=new Scene(bPane);
        primaryStage.setTitle("视频播放器");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
