package com.dadao.mvvm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author dadao on 4/24/21
 */
public class User implements Parcelable {
    String id;
    String name;
    String avatar;
    int age;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        avatar = in.readString();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAge() {
        return age;
    }

    public String getDisplayAge() {
        return age + "";
    }

    public boolean isFlower() {
        return age >= 40;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
