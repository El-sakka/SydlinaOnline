package www.sydlinaonline.com.sydlinaonline.View;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import www.sydlinaonline.com.sydlinaonline.Model.PharmacyInfo;
import www.sydlinaonline.com.sydlinaonline.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoosePharmacyFragment extends Fragment {

    private static final String TAG = "ChoosePharmacyFragment";

    RecyclerView mRecyclerView;
    DatabaseReference mRef;


    public ChoosePharmacyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_pharmacy, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView)getActivity().findViewById(R.id.rv_pharmacy);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRef = FirebaseDatabase.getInstance().getReference().child("Database").child("Pharmacy");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: "+snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
