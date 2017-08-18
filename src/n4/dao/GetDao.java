package n4.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import casestudy.DbItem;

public abstract class GetDao extends N1PramDao {

	public void executeQuery(DbItem[] dbItem) throws SQLException {
		try{
			open();
			super.setSql(getSql());
			PreparedStatement stmt = super.getStmt();
			ResultSet result = stmt.executeQuery();
			int i=0;
			while(result.next()){
				executeQuery(dbItem, result,i);
				i++;
			}

		}finally{
			close();
		}
	}
	public abstract void executeQuery(DbItem[] dbItem, ResultSet result,int i) throws SQLException;

	public abstract String getSql() ;

}
