package com.modak.BasicST;
import org.apache.commons.dbutils.QueryRunner;
        import org.apache.commons.dbutils.handlers.MapListHandler;
        import org.stringtemplate.v4.ST;
        import org.stringtemplate.v4.STGroup;
        import org.stringtemplate.v4.STGroupFile;
        import org.stringtemplate.v4.STGroupString;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.util.List;
        import java.util.Map;

public class TestDynamicST {
    static Connection connectionobj = null;
    static String sqlString="select *from mt1019.employee";
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            connectionobj = DriverManager
                    .getConnection("jdbc:postgresql://192.168.1.50:5432/trainer", "trainer1", "training");
            QueryRunner queryRunner=new QueryRunner();
            List<Map<String,Object>> mapList=queryRunner.query(connectionobj,sqlString,new MapListHandler());
            STGroup dynamicString = new STGroupFile("dynamicTable.stg", '$', '$');
            ST st1=dynamicString.getInstanceOf("eachRowST");
            System.out.println(mapList.get(1).keySet());

            st1.add("listofcolumns",mapList.get(1).keySet());
            System.out.println(st1.render());
            ST st2=dynamicString.getInstanceOf("renderHTMLTable");
            st2.add("dynamicColumnNameST",st1.render());
            STGroupString columnsDynamicST = new STGroupString("htmlDynamicTemplate", st2.render(), '$', '$');
            ST st3=columnsDynamicST.getInstanceOf("mainHTMLTemplate");
            st3.add("list",mapList);
            System.out.println(st3.render());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(connectionobj!=null && connectionobj.isClosed()){
                    connectionobj.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

