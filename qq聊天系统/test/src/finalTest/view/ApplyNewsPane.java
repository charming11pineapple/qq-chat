package finalTest.view;

import java.util.ArrayList;

import finalTest.model.ConOperate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ApplyNewsPane extends Application{
	Stage displayStage = new Stage();
	Pane applyNewsPane = new Pane();
	ScrollPane scrollPane = new ScrollPane();
	FlowPane flowPane = new FlowPane();
	Text title = new Text("好友验证");
	private int qq = 111111;
	ApplyNewsPane instance;
	
	public void start(Stage primaryStage) {
		applyNewsPane.setPrefSize(600, 400);
		title.setFont(Font.font(30));
		title.setLayoutX(250);
		title.setLayoutY(30);
		
		flowPane.setPrefSize(600, 400);
		flowPane.setStyle("-fx-background-color: #ffffff;");
		scrollPane.setContent(flowPane);
		scrollPane.setStyle("-fx-padding: 0;-fx-hbar-policy: never;");// 不显示左右滚动条
		scrollPane.setLayoutX(5);
		scrollPane.setLayoutY(50);
		getApply();
		
		applyNewsPane.getChildren().addAll(title, scrollPane);
		Scene scene = new Scene(applyNewsPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);//设置查找窗口大小不可变
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public ApplyNewsPane() {
		// TODO Auto-generated constructor stub
		instance = this;
	}

	public ApplyNewsPane getInstance() {
		return instance;
	}
	
	public void showNewsBox() {
		start(displayStage);
	}
	
	public void setQQ(int qq) {
		this.qq = qq;
	}
	public int getQQ() {
		return qq;
	}
	
	public void getApply() {
		ConOperate con = new ConOperate();
		ArrayList<Integer> list = con.getNewsList(qq);
		for (int i = 0; i < list.size(); i++) {
			int id = list.get(i);
			ConOperate con2 = new ConOperate("qqdemo");
			flowPane.getChildren().add(getPane(id, con2.getQName(id)));
		}
	}
	
	public Pane getPane(int id, String fromName) {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: #e5e5e5;");
		pane.setPrefSize(600, 100);
		Circle headShot = new Circle();
		headShot.setRadius(40);
    	headShot.setLayoutX(50);
    	headShot.setLayoutY(50);
    	ImagePattern imagePattern = new ImagePattern(new Image("file:D:\\img\\头像.jpg"),0,0,1,1,true);
		headShot.setFill(imagePattern);
		Text name = new Text(fromName);
    	name.setFont(new Font("SansSerif", 30));
    	name.setLayoutX(150);
    	name.setLayoutY(40);
    	Text qq = new Text(String.valueOf(id));
    	qq.setFont(new Font("SansSerif", 20));
    	qq.setLayoutX(150);
    	qq.setLayoutY(75);
    	Button agree = new Button("同意");
    	agree.setStyle("-fx-font: 15 arial; -fx-base: #8fc0e7;");
    	agree.setTextFill(Paint.valueOf("#ffffff"));
    	agree.setPrefSize(70, 30);
    	agree.setLayoutX(400);
    	agree.setLayoutY(50);
    	Button refuse = new Button("拒绝");
    	refuse.setStyle("-fx-font: 15 arial; -fx-base: #8fc0e7;");
    	refuse.setTextFill(Paint.valueOf("#ffffff"));
    	refuse.setPrefSize(70, 30);
    	refuse.setLayoutX(500);
    	refuse.setLayoutY(50);
    	pane.getChildren().addAll(headShot, name, qq, agree, refuse);
    	agree.setOnMouseClicked(e -> {
    		ConOperate con = new ConOperate("qqdemo");
    		con.agreeApply(getQQ(), id);
    		flowPane.getChildren().remove(pane);
    	});
    	refuse.setOnMouseClicked(e -> {
    		ConOperate con = new ConOperate("qqdemo");
    		con.refuseApply(getQQ(), id);
    		flowPane.getChildren().remove(pane);
    	});
    	return pane;
	}
}
