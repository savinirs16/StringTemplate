package com.modak.BasicST;

import java.sql.*;

        import static java.lang.Class.forName;

public class TestSTDB
{

    public static void main(String[] args) {
        TestSTDB testSTDB = new TestSTDB();
        testSTDB.resultsetmetadata();
    }
    public static void resultsetmetadata()
    {
        Connection connectionobj = null;
        Statement statementobj = null;
        ResultSet resultSetobj = null;
        ResultSetMetaData resultSetMetaData=null;
        try {
            Class.forName("org.postgresql.Driver");
            connectionobj= DriverManager.getConnection("jdbc:postgresql://192.168.1.50:5432/trainer", "trainer1", "training");
            statementobj = connectionobj.createStatement();
            String Selectquery = "select * from mt1019.employeest";

            resultSetobj = statementobj.executeQuery(Selectquery);
            resultSetMetaData= resultSetobj.getMetaData();
            int number_of_columns=resultSetMetaData.getColumnCount();
            while (resultSetobj.next()) {
                for(int i=1;i<=number_of_columns;i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    String columnType = resultSetMetaData.getColumnTypeName(i);

                    System.out.println("columnName= " + columnName +"|"+ " columnType= " +columnType);
                }
                System.out.println("*********************************************");

            }
            resultSetobj.close();
            statementobj.close();
            connectionobj.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if(resultSetobj==null && !resultSetobj.isClosed())
                    resultSetobj.close();
                if(statementobj==null && !statementobj.isClosed())
                    statementobj.close();
                if(connectionobj==null && !connectionobj.isClosed())
                    connectionobj.close();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
