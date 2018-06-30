package www.sydlinaonline.com.sydlinaonline.View;


import android.app.Dialog;
import android.content.Intent;
import android.media.MediaExtractor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import java.util.HashMap;
import java.util.List;

import www.sydlinaonline.com.sydlinaonline.FirebaseHelper.FirebaseHelper;
import www.sydlinaonline.com.sydlinaonline.Model.Category;
import www.sydlinaonline.com.sydlinaonline.Model.Medicine;
import www.sydlinaonline.com.sydlinaonline.Model.PharmacyAndMedicine;
import www.sydlinaonline.com.sydlinaonline.Model.PharmacyInfo;
import www.sydlinaonline.com.sydlinaonline.R;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * note remember to search using name and get the key
 */
public class MedicineFragment extends Fragment {


    // declare keys for map and for intent
    private static final String TAG = "MedicineFragment";
    private static final String PHARMACY_NAME = "pharmacy_name";
    private static final String PHARMACY_KEY = "pharmacy_key";
    private static final String MEDICINE_KEY = "medicine_key";
    private static final String MEDICINE_QUANTITY = "medicine_quantity";
    private static final String CHOOSE_KEY = "choose";





    // hash map for medicine key , and medinine quantity
    HashMap<String, String> map = new HashMap<>();

    //declare views to get text
    private EditText mMedicineName;
    private EditText mMedicineCategory;
    private EditText mMedicineQuantity;
    private EditText mMedicinePrice;
    private EditText mMedicineDiscription;
    private EditText mPharmacyName;
    private EditText mMedicineImage;
    private FloatingActionButton mMedicineDone;


    // database references for firebase
    DatabaseReference mDatabaseReferencePharmacy;
    DatabaseReference mDatabaseReferenceMedicine;
    DatabaseReference mDatabaseReferenceMedicinePharmacy;
    DatabaseReference mDatabaseReferenceCategory;


    String pharmacySearch;
    String pharmacyName;

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


        // specify which view to get its value
        mMedicineName = (EditText) getActivity().findViewById(R.id.et_medicine_name);
        mMedicineCategory = (EditText) getActivity().findViewById(R.id.et_category_name);
        mMedicineQuantity = (EditText) getActivity().findViewById(R.id.et_medicine_quantity);
        mMedicinePrice = (EditText) getActivity().findViewById(R.id.et_medicine_price);
        mMedicineDiscription = (EditText) getActivity().findViewById(R.id.et_medicine_description);
        mPharmacyName = (EditText) getActivity().findViewById(R.id.et_pharmacy_name);
        mMedicineImage = (EditText) getActivity().findViewById(R.id.et_medicine_image_url);
        mMedicineDone = (FloatingActionButton) getActivity().findViewById(R.id.btn_medicine_save);


        // identify intent to get data from it's prev intent
        Intent intent = getActivity().getIntent();
        String classType = intent.getStringExtra("Class");
        if(classType.equals("A")){
            pharmacyName = intent.getStringExtra(PHARMACY_NAME);
        }else if(classType.equals("B")){
            pharmacyName = intent.getStringExtra(CHOOSE_KEY);
        }
       // final String pharmacyKey = intent.getStringExtra(PHARMACY_KEY);



        mPharmacyName.setText(pharmacyName);
        pharmacySearch = pharmacyName;
        
        // tag for debugging
        Log.d(TAG, "onActivityCreated: pharmacy Name: " + pharmacyName);
/*
        // define ref to pharmacy node in firebase
        mDatabaseReferencePharmacy = FirebaseDatabase.getInstance().getReference()
                .child("Database").child("Pharmacy").child(pharmacySearch);*/
        // define ref for medicine node in firebase
        mDatabaseReferenceMedicine = FirebaseDatabase.getInstance().getReference()
                .child("Database").child("Medicine");
        // deine ref for medicine pharamcy node in firebase
        mDatabaseReferenceMedicinePharmacy = FirebaseDatabase.getInstance().getReference()
                .child("Database").child("PharamcyAndMedicine");
        // define ref for catefory node in firebase
        mDatabaseReferenceCategory = FirebaseDatabase.getInstance().getReference()
                .child("Database").child("Category");

        // when clicking on Done Button
        mMedicineDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: pressed");
                // check if medicine name is empty or not
                if (!TextUtils.isEmpty(mMedicineName.getText().toString())) {
                    // getting all text from Edit text (Views)
                    String medicineName = mMedicineName.getText().toString();
                    String medicinePrice = mMedicinePrice.getText().toString();
                    String medicineCategory = mMedicineCategory.getText().toString();
                    String medicineQuantity = mMedicineQuantity.getText().toString();
                    String medicineDescription = mMedicineDiscription.getText().toString();
                    String medicineImageUrl = mMedicineImage.getText().toString();

                    // get medicine key when adding new medicine
                    String medicineKey = mDatabaseReferenceMedicine.push().getKey();

                    // object from medicine model
                    Medicine medicineObj = new Medicine(medicineName, medicinePrice, medicineDescription,
                            medicineImageUrl, medicineKey,0);
                    // object from medicince and pharmacy model
                    PharmacyAndMedicine pharmacyAndMedicine = new PharmacyAndMedicine(pharmacyName, medicineName,medicineQuantity);

                    //set value in medinince node
                    mDatabaseReferenceMedicine.child(medicineName).setValue(medicineObj);
                    // set value in medince and phramacy node
                    mDatabaseReferenceMedicinePharmacy.push().setValue(pharmacyAndMedicine);
                    // set value in pharmacy node
                    //mDatabaseReferencePharmacy.child("ListMedicine").push().setValue(map);
                    // set value in category node
                    mDatabaseReferenceCategory.child(medicineCategory).push().setValue(medicineName);


                    Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();
                    clearData();

                } else {
                    Toast.makeText(getActivity(), "Medicine Name is Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void clearData(){
        mMedicineName.setText("");
        mMedicineDiscription.setText("");
        mMedicineQuantity.setText("");
        mMedicinePrice.setText("");
        mMedicineImage.setText("");
       // mMedicineCategory.setText("");
    }


}
