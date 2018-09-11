package com.modak.BasicST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class TestSTDynamicResolution {

    public TestSTDynamicResolution() {
    }

    public static void main(String[] args) {
        TestSTDynamicResolution testSTDynamicResolution = new TestSTDynamicResolution();
        testSTDynamicResolution.testSTResolution();
    }

    private void testSTResolution() {

        List<Map<String, Object>> informationListMap = new ArrayList<Map<String, Object>>();
        Map m1 = new HashMap();
        m1.put("pno", "1");
        m1.put("Name", "rajesh");
        m1.put("country", "india");

        Map m2 = new HashMap();
        m2.put("pno", "2");
        m2.put("Name", "Gary");
        m2.put("country", "US");
        m2.put("Gender", "male");


        Map m3 = new HashMap();
        m3.put("pno", "3");
        m3.put("Name", "Milind");
        m3.put("country", "india");
        m3.put("Gender", "male");


        informationListMap.add(m1);
        informationListMap.add(m2);
        informationListMap.add(m3);

        STGroup dynamicSTResolution = new STGroupFile("dynamicSTResolution.stg",'$','$');
        ST wishAll = dynamicSTResolution.getInstanceOf("wishAllTemplate");
        wishAll.add("informationListOfMaps",informationListMap);
        System.out.println(wishAll.render());

    }
}
