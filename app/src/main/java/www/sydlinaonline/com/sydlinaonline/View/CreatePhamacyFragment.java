package www.sydlinaonline.com.sydlinaonline.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import www.sydlinaonline.com.sydlinaonline.MainActivity;
import www.sydlinaonline.com.sydlinaonline.Map.SetLocationActivity;
import www.sydlinaonline.com.sydlinaonline.Model.PharmacyInfo;
import www.sydlinaonline.com.sydlinaonline.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePhamacyFragment extends Fragment {
    private static final String TAG = "CreatePhamacyFragment";
    private static final String PHARMACY_KEY = "pharmacy_key";
    private static final String PHARMACY_NAME = "pharmacy_name";
    private static final String LATITUDE_KEY = "latitude";
    private static final String LANGITUDE_KEY = "longitude";
    private static final String NAME_KEY = "name";

    private EditText mPharmacyNameEditText;
    private EditText mPharmacyPhoneEditText;
    private EditText mPharmacylatEditText;
    private EditText mPharmacylanEditText;
    private Button mSetLocationBtn;


    private FloatingActionButton fabNext;
    private FloatingActionButton fabDone;
    //private DatabaseReference mDatabaseReference;


    public CreatePhamacyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_phamacy, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPharmacyNameEditText = (EditText) getActivity().findViewById(R.id.ed_pharmacy_name);
        mPharmacyPhoneEditText = (EditText) getActivity().findViewById(R.id.ed_pharmacy_phone);
        mPharmacylatEditText = (EditText) getActivity().findViewById(R.id.et_lat);
        mPharmacylanEditText = (EditText) getActivity().findViewById(R.id.et_lan);
        mSetLocationBtn = (Button) getActivity().findViewById(R.id.btn_setLocation);

        fabNext = (FloatingActionButton) getActivity().findViewById(R.id.fab_next);
        fabDone = (FloatingActionButton) getActivity().findViewById(R.id.fab_done_phrmacy);


        /*
         * check if intent comes from set location Activity
         * */
        // get lat,lan from location Activity
        Intent intent = getActivity().getIntent();

        double lat = intent.getDoubleExtra(LATITUDE_KEY, 0.0);
        double lng = intent.getDoubleExtra(LANGITUDE_KEY, 0.0);
        String name = intent.getStringExtra(NAME_KEY);
        Log.d(TAG, "onActivityCreated: lat:" + lat + " lng: " + lng);
        // set values to editTexts
        mPharmacylatEditText.setText(String.valueOf(lat));
        mPharmacylanEditText.setText(String.valueOf(lng));
        mPharmacyNameEditText.setText(name);





        final DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Database").child("Pharmacy");

        mSetLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToLocation(mPharmacyNameEditText.getText().toString());
            }
        });

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mPharmacyNameEditText.getText().toString())) {
                    Log.d(TAG, "onClick: pushing pharmacy data into firebase");
                    String pharmacyName = mPharmacyNameEditText.getText().toString();
                    String pharmacyPhone = mPharmacyPhoneEditText.getText().toString();
                    String pharmacylat = mPharmacylatEditText.getText().toString();
                    String pharmacylan = mPharmacylanEditText.getText().toString();

                    String pharmacyKey = mDatabaseReference.push().getKey();

                    PharmacyInfo pharmacy = new PharmacyInfo(pharmacyName, pharmacyPhone, pharmacylat, pharmacylan, pharmacyKey);
                    mDatabaseReference.child(pharmacyName).setValue(pharmacy);

                    Intent intent = new Intent(getActivity(), MedicineActivity.class);
                    intent.putExtra("Class","A");
                    intent.putExtra(PHARMACY_NAME, pharmacyName);
                    startActivity(intent);

                } else {
                    Log.d(TAG, "onClick: edit text field is emtpy");
                    Toast.makeText(getActivity(), "Pharamcy name is Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mPharmacyNameEditText.getText().toString())) {
                    Log.d(TAG, "onClick: pushing pharmacy data into firebase");
                    String pharmacyName = mPharmacyNameEditText.getText().toString();
                    String pharmacyPhone = mPharmacyPhoneEditText.getText().toString();
                    String pharmacylat = mPharmacylatEditText.getText().toString();
                    String pharmacylan = mPharmacylanEditText.getText().toString();

                    String pharmacyKey = mDatabaseReference.push().getKey();

                    PharmacyInfo pharmacy = new PharmacyInfo(pharmacyName, pharmacyPhone, pharmacylat, pharmacylan, pharmacyKey);
                    mDatabaseReference.child(pharmacyName).setValue(pharmacy);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                } else {
                    Log.d(TAG, "onClick: edit text field is emtpy");
                    Toast.makeText(getActivity(), "Pharamcy name is Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void directToLocation(String phrmacyName) {
        Intent intent = new Intent(getActivity(), SetLocationActivity.class);
        intent.putExtra(NAME_KEY,phrmacyName);
        startActivity(intent);
    }


}
