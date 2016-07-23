package com.pocketclass.utils;
//字符串转换器
public class StringConverter {

//	采用正则表达式将包含有 单引号(‘)，分号(;) 和 注释符号(–)的语句给替换掉来防止SQL注入
	public static String TransactSQLInjection(String str)
    {
//          return str.replaceAll(".*([';]+|(--)+).*", " ");
          return str.replaceAll("[';(--)]", " ");
    }
}
