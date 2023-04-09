package com.shun.service;

import com.shun.pojo.Teacher;
import com.shun.utils.QuestionUtils;
import com.shun.utils.TeacherUtils;

import java.util.Scanner;

public class TestService {
    public static Teacher teacher; // 记录当前登录的用户

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (teacher == null) {
            // 登陆
            login(scanner);
            // 出题
            while (true) {
                System.out.println(
                        "准备生成" + teacher.getType() + "数学题目，请输入生成题目数量（10-30，输入-1将退出当前用户，重新登录，0退出系统）：");
                String inputString = scanner.nextLine();
                if (inputString.startsWith("切换为")) {
                    if (inputString.length() == 5) {
                        String type = inputString.substring(3);
                        if ("小学".equals(type) || "初中".equals(type) || "高中".equals(type)) {
                            teacher.setType(type);
                        } else {
                            System.out.println("请输入小学、初中和高中三个选项中的一个");
                            continue;
                        }
                    } else {
                        System.out.println("输入有误");
                        continue;
                    }
                    System.out.println("切换成功");
                } else {
                    int numberOfQuestion;
                    try {
                        numberOfQuestion = Integer.parseInt(inputString);
                    } catch (NumberFormatException exception) {
                        System.out.println("输入格式错误");
                        continue;
                    }
                    if (numberOfQuestion == -1) {
                        System.out.println("=====退出登录=====");
                        teacher = null;
                        break;
                    } else if (numberOfQuestion == 0) {
                        System.out.println("=====退出系统=====");
                        return;
                    } else if (numberOfQuestion < 10 || numberOfQuestion > 30) {
                        System.out.println("数量不规范");
                        continue;
                    }
                    QuestionUtils.createQuestion(teacher.getUsername(), teacher.getType(), numberOfQuestion);
                }
            }
        }
    }

    // login
    public static void login(Scanner scanner) {
        System.out.println("=====登陆系统=====");
        System.out.println("请输入用户名和密码（以空格间隔）：");
        boolean flag = true;
        while (flag) {
            String infoInput = scanner.nextLine();
            String[] infos = infoInput.split(" ", 2);
            if (infos.length != 2) {
                System.out.println("请输入正确的用户名、密码");
                continue;
            }
            String type = TeacherUtils.verifyIdentity(infos[0], infos[1]);
            if (!"".equals(type)) {
                System.out.println("当前选择为" + type + "出题");
                teacher = new Teacher(infos[0], infos[1], type);
                flag = false;
            } else {
                System.out.println("请输入正确的用户名、密码");
            }
        }
    }
}
