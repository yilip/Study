package com.lip.uitl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Lip
 * @Date 2016-10-28 17:09
 */
public class FileUtil {
    public static void main(String[] args)throws Exception {
        List<Map<String,Object>>list=new ArrayList<>();
        for(int i=0;i<10;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId","lip");
            map.put("data",Math.random());
            list.add(map);
        }
        File file=new File("c:/temp/data.log");
        if(!file.exists())
        {
            file.createNewFile();
        }
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  date=format.format(new Date());
        FileWriter fileWriter=new FileWriter(file);
        for(Map<String,Object>map:list)
        {
            String str=map.get("userId")+","+map.get("data")+","+date+"\r\n";
            fileWriter.write(str);
        }
        fileWriter.close();

        System.out.println(date);
    }
}
