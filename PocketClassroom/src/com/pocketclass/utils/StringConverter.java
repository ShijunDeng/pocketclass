package com.pocketclass.utils;
//�ַ���ת����
public class StringConverter {

//	����������ʽ�������� ������(��)���ֺ�(;) �� ע�ͷ���(�C)�������滻������ֹSQLע��
	public static String TransactSQLInjection(String str)
    {
//          return str.replaceAll(".*([';]+|(--)+).*", " ");
          return str.replaceAll("[';(--)]", " ");
    }
}
