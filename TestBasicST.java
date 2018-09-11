package com.modak.BasicST;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class TestBasicST {
    public static void main(String[] args) {
        TestBasicST testBasicST = new TestBasicST();
        testBasicST.basicst();
    }
    public void basicst(){
        STGroup helloWorldGroup = new STGroupFile("hello_world_st.stg", '$', '$');
        ST wishGoodMorningst=helloWorldGroup.getInstanceOf("WishGoodMorning");
        wishGoodMorningst.add("username","Modak");
        System.out.println(wishGoodMorningst.render());
        STGroup helloWorldGroup1 = new STGroupFile("hello_world_st.stg", '$', '$');
        ST wishGoodAfternoonst=helloWorldGroup1.getInstanceOf("WishGoodAfternoon");
        wishGoodAfternoonst.add("username","Modak");
        System.out.println(wishGoodAfternoonst.render());
    }
}
