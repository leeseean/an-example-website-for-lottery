package com.mh.commons.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
  
/**
 * excel导出时标题显示的名字，如果没有设置Annotation属性，将不会被导出和导入 
 * @author Victor.Chen
 *
 */
@Documented  //这个Annotation可以被写入javadoc   
@Inherited   //这个Annotation 可以被继承 
@Retention(RetentionPolicy.RUNTIME)   
@Target(ElementType.FIELD)   
public @interface ExcelAnnotation {
	
	/** 与excel标题头对应  */
    public String exportName();
    
    /** 转换格式, 如时间类型 yyyy-MM-dd HH:mm:ss */
    public String pattern() default ""; 
    
    /** 在excel中位置 */
    public int order() default 0;
}  