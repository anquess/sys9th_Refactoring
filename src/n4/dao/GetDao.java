package n4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import casestudy.DbItem;

public abstract class GetDao extends N1PramDao {

	public abstract void executeQuery(DbItem[] dbItem, ResultSet result,int i) throws SQLException;

	public abstract String getSql() ;

}
