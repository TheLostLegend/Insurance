package com.example.insurance.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ConnectorDB {
    private static Connection connection;
    private static Statement statement;

    public void setConnection() throws ClassNotFoundException{
        String user_name = "InsAdmin";
        String password = "password";
        String url = "jdbc:postgresql://localhost:5432/InsDB";
        try{
            connection = DriverManager.getConnection(url, user_name, password);
            statement = connection.createStatement();
            Logger.getGlobal().info("Приложение подключилось к БД");
        } catch (SQLException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }

    public static boolean updateTable(String query) {
        try {
            statement.executeUpdate(query);
            Logger.getGlobal().info("Таблица успешно обновлена");
            return true;
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            return false;
        }
    }

    public static Statement getStatement() {
        return statement;
    }

    public static Boolean ifExist(String query) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println();
            Logger.getGlobal().info("Проверка на уникальность поля");
            return resultSet.next();
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
            return true;
        }
    }

    public static ArrayList<String> showID(String query) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println();
            //arrayList.add("id");
            while (resultSet.next())
                arrayList.add(resultSet.getString("id"));
            Logger.getGlobal().info("Все ID данные из БД успешно отображены");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
        }
        return arrayList;
    }

    public static String getValueString(String query, String column) {
        String res = null;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            res = resultSet.getString(column);
            Logger.getGlobal().info("Строка успешно получена из БД");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
        }
        return res;
    }

    public static int getValueInt(String query, String column) {
        int res = 0;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            res = resultSet.getInt(column);
            Logger.getGlobal().info("Целое значение получено из БД.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
        }
        return res;
    }

    public static float getValueFloat(String query, String column) {
        float res = 0;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            res = resultSet.getFloat(column);
            Logger.getGlobal().info("Float значение получено из БД.");
        }catch (SQLException e){
            Logger.getGlobal().severe(e.getMessage());
        }
        return res;
    }

    public static void closeConnection() {
        try {
            connection.close();
            Logger.getGlobal().info("Соединение с БД успешно закрыто.");
        }catch(SQLException e){
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}
