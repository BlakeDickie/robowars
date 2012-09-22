/*
 * DatabaseConnector.java
 *
 * Created on July 4, 2004, 10:54 AM
 */

package robowars.common;

import java.io.*;
import org.w3c.dom.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author  Blake Dickie
 */
public class DatabaseConnector {
    private static DatabaseConnector instance;
    
    private String driverName;
    private String driverURL;
    private String username;
    private String password;
    
    private Connection connection;
    
    private String[] replacements = {"'", "\\'", "\"", "\\\"", "\0", "\\0", "\n", "\\n"};
    
    private Map safeString = new HashMap();
    
    /** Creates a new instance of DatabaseConnector */
    public DatabaseConnector() {
        loadData(getClass().getClassLoader().getResourceAsStream("database-settings.xml"));
        instance = this;
    }
    
    
    public DatabaseConnector(InputStream documentBase)
    {
        loadData(documentBase);
        instance = this;
    }
    
    private void loadData(InputStream input)
    {
        for (int i = 0; i < replacements.length; i += 2)
            safeString.put(replacements[i], replacements[i + 1]);
        
        Document settings = SupportFunctions.parseXML(input, false);
        Element root = settings.getDocumentElement();
        driverName = SupportFunctions.getTextValue(SupportFunctions.findElement(root, "driver"));
        driverURL = SupportFunctions.getTextValue(SupportFunctions.findElement(root, "url"));
        username = SupportFunctions.getTextValue(SupportFunctions.findElement(root, "username"));
        password = SupportFunctions.getTextValue(SupportFunctions.findElement(root, "password"));
        connect();
    }
    
    public boolean connect()
    {
        try {
        Class.forName(driverName).newInstance();
        connection = DriverManager.getConnection(driverURL, username, password);
        return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
    }
    
    public void disconnect()
    {
        try {
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
    
    public synchronized static DatabaseConnector getInstance()
    {
        if (instance == null)
            return new DatabaseConnector();
        else
            return instance;
    }
    
    public synchronized ResultSet doQuery(String sql) throws SQLException
    {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }
    
    public synchronized boolean doExecute(String sql) throws SQLException
    {
        Statement stmt = connection.createStatement();
        return stmt.execute(sql);
    }
    
    public synchronized int doUpdate(String sql) throws SQLException
    {
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql);
    }
    
    public synchronized String singleResultQuery(String sql)
    {
        try {
            ResultSet rs = doQuery(sql);
            if (rs.next())
                return rs.getString(1);
            else
                return null;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public String makeSingleSafe(String text)
    {
        StringBuffer result = new StringBuffer(text.length() * 2);
        for(int i = 0; i < text.length(); i++)
        {
            String cChar = text.substring(i, i + 1);
            if (safeString.containsKey(cChar))
                result.append(safeString.get(cChar));
            else
                result.append(cChar);
        }
        return result.toString();
    }
}
