package com.modak.BasicST;
//package
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicSTHTML {
    public static void main(String[] args) {
        BasicSTHTML basicSTHTML=new BasicSTHTML();
        basicSTHTML.html();
    }
    public void html(){
        STGroup helloWorldGroup = new STGroupFile("basichtml.stg", '$', '$');
        ST renderHTMLTableST = helloWorldGroup.getInstanceOf("renderHTMLTable");
        List list_of_students = new ArrayList<Map>();

        //row1
        Map studentMap1 = new HashMap();
        studentMap1.put("name","Ram");
        studentMap1.put("snumber","1234");

        //row2
        Map studentMap2 =  new HashMap();
        studentMap2.put("name","Robert");
        studentMap2.put("snumber","1234");

        //row3
        Map studentMap3 =  new HashMap();
        studentMap3.put("name","Rahim");
        studentMap3.put("snumber","1234");

        //row4
        Map studentMap4 = new HashMap();
        studentMap4.put("name","sushmitha");
        studentMap4.put("snumber","1234");


        //add all maps to the list
        list_of_students.add(studentMap1);
        list_of_students.add(studentMap2);
        list_of_students.add(studentMap3);
        list_of_students.add(studentMap4);


        //CONTROLLER LOGIC
        renderHTMLTableST.add("list_map_of_students",list_of_students);
        System.out.println(renderHTMLTableST.render());
    }
}



