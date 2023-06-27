package finalTest.view;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class ImagesViewer extends Application {

    public ImagesViewer(String Í¼Ïñ²é¿´Æ÷, String url) {
        this.title= title;
        this.url = url;
    }

    public static void main(String[] args) {
        launch(args);
    }




    private String title = "Í¼Æ¬ä¯ÀÀÆ÷";
    public String url ;

    private static double size = 1;
    private static double count = 1.0;

    @Override
    public void start(Stage stage) {
        AnchorPane root = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-border-color: #ffffff;-fx-background-color: #ffffff;");
        root.getChildren().add(scrollPane);
        System.out.println(url);
        Image image = new Image(url);

        double width = image.getWidth();
        double height = image.getHeight();
        double percent = 1000 / image.getWidth() < 600 / image.getHeight() ? 1000 / image.getWidth() : 600 / image.getHeight();
        if (percent < 1) {
            width = width * percent;
            height = height * percent;
        }

        VBox vBox = new VBox();
        vBox.setPrefWidth(1000);
        vBox.setPrefHeight(600);
        vBox.setAlignment(Pos.CENTER);
        scrollPane.setContent(vBox);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        vBox.getChildren().add(imageView);
        size = 1;
        count = 1;
        imageView.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
                count = count + 1.0 / 10;
                size = 1.0 / 200 * (count - 1) * (count - 1) * (count - 1) + 1;
                imageView.setFitWidth(imageView.getFitWidth() * size);
                imageView.setFitHeight(imageView.getFitHeight() * size);
                count++;
            } else {
                count = count - 1.0 / 10;
                double y = 1.0 / 200 * (count - 1) * (count - 1) * (count - 1) + 1;
                size = y < 0 ? size : y;
                imageView.setFitWidth(imageView.getFitWidth() / size);
                imageView.setFitHeight(imageView.getFitHeight() / size);
                count--;
            }
        });

        Scene scene = new Scene(root);
        stage.setTitle(title);
        // ÉèÖÃÈí¼þÍ·²¿icon
//        File iconFile = ResourceUtils.getIconFile();
//        FileInputStream inputStream = FileUtils.getFileInputStream(iconFile);
//        primary.getIcons().add(new Image(inputStream));
        stage.setScene(scene);
        stage.setWidth(1000);
       stage.setHeight(700);
        stage.show();
    }


}