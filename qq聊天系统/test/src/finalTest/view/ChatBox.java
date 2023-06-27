package finalTest.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import finalTest.controller.Camera;
import finalTest.controller.Client;
import finalTest.controller.Message;
import finalTest.controller.MessageType;
import finalTest.model.ConOperate;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChatBox extends Application {
	double xOffset = 0;
	double yOffset = 0;
	private int myqq;
	private int friendqq;
	Circle myHeadShot = new Circle();// 我的头像
	Circle friendHeadShot = new Circle();// 好友的头像
	Stage displayInter = new Stage();// 聊天框
	Pane displayInterBox = new Pane();
	Pane topLabelBar = new Pane();// 聊天框的上标签栏
	Pane qqShowPane = new Pane();// qq秀显示框
	ScrollPane communicates = new ScrollPane();// 聊天信息显示框
	ScrollPane history;// 历史聊天信息
	FlowPane chatPane = new FlowPane();// 聊天信息显示框
	FlowPane historyChatPane = new FlowPane();// 历史消息显示框
	Pane enterContentPane = new Pane();// 聊天信息输入框
	TextArea contentfield = new TextArea();// 输入聊天内容框
	Text friendId = new Text("刘磊");// 好友的id
	HBox friendIdBox = new HBox();
	Button chatMinimizes = new Button("—");
	Button chatClose = new Button("Ⅹ");
	Button closeBtn = new Button("关闭(C)");
	Button sendBtn = new Button("发送(S)");
	Client client = new Client();
	ChatBox instance;
	private int openHistory = 0;

	public ChatBox() {
		// TODO Auto-generated constructor stub
		instance = this;
	}

	public ChatBox getInstance() {
		return instance;
	}

	public void start(Stage primaryStage) {
		displayInterBox.setPrefSize(1000, 750);
		displayInterBox.setStyle("-fx-background-color: #e5e5e5;");
		friendId.setStyle("-fx-font: 22 Regular;");
		ImageView qqSpaceImg = new ImageView(new Image("file:D:\\img\\qq空间图标.png"));
		friendIdBox.getChildren().addAll(friendId, qqSpaceImg);
		friendIdBox.setSpacing(5);
		friendIdBox.setLayoutX(450);
		friendIdBox.setLayoutY(10);
		// 最小化和关闭按钮
		HBox btnHb = new HBox();
		chatMinimizes.setTextFill(Paint.valueOf("#4b4f53"));
		chatMinimizes.setFont(Font.font("arial", 15));
		chatMinimizes.setStyle("-fx-background-color: transparent;");
		chatMinimizes.setPrefSize(40, 40);
		chatClose.setTextFill(Paint.valueOf("#4b4f53"));
		chatClose.setFont(Font.font("arial", 20));
		chatClose.setStyle("-fx-background-color: transparent;");
		chatClose.setPrefSize(40, 40);
		btnHb.setLayoutX(920);
		btnHb.getChildren().addAll(chatMinimizes, chatClose);
		topLabelBar.setPrefSize(1000, 50);
		topLabelBar.setStyle("-fx-background-color: #989799;");
		topLabelBar.getChildren().addAll(friendIdBox, btnHb);
		// 视频通话图标
		Pane qqVideoCallBox = new Pane();
		qqVideoCallBox.setStyle("-fx-background-color: transparent;");
		qqVideoCallBox.setPrefSize(800, 60);
		ImageView qqVideoCall = new ImageView(new Image("file:D:\\img\\qq视频通话图标.png"));
		qqVideoCall.setLayoutX(620);
		qqVideoCallBox.getChildren().addAll(qqVideoCall);
		qqVideoCallBox.setLayoutY(50);

		qqShowPane.setStyle(
				"-fx-background-color: transparent;-fx-border-width: 0 0 0 1;-fx-border-style: solid;-fx-border-color: #dbdbdb");
		qqShowPane.setPrefSize(200, 700);
		ImageView qqshowImg = new ImageView(new Image("file:D:\\img\\男性正装站立.png"));
		qqshowImg.setLayoutX(30);
		qqshowImg.setLayoutY(50);
		qqShowPane.getChildren().addAll(qqshowImg);
		qqShowPane.setLayoutX(800);
		qqShowPane.setLayoutY(50);

		chatPane.setPrefWidth(800);
		chatPane.setMinHeight(400);
		chatPane.setStyle(
				"-fx-background-color: #e5e5e5;-fx-border-width: 0 0 1 0;-fx-border-style: solid;-fx-border-color: #dbdbdb;");
		communicates.setContent(chatPane);
		communicates.setStyle("-fx-padding: 0;-fx-hbar-policy: never;");// 不显示左右滚动条
		communicates.setPrefSize(800, 400);
		communicates.setLayoutY(110);

		enterContentPane.setPrefSize(800, 240);
		enterContentPane.setLayoutY(510);
		contentfield.setPrefSize(800, 150);
		contentfield.setLayoutY(45);
		contentfield.setWrapText(true);// 自动换行
		contentfield.setStyle("-fx-background-color: #e5e5e5;-fx-control-inner-background: #e5e5e5;");
		closeBtn.setLayoutX(600);
		closeBtn.setLayoutY(200);
		closeBtn.setPrefSize(80, 30);
		closeBtn.setStyle("-fx-background-color: #ededed;");
		sendBtn.setLayoutX(700);
		sendBtn.setLayoutY(200);
		sendBtn.setPrefSize(80, 30);
		sendBtn.setStyle("-fx-background-color: #989798;");
		enterContentPane.getChildren().addAll(docImg(), contentfield, closeBtn, sendBtn);

		displayInterBox.getChildren().addAll(topLabelBar, qqVideoCallBox, communicates, qqShowPane, enterContentPane);
		displayInterBox.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});

		displayInterBox.setOnMouseDragged(event -> {
			primaryStage.setX(event.getScreenX() - xOffset);
			primaryStage.setY(event.getScreenY() - yOffset);
		});

		chatMinimizes.setOnMouseEntered(e -> mouseOverChangeColor(chatMinimizes, 1));
		chatMinimizes.setOnMouseExited(e -> mouseOutChangeColor(chatMinimizes));
		chatClose.setOnMouseMoved(e -> mouseOverChangeColor(chatClose, 2));
		chatClose.setOnMouseExited(e -> mouseOutChangeColor(chatClose));
		sendBtn.setOnMouseEntered(e -> mouseOverChangeColor(sendBtn, 1));
		sendBtn.setOnMouseExited(e -> {
			sendBtn.setStyle("-fx-background-color: #989798;");
		});
		sendBtn.setOnMouseClicked(e -> sendMessage());
		qqSpaceImg.setOnMouseClicked(e -> {//打开qq空间
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"https://qzone.qq.com/");
			} catch (Exception e2) {
				// TODO: handle exception
			}			
		});
		/*
		 * contentfield.setOnKeyPressed(e -> {//设置键盘点击事件ENTER发生消息 if
		 * (e.getCode().name().equals("ENTER")) { sendMessage(); } });
		 */

		chatMinimizes.setOnAction(e -> primaryStage.setIconified(true));// 登录窗口最小化
		chatClose.setOnAction(e -> primaryStage.hide());// 关闭聊天窗口
		closeBtn.setOnAction(e -> primaryStage.hide());// 关闭聊天窗口
		Scene scene = new Scene(displayInterBox);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);// 透明标签栏
		primaryStage.setResizable(false);// 设置登录窗口大小不可变
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showChatBox() {
		start(displayInter);
	}

	public void mouseOverChangeColor(Button btn, int num) {
		switch (num) {
		case 1:
			btn.setStyle("-fx-background-color: #a6a5a8;");
			break;
		case 2:
			btn.setStyle("-fx-background-color: #f84c4c;");
			break;
		}
	}

	public void mouseOutChangeColor(Button btn) {
		btn.setStyle("-fx-background-color: transparent;");
	}

	// 输入框上方的应用
	public Pane docImg() {
		Pane pane = new Pane();
		pane.setPrefSize(800, 40);
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		hb1.setSpacing(10);
		hb2.setSpacing(10);
		ImageView p1 = new ImageView(new Image("file:D:\\img\\p1.png"));//表情按钮
		ImageView p2 = new ImageView(new Image("file:D:\\img\\p2.png"));
		ImageView p3 = new ImageView(new Image("file:D:\\img\\p3.png"));
		ImageView p4 = new ImageView(new Image("file:D:\\img\\p4.png"));
		ImageView p5 = new ImageView(new Image("file:D:\\img\\p5.png"));
		ImageView p6 = new ImageView(new Image("file:D:\\img\\p6.png"));
		ImageView p7 = new ImageView(new Image("file:D:\\img\\p7.png"));
		ImageView p8 = new ImageView(new Image("file:D:\\img\\p8.png"));
		ImageView p9 = new ImageView(new Image("file:D:\\img\\p9.png"));
		ImageView p10 = new ImageView(new Image("file:D:\\img\\p10.png"));
		hb1.getChildren().addAll(p1, p2, p3, p4, p5, p6, p7, p8);
		hb1.setLayoutX(10);
		hb1.setLayoutY(10);
		hb2.getChildren().addAll(p9, p10);
		hb2.setLayoutX(710);
		hb2.setLayoutY(9);
		pane.getChildren().addAll(hb1, hb2);
		p1.setOnMouseClicked(event -> emoji());
		p3.setOnMouseClicked(e -> screenshot());//截屏
		p4.setOnMouseClicked(e -> uploadFiles());//发送文件
		p6.setOnMouseClicked(e -> uploadPic());//发送图片
		p10.setOnMouseClicked(e -> showHistoryChat());
		return pane;
	}

	// 输出显示聊天信息框以及内容
	public TextArea chatMessages(String messages) {
		TextArea textArea = new TextArea();
		textArea.setText(messages);
		textArea.setMaxWidth(500);
		textArea.setStyle("-fx-background-color: #e5e5e5;-fx-control-inner-background: #e5e5e5;");
		textArea.setEditable(false);// 文本区不可编辑
		return textArea;
	}

	public void sendMessage() {
		String content = contentfield.getText();
		HBox hb = new HBox();
		hb.setPrefWidth(750);
		hb.setSpacing(10);
		Label label = new Label(content);
		label.setWrapText(true);// 自动换行
		label.setStyle("-fx-background-radius: 8px;-fx-background-color: rgb(179,231,244);");
		label.setPadding(new Insets(10, 5, 10, 5));
		label.setMaxWidth(300);
		label.setFont(new Font(17));
		if (content.equals("")) {// 发送内容不能为空
			return;
		}
		// 如果是自己发送的消息靠右对齐
		Circle headPic = returnHeadShot();
		hb.getChildren().addAll(label, headPic);
		hb.setAlignment(Pos.TOP_RIGHT);
		client.sendMsg(content, myqq, friendqq);// 发送消息内容
		chatPane.getChildren().add(hb);
		chatPane.setPadding(new Insets(0, 20, 0, 10));
		chatPane.setVgap(10);// 垂直单位间距
		communicates.setVvalue(1.0);
		contentfield.setText("");
	}

	public void getMessage(String msg) {
		String content = msg;
		HBox hb = new HBox();
		hb.setPrefWidth(750);
		hb.setSpacing(10);
		Label label = new Label(content);
		label.setWrapText(true);// 自动换行
		label.setStyle("-fx-background-radius: 8px;-fx-background-color: rgb(179,231,244);");
		label.setPadding(new Insets(10, 5, 10, 5));
		label.setMaxWidth(300);
		label.setFont(new Font(17));
		if (content.equals("")) {// 发送内容不能为空
			return;
		}
		Circle headPic = returnHeadShot();
		hb.getChildren().addAll(headPic, label);// 收到的消息靠左对齐
		hb.setAlignment(Pos.TOP_LEFT);
		chatPane.getChildren().add(hb);
		chatPane.setPadding(new Insets(0, 20, 0, 10));
		chatPane.setVgap(10);// 垂直单位间距
		communicates.setVvalue(1.0);
		contentfield.setText("");
	}

	public void showChatWindow(Client client) {
		start(displayInter);
		this.client = client;
		client.sendMsg(myqq, friendqq);
	}

	public void setChatInfo(String name, int myqq, int friendqq) {
		friendId.setText(name);
		this.myqq = myqq;
		this.friendqq = friendqq;
	}

	public Circle returnHeadShot() {
		Circle circle = new Circle();
		circle.setRadius(25);
		ImagePattern imagePattern2 = new ImagePattern(new Image("file:D:\\img\\头像.jpg"), 0, 0, 1, 1, true);
		circle.setFill(imagePattern2);
		return circle;
	}

	// 显示历史消息
	public void showHistoryChat() {
		if (openHistory == 0) {
			history = new ScrollPane();
			history.setContent(chatPane);
			history.setStyle("-fx-padding: 0;-fx-hbar-policy: never;");// 不显示左右滚动条
			history.setPrefSize(800, 400);
			history.setLayoutY(110);
			history.setPrefSize(800, 400);
			historyChatPane.setPrefWidth(800);
			historyChatPane.setMinHeight(400);
			historyChatPane.setStyle(
					"-fx-background-color: #e5e5e5;-fx-border-width: 0 0 1 0;-fx-border-style: solid;-fx-border-color: #ffffff;");
			ConOperate con = new ConOperate("qqdemo");
			ArrayList<Message> msgList = con.getHistoryCom(myqq, friendqq);
			for (int i = 0; i < msgList.size(); i++) {
				historyChatPane.getChildren().addAll(oneMessage(msgList.get(i)));
			}
			openHistory = 1;
			history.setContent(historyChatPane);
			history.setVvalue(1);// 使滚动条在最下方
			displayInterBox.getChildren().addAll(history);
		} else if (openHistory == 1) {
			historyChatPane.getChildren().clear();
			displayInterBox.getChildren().remove(history);
			openHistory = 0;
		}

	}

	// 一条历史消息组件
	public VBox oneMessage(Message msg) {
		ConOperate con1 = new ConOperate("qqdemo");
		String name = con1.getQName(msg.getFrom());// 发送者姓名
		String content = msg.getInfo();
		VBox vb = new VBox();
		vb.setPrefWidth(750);
		Label label1 = new Label(name);
		Label label2 = new Label(content);
		label1.setWrapText(true);// 自动换行
		label1.setStyle("-fx-background-color: #e5e5e5;");
		label1.setPadding(new Insets(10, 5, 10, 5));
		label1.setMaxWidth(600);
		label1.setFont(new Font(15));
		label1.setTextFill(Paint.valueOf("#6c45ff"));
		label2.setWrapText(true);// 自动换行
		label2.setStyle("-fx-background-color: #e5e5e5;");
		label2.setPadding(new Insets(10, 5, 10, 5));
		label2.setMaxWidth(600);
		label2.setFont(new Font(17));
		vb.getChildren().addAll(label1, label2);
		vb.setAlignment(Pos.TOP_LEFT);
		return vb;
	}

	//上传文件
	public void uploadFiles() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All", "*.*"));
		File file = fileChooser.showOpenDialog(displayInter);
