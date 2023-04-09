package com.shun.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUtils {
    /**
     * 将题目写入文件，每个题目之间有空行
     *
     * @param username 用户名
     * @param type     试卷类型
     * @param question 问题的字符串
     * @return 写入是否成功的信息提示
     */
    public static String writeQuestion(String username, String type, String question) {
        String filePath = System.getProperty("user.dir") + "\\src\\com\\shun\\resources\\";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String fileName = sdf.format(date);
        filePath = filePath + username + "\\" + type + "\\" + fileName + ".txt";
        File file = new File(filePath);
        FileOutputStream fop;
        OutputStreamWriter writer;
        try {
            fop = new FileOutputStream(file);
            writer = new OutputStreamWriter(fop);
            writer.append(question);
            writer.close();
            fop.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "写入题目失败";
        }
        return "写入题目成功";
    }

    /**
     * 将题目的hashcode值写入文件 如果是同一份试卷，写在同一行，空格间开 如果是不同试卷，则换行写入
     */
    public static void writeHashcode(String username, String hashcode) {
        String filePath = System.getProperty("user.dir") + "\\src\\com\\shun\\resources\\";
        filePath = filePath + username + "\\hashcode.txt";
        File file = new File(filePath);
        FileOutputStream fop;
        OutputStreamWriter writer;
        try {
            fop = new FileOutputStream(file, true);
            writer = new OutputStreamWriter(fop);
            writer.append(hashcode);
            writer.close();
            fop.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取已有题目的所有hashcode
     *
     * @return 每份卷子的hashcode值的字符串的集合
     */
    public static ArrayList<String> readHashcode(String username) {
        String filePath = System.getProperty("user.dir") + "\\src\\com\\shun\\resources\\";
        filePath = filePath + username + "\\hashcode.txt";
        BufferedReader bf;
        ArrayList<String> hashCodes = new ArrayList<>();
        try {
            bf = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bf.readLine()) != null) {
                hashCodes.add(line);
            }
            bf.close();
        } catch (Exception e) {
            // do nothing
        }
        return hashCodes;
    }
}
