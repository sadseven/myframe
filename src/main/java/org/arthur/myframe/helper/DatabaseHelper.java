package org.arthur.myframe.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.arthur.myframe.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DatabaseHelper {
	/**
	 * 数据库操作类
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
	private static final BasicDataSource DATA_SOURCE;

	
	static {
		Properties conf = PropsUtil.loadProps("config.properties");
		String driver = conf.getProperty("jdbc.driver");
		String url = conf.getProperty("jdbc.url");
		String username = conf.getProperty("jdbc.username");
		String password = conf.getProperty("jdbc.password");
		
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);

	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DATA_SOURCE.getConnection(); 
			} catch (SQLException e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}
	
	/**
	 * 查询一个实体类List
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
		List<T> entityList;
		Connection conn = getConnection();
		try {
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		}
		
		return entityList;
	}
	
	/**
	 * 查询一个实体类
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params){
		T entity;
		Connection conn = getConnection();
		try {
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} 
		return entity;
	}
	
	/**
	 * 插入实体
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap){
		if (fieldMap.isEmpty()) {
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}
		String sql = "insert into " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " values" + values;
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql, params) == 1;
	}
	
	/**
	 * 更新实体
	 * @param entityClass
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
		if (fieldMap.isEmpty()) {
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}
		String sql = "update " + getTableName(entityClass) + " set ";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), "");
		sql += columns + " where id = ? " ;
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		return executeUpdate(sql, params) == 1;
	}
	
	/**
	 * 删除实体
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
		String sql = "delete from " + getTableName(entityClass) + " where id = ?";
		return executeUpdate(sql, id) == 1;
	}
	
	/**
	 * 执行查询语句(update,insert,delete)
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object... params){
		List<Map<String, Object>> result;
		try {
			Connection conn = getConnection();
			result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
		} catch (Exception e) {
			LOGGER.error("execute query failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
	public static int executeUpdate(String sql, Object... params){
		int rows = 0;
		try {
			Connection conn = getConnection();
			rows = QUERY_RUNNER.update(conn, sql, params);
		} catch (Exception e) {
			LOGGER.error("execute query failure", e);
			throw new RuntimeException(e);
		}
		return rows;
	}
	
	/**
	 * 将类名变为表名
	 * @param entityClass
	 * @return
	 */
	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}
	
}
