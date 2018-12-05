package com.andey;

import com.andey.config.utils.Md5PasswordEncoder;

/**
 * Created by jiangbin on 2018/12/3.
 */
public class test1{

    public  static  void  main(String[] args){
        Md5PasswordEncoder md5PasswordEncoder=new Md5PasswordEncoder();
        md5PasswordEncoder.encode("3333");
        System.out.print("你好");
        System.out.print(md5PasswordEncoder.matches("mmyy12345","25491e587d30a04c6117e1e0c1125574"));

    }


}
