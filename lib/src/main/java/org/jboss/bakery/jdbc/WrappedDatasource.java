package org.jboss.bakery.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * Everything is better when it is wrapped.
 */
public class WrappedDatasource implements DataSource {
	private final DataSource wrappedDataSource;

	public WrappedDatasource(DataSource wrappedDataSource) {
		this.wrappedDataSource = wrappedDataSource;
	}

	public Connection getConnection() throws SQLException {
		return this.wrappedDataSource.getConnection();
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return this.wrappedDataSource.getConnection(username, password);
	}

	public PrintWriter getLogWriter() throws SQLException {
		return this.wrappedDataSource.getLogWriter();
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		this.wrappedDataSource.setLogWriter(out);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		this.wrappedDataSource.setLoginTimeout(seconds);
	}

	public int getLoginTimeout() throws SQLException {
		return this.wrappedDataSource.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
}
