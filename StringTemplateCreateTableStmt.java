package com.modak.BasicST;
import org.stringtemplate.v4.ST;
        import org.stringtemplate.v4.STGroup;
        import org.stringtemplate.v4.STGroupFile;
        import java.sql.*;
        import java.util.*;

public class StringTemplateCreateTableStmt {
    Connection connectionobj = null;
    ResultSet resultSetobj = null;
    ResultSetMetaData resultSetMetaDataobj = null;
    static String schema="mt1019",table="employeest";
    public static void main(String[] args) {
        StringTemplateCreateTableStmt obj = new StringTemplateCreateTableStmt();
        obj.createConnection();
        obj.gettableDDL();
    }
    public Connection createConnection() {
        try{
            Class.forName("org.postgresql.Driver");
            connectionobj= DriverManager.getConnection("jdbc:postgresql://192.168.1.50:5432/trainer", "trainer1", "training");
        }catch (Exception e){
            e.printStackTrace();
        }
        return connectionobj;
    }

    public void gettableDDL(){
        try {
            Statement statementobj = connectionobj.createStatement();
            String sqlquery = "select * from "+schema+"."+table;
            resultSetobj= statementobj.executeQuery(sqlquery);
            resultSetMetaDataobj = resultSetobj.getMetaData();
            Map columndetailsobj = new HashMap();
            for (int i = 1; i <= resultSetMetaDataobj.getColumnCount() ; i++) {
                if(resultSetMetaDataobj.getColumnTypeName(i)=="varchar"||resultSetMetaDataobj.getColumnTypeName(i)=="char")
                    columndetailsobj.put(resultSetMetaDataobj.getColumnName(i),resultSetMetaDataobj.getColumnTypeName(i)+"("+resultSetMetaDataobj.getPrecision(i)+")");
                else
                    columndetailsobj.put(resultSetMetaDataobj.getColumnName(i),resultSetMetaDataobj.getColumnTypeName(i));
            }

            STGroup createstmt= new STGroupFile("createScript.stg",'$','$');
            ST mainInstance = createstmt.getInstanceOf("mainCreateStatement");
            mainInstance.add("schemaName",schema);
            mainInstance.add("tableName",table);
            mainInstance.add("map",columndetailsobj);
            System.out.println(mainInstance.render());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(connectionobj!=null && connectionobj.isClosed()){
                    connectionobj.close();
                }
                resultSetobj.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
