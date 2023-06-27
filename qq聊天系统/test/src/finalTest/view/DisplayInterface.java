package finalTest.view;

import java.util.ArrayList;

import com.mysql.cj.conf.RuntimeProperty;

import finalTest.controller.Client;
import finalTest.model.ConOperate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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

public class DisplayInterface extends Application {
	private double xOffset = 0;
	private double yOffset = 0;
	private int qq;
	Stage displayInter = new Stage();// QQ界面
	Pane qqBox = new Pane();// qq总界面
	Pane top = new Pane();// top存放qq头像、id等
	Pane center = new Pane();// 显示好友和群聊列表
	ScrollPane peoplePane, groupPane;// 好友列表界面,群聊列表界面
	HBox botton = new HBox();// 放置其他软件的pane
	Circle headShot = new Circle();// 头像
	Circle cirOnline = new Circle();// 在线图标
	Text textEquipment = new Text(">我的设备");
	Text textFriends = new Text(">我的好友");
	Text textblacklist = new Text("黑名单");
	Text id = new Text("黄峰鸿");// id
	TextField signature = new TextField();// qq个性签名
	TextField search = new TextField();// 联系人列表搜索联系人
	Button paneMinimizes = new Button("—");
	Button paneClose = new Button("Ⅹ");
	Button qqSpace = new Button("空间");
	Button contactPerson = new Button("联系人");
	Button info = new Button("消息");
	Button friends = new Button("好友");
	Button grouChat = new Button("群聊");
	Button addPeoOrGroup = new Button("╋");
	Pane myFriends, myEquipment, blacklist;// 我的好友，我的设备，黑名单
	private int listOpen = 0;

