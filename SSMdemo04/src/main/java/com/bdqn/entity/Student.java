package com.bdqn.entity;

/**
 * Created by Administrator on 2017/12/13/013.
 */
public class Student {
    /*
        id int(11) NOT NULL学员id
        grade_id int(11) NULL班级id
        student_name varchar(10) NULL学生姓名
        gender varchar(2) NULL性别
        age int(11) NULL年龄
        student_num varchar(20) NULL学号
    */
    private Integer id;           //学员id
    private String studentName;  //学生姓名
    private String gender;       //性别
    private Integer age;         //年龄
    private String studentNum;  //学号

    private Grade grade;        //年级属性  与学生表进行关联查询

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", studentNum='" + studentNum + '\'' +
                ", grade=" + grade +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
