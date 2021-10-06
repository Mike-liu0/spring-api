package com.example.demo;


import com.example.demo.user.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Add the controller.
//	@RestController
//	class HelloWorldController {
//		@GetMapping("/")
//		public String hello() {
//			return "hello world! aasaa";
//		}
//	}

	@RestController
	class UserController {

		@Autowired
		FirebaseInitialize db;


		@GetMapping("/")
		public List<User> getUsers() throws ExecutionException, InterruptedException {
			List<User> userList = new ArrayList<>();
			CollectionReference users = db.getFirebase().collection("users");
			ApiFuture<QuerySnapshot> query = users.get();
			for(DocumentSnapshot doc: query.get().getDocuments()){
				User stu = doc.toObject(User.class);
				userList.add(stu);
			}
			return userList;

		}
	}


}
