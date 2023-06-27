package finalTest.view;

import finalTest.model.ConOperate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application{
	double xOffset = 0;
	double yOffset = 0;
	Pane login = new Pane();
	Pane top, info;
	Circle headShot = new Circle();
	TextField id = new TextField();
	PasswordField code = new PasswordField();
	CheckBox automaticLogin = new CheckBox("自动登录");
	CheckBox rememberCode = new CheckBox("记住密码");
	Text retrieveCode = new Text("找回密码");
	Text signUpForAccount = new Text("注册账号");
	Button registerBtn = new Button("登录");
	Button loginMinimizes = new Button("—");
	Button loginClose = new Button("Ⅹ");
	Stage loginStage;
	
	public void start(Stage primaryStage) {
		login.setPrefSize(520,420);
		login.setStyle("-fx-base: #ffffff;");
		id.setPrefSize(260, 40);
		code.setPrefSize(260, 40);
		id.setStyle("-fx-font: 20 STXihei;-fx-background-color: transparent;");
		code.setStyle("-fx-font: 20 STXihei;-fx-background-color: transparent;");
		ImageView idImg = new ImageView(new Image("file:D:\\img\\qq账号图标.png"));
		idImg.setFitHeight(35);
		idImg.setFitWidth(35);	
		ImageView codeImg = new ImageView(new Image("file:D:\\img\\qq密码图标.png"));
		codeImg.setFitHeight(35);
		codeImg.setFitWidth(35);	
		HBox idHb = new HBox();
		idHb.getChildren().addAll(idImg,id);
		idHb.setStyle("-fx-border-width:1;" + "-fx-border-color:#cdcfd0");
		HBox codeHb = new HBox();
		codeHb.getChildren().addAll(codeImg,code);
		codeHb.setStyle("-fx-border-width:1;" + "-fx-border-color:#cdcfd0");
		Font font = new Font("SansSerif", 15);
		automaticLogin.setFont(font);
		automaticLogin.setTextFill(Paint.valueOf("#d6d6d6"));
		rememberCode.setFont(font);
		rememberCode.setTextFill(Paint.valueOf("#d6d6d6"));
		retrieveCode.setFont(font);
		retrieveCode.setFill(Color.LIGHTGREY);
		signUpForAccount.setFont(font);
		signUpForAccount.setFill(Color.LIGHTGREY);
		registerBtn.setStyle("-fx-font: 20 arial; -fx-base: #07bcfc;");
		registerBtn.setTextFill(Paint.valueOf("#ffffff"));
		registerBtn.setPrefSize(300, 45);
		
		//登录窗口的最小化和关闭按钮
		HBox hb = new HBox();
		loginMinimizes.setTextFill(Paint.valueOf("#ffffff"));
		loginMinimizes.setFont(Font.font("arial", 15));
		loginMinimizes.setStyle("-fx-background-color: transparent;");
		loginMinimizes.setPrefSize(40, 40);
		loginClose.setTextFill(Paint.valueOf("#ffffff"));
		loginClose.setFont(Font.font("arial", 20));
		loginClose.setStyle("-fx-background-color: transparent;");
		loginClose.setPrefSize(40, 40);
		hb.getChildren().addAll(loginMinimizes,loginClose);
		
		//设置top背景
		top = new Pane();
		top.setPrefSize(533, 160);
		ImagePattern imagePattern1 = new ImagePattern(new Image("file:D:\\img\\马尔代夫.jpg"),0,0,1,1,true);
		top.setBackground(new Background(new BackgroundFill(imagePattern1, CornerRadii.EMPTY, Insets.EMPTY)));
		ImageView qqimg = new ImageView(new Image("file:D:\\img\\chat (2).png"));
		Text qq = new Text("Chat");
		qq.setFill(Color.WHITE);
		qq.setFont(Font.font(25));
		//设置头像	
		top.getChildren().addAll(qqimg,qq,hb,headShot);
		qqimg.setLayoutX(20);
		qqimg.setLayoutY(20);
		qq.setLayoutX(55);
		qq.setLayoutY(45);
		hb.setLayoutX(440);
		headShot.setRadius(45);
		headShot.setCenterX(266.5);
		headShot.setCenterY(160);
		ImagePattern imagePattern2 = new ImagePattern(new Image("file:D:\\img\\头像.jpg"),0,0,1,1,true);
		headShot.setFill(imagePattern2);
		//账号密码等排版
		info = new Pane();
		HBox hbox1 = new HBox();//装载automaticLogin, rememberCode, retrieveCode
		hbox1.setSpacing(30);
		hbox1.getChildren().addAll(automaticLogin, rememberCode, retrieveCode);
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(50,50,0,50));
		vbox.setSpacing(10);
		vbox.getChildren().addAll(idHb, codeHb, hbox1, registerBtn);
		Pane pane2 = new Pane();
		ImageView QRCode = new ImageView(new Image("file:D:\\img\\qq二维码.png"));
		pane2.getChildren().addAll(signUpForAccount, vbox, QRCode);
		info.getChildren().add(pane2);
		signUpForAccount.setLayoutX(15);
		signUpForAccount.setLayoutY(236);
		QRCode.setLayoutX(480);
		QRCode.setLayoutY(205);
		vbox.setLayoutX(70);
		info.setLayoutY(170);		
		login.getChildren().addAll(top,info);
		
		//实现鼠标对登录窗口的拖动
		login.setOnMousePressed(event -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
		
		login.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
		
		loginMinimizes.setOnAction(e -> primaryStage.setIconified(true));//登录窗口最小化
		loginClose.setOnAction(e -> System.exit(0));//关闭登录窗口
		loginMinimizes.setOnMouseEntered(e -> mouseOverChangeColor(loginMinimizes, 1));
		loginMinimizes.setOnMouseExited(e -> mouseOutChangeColor(loginMinimizes));
		loginClose.setOnMouseMoved(e -> mouseOverChangeColor(loginClose, 2));
		loginClose.setOnMouseExited(e -> mouseOutChangeColor(loginClose));
		registerBtn.setOnMouseClicked(e -> register(primaryStage));
		signUpForAccount.setOnMouseClicked(e -> {
			RegisterPane registerPane = new RegisterPane();
			registerPane.showRegisterPane();
		});
		
		Scene scene = new Scene(login);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);//透明标签栏
		primaryStage.setResizable(false);//设置登录窗口大小不可变
//		primaryStage.setAlwaysOnTop(true);//窗口始终悬浮
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
    public void mouseOverChangeColor(Button btn, int num) {
    	switch (num) {
		case 1:
			btn.setStyle("-fx-background-color: #dfe7fa;");
			break;
		case 2:
			btn.setStyle("-fx-background-color: #d90f0f;");
			break;
		}
    }
    public void mouseOutChangeColor(Button btn) {
		btn.setStyle("-fx-background-color: transparent;");
    }
    
    public void register(Stage stage) {
    	ConOperate con = new ConOperate("qqdemo");
    	if (con.register(Integer.valueOf(id.getText()), code.getText())) {
			DisplayInterface displayInterface = new DisplayInterface();
			ConOperate con2 = new ConOperate("qqdemo");
			displayInterface.id.setText(con2.getQName(Integer.valueOf(id.getText())));
			displayInterface.showQQWindow();
			displayInterface.setQQ(Integer.valueOf(id.getText()));
			stage.hide();
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("提示！");
			alert.setContentText("账号或密码错误！");
			alert.showAndWait();
		}	
    }
    
    public void showLogin() {
    	start(loginStage);
    }
}