//		String name =file.getPath();
//		name = new StringBuilder(name).reverse().toString();
//		for(int i=0;i<name.length();i++){
//
//		}
		if (file == null) {
			return;
		}
		client.sendMsg(file, myqq, friendqq);
		sendFile(file);
	}

	//上传图片
	public void uploadPic() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("图像文件", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog(displayInter);
		if (file == null) {
			return;
		}
		client.sendMsg(file, myqq, friendqq, MessageType.TYPE_PIC);
		sendPic(file);
	}

	public void sendFile(File file) {
		HBox hb = new HBox();
		hb.setPrefWidth(750);
		ImageView qqDocImg = new ImageView(new Image("file:D:\\img\\qq文件夹.png"));
		Pane qqDoc = new Pane();
		qqDoc.getChildren().add(qqDocImg);
		Circle headPic = returnHeadShot();
		hb.getChildren().addAll(qqDoc, headPic);
		hb.setAlignment(Pos.TOP_RIGHT);// 发送的文件靠右对齐
		chatPane.getChildren().addAll(hb);
		chatPane.setPadding(new Insets(0, 20, 0, 10));
		chatPane.setVgap(10);// 垂直单位间距
		communicates.setVvalue(1.0);
		qqDoc.setOnMouseClicked(e -> downLoadFile(file));
	}

	//
	public void getFile(File file) {
		HBox hb = new HBox();
		hb.setPrefWidth(750);
		ImageView qqDocImg = new ImageView(new Image("file:D:\\img\\qq文件夹.png"));
		Pane qqDoc = new Pane();
		qqDoc.getChildren().add(qqDocImg);
		Circle headPic = returnHeadShot();
		hb.getChildren().addAll(headPic, qqDoc);
		hb.setAlignment(Pos.TOP_LEFT);// 收到的消息靠左对齐
		chatPane.getChildren().add(hb);
		chatPane.setPadding(new Insets(0, 20, 0, 10));
		chatPane.setVgap(10);// 垂直单位间距
		communicates.setVvalue(1.0);
		qqDoc.setOnMouseClicked(e -> downLoadFile(file));
	}

	//下载文件
	public void downLoadFile(File f) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All", "*.*"));
		File file = fileChooser.showSaveDialog(displayInter);
		if (file == null) {
			return;
		}
		try {
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流保存到本地文件
			FileInputStream inputStream = new FileInputStream(f);
			FileOutputStream fos = new FileOutputStream(file);
			while ((len = inputStream.read(bs)) != -1) {
				fos.write(bs, 0, len);
			}
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 发送图片,,
	public void sendPic(File file) {
		HBox hb = new HBox();
		hb.setPrefWidth(750);
		String path = file.getPath();
		System.out.println(path);
		ImageView qqDocImg = autoPicSize(new Image("file:" + path));

		Pane qqDoc = new Pane();
		qqDoc.getChildren().add(qqDocImg);
		System.out.println("图片");

		String url = "file:"+path;
		//点击图片打开图片浏览器
		qqDocImg.setOnMouseClicked(e -> openImagesViewer(url));

		Circle headPic = returnHeadShot();
		hb.getChildren().addAll(qqDoc, headPic);
		hb.setAlignment(Pos.TOP_RIGHT);// 发送的图片靠右对齐
		chatPane.getChildren().addAll(hb);
		chatPane.setPadding(new Insets(0, 20, 0, 10));
		chatPane.setVgap(10);// 垂直单位间距
		communicates.setVvalue(1.0);
	}

	// 接收图片
	public void getPic(File file) {
		HBox hb = new HBox();
		hb.setPrefWidth(750);		
		ImageView qqDocImg = autoPicSize(new Image("file:" + saveGetPic(file)));
		String url = "file:"+saveGetPic(file);
		qqDocImg.setOnMouseClicked(e -> openImagesViewer(url));
		Pane qqDoc = new Pane();
		qqDoc.getChildren().add(qqDocImg);
		Circle headPic = returnHeadShot();
		hb.getChildren().addAll(headPic, qqDoc);
		hb.setAlignment(Pos.TOP_LEFT);// 收到的图片靠左对齐
		chatPane.getChildren().addAll(hb);
		chatPane.setPadding(new Insets(0, 20, 0, 10));
		chatPane.setVgap(10);// 垂直单位间距
		communicates.setVvalue(1.0);
	}

	// 调整发送图片的大小
	public ImageView autoPicSize(Image img) {
		ImageView imgv = new ImageView(img);
		DoubleProperty zoomProperty = new SimpleDoubleProperty(300);
		double proportion;
		double height = img.getHeight();
		double width = img.getWidth();
		if (height > width) {
			proportion = zoomProperty.get() / height;
		} else {
			proportion = zoomProperty.get() / width;
		}
		imgv.setFitHeight(height * proportion);
		imgv.setFitWidth(width * proportion);
		return imgv;
	}
		
	//保存对方发送的图片
	public String saveGetPic(File f) {	
		File file = new File("D:\\getPic");
		try {
			if (!file.exists()) {
				file.mkdir();
			}
			byte[] bs = new byte[(int)f.length()];
			// 读取到的数据长度
			int len;
			// 输出的文件流保存到本地文件
			FileInputStream inputStream = new FileInputStream(f);
			FileOutputStream fos = new FileOutputStream(file.getPath() + "/" + f.getName());
			while ((len = inputStream.read(bs)) != -1) {
				fos.write(bs, 0, len);
			}
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return file.getPath() + "/" + f.getName();//返回存储图片的路径
	}
	
	@SuppressWarnings("unused")
	public void screenshot() {
		Camera cam = new Camera("D:\\screenshot\\截屏", "png");
        cam.snapShot();
        File file = new File(cam.getPath());
        client.sendMsg(file, myqq, friendqq, MessageType.TYPE_PIC);		
        sendPic(file);
	}

	//打开图片浏览器
	public void openImagesViewer(String url){
      ImagesViewer imagesViewer = new ImagesViewer("图像查看器",url);
		imagesViewer.start(new Stage());
	}


//emoji 面板打开
	public  void emoji(){
		EmojiPane emojiPane = new EmojiPane();
		try {
			emojiPane.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//发送emoji
	public  void sendEmoji1(){

		File file = new File("D:\\emotions\\1.jpg");
		if (file == null) {
			return;
		}

		client.sendMsg(file, myqq, friendqq, MessageType.TYPE_PIC);
		sendPic(file);
	}

}