	public void start(Stage primaryStage) {
		qqBox.setPrefSize(360, 740);
		top.setPrefSize(360, 170);
		//界面窗口的最小化和关闭按钮
		HBox hb = new HBox();
		paneMinimizes.setTextFill(Paint.valueOf("#ffffff"));
		paneMinimizes.setFont(Font.font("arial", 15));
		paneMinimizes.setStyle("-fx-background-color: transparent;");
		paneMinimizes.setPrefSize(40, 40);
		paneClose.setTextFill(Paint.valueOf("#ffffff"));
		paneClose.setFont(Font.font("arial", 20));
		paneClose.setStyle("-fx-background-color: transparent;");
		paneClose.setPrefSize(40, 40);
		hb.getChildren().addAll(paneMinimizes,paneClose);
		hb.setLayoutX(280);
		headShot.setRadius(35);
		headShot.setLayoutX(60);
		headShot.setLayoutY(80);
		ImagePattern imagePattern2 = new ImagePattern(new Image("file:D:\\img\\头像.jpg"),0,0,1,1,true);
		headShot.setFill(imagePattern2);
		cirOnline.setRadius(8);
		cirOnline.setLayoutX(85);
		cirOnline.setLayoutY(108);
		cirOnline.setFill(Paint.valueOf("#08f074"));
		id.setLayoutX(110);
		id.setLayoutY(70);
		id.setStyle("-fx-font: 20 STXihei; -fx-font-weight:bold;");
		signature.setPrefSize(90, 15);
		signature.setLayoutX(110);
		signature.setLayoutY(90);
		signature.setStyle("-fx-background-color: transparent;");
		search.setPrefSize(330, 20);
		search.setLayoutX(30);
		search.setStyle("-fx-background-color: transparent;");
		ImageView searchImg = new ImageView(new Image("file:D:\\img\\搜索图标.png"));
		searchImg.setLayoutX(5);
		searchImg.setLayoutY(5);		
		Pane searchP = new Pane();
		searchP.setStyle("-fx-background-color: rgba(223, 219, 224, 0.7);");
		searchP.getChildren().addAll(searchImg,search);
		searchP.setLayoutY(140);
		top.getChildren().addAll(hb,headShot,cirOnline,id,signature,searchP);
		ImagePattern imagePattern1 = new ImagePattern(new Image("file:D:\\img\\小狗.jpg"),0,0,1,1,true);
		top.setBackground(new Background(new BackgroundFill(imagePattern1, CornerRadii.EMPTY, Insets.EMPTY)));
		/*--------------------------------------*/
		center.setPrefSize(360, 510);
		center.setLayoutY(170);
		center.setStyle("-fx-background-color: #e5e5e5;");
		qqSpace.setPrefSize(100, 50);
		qqSpace.setStyle("-fx-font: 18 STXihei; -fx-background-color: transparent;");
		qqSpace.setTextFill(Paint.valueOf("#949495"));
		contactPerson.setPrefSize(100, 50);
		contactPerson.setStyle("-fx-font: 18 STXihei; -fx-background-color: transparent;");
		contactPerson.setTextFill(Paint.valueOf("#949495"));
		info.setPrefSize(100, 50);
		info.setStyle("-fx-font: 18 STXihei;");
		info.setStyle("-fx-font: 18 STXihei; -fx-background-color: transparent;");
		info.setTextFill(Paint.valueOf("#949495"));
		HBox hb1 = new HBox();//放空间、联系人、消息按钮		
		hb1.setPadding(new Insets(0,0,0,14));
		hb1.setSpacing(15);
		hb1.getChildren().addAll(qqSpace,contactPerson,info);
		Pane hb2 = new Pane();//放好友和群聊按钮
		hb2.setStyle("-fx-border-width: 1 0 0 0;-fx-border-style: solid;-fx-border-color: #dbdbdb");
		hb2.setPrefSize(360, 50);
		hb2.setLayoutY(51);	
		friends.setPrefSize(80, 35);
		friends.setStyle("-fx-font: 17 STXihei; -fx-background-color: transparent;");
		friends.setTextFill(Paint.valueOf("#848484"));
		friends.setLayoutX(10);
		friends.setLayoutY(10);
		grouChat.setPrefSize(80, 35);
		grouChat.setStyle("-fx-font: 17 STXihei; -fx-background-color: transparent;");
		grouChat.setTextFill(Paint.valueOf("#848484"));
		grouChat.setLayoutX(100);
		grouChat.setLayoutY(10);
		addPeoOrGroup.setPrefSize(35, 35);
		addPeoOrGroup.setStyle("-fx-font: 17 STXihei;-fx-font-weight:bold; -fx-background-color: transparent;");
		addPeoOrGroup.setTextFill(Paint.valueOf("#848484"));
		addPeoOrGroup.setLayoutX(300);
		addPeoOrGroup.setLayoutY(10);
		hb2.getChildren().addAll(friends,grouChat,addPeoOrGroup);
		//联系人和群聊界面
		peoplePane = new ScrollPane();
		VBox peopleContent = new VBox();//显示的好友列表
		VBox groupContent = new VBox();//显示群聊列表
		HBox myFridHbox = new HBox();
		myFridHbox.setStyle("-fx-background-color: #e5e5e5;");
		myFridHbox.setPrefSize(360, 40);
		myFridHbox.getChildren().addAll(textFriends);//'>我的好友'列表
		
		groupPane = new ScrollPane();		
		HBox myeqptHbox = new HBox();
		myeqptHbox.setStyle("-fx-background-color: #e5e5e5;");
		myeqptHbox.setPrefSize(360, 40);
		myeqptHbox.getChildren().addAll(textEquipment);//'>我的设备'列表
		myEquipment = new Pane();
		myEquipment.setStyle("-fx-background-color: #e5e5e5;");
		myEquipment.getChildren().addAll(myeqptHbox);
		
		Pane friendList = new Pane();
		
		peopleContent.getChildren().addAll(myeqptHbox,myFridHbox, friendList);
		peoplePane.setContent(peopleContent);
		peoplePane.setLayoutY(110);
		center.getChildren().addAll(hb1,hb2,peoplePane);
		/*-------------------------------------*/
		botton.setPrefSize(360, 60);
		botton.setLayoutY(680);
		botton.setStyle("-fx-background-color: #e5e5e5;-fx-border-width: 2 0 0 0;-fx-border-style: solid;-fx-border-color: #dbdbdb");
		botton.setPadding(new Insets(0,0,0,12));
		botton.setSpacing(12);
		Pane p1 = btnPane(new Image("file:D:\\img\\其他.png"));
		Pane p2 = btnPane(new Image("file:D:\\img\\添加好友图标.png"));
		Pane p3 = btnPane(new Image("file:D:\\img\\腾讯文档图标.png"));
		Pane p4 = btnPane(new Image("file:D:\\img\\其他游戏图标.png"));
		Pane p5 = btnPane(new Image("file:D:\\img\\qq游戏图标.png"));
		Pane p6 = btnPane(new Image("file:D:\\img\\qq音乐图标.png"));
		Pane p7 = btnPane(new Image("file:D:\\img\\其他应用图标.png"));
		botton.getChildren().addAll(p1,p2,p3,p4,p5,p6,p7);
		
		
		qqBox.getChildren().addAll(top,center,botton);
		
		//实现鼠标对查找窗口的拖动
		top.setOnMousePressed(event -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
		
		top.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
		//鼠标移入改变背景颜色
		p1.setOnMouseEntered(e -> changeColor(p1));
		p2.setOnMouseEntered(e -> changeColor(p2));
		p3.setOnMouseEntered(e -> changeColor(p3));
		p4.setOnMouseEntered(e -> changeColor(p4));
		p5.setOnMouseEntered(e -> changeColor(p5));
		p6.setOnMouseEntered(e -> changeColor(p6));
		p7.setOnMouseEntered(e -> changeColor(p7));
		p1.setOnMouseExited(e -> returnColor(p1));
		p2.setOnMouseExited(e -> returnColor(p2));
		p3.setOnMouseExited(e -> returnColor(p3));
		p4.setOnMouseExited(e -> returnColor(p4));
		p5.setOnMouseExited(e -> returnColor(p5));
		p6.setOnMouseExited(e -> returnColor(p6));
		p7.setOnMouseExited(e -> returnColor(p7));
		p2.setOnMouseClicked(e -> openSearchBox());//打开好友搜索框
		p5.setOnMouseClicked(e -> startApp(3));//打开qq游戏
		p6.setOnMouseClicked(e -> startApp(4));//打开qq音乐
		info.setOnMouseClicked(e -> openApplyNewsPane());//打开好友申请界面
		myFridHbox.setOnMouseClicked(e -> friendList(friendList));
		paneMinimizes.setOnAction(e -> primaryStage.setIconified(true));//登录窗口最小化
		paneClose.setOnAction(e -> primaryStage.hide());//关闭登录窗口
		paneMinimizes.setOnMouseEntered(e -> mouseOverChangeColor(paneMinimizes, 1));
		paneMinimizes.setOnMouseExited(e -> mouseOutChangeColor(paneMinimizes));
		paneClose.setOnMouseMoved(e -> mouseOverChangeColor(paneClose, 2));
		paneClose.setOnMouseExited(e -> mouseOutChangeColor(paneClose));
		
		Scene scene = new Scene(qqBox);
		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);//设置登录窗口大小不可变
		primaryStage.initStyle(StageStyle.TRANSPARENT);//透明标签栏
		primaryStage.setAlwaysOnTop(true);//窗口始终悬浮
		primaryStage.show();
	}

	

