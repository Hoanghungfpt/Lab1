package com.hunghhph44272.myapp123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {

    Context context=this;
    FirebaseFirestore database;
    String id="";
    ToDo todo=null;
    TextView tvKQ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKQ=findViewById(R.id.tvKQ);
        database=FirebaseFirestore.getInstance();//khoi tao
        //insertData();
        //updateData();
        selectData();
        //deleteData();
    }
    void insertData()
    {
        id = UUID.randomUUID().toString();
        todo = new ToDo(id,"title 1 04","content 1 04");
        HashMap<String,Object> mapToDo = todo.convertHashMap();
        database.collection("TODO").document(id).
                set(mapToDo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                });

    }
    void updateData(){
        id ="d6164f0b-3b4c-4d8d-84eb-46f7f61afed2";
        todo = new ToDo(id,"title update hung 1","content update hung 1");
        database.collection("TODO")
                .document(todo.getId())
                .update(todo.convertHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    void deleteData()
    {
        id ="d6164f0b-3b4c-4d8d-84eb-46f7f61afed2";
        database.collection("TODO")
                .document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    
    String strKQ = "";
    ArrayList<ToDo> selectData(){
        ArrayList<ToDo> list = new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            strKQ = "";
                            for (QueryDocumentSnapshot doc: task.getResult()){
                                ToDo t = doc.toObject(ToDo.class);
                                strKQ += "id: "+t.getId()+"\n";
                                strKQ += "title: "+t.getTitle()+"\n";
                                strKQ += "content: "+t.getContent()+"\n";
                                list.add(t);
                            }
                            Toast.makeText(context, "Đọc thành công", Toast.LENGTH_SHORT).show();
                            tvKQ.setText(strKQ);
                        }
                        else {
                            Toast.makeText(context, "Đo thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return  list;
    }
    
}