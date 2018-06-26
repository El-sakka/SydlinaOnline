package www.sydlinaonline.com.sydlinaonline.View;


import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import www.sydlinaonline.com.sydlinaonline.Model.PharmacyInfo;
import www.sydlinaonline.com.sydlinaonline.R;
public class ChoosePharmacyFragment extends Fragment {

    private static final String TAG = "ChoosePharmacyFragment";
    private static final String PHRMACY_MODEL = "pharmcy_model";
    private static final String SET_CLASS = "set_class";

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

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_pharmacy);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRef = FirebaseDatabase.getInstance().getReference().child("Database").child("Pharmacy");
        mRef.keepSynced(true);


        final FirebaseRecyclerAdapter<PharmacyInfo,myViewHolder> adapter = new FirebaseRecyclerAdapter<PharmacyInfo, myViewHolder>(
                PharmacyInfo.class,
                R.layout.pharmacy_custom,
                myViewHolder.class,
                mRef
        ) {
            @Override
            protected void populateViewHolder(myViewHolder viewHolder, final PharmacyInfo model, final int position) {
                viewHolder.phrmacyNameTextView.setText(model.getPharmacyName());
                viewHolder.phrmacyPhoneTextView.setText(model.getPharmacyPhone());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "you Clicked on: "+position);
                        Toast.makeText(getActivity(),"Item #"+model.getPharmacyName(),Toast.LENGTH_SHORT).show();

                        directToCreatePharmacy(model);
                    }
                });
            }
        };

        mRecyclerView.setAdapter(adapter);


    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public TextView phrmacyNameTextView;
        public TextView phrmacyPhoneTextView;
        public myViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            phrmacyNameTextView = (TextView)itemView.findViewById(R.id.tv_retrice_pharmacy_name);
            phrmacyPhoneTextView = (TextView)itemView.findViewById(R.id.tv_retrice_pharmacy_phone);
        }

    }

    private void directToCreatePharmacy(PharmacyInfo model){
        Intent intent = new Intent(getActivity(),CreatePharmacy.class);

        intent.putExtra(PHRMACY_MODEL,model);
        intent.putExtra(SET_CLASS,"choose");
        startActivity(intent);
    }


}
