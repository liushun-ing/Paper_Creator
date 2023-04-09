package com.shun.utils;

import java.util.Random;

public class QuestionUtils {
    private static final Random random = new Random();

    /**
     * 根据类型和数目，完成题目的生成
     * 同时，进行查重，并写入文件
     *
     * @param username 用户名
     * @param type     题目类型
     * @param number   题目个数
     */
    public static void createQuestion(String username, String type, int number) {
        StringBuilder question = new StringBuilder();
        if ("小学".equals(type)) {
            for (int i = 0; i < number; i++) {
                StringBuilder temp = new StringBuilder();
                temp.append(i + 1).append('、').append(createOnePrimaryQuestion())
                        .append("\r\n").append("\r\n");
                if (CheckUtils.checkRepeated(username, temp.toString().hashCode() + "")) {
                    i--; // 重新产生一次题目
                    continue;
                }
                question.append(temp); // 不重复，题目才生效
                FileUtils.writeHashcode(username, question.toString().hashCode() + " ");
            }
            FileUtils.writeHashcode(username, "\r\n"); // 不同试卷之间换行隔开
            System.out.println(FileUtils.writeQuestion(username, type, question.toString()));
        } else if ("初中".equals(type)) {
            for (int i = 0; i < number; i++) {
                StringBuilder temp = new StringBuilder();
                temp.append(i + 1).append('、').append(createOneJuniorQuestion())
                        .append("\r\n").append("\r\n");
                if (CheckUtils.checkRepeated(username, temp.toString().hashCode() + "")) {
                    i--;
                    continue;
                }
                question.append(temp);
                FileUtils.writeHashcode(username, question.toString().hashCode() + " ");
            }
            FileUtils.writeHashcode(username, "\r\n");
            System.out.println(FileUtils.writeQuestion(username, type, question.toString()));
        } else {
            for (int i = 0; i < number; i++) {
                StringBuilder temp = new StringBuilder();
                temp.append(i + 1).append('、').append(createOneSeniorQuestion())
                        .append("\r\n").append("\r\n");
                if (CheckUtils.checkRepeated(username, temp.toString().hashCode() + "")) {
                    i--;
                    continue;
                }
                question.append(temp);
                FileUtils.writeHashcode(username, question.toString().hashCode() + " ");
            }
            FileUtils.writeHashcode(username, "\r\n");
            System.out.println(FileUtils.writeQuestion(username, type, question.toString()));
        }
    }

    /**
     * 产生一个小学题目
     *
     * @return 题目的字符串形式
     */
    public static String createOnePrimaryQuestion() {
        int operationNumber = random.nextInt(4) + 2; // 操作数个数：2-5
        StringBuilder questionBody = new StringBuilder();
        boolean brackets = random.nextBoolean(); // 是否添加括号
        int leftBrackets = 0; // 0代表括号无效
        int rightBrackets = 0;
        if (brackets) { // 如果添加括号，根据随机数获得括号添加位置
            leftBrackets = random.nextInt(operationNumber) + 1;
            rightBrackets = random.nextInt(operationNumber) + 1;
            while (leftBrackets == rightBrackets) {
                rightBrackets = random.nextInt(operationNumber) + 1;
            }
            if (leftBrackets > rightBrackets) {
                int temp = leftBrackets;
                leftBrackets = rightBrackets;
                rightBrackets = temp;
            }
            if (leftBrackets == 1 && rightBrackets == operationNumber) {
                leftBrackets = 0;
                rightBrackets = 0;
            }
        }
        for (int i = 1; i <= operationNumber; i++) {
            if (leftBrackets == i) {
                questionBody.append('(');
            }
            questionBody.append(random.nextInt(100) + 1); // 添加操作数
            if (rightBrackets == i) {
                questionBody.append(')');
            }
            if (i == operationNumber) { // 最后一个不用再添加操作符了
                break;
            }
            int chooseOperator = random.nextInt(4) + 1; // 添加操作符
            switch (chooseOperator) {
                case 1 -> questionBody.append('+');
                case 2 -> questionBody.append('-');
                case 3 -> questionBody.append('*');
                case 4 -> questionBody.append('/');
                default -> {
                }
            }
        }
        return questionBody + "=";
    }

