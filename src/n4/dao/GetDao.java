package n4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import casestudy.DbItem;
import casestudy.Position;

public abstract class GetDao extends N1PramDao {

	public GetDao(Connection connection){
		super(connection);
	}
	public void executeQuery(DbItem[] dbItem) throws SQLException {
//		try{
//			open();
			super.setSql(getSql());
			PreparedStatement stmt = super.getStmt();
			ResultSet result = stmt.executeQuery();
			int i=0;
			while(result.next()){
				executeQuery(dbItem, result,i);
				i++;
			}
/*
		}finally{
			close();
		}
*/
	}

	public void executeQuery(DbItem[] dbItem, Position[] position) throws SQLException {
		int i = 0;

		try{
			open();
			super.setSql(getSql());
			PreparedStatement stmt = super.getStmt();

			while(position[i]!=null){
				stmt.setString(1,position[i].getModes());
				ResultSet result = stmt.executeQuery();

				if(result.next()){
					executeQuery(dbItem, result,i);

				}
				i++;
			}
		}finally{
			close();
		}
	}

	public abstract void executeQuery(DbItem[] dbItem, ResultSet result,int i) throws SQLException;

	public abstract String getSql() ;

}