	public static void main(String[] args) {
		launch(args);
	}
	
	//打开添加好友窗口
	public void openSearchBox() {
		AddFriendPane searchPane = new AddFriendPane();
		searchPane.setMyqq(qq);
		searchPane.showSearchPane();
	}
	
	//接受好友申请界面
	public void openApplyNewsPane() {
		ApplyNewsPane applyNewsPane = new ApplyNewsPane();
		applyNewsPane.setQQ(qq);
		applyNewsPane.showNewsBox();
	}

	public void showQQWindow() {
		start(displayInter);
	}

	public Pane btnPane(Image img) {
		Pane pane = new Pane();
		pane.setPrefSize(40, 60);
		ImageView imgView = new ImageView(img);
		imgView.setLayoutX(5);
		imgView.setLayoutY(16);
		pane.getChildren().addAll(imgView);
		return pane;
	}

	public void startApp(int num) {
		try {
			Runtime app = Runtime.getRuntime();
			if (num == 3) {
				app.exec("D:\\360安全浏览器下载\\QQGameTempest\\QQGame.exe");
			} else if (num == 4) {
				app.exec("C:\\Program Files (x86)\\Tencent\\QQMusic\\QQMusic.exe");
			} else {
				System.out.println("未下载该应用");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void changeColor(Pane pane) {
		pane.setStyle("-fx-background-color: #dbdbdb;");
	}

	public void returnColor(Pane pane) {
		pane.setStyle("-fx-background-color: transparent;");
	}

	public Circle returnHeadShot(Circle headShot) {
		Circle circle = new Circle();
		circle = headShot;
		circle.setRadius(25);
		return circle;
	}

	// 打开聊天框
	public void openChatBox(HBox friend, String name, int myqq, int friendqq) {
		ChatBox chatBox = new ChatBox();
		chatBox.myHeadShot = returnHeadShot(headShot);// 聊天框我的头像
		chatBox.setChatInfo(name, myqq, friendqq);
		Client client = new Client(chatBox.getInstance(), myqq, friendqq);
		chatBox.showChatWindow(client);
	}

	// 打开好友列表
	public void friendList(Pane pane) {
		if (listOpen == 0) {//打开好友列表
			textFriends.setText("∨我的好友");
			ConOperate con = new ConOperate();
			ArrayList<Integer> friendsList = con.getFriendList("frididof" + String.valueOf(qq));
			VBox frdList = new VBox();
			frdList.setStyle("-fx-background-color: #e5e5e5;");
			for (int i = 0; i < friendsList.size(); i++) {
				HBox hb = new HBox();
				Circle cir = new Circle();
				cir.setRadius(25);
				ImagePattern imagePattern3 = new ImagePattern(new Image("file:D:\\img\\头像.jpg"), 0, 0, 1, 1, true);
				cir.setFill(imagePattern3);
				hb.setStyle("-fx-background-color: #e5e5e5;");
				hb.setPrefSize(360, 50);
				Text id = new Text(String.valueOf(friendsList.get(i)));
				id.setStyle("-fx-font: 10 STXihei;");
				ConOperate con2 = new ConOperate("qqdemo");
				String name = con2.getQName(Integer.valueOf(id.getText()));
				Text qqName = new Text(name);// 好友名字
				qqName.setStyle("-fx-font: 20 STXihei;");
				hb.getChildren().addAll(cir, qqName, id);
				hb.setOnMouseClicked(e -> openChatBox(hb, name, qq, Integer.valueOf(id.getText())));// 打开聊天框
				frdList.getChildren().addAll(hb);
			}
			pane.getChildren().addAll(frdList);
			listOpen = 1;
		} else {//关闭好友列表
			textFriends.setText(">我的好友");
			pane.getChildren().clear();
			listOpen = 0;
		}

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
	
	public void setQQ(int qq) {
		this.qq = qq;
	}
}
