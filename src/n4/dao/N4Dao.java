package n4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class N4Dao {

	private Connection con;				// 接続オブジェクト
	private String server;		// DBサーバ(IPアドレスorFQDN)
	private int portNum;		// DBサーバのポート番号
	private String dbName;		// DBの名前
	private String user;		// ユーザー名
	private String password;	// パスワード

	private PreparedStatement stmt;		// SQL文を解析して対象のサーバに実行するオブジェクト


	public N4Dao(Connection con) {
		super();
		this.con = con;
	}
	/**
	 * コンストラクタ
	 * @param server	DBサーバ(IPアドレスorFQDN)
	 * @param portNum	DBサーバのポート番号
	 * @param dbName	DBの名前
	 * @param user		ユーザー名
	 * @param password	パスワード
	 */
	public N4Dao(String server, int portNum, String dbName, String user, String password) {
		super();
		this.server = server;
		this.portNum = portNum;
		this.dbName = dbName;
		this.user = user;
		this.password = password;
	}

	/**
	 * 接続
	 * @throws SQLException
	 */
	public void open() throws SQLException{
		if(con == null){
//			String url = "jdbc:oracle:thin:@" + this.server + ":" + this.portNum + "/" + this.dbName;
//			this.con = DriverManager.getConnection(url,this.user,this.password);
			Context context;
			try {
				context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			this.con = ds.getConnection();
			} catch (NamingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	/**
	 * 接続のクローズ
	 * @throws SQLException
	 */
	public void close() throws SQLException{
		if(con != null){
			con.close();
		}
	}

	final void setSql(String sql)throws SQLException{
		if(con == null){
			throw new NullPointerException("Connection is null");
		}if(sql == null){
			throw new NullPointerException("SQL is null");
		}
		stmt =  con.prepareStatement(sql);
	}

	public PreparedStatement getStmt() {
		return stmt;
	}



}
