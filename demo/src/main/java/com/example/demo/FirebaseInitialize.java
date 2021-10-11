package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class FirebaseInitialize {



    @PostConstruct
    public void initialize() {
//            FileInputStream serviceAccount = null;
        try {
            InputStream serviceAccount= this.getClass().getClassLoader().getResourceAsStream("./serviceAccountKey.json");
//            serviceAccount = new FileInputStream("./serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
//            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options );
//               System.out.println(app.getName());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirebase(){
        return FirestoreClient.getFirestore();
    }
}