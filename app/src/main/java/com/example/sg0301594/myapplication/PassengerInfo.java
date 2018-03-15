package com.example.sg0301594.myapplication;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.TextView;

public class PassengerInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_info);

        boolean male = getIntent().getBooleanExtra("male", false);
        boolean female = getIntent().getBooleanExtra("female", false);
        boolean kids = getIntent().getBooleanExtra("kids", false);

        CheckBox checkBoxMale = (CheckBox) findViewById(R.id.mencb);
        checkBoxMale.setChecked(male);

        CheckBox checkBoxFemale = (CheckBox) findViewById(R.id.womencb);
        checkBoxFemale.setChecked(female);

        CheckBox checkBoxKids = (CheckBox) findViewById(R.id.kidscb);
        checkBoxKids.setChecked(kids);

        final Button button = (Button) findViewById(R.id.contbt2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pnrIntent = getIntent();
                Intent intent = new Intent(PassengerInfo.this, MainActivity.class);
                intent.putExtras(pnrIntent);
                startActivity(intent);
            }
        });
    }
}

