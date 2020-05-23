package com.txt.powermock.statics;

public class DatabaseChecker {

	public boolean isDatabaseConnected() {
		return DatabaseHelper.connect();
	}

	public void checkSql(String sql) {
		DatabaseHelper.executeSQL(sql);
	}

}
