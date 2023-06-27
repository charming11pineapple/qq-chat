package finalTest.view;

import finalTest.model.ConOperate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddFriendPane extends Application{
	double xOffset = 0;
	double yOffset = 0;
	VBox addFriendPane = new VBox();//���Һ��ѽ���
	Pane top = new Pane();//����
	Pane center = new Pane();//�м����������Ϣ
	FlowPane bottom = new FlowPane();//�ײ���ʾ���ҵĺ���
	ScrollPane bottomScroll = new ScrollPane();


	TextField findField = new TextField();//���Һ��������
	Button findBtn = new Button("���Һ���");

	TextField findgroupField = new TextField();//����Ⱥ�������
	Button findgroupBtn = new Button("����Ⱥ��");


	Button paneMinimizes = new Button("��");
	Button paneClose = new Button("��");
	Stage displaySearchPane = new Stage();
	private AddFriendPane instance;
	private int myqq;
	
	public void start(Stage primaryStage) {
		top.setPrefSize(1100, 75);
		top.setStyle("-fx-background-color: #009bdb;");
		//��¼���ڵ���С���͹رհ�ť
		HBox hb = new HBox();
		ImageView qqimg = new ImageView(new Image("file:D:\\img\\qq��ɫ���.png"));
		qqimg.setLayoutX(5);
		qqimg.setLayoutY(5);
		Text findText = new Text("����");
		findText.setFill(Color.WHITE);
		findText.setFont(Font.font(20));
		findText.setLayoutX(40);
		findText.setLayoutY(30);
		paneMinimizes.setTextFill(Paint.valueOf("#ffffff"));
		paneMinimizes.setFont(Font.font("arial", 15));
		paneMinimizes.setStyle("-fx-background-color: transparent;");
		paneMinimizes.setPrefSize(40, 40);
		paneClose.setTextFill(Paint.valueOf("#ffffff"));
		paneClose.setFont(Font.font("arial", 20));
		paneClose.setStyle("-fx-background-color: transparent;");
		paneClose.setPrefSize(40, 40);
		hb.getChildren().addAll(paneMinimizes,paneClose);
		hb.setLayoutX(1020);
		top.getChildren().addAll(qqimg, findText,hb);
		
		//center
		center.setPrefSize(1100, 200);
		center.setStyle("-fx-background-color: #ebf2f9;-fx-border-width: 0 0 1 0;-fx-border-style: solid;-fx-border-color: #dbdbdb;");
		findField.setPrefSize(600, 40);

		findField.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		findField.setPromptText("�˺�");
		findField.setLayoutX(50);
		findField.setLayoutY(40);
		findBtn.setStyle("-fx-font: 20 arial; -fx-base: #009bdb;");
		findBtn.setTextFill(Paint.valueOf("#ffffff"));
		findBtn.setPrefSize(145, 50);
		findBtn.setLayoutX(740);
		findBtn.setLayoutY(40);

		findgroupField.setPrefSize(600, 40);
		findgroupField.setStyle("-fx-font: 20 STXihei;-fx-background-color: #ffffff;");
		findgroupField.setPromptText("Ⱥ��");
		findgroupField.setLayoutX(50);
		findgroupField.setLayoutY(100);
		findgroupBtn.setStyle("-fx-font: 20 arial; -fx-base: #009bdb;");
		findgroupBtn.setTextFill(Paint.valueOf("#ffffff"));
		findgroupBtn.setPrefSize(145, 50);
		findgroupBtn.setLayoutX(740);
		findgroupBtn.setLayoutY(100);


		Text friendText = new Text("ͬ�ǽ��ѣ�ͬ������");
		friendText.setFont(new Font("SansSerif", 15));
		friendText.setFill(Color.LIGHTGREY);
		friendText.setLayoutX(900);
		friendText.setLayoutY(70);
		center.getChildren().addAll(findField, findBtn,findgroupField,findgroupBtn,friendText);

		
		//bottom
		bottom.setPrefSize(1100, 500);
		bottom.setStyle("-fx-background-color: #ebf2f9;");
		bottomScroll.setContent(bottom);
		bottomScroll.setStyle("-fx-padding: 0;-fx-hbar-policy: never;");// ����ʾ���ҹ�����
		
		addFriendPane.getChildren().addAll(top, center, bottomScroll);
		//ʵ�����Բ��Ҵ��ڵ��϶�
		addFriendPane.setOnMousePressed(event -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
		
		addFriendPane.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
		
		paneMinimizes.setOnAction(e -> primaryStage.setIconified(true));//��¼������С��
		paneClose.setOnAction(e -> primaryStage.hide());//�رյ�¼����
		paneMinimizes.setOnMouseEntered(e -> mouseOverChangeColor(paneMinimizes, 1));
		paneMinimizes.setOnMouseExited(e -> mouseOutChangeColor(paneMinimizes));
		paneClose.setOnMouseMoved(e -> mouseOverChangeColor(paneClose, 2));
		paneClose.setOnMouseExited(e -> mouseOutChangeColor(paneClose));
		findBtn.setOnMouseClicked(e -> friendBox());
		
		Scene scene = new Scene(addFriendPane);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);//͸����ǩ��
		primaryStage.setResizable(false);//���ò��Ҵ��ڴ�С���ɱ�
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public AddFriendPane() {
		instance = this;
	}
	
	public AddFriendPane getInstance() {
		return instance;
	}
	
	public int getMyqq() {
		return myqq;
	}

	public void setMyqq(int myqq) {
		this.myqq = myqq;
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
    
    public void friendBox() {
    	Pane pane = new Pane();
    	pane.setPrefSize(250, 150);
    	pane.setStyle("-fx-background-color: transparent;");
    	Circle headShot = new Circle();
    	headShot.setRadius(50);
    	headShot.setLayoutX(60);
    	headShot.setLayoutY(75);
    	ImagePattern imagePattern = new ImagePattern(new Image("file:D:\\img\\ͷ��.jpg"),0,0,1,1,true);
		headShot.setFill(imagePattern);
    	ConOperate con = new ConOperate("qqdemo");
    	int id;
    	if (findField.getText().equals("")) {//��������Ϊ��
			return;
		}else {
			id = Integer.valueOf(findField.getText());
		}  	 
    	String qName = con.getQName(id);
    	if (qName == null) {//���û�ҵ����˳�
			return;
		}
    	Text name = new Text(qName);
    	name.setFont(new Font("SansSerif", 30));
    	name.setLayoutX(130);
    	name.setLayoutY(40);
    	Text qq = new Text(String.valueOf(id));
    	qq.setFont(new Font("SansSerif", 20));
    	qq.setFill(Color.LIGHTGREY);
    	qq.setLayoutX(130);
    	qq.setLayoutY(80);
    	Button applyBtn = new Button("+����");
    	applyBtn.setStyle("-fx-font: 15 arial; -fx-base: #8fc0e7;");
    	applyBtn.setTextFill(Paint.valueOf("#ffffff"));
    	applyBtn.setPrefSize(70, 30);
    	applyBtn.setLayoutX(130);
    	applyBtn.setLayoutY(100);
    	pane.getChildren().addAll(headShot, name, qq, applyBtn);
    	bottom.getChildren().addAll(pane);
    	applyBtn.setOnMouseClicked(e -> sendApply(id));
    }
    
    public void showSearchPane() {
    	start(displaySearchPane);
    }
    
    public void sendApply(int to) {
    	ConOperate con = new ConOperate("qqdemo");
    	con.sendApply(myqq, to);
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("��Ϣ");
		alert.setHeaderText("������Ӻ����ѷ���");
		alert.setContentText("��ȴ��ظ�");
		alert.showAndWait();
    }
}
