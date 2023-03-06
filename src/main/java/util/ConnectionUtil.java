package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionUtil {
	
	private static final String RESOURCE ="java:/comp/env";
	private static final String RESOURCE_Name ="jdbc/oracle";
	
	public static DataSource getDatasource() {
		DataSource dataSource =null;
		
		try {
			Context ctx = new InitialContext();
			Context envctx = (Context) ctx.lookup(RESOURCE);
			dataSource = (DataSource) envctx.lookup(RESOURCE_Name);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return dataSource;
	}
}
