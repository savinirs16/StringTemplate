package com.modak.BasicST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.STGroupString;

public class DisplayTable {

    public static void main(String[] args) {
        DisplayTable displayTableObj = new DisplayTable();
        displayTableObj.displayMyTable();
    }

    public void displayMyTable() {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://192.168.1.50:5432/trainer", "trainer1", "training");
            st = connection.createStatement();
            String sqlString = "select * from mt1019.employee order by emp_id";
            rs = st.executeQuery(sqlString);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            List columnNamesList = new ArrayList<Map<String, Object>>();
            System.out.println(numberOfColumns);
            for (int i = 1; i <= numberOfColumns; i++) {
                Map columnNamesMap = new HashMap();
                columnNamesMap.put("columnName", resultSetMetaData.getColumnName(i));
                columnNamesList.add(columnNamesMap);
            }
            System.out.println(columnNamesList);
            STGroup displayTableST = new STGroupFile("displayTable.stg", '$', '$');
            ST eachRowST = displayTableST.getInstanceOf("eachRowST");
            eachRowST.add("listofcolumns", columnNamesList);
            System.out.println(eachRowST.render());
            // Use the dynamically generated ST file to populate data
            STGroup columnsDynamicST = new STGroupString("htmlDynamicTemplate", eachRowST.render(), '$', '$');
            ST renderHTMLTableST = displayTableST.getInstanceOf("renderHTMLTable");
            renderHTMLTableST.add("dynamicColumnNameST", eachRowST);
            System.out.println(renderHTMLTableST.render());
            rs.close();
            st.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();

                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


}