    /**
     * 生成一个初中题目
     *
     * @return 题目的字符串形式
     */
    public static String createOneJuniorQuestion() {
        int operationNumber = random.nextInt(4) + 2;
        StringBuilder questionBody = new StringBuilder();
        // squareOrExtract 处理平方与开方的选择与添加 0-平方 1-开方 2-都有
        int squareOrExtract = random.nextInt(3);
        int leftSquare = 0; // 0代表括号无效
        int rightSquare = 0;
        if (squareOrExtract == 0 || squareOrExtract == 2) {
            leftSquare = random.nextInt(operationNumber) + 1;
            rightSquare = random.nextInt(operationNumber) + 1;
            while (leftSquare == rightSquare) {
                rightSquare = random.nextInt(operationNumber) + 1;
            }
            if (leftSquare > rightSquare) {
                int temp = leftSquare;
                leftSquare = rightSquare;
                rightSquare = temp;
            }
        }
        for (int i = 1; i <= operationNumber; i++) {
            if (leftSquare == i) {
                questionBody.append('(');
            }
            if (squareOrExtract == 1 || squareOrExtract == 2) { // 有开方运算
                if (i == 1) {
                    questionBody.append(getExtract());
                } else {
                    if (random.nextBoolean()) {
                        questionBody.append(getExtract());
                    } else {
                        questionBody.append(random.nextInt(100) + 1);
                    }
                }
            } else {
                questionBody.append(random.nextInt(100) + 1);
            }
            if (rightSquare == i) {
                questionBody.append(")^2");
            }
            if (i == operationNumber) {
                break;
            }
            int chooseOperator = random.nextInt(4) + 1;
            switch (chooseOperator) {
                case 1 -> questionBody.append('+');
                case 2 -> questionBody.append('-');
                case 3 -> questionBody.append('*');
                case 4 -> questionBody.append('/');
                default -> {
                }
            }
        }
        return questionBody + "=";
    }

    /**
     * 生成一个高中题目
     *
     * @return 题目的字符串形式
     */
    public static String createOneSeniorQuestion() {
        // 只需要在初中题目的基础上做修改即可
        // 原本是数字或者开方的操作数，随机变换为三角函数运算
        int operationNumber = random.nextInt(4) + 2;
        StringBuilder questionBody = new StringBuilder();
        int squareOrExtract = random.nextInt(3);
        int leftSquare = 0;
        int rightSquare = 0;
        if (squareOrExtract == 0 || squareOrExtract == 2) {
            leftSquare = random.nextInt(operationNumber) + 1;
            rightSquare = random.nextInt(operationNumber) + 1;
            while (leftSquare == rightSquare) {
                rightSquare = random.nextInt(operationNumber) + 1;
            }
            if (leftSquare > rightSquare) {
                int temp = leftSquare;
                leftSquare = rightSquare;
                rightSquare = temp;
            }
        }
        for (int i = 1; i <= operationNumber; i++) {
            if (leftSquare == i) {
                questionBody.append('(');
            }
            if (squareOrExtract == 0) { // 不存在开方，两者之间随机选择
                if (i == 1) {
                    questionBody.append(getTrigonometric());
                } else {
                    if (random.nextBoolean()) {
                        questionBody.append(getTrigonometric());
                    } else {
                        questionBody.append(random.nextInt(100) + 1);
                    }
                }
            } else { // 存在开方，则三者之间选择
                if (i == 1) {
                    questionBody.append(getExtract());
                } else if (i == operationNumber) {
                    questionBody.append(getTrigonometric());
                } else {
                    int temp = random.nextInt(4);
                    if (temp < 2) {
                        questionBody.append(getTrigonometric());
                    } else if (temp == 2) {
                        questionBody.append(getExtract());
                    } else {
                        questionBody.append(random.nextInt(100) + 1);
                    }
                }
            }
            if (rightSquare == i) {
                questionBody.append(")^2");
            }
            if (i == operationNumber) {
                break;
            }
            int chooseOperator = random.nextInt(4) + 1;
            switch (chooseOperator) {
                case 1 -> questionBody.append('+');
                case 2 -> questionBody.append('-');
                case 3 -> questionBody.append('*');
                case 4 -> questionBody.append('/');
                default -> {
                }
            }
        }
        return questionBody + "=";
    }

