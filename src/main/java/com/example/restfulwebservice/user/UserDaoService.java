package com.example.restfulwebservice.user;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"hwang",new Date()));
        users.add(new User(2,"Kim",new Date()));
        users.add(new User(3,"Park",new Date()));
    }

    private static int usersCount = users.size();

    public List<User> findAll() {
        System.out.println(users);
        return users;
    }

    public User save(User user){

        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(Integer id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }

        return null;
    }
}
