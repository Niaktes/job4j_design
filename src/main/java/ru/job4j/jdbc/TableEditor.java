package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void statementExecute(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String query = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        statementExecute(query);
    }

    public void dropTable(String tableName) {
        String query = String.format("DROP TABLE %s CASCADE;", tableName);
        statementExecute(query);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String query = String.format("ALTER TABLE %s "
                        + "ADD COLUMN %s %s;",
                        tableName, columnName, type);
        statementExecute(query);
    }

    public void dropColumn(String tableName, String columnName) {
        String query = String.format("ALTER TABLE %s "
                        + "DROP COLUMN %s;",
                        tableName, columnName);
        statementExecute(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String query = String.format("ALTER TABLE %s "
                        + "RENAME COLUMN %s TO %s;",
                        tableName, columnName, newColumnName);
        statementExecute(query);
    }

    public String getTableScheme(String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n", metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
