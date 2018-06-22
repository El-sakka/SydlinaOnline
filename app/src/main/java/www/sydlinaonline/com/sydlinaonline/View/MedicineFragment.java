package www.sydlinaonline.com.sydlinaonline.View;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import www.sydlinaonline.com.sydlinaonline.FirebaseHelper.FirebaseHelper;
import www.sydlinaonline.com.sydlinaonline.Model.Category;
import www.sydlinaonline.com.sydlinaonline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineFragment extends Fragment {

    private static final String TAG = "MedicineFragment";
    private ArrayList<String> categories = new ArrayList<>();
    List<String> s = new ArrayList<>();
    private ArrayAdapter<String>categoryAdapter;
    private TextView mCategoryTextView;


    DatabaseReference mDatabaseReference;
    FirebaseHelper mFirebaseHelper;
    Button addButton;

    public MedicineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        categories = new ArrayList<>();
        final Spinner spinner = (Spinner)getActivity().findViewById(R.id.spn_category);
        addButton = (Button)getActivity().findViewById(R.id.btn_add_category);



        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Database").child("Pharmacy").child("Category");
        mFirebaseHelper = new FirebaseHelper(mDatabaseReference);
        //categories = mFirebaseHelper.retrive();

        /*mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(TAG, "onDataChange: ");
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name = snapshot.getValue(String.class);
                    categories.add(name);
                    s.add(name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        categoryAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(categoryAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });

        Log.d(TAG, "onActivityCreated: "+spinner.getCount() + "Arraylist Size : "+categories.size());
    }



    //dispaly input Dialog
    private void displayInputDialog(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("Add Category");
        dialog.setContentView(R.layout.category_dialog);

        final EditText nameEditText = (EditText)dialog.findViewById(R.id.et_category_name);
        Button saveBtn = (Button)dialog.findViewById(R.id.btn_category_saved);



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();

                Category category = new Category();
                category.setCategoryName(name);
                Log.d("A7a1", "onClick: ");

                if(name != null && name.length()>0){
                    if(mFirebaseHelper.save(category)){
                        nameEditText.setText("");
                        Log.d("A7a2", "onClick: ");
                    }
                }else{
                    Toast.makeText(getActivity(),"Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    Log.d("A7a3", "onClick: ");
                }
            }
        });
        dialog.show();
    }
}
