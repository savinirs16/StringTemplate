package com.modak.BasicST;


import org.apache.commons.dbutils.QueryRunner;
        import org.apache.commons.dbutils.handlers.MapListHandler;
        import org.stringtemplate.v4.ST;
        import org.stringtemplate.v4.STGroup;
        import org.stringtemplate.v4.STGroupFile;

        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.Writer;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class DBtoHTML {
    Connection connectionObj;
    String sqlQuery;
    QueryRunner queryRunnerObj=new QueryRunner();
    List<Map<String, Object>> employeesMap;
    public static void main(String[] args) {
        DBtoHTML dBtoHTML=new DBtoHTML();
        dBtoHTML.createConnection();
        dBtoHTML.sqlQuery=args[0];
        dBtoHTML.testJDBCSelect(dBtoHTML.sqlQuery);
        dBtoHTML.sayHello();

    }
    public Connection createConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            connectionObj=DriverManager.getConnection("jdbc:postgresql://192.168.1.50:5432/trainer","trainer1","training");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return connectionObj;

    }
    public void testJDBCSelect(String  sqlQuery)
    {

        try {
            employeesMap=queryRunnerObj.query(connectionObj,sqlQuery,new MapListHandler());
            System.out.println("Employees : "+employeesMap);
            connectionObj.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void sayHello(){
        //get the StringTemplate group file
        STGroup helloWorldGroup = new STGroupFile("basichtml.stg", '$', '$');
        //get the StringTemplate which you wanna use
        //VIEW
        ST renderHTMLTableST = helloWorldGroup.getInstanceOf("renderHTMLTable");




        //CONTROLLER LOGIC
        renderHTMLTableST.add("list_map_of_students",employeesMap);

//for html file
        try {
            FileWriter writer= new FileWriter("C:\\workarea\\java_Projects\\st_aid\\src\\main\\resources\\sqltable.html") ;
            writer.write(renderHTMLTableST.render());
            System.out.println(renderHTMLTableST.render());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

