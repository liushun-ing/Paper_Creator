package com.shun.utils;

import com.shun.pojo.Teacher;

public class TeacherUtils {
    public static Teacher[] teachers;

    static {
        // 完成初始化工作
        teachers =
                new Teacher[]{
                        new Teacher("张三1", "123", "小学"),
                        new Teacher("张三2", "123", "小学"),
                        new Teacher("张三3", "123", "小学"),
                        new Teacher("李四1", "123", "初中"),
                        new Teacher("李四2", "123", "初中"),
                        new Teacher("李四3", "123", "初中"),
                        new Teacher("王五1", "123", "高中"),
                        new Teacher("王五2", "123", "高中"),
                        new Teacher("王五3", "123", "高中")
                };
    }

    /**
     * 身份验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 验证成功则返回用户类型，否则返回空串
     */
    public static String verifyIdentity(String username, String password) {
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(username) && teacher.getPassword().equals(password)) {
                return teacher.getType();
            }
        }
        return "";
    }
}
