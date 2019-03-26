package com.domain;

import java.util.Objects;

/**
 *  @author  Reve
 *  用户信息表
 */
public class User {

    private int user_id ;
    private String telephone ;
    private String password ;
    private String nickname ;
    private String email ;
    private int sex ;    //  1:男  2:女
    private int age ;
    private String securities_account_id ; // 证券账户号码
    private int role ;          // 角色：操作权限

    public String getSecurities_account_id() {
        return securities_account_id;
    }

    public void setSecurities_account_id(String securities_account_id) {
        this.securities_account_id = securities_account_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id &&
                sex == user.sex &&
                age == user.age &&
                role == user.role &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(securities_account_id, user.securities_account_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user_id, telephone, password, nickname, email, sex, age, securities_account_id, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", securities_account_id='" + securities_account_id + '\'' +
                ", role=" + role +
                '}';
    }

    public User() {
        this.nickname = "";
        this.email = "";
        this.sex = 0;
        this.age = 18;
    }
}
