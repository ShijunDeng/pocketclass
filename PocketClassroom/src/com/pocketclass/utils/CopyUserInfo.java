package com.pocketclass.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.pocketclass.domain.User;
/**
 * 
 * 复制User
 * 
 * @author 邓仕军 wust_dengshijun@163.com
 * @version 1.0 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2012-11-01 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by dengshijun
 */
public class CopyUserInfo {
	/**
	 *当b的某个属性满足以下条件时： 将b 对应的属性的值赋给a对应的属性
	 *1.若该属性为非String类型，则改属性不能为空
	 *2.若该属性为String类型，则该属性不能为空，也不能为""
	 * @param a
	 * @param b
	 */
	public static void copy(User a,User b){
		try {
			BeanInfo beanInfo=Introspector.getBeanInfo(User.class,Object.class);
			PropertyDescriptor[]pds=beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pd:pds){
				Method f=pd.getReadMethod();
				try {
					Object value=f.invoke(b);
					if(value!=null){
						if(value instanceof String&&((String) value).trim().equals("")==false){
							BeanUtils.setProperty(a, pd.getName(),value);	
						}else if(value instanceof Date){
							BeanUtils.setProperty(a, pd.getName(), value);							
						}
						
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}	
	}
}
