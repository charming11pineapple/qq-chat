package finalTest.view;

import finalTest.model.ConOperate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterPane extends Application{
	Stage registerPane = new Stage();
	Pane displayPane = new Pane();
	TextField nameField = new TextField();//设置昵称
	TextField idField = new TextField();//设置id
	PasswordField enterCode = new PasswordField();//第一次输入密码
	PasswordField confirmCode = new PasswordField();//第二次输入确认密码
	Button confirm = new Button("注册");
	private RegisterPane instance;
	
	public void start(Stage primaryStage) {
		Text title = new Text("注册");
		title.setFont(Font.font(50));
		title.setLayoutX(230);
		title.setLayoutY(50);
		displayPane.setPrefSize(600, 400);
		nameField.setPrefSize(400, 40);
		nameField.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		nameField.setPromptText("请输入昵称");
		idField.setPrefSize(400, 40);
		idField.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		idField.setPromptText("请输入账号");
		enterCode.setPrefSize(400, 40);
		enterCode.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		enterCode.setPromptText("请输入密码");
		confirmCode.setPrefSize(400, 40);
		confirmCode.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		confirmCode.setPromptText("请确认密码");
		VBox vb = new VBox();
		vb.setSpacing(20);
		vb.getChildren().addAll(nameField, idField, enterCode, confirmCode);
		vb.setLayoutX(90);
		vb.setLayoutY(100);
		
		confirm.setStyle("-fx-font: 20 arial; -fx-base: #009bdb;");
		confirm.setTextFill(Paint.valueOf("#ffffff"));
		confirm.setPrefSize(145, 50);
		confirm.setLayoutX(350);
		confirm.setLayoutY(350);
		
		confirm.setOnMouseClicked(e -> registerQQ());
		displayPane.getChildren().addAll(title, vb, confirm);
		Scene scene = new Scene(displayPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);//设置查找窗口大小不可变
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public RegisterPane() {
		instance = this;
	}
	
	public RegisterPane getInstance() {
		return instance;
	}
	
	public void showRegisterPane() {
    	start(registerPane);
    }
	
	public void registerQQ() {
		if (nameField.getText() == "") {
			System.out.println("请输入昵称");
			return;
		}else if (idField.getText() == "") {
			System.out.println("请输入账号");
			return;
		}else if (enterCode.getText() == "") {
			System.out.println("请输入密码");
			return;
		}else if (!enterCode.getText().equals(confirmCode.getText())) {
			System.out.println("密码不一致");
			return;
		}
		String name = nameField.getText();
		int id = Integer.valueOf(idField.getText());
		int code = Integer.valueOf(enterCode.getText());
		ConOperate con = new ConOperate("qqdemo");
		con.registerQQ(name, id, code);
		registerPane.hide();
	}
}
