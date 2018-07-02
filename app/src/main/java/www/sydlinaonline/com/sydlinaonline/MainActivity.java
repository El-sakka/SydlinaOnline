package www.sydlinaonline.com.sydlinaonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import www.sydlinaonline.com.sydlinaonline.View.ChoosePharmacy;

public class MainActivity extends AppCompatActivity {

    private TextView tvChoosePharmacy ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvChoosePharmacy = (TextView) findViewById(R.id.tv_choose_pharmacy);



        tvChoosePharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chooseIntent = new Intent(MainActivity.this, ChoosePharmacy.class);
                startActivity(chooseIntent);
            }
        });
    }
}
