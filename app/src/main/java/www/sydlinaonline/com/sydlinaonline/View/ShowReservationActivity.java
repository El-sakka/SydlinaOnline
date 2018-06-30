package www.sydlinaonline.com.sydlinaonline.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import www.sydlinaonline.com.sydlinaonline.Model.PharmacyInfo;
import www.sydlinaonline.com.sydlinaonline.Model.Reservation;
import www.sydlinaonline.com.sydlinaonline.R;

public class ShowReservationActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private String phramcyName;



    private static final String PHRMACY_KEY = "phrmacy" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reservation);
        init();
    }

    private void init(){
        Intent intent = getIntent();
        phramcyName = intent.getStringExtra(PHRMACY_KEY);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_show_reservaion);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Database")
                .child("Reservation");
        Query query = reference.orderByChild("phrmacyName").equalTo(phramcyName);
        FirebaseRecyclerAdapter<Reservation,myViewHolder> adapter = new FirebaseRecyclerAdapter<Reservation, myViewHolder>(
                Reservation.class,
                R.layout.custom_reservation_layout,
                ShowReservationActivity.myViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(final myViewHolder viewHolder, Reservation model, int position) {
                viewHolder.emailTextView.setText(model.getUserEmail());
                viewHolder.codeTextView.setText(model.getCode());
                viewHolder.quantityTextView.setText(model.getQuantity());
                viewHolder.locationTextView.setText(model.getLocation());
                viewHolder.dateTextView.setText(model.getDate());
                viewHolder.medicineTextView.setText(model.getMedName());

                viewHolder.done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteReservation(viewHolder.codeTextView.getText().toString());
                    }
                });

                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteReservation(viewHolder.codeTextView.getText().toString());
                    }
                });

            }
        };

        mRecyclerView.setAdapter(adapter);
    }




    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView emailTextView ;
        TextView codeTextView ;
        TextView quantityTextView ;
        TextView locationTextView ;
        TextView dateTextView ;
        TextView medicineTextView ;

        Button delete;
        Button done;

        public myViewHolder(View itemView) {
            super(itemView);

            emailTextView = (TextView) itemView.findViewById(R.id.tv_user_email);
            codeTextView = (TextView) itemView.findViewById(R.id.tv_user_code);
            quantityTextView = (TextView) itemView.findViewById(R.id.tv_user_quantity);
            locationTextView = (TextView) itemView.findViewById(R.id.tv_user_location);
            dateTextView = (TextView) itemView.findViewById(R.id.tv_user_date);
            medicineTextView =(TextView) itemView.findViewById(R.id.tv_user_medicine_name);

            delete = (Button)itemView.findViewById(R.id.btn_user_delete);
            done = (Button)itemView.findViewById(R.id.btn_user_done);


        }
    }

    private void deleteReservation(String code){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Database").child("Reservation");

        Query query = reference.orderByChild("code").equalTo(code);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    snapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
