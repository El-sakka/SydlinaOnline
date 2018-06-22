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
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import www.sydlinaonline.com.sydlinaonline.Model.PharmacyInfo;
import www.sydlinaonline.com.sydlinaonline.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePhamacyFragment extends Fragment {
    private static final String TAG = "CreatePhamacyFragment";


    private EditText mPharmacyNameEditText;
    private EditText mPharmacyPhoneEditText;
    private EditText mPharmacyAddressEditText;
    private FloatingActionButton fabNext;
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
        mPharmacyPhoneEditText = (EditText)getActivity().findViewById(R.id.ed_pharmacy_phone);
        mPharmacyAddressEditText = (EditText)getActivity().findViewById(R.id.ed_pharmacy_address);
        fabNext = (FloatingActionButton)getActivity().findViewById(R.id.fab_next);


        final DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Database").child("Pharmacy");
        final DatabaseReference keyRef = mDatabaseReference.push();

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mPharmacyAddressEditText.getText().toString())) {
                    Log.d(TAG, "onClick: pushing pharmacy data into firebase");
                    String pharmacyName = mPharmacyNameEditText.getText().toString().trim();
                    String pharmacyPhone = mPharmacyPhoneEditText.getText().toString().trim();
                    String pharmacyAddress = mPharmacyAddressEditText.getText().toString().trim();
                    String key = keyRef.getKey();

                    PharmacyInfo pharmacyInfo = new PharmacyInfo(pharmacyName, pharmacyPhone, pharmacyAddress, key);
                    mDatabaseReference.push().setValue(pharmacyInfo);

                    Toast.makeText(getActivity(),"Done",Toast.LENGTH_SHORT).show();

                    mPharmacyNameEditText.setText("");
                    mPharmacyPhoneEditText.setText("");
                    mPharmacyAddressEditText.setText("");


                    Intent intent = new Intent(getActivity(),MedicineActivity.class);
                    startActivity(intent);

                }else{
                    Log.d(TAG, "onClick: edit text field is emtpy");
                    Toast.makeText(getActivity(),"Pharamcy name is Empty",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
