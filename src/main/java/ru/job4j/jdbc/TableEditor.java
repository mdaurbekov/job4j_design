package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
            String password = properties.getProperty("hibernate.connection.password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalArgumentException();
        }

    }

    public void createTable(String tableName) {
        request(String.format("create table %s();", tableName));

    }

    public void dropTable(String tableName) {
        request(String.format("drop table %s;", tableName));

    }

    public void addColumn(String tableName, String columnName, String type) {
        request(String.format("alter table %s add column %s %s;", tableName, columnName, type));

    }

    public void dropColumn(String tableName, String columnName) {
        request(String.format("alter table %s drop column %s;", tableName, columnName));

    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        request(String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName));

    }

    public void request(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
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

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();

        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }

        try (TableEditor tableEditor = new TableEditor(config)) {

            System.out.println("?????????????? ??????????????");
            tableEditor.createTable("test_table");
            System.out.println(getTableScheme(tableEditor.connection, "test_table"));

            System.out.println("?????????????????? ??????????????");
            tableEditor.addColumn("test_table", "id", "serial primary key");
            System.out.println(getTableScheme(tableEditor.connection, "test_table"));

            System.out.println("?????????????????????????????? ??????????????");
            tableEditor.renameColumn("test_table", "id", "index");
            System.out.println(getTableScheme(tableEditor.connection, "test_table"));

            System.out.println("?????????????? ??????????????");
            tableEditor.dropColumn("test_table", "index");
            System.out.println(getTableScheme(tableEditor.connection, "test_table"));

            System.out.println("?????????????? ??????????????");
            tableEditor.dropTable("test_table");
        }
    }
}