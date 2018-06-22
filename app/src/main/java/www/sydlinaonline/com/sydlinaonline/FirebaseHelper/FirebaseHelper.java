package www.sydlinaonline.com.sydlinaonline.FirebaseHelper;



/*
* firebase database helper class to get category name from firebase and
* add it to the spinner
* provideing some method like retriving data and check if data saved of not
*
* */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import www.sydlinaonline.com.sydlinaonline.Model.Category;

public class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";
    DatabaseReference db;
    Boolean saved = null;

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }


    public boolean save(Category category){
        if(category == null){
            saved = false;
        }
        else{
            try{
                Log.d(TAG, "save: category saved in firebase");
               // db.child("Database").child("Pharmacy").child("Category");
                db.push().setValue(category.getCategoryName());
                saved = true;
            }catch (DatabaseException e){
                Log.d(TAG, "save: error occured while saving category"+e.getMessage());
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }


    // read data from firebase
    public ArrayList<String>retrive(){

        Log.d(TAG, "retrive: ");
        final ArrayList<String> categories = new ArrayList<>();

        Log.d(TAG, "retrive: "+db.getKey());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               /* for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name = snapshot.getValue(String.class);
                    Log.i(TAG, "onDataChange: "+name);
                }*/
               fetchData(dataSnapshot,categories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchData(dataSnapshot,categories);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchData(dataSnapshot,categories);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        return categories;
    }


    private void fetchData(DataSnapshot snapshot , ArrayList<String> category){
        category.clear();
        for(DataSnapshot ds : snapshot.getChildren()){
            String name = ds.getValue(String.class);

            Log.d(TAG, "fetchData: "+name);

            category.add(name);
        }
    }
}
