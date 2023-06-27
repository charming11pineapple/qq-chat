package finalTest.controller;

import java.io.IOException;
import java.io.ObjectInputStream;

import finalTest.view.ChatBox;
import javafx.application.Platform;

class readInfoThread implements Runnable {
	ChatBox chatBox;
	private ObjectInputStream oIn; // 输入流 用来读操作
	private boolean flag = true; // 标记

	public readInfoThread(ObjectInputStream oIn, ChatBox chatBox) {
		this.chatBox = chatBox;
		this.oIn = oIn;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {

		try {
			// 循环 不断读取消息
			while (flag) {
				// 读取信息
				Message message = (Message) oIn.readObject();
				switch (message.getType()) {
				case MessageType.TYPE_SEND:
					Platform.runLater(() -> {
						chatBox.getMessage(message.getInfo());
					});
					break;
				case MessageType.TYPE_DOC:
					Platform.runLater(() -> {
						chatBox.getFile(message.getFile());
					});
					break;
				case MessageType.TYPE_PIC:
					Platform.runLater(() -> {
						chatBox.getPic(message.getFile());
					});
					break;
				}

			}
			// 没有数据就关闭
			if (oIn != null) {
				oIn.close();
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}