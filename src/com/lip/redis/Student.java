package com.lip.redis;

import java.io.Serializable;

/**
 * Created by Lip on 2016/7/19 0019.
 */
public class Student implements Serializable {
    private String name;
    private String phone;
    private int age;
    public Student(String _name)
    {
        this.name=_name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
