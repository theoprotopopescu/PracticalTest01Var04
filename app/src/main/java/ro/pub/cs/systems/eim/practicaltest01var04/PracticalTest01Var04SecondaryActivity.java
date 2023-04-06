package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    Button okButton, cancelButton;
    TextView resultTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        okButton = findViewById(R.id.buttonOk);
        cancelButton = findViewById(R.id.buttonCancel);
        resultTextView = findViewById(R.id.textView);

        resultTextView.setText(getIntent().getStringExtra("name") + " " +
                getIntent().getStringExtra("group"));

        okButton.setOnClickListener(view -> {
            setResult(RESULT_OK, null);
            finish();
        });

        cancelButton.setOnClickListener(view -> {
            setResult(RESULT_CANCELED, null);
            finish();
        });

    }
}