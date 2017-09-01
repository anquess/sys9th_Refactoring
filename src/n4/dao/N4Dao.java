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
	private PreparedStatement stmt;		// SQL文を解析して対象のサーバに実行するオブジェクト
	public N4Dao(Connection con) {
		super();
		this.con = con;
	}
	
	public void open() throws SQLException{
		if(con == null){

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
