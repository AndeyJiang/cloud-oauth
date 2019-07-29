package com.andey;

import com.andey.config.utils.Md5PasswordEncoder;

/**
 * Created by jiangbin on 2018/12/3.
 */
public class test1{

    public  static  void  main(String[] args){
        Md5PasswordEncoder md5PasswordEncoder=new Md5PasswordEncoder();
        md5PasswordEncoder.encode("3333");
        System.out.println( md5PasswordEncoder.encode("mmyy12345"));
        System.out.println(md5PasswordEncoder.matches("mmyy12345","a141c47927929bc2d1fb6d336a256df4"));

    }


}
