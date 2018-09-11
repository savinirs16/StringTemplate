package com.modak.BasicST;

import org.antlr.stringtemplate.*;
class Simple {
    public static void main(String[] args) {
        StringTemplate query =
                new StringTemplate("create table $table$;");
        //query.setAttribute("column", "empid");
        query.setAttribute("table", "mt1019.employeest");
        System.out.println("QUERY: "+query.toString());
    }
}
