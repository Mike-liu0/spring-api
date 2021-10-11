package com.example.demo.service;

import com.example.demo.user.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(COLLECTION_NAME).document(user.getName()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public User getUserDetails(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> future = dbFirestore.collection(COLLECTION_NAME).document(name).get();

        DocumentSnapshot document = future.get();

        if(document.exists()){
            User user = document.toObject(User.class);
            return user;

        }else {
            return null;
        }
    }

}
