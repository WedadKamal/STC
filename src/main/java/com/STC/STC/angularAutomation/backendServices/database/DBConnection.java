package com.STC.STC.angularAutomation.backendServices.database;

import com.STC.STC.angularAutomation.utils.Log;
import com.STC.STC.angularAutomation.utils.PropertiesFilesHandler;
import com.STC.STC.angularAutomation.constants.GeneralConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {

    public  Connection openConnection(String dbName) throws SQLException, ClassNotFoundException
    {
        Log.info("***********      Openning DB connection to " + dbName + "       **************");
        Connection con = null;
        // Create Connection to DataBase
        String dbUrl =null;
        String username =null;
        String password =null;
        PropertiesFilesHandler propHandler = new PropertiesFilesHandler();
        Properties prop = propHandler.loadPropertiesFile(GeneralConstants.DB_CONFIG_FILE_NAME);
        if (dbName.equalsIgnoreCase(GeneralConstants.BUSINESS_ENTITY_DB_NAME)) {
            dbUrl = prop.getProperty(GeneralConstants.BUSINESS_ENTITY_DB_URL_KEY);
            username = prop.getProperty(GeneralConstants.BUSINESS_ENTITY_DB_USERNAME_KEY);
            password = prop.getProperty(GeneralConstants.BUSINESS_ENTITY_DB_PASSWORD_KEY);
        }
        else if (dbName.equalsIgnoreCase(GeneralConstants.USERS_DB_NAME)) {
            dbUrl = prop.getProperty(GeneralConstants.USERS_DB_URL_KEY);
            username = prop.getProperty(GeneralConstants.USERS_DB_USERNAME_KEY);
            password = prop.getProperty(GeneralConstants.USERS_DB_PASSWORD_KEY);
        }
        else if (dbName.equalsIgnoreCase(GeneralConstants.SOF_DB_NAME)) {
            dbUrl = prop.getProperty(GeneralConstants.SOF_DB_URL_KEY);
            username = prop.getProperty(GeneralConstants.SOF_DB_USERNAME_KEY);
            password = prop.getProperty(GeneralConstants.SOF_DB_PASSWORD_KEY);
        }
        else if (dbName.equalsIgnoreCase(GeneralConstants.CORE_DB_NAME)) {
            dbUrl = prop.getProperty(GeneralConstants.CORE_DB_URL_KEY);
            username = prop.getProperty(GeneralConstants.CORE_DB_USERNAME_KEY);
            password = prop.getProperty(GeneralConstants.CORE_DB_PASSWORD_KEY);
        }

        Class.forName("oracle.jdbc.OracleDriver");
        con = DriverManager.getConnection(dbUrl, username, password);

        Log.info("**********      DB Connection created successfully to " + dbName + "       ***********");

        return con;
    }

    public ArrayList<ArrayList<Object>> executeQuery(Connection conn, String sqlQuery) throws SQLException
    {
        Log.info(" ************    Executing DB Query   ************");
        Log.info(sqlQuery);
        ArrayList<ArrayList<Object>> queryResults = new ArrayList<ArrayList<Object>>();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while(rs.next()) {
            ArrayList<Object> row = new ArrayList<Object>();
            for (int i = 1; i < columnsNumber + 1; i++) {
                Object columnValue = new Object();
                columnValue = rs.getObject(i);
                row.add(columnValue);
            }
            queryResults.add(row);
        }

        Log.info("************ DB Query executed successfully ***************");
        return queryResults;
    }

    public ResultSet executeQueryAndGetRS(Connection conn, String sqlQuery) throws SQLException
    {
        Log.info("*********      Executing DB Query     ************");
        Log.info(sqlQuery);

        Statement statement = conn.createStatement();;
        ResultSet rs =  statement.executeQuery(sqlQuery);
        Log.info("************     DB Query executed successfully     ***************");

        return rs;
    }

    public int executeUpdateQuery(Connection conn, String sqlQuery ,int id) throws SQLException
    {
        Log.info("*********      Executing DB Query     ************");
        Log.info(sqlQuery);
        //conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        String[] splitedQuery = sqlQuery.split(";");
        for (int i = 0 ; i<splitedQuery.length ; i++  )
        {
            id++;
            splitedQuery[i] =  splitedQuery[i].replaceAll("ID_VALUE", String.valueOf((id)));
            System.out.println("########" +splitedQuery[i]);
            statement.addBatch(splitedQuery[i]);
            int[] result = statement.executeBatch();
        }

        Log.info("************     DB Query executed successfully     ***************");
        commit(conn);
        return 1;
    }
    public void commit(Connection conn) throws SQLException
    {
        Log.info("*********     Commit DB query    ************");
        conn.commit();
        Log.info("************     DB Query executed successfully     ***************");
    }

    public  void closeDBConnection(Connection con) throws SQLException {
        Log.info("******     Closing DB Connection      *******");
        con.close();
        Log.info("******      DB Connection closed successfully     *******");
    }

}