    /**
     * 生成一个包含根号运算的字符串
     *
     * @return 包含三角函数的操作数的字符串形式
     */
    public static String getExtract() {
        /*
        在√2, √3, √5, √6, √7的基础上乘以一个平方数，作为最终的操作数
         */
        int baseNumber = random.nextInt(5); // 按顺序依次匹配上面的数字
        int base = 2;
        switch (baseNumber) {
            case 0 -> base = 2;
            case 1 -> base = 3;
            case 2 -> base = 5;
            case 3 -> base = 6;
            case 4 -> base = 7;
            default -> {
            }
        }
        int multiple = 100 / base; // 保证最终的数在100以内
        int maxNum = (int) (Math.sqrt(multiple) - 0.5); // 找到multiple以内的最大平方数
        int factor = random.nextInt(maxNum) + 1;
        int finalNum = factor * factor * base;
        return "√" + finalNum;
    }

    /**
     * 生成一个包含三角函数运算的字符串，作为操作数的替换选择
     *
     * @return 包含三角函数的操作数的字符串形式
     */
    public static String getTrigonometric() {
        // 基础运算为1/6Pi 1/4Pi 1/3Pi 1/2Pi（正或负）
        // 总的范围为(-2PI, 0),(0, 2Pi)
        // 通过随机数决定是否改变：随机数作为新的分子，然后再约分
        Random random = new Random();
        int baseNumber = random.nextInt(4); // 0-1/6,1-1/4,2-1/3,3-1/2
        int denominator; // 分母
        int molecule; // 分子
        if (baseNumber == 0) {
            denominator = 6;
            molecule = random.nextInt(denominator * 2 - 1) + 1;
            if (molecule % 6 == 0) {
                molecule /= 6;
                denominator /= 6;
            } else if (molecule % 3 == 0) {
                molecule /= 3;
                denominator /= 3;
            } else if (molecule % 2 == 0) {
                molecule /= 2;
                denominator /= 2;
            }
        } else if (baseNumber == 1) {
            denominator = 4;
            molecule = random.nextInt(denominator * 2 - 1) + 1;
            if (molecule % 4 == 0) {
                molecule /= 4;
                denominator /= 4;
            } else if (molecule % 2 == 0) {
                molecule /= 2;
                denominator /= 2;
            }
        } else if (baseNumber == 2) {
            denominator = 3;
            molecule = random.nextInt(denominator * 2 - 1) + 1;
            if (molecule % 3 == 0) {
                molecule /= 3;
                denominator /= 3;
            }
        } else {
            denominator = 2;
            molecule = random.nextInt(denominator * 2 - 1) + 1;
            if (molecule % 2 == 0) {
                molecule /= 2;
                denominator /= 2;
            }
        }
        // 拼接各个部分
        StringBuilder trigonometricBody = new StringBuilder();
        int chooseFunction = random.nextInt(3); // 选择三角函数 0-sin, 1-cos, 2-tan
        if (chooseFunction == 0) {
            trigonometricBody.append("sin(");
        } else if (chooseFunction == 1) {
            trigonometricBody.append("cos(");
        } else {
            trigonometricBody.append("tan(");
        }
        boolean positiveOrNegative = random.nextBoolean(); // true-正数，false-负数
        if (!positiveOrNegative) {
            trigonometricBody.append('-');
        }
        if (molecule == denominator) {
            trigonometricBody.append("Pi");
        } else {
            trigonometricBody.append(molecule);
            trigonometricBody.append('/');
            trigonometricBody.append(denominator);
            trigonometricBody.append("Pi");
        }
        trigonometricBody.append(')');
        // 去除tan不合理情况
        if (chooseFunction == 2) {
            if ((denominator == 2 && molecule == 1) || (denominator == 2 && molecule == 3)) {
                int index = trigonometricBody.indexOf("/2");
                trigonometricBody.replace(index - 1, index + 2, "1/3");
            }
        }
        // 去除结果为零的情况
        if (molecule == denominator) {
            int index = trigonometricBody.indexOf("Pi");
            trigonometricBody.insert(index, "1/3");
        }
        return trigonometricBody.toString();
    }
}
