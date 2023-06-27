package finalTest.view;
import finalTest.controller.Client;
import finalTest.controller.MessageType;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;

public class EmojiPane extends Application {




    public EmojiPane(){

}

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("GridPane Experiment");

        ImageView ImageView1 = new ImageView("file:D:\\emotions\\1.jpg");
        ImageView ImageView2 = new ImageView("file:D:\\emotions\\2.jpg");
        ImageView ImageView3 = new ImageView("file:D:\\emotions\\3.jpg");
        ImageView ImageView4 = new ImageView("file:D:\\emotions\\4.jpg");
        ImageView ImageView5 = new ImageView("file:D:\\emotions\\5.jpg");
        ImageView ImageView6 = new ImageView("file:D:\\emotions\\6.jpg");

        GridPane gridPane = new GridPane();


        gridPane.add(ImageView1, 0, 0);
        gridPane.add(ImageView2, 1, 0);
        gridPane.add(ImageView3, 2, 0 );
        gridPane.add(ImageView4, 0, 1);
        gridPane.add(ImageView5, 1, 1);
        gridPane.add(ImageView6, 2, 1);

        Scene scene = new Scene(gridPane, 470, 320);
        stage.setScene(scene);
        stage.show();


        ChatBox chatBox = new ChatBox();
        ImageView1.setOnMouseClicked(event -> chatBox.sendEmoji1());
        
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
