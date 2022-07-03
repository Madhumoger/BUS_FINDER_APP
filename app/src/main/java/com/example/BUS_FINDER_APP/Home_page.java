package com.example.BUS_FINDER_APP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home_page extends AppCompatActivity {
    TextView txtFullName,txtEmail,txtMobile;
    String rtvFullName,rtvEmail,rtvMobile,loggedEmail;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        txtFullName=findViewById(R.id.rtvFullName);
        txtEmail=findViewById(R.id.rtvEmail);
        txtMobile=findViewById(R.id.rtvMobile);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
//        if(mAuth.getCurrentUser()!=null){
//            loggedEmail=mAuth.getCurrentUser().getEmail();
//        }else{
//            Toast.makeText(Home_page.this, "Error=No user found!", Toast.LENGTH_SHORT).show();
//        }
//        db.collection("users").document(loggedEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()){
//                    DocumentSnapshot documentSnapshot =task.getResult();
//                    if (documentSnapshot != null && documentSnapshot.exists()) {
//                        rtvFullName=documentSnapshot.getString("FullName");
//                        rtvEmail=documentSnapshot.getString("Email");
//                        rtvMobile=documentSnapshot.getString("MobileNumber");
//                        System.out.println(rtvFullName+"Xxxxxxx88888888888888888888888888888888888888888888888888888");
//                        txtFullName.setText(rtvFullName);
//                        txtEmail.setText(rtvEmail);
//                        txtMobile.setText(rtvMobile);
//                        Toast.makeText(Home_page.this, "Data added scuesfully", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Home_page.this, "Error="+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
        if (mAuth.getCurrentUser() != null) {
            loggedEmail = mAuth.getCurrentUser().getEmail();
        } else {
            Toast.makeText(Home_page.this, "Error=No user found!", Toast.LENGTH_SHORT).show();
        }

        db.collection("users")
                .document(loggedEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                rtvFullName = documentSnapshot.getString("FullName");
                                rtvEmail = documentSnapshot.getString("Email");
                                rtvMobile = documentSnapshot.getString("MobileNumber");

                                txtFullName.setText(rtvFullName);
                                txtEmail.setText(rtvEmail);
                                txtMobile.setText(rtvMobile);
                                Toast.makeText(Home_page.this, "Data added scuesfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Home_page.this, "Error="+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}