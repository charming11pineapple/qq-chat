package finalTest.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import finalTest.controller.Message;
import javafx.scene.image.ImageView;

public class ConOperate {
	private Connection con;
	
	public ConOperate() {
		
	}
	
	public ConOperate(String dataName) {
		try {
			con = MySqlDAO.getConnection(dataName);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(dataName + "该数据库不存在");
		}		
	}
	
	//登录
	public boolean register(int id, String passWord) {
		try {
			String sql = "select id, passWord from personal";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (id == rs.getInt("id") && passWord.equals(rs.getString("passWord"))) {
					closeCon(con, statement, rs);
					return true;
				}
			}
			closeCon(con, statement, rs);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	//关闭连接
	public void closeCon(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			con.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}		
	}
	
	//获取好友列表
	public ArrayList<Integer> getFriendList(String tableName) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			con = MySqlDAO.getConnection("qqdemo");	
			String sql = "select idOfFriends from " + tableName;	
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				list.add(Integer.valueOf(id));
			}			
			closeCon(con, statement, rs);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error:" + e.getMessage());
		}
		return list;
	}
/**	
	//获取头像
	public ImageView getHeadShot(int id) {
		try {
			con = MySqlDAO.getConnection("qqdemo");	
			String sql = "select headShot from personal where id = ?";	
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
			}			
			closeCon(con, statement, rs);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error:" + e.getMessage());
		}
	}
	*/
	public ArrayList<Message> getHistoryCom(int fromId, int toId) {
		ArrayList<Message> list = new ArrayList<Message>();
		try {
			String sql = "select Message from messages";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Blob message = rs.getBlob(1);
				InputStream is = message.getBinaryStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				byte[] buff = new byte[(int) message.length()];
				while (-1 != (bis.read(buff, 0, buff.length))) {
					ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buff));
					Message msg = (Message) in.readObject();
					if ((msg.getFrom() == fromId && msg.getTo() == toId) || (msg.getFrom() == toId && msg.getTo() == fromId)) {
						list.add(msg);
					}			
				}
			}
	        closeCon(con, statement, rs);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public String getQName(int id) {
		try {
			String sql = "select qqName from personal where id = " + id;	
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("qqName");
				return name;
			}			
			closeCon(con, statement, rs);			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error:" + e.getMessage());
		}
		return null;
	}
	
	public static void saveMsg(Connection con, Message msg) {
		try {
			String sql = "insert into messages value(?)";
			PreparedStatement pres = con.prepareStatement(sql);
			pres.setObject(1, msg);
			pres.addBatch();
			pres.executeBatch();
	        pres.close();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public void registerQQ(String name, int id, int code) {
		try {
			String sql1 = "insert into personal value(?,?,?,?,?)";		
			PreparedStatement pres = con.prepareStatement(sql1);
			pres.setObject(1, id);
			pres.setObject(2, name);
			pres.setObject(3, code);
			pres.setObject(4, null);
			pres.setObject(5, "此人很懒，什么都没写");
			pres.addBatch();
			pres.executeBatch();
	        pres.close();
	        createTable(id);
	        con.close();
		} catch (Exception e) {
			// TODO: handle exception
		    System.out.println(e.toString());
		}
	}
	
	public void createTable(int id){
		try {
			String sql = "create table frididof." + String.valueOf(id) +"(idOfFriends int(11) KEY UNIQUE)";
			Statement stat = con.createStatement();
			stat.executeLargeUpdate(sql);
			stat.close();
			String sql2 = "create table newsof" + String.valueOf(id) +"(id int(11) KEY UNIQUE)";
			Statement stat2 = con.createStatement();
			stat2.executeLargeUpdate(sql2);
			stat2.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void sendApply(int from, int to) {
		try {
			String sql1 = "insert into newsof? value(?)";		
			PreparedStatement pres = con.prepareStatement(sql1);
			pres.setObject(1, to);
			pres.setObject(2, from);
			pres.addBatch();
			pres.executeBatch();
	        pres.close();
	        con.close();
		} catch (Exception e) {
			// TODO: handle exception
		    System.out.println(e.toString());
		}
	}
	
	public ArrayList<Integer> getNewsList(int qq) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			con = MySqlDAO.getConnection("qqdemo");
			String sql = "select id from newsof" + qq;	
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				list.add(Integer.valueOf(id));
			}			
			closeCon(con, statement, rs);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error:" + e.getMessage());
		}
		return list;
	}
	
	//同意添加好友
	public void agreeApply(int myqq, int fromqq) {
		try {
			String sql1 = "insert into frididof? value(?)";		
			PreparedStatement pres = con.prepareStatement(sql1);
			pres.setObject(1, myqq);
			pres.setObject(2, fromqq);
			pres.addBatch();
			pres.executeBatch();
	        pres.close();
	        String sql2 = "insert into frididof? value(?)";		
			PreparedStatement pres2 = con.prepareStatement(sql2);
			pres2.setObject(1, fromqq);
			pres2.setObject(2, myqq);
			pres2.addBatch();
			pres2.executeBatch();
	        pres2.close();
	        refuseApply(myqq, fromqq);
	        con.close();
		} catch (Exception e) {
			// TODO: handle exception
		    System.out.println(e.toString());
		}
	}
	
	//拒绝添加好友
	public void refuseApply(int myqq, int fromqq) {
		try {
			String sql = "delete from newsof" + String.valueOf(myqq) + " where Id=" + fromqq;
			PreparedStatement pres = con.prepareStatement(sql);
			pres.executeUpdate();
			pres.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}		
	}
}
