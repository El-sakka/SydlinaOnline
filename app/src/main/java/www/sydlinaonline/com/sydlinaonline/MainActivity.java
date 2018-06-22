package www.sydlinaonline.com.sydlinaonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import www.sydlinaonline.com.sydlinaonline.View.ChoosePharmacy;
import www.sydlinaonline.com.sydlinaonline.View.CreatePharmacy;

public class MainActivity extends AppCompatActivity {

    private TextView tvCreatePharmacy ;
    private TextView tvChoosePharmacy ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCreatePharmacy = (TextView) findViewById(R.id.tv_create_pharmacy);
        tvChoosePharmacy = (TextView) findViewById(R.id.tv_choose_pharmacy);


        tvCreatePharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainActivity.this, CreatePharmacy.class);
                startActivity(createIntent);
            }
        });

        tvChoosePharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chooseIntent = new Intent(MainActivity.this, ChoosePharmacy.class);
                startActivity(chooseIntent);
            }
        });
    }
}
