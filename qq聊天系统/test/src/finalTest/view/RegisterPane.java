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
	TextField nameField = new TextField();//�����ǳ�
	TextField idField = new TextField();//����id
	PasswordField enterCode = new PasswordField();//��һ����������
	PasswordField confirmCode = new PasswordField();//�ڶ�������ȷ������
	Button confirm = new Button("ע��");
	private RegisterPane instance;
	
	public void start(Stage primaryStage) {
		Text title = new Text("ע��");
		title.setFont(Font.font(50));
		title.setLayoutX(230);
		title.setLayoutY(50);
		displayPane.setPrefSize(600, 400);
		nameField.setPrefSize(400, 40);
		nameField.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		nameField.setPromptText("�������ǳ�");
		idField.setPrefSize(400, 40);
		idField.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		idField.setPromptText("�������˺�");
		enterCode.setPrefSize(400, 40);
		enterCode.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		enterCode.setPromptText("����������");
		confirmCode.setPrefSize(400, 40);
		confirmCode.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		confirmCode.setPromptText("��ȷ������");
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
		primaryStage.setResizable(false);//���ò��Ҵ��ڴ�С���ɱ�
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
			System.out.println("�������ǳ�");
			return;
		}else if (idField.getText() == "") {
			System.out.println("�������˺�");
			return;
		}else if (enterCode.getText() == "") {
			System.out.println("����������");
			return;
		}else if (!enterCode.getText().equals(confirmCode.getText())) {
			System.out.println("���벻һ��");
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
