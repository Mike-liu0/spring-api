package com.example.demo.controller;

import com.example.demo.FirebaseInitialize;
import com.example.demo.service.UserService;
import com.example.demo.user.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "hello world! This is a default page";
    }

    @PostMapping("/users")
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.saveUser(user);
    }

    @GetMapping("/users/{name}")
    public User getUser(@PathVariable String name) throws ExecutionException, InterruptedException {
        return userService.getUserDetails(name);
    }


}



//    @GetMapping("/getUsers")
//    public List<User> getUsers() throws ExecutionException, InterruptedException {
//
//
//    }
//        List<User> userList = new ArrayList<>();
//        CollectionReference users = db.getFirebase().collection("users");
//        ApiFuture<QuerySnapshot> query = users.get();
//        for(DocumentSnapshot doc: query.get().getDocuments()){
//            User stu = doc.toObject(User.class);
//            userList.add(stu);
//        }
//        return userList;