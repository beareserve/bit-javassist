package com.uncleardiff;

/**
 * @param
 * @author ：by
 * @description：TODO
 * @date ：2021/11/7 17:07
 */
public class StringUtil {

    private int id;
    private String phone;

    private Student student = new Student();

    public String addString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (char) (i % 26 + 'a');
        }
        return result;
    }

    public String buildString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (i % 26 + 'a'));
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public StringUtil setId(int id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public StringUtil setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public StringUtil setStudent(Student student) {
        this.student = student;
        return this;
    }
}
