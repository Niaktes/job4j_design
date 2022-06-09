package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.util.Properties;

public class StatementDemo {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("app.properties")) {
            properties.load(fileInputStream);
        }
        try (TableEditor te = new TableEditor(properties)) {
            String tableName = "test_table";
            System.out.println("--Создание таблицы--");
            te.createTable(tableName);
            System.out.println(te.getTableScheme(tableName));
            System.out.println("--Добавление столбца--");
            te.addColumn(tableName, "test_column", "int");
            System.out.println(te.getTableScheme(tableName));
            System.out.println("--Переименование столбца--");
            te.renameColumn(tableName, "test_column", "column_tested");
            System.out.println(te.getTableScheme(tableName));
            System.out.println("--Удаление столбца--");
            te.dropColumn(tableName, "column_tested");
            System.out.println(te.getTableScheme(tableName));
            System.out.println("--Удаление таблицы--");
            te.dropTable(tableName);
        }
    }

}
