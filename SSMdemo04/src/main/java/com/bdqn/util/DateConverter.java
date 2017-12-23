package com.bdqn.util;

/**
 * Created by Administrator on 2017/12/12/012.
 */

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
   /*
   	由于日期数据有很多种格式，所以springmvc没办法把字符串转换成日期类型。
   	所以需要自定义参数绑定。前端控制器接收到请求后，找到注解形式的处理器适配器，
   	对RequestMapping标记的方法进行适配，并对方法中的形参进行参数绑定。
   	在springmvc这可以在处理器适配器上自定义Converter进行参数绑定。
   	如果使用<mvc:annotation-driven/>可以在此标签上进行扩展。
   */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}