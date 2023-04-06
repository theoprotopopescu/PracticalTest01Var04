package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    Button buttonNavigateToSecondaryActivity, buttonDisplayInfo;
    TextView resultText;
    CheckBox checkBoxName, checkBoxGroup;
    EditText nameText, groupText;

    private String name = "";
    private String group = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        buttonNavigateToSecondaryActivity = findViewById(R.id.buttonNavigate);
        buttonDisplayInfo = findViewById(R.id.buttonDisplayInformation);
        resultText = findViewById(R.id.textView);
        checkBoxName = findViewById(R.id.checkBoxName);
        checkBoxGroup = findViewById(R.id.checkBoxGroup);
        nameText = findViewById(R.id.edit_text_name);
        groupText = findViewById(R.id.edit_text_group);


        buttonDisplayInfo.setOnClickListener(view -> {
            if (checkBoxName.isChecked()) {
                name = nameText.getText().toString();
            } else {
                name = "";
            }

            if (checkBoxGroup.isChecked()) {
                group = groupText.getText().toString();
            } else {
                group = "";
            }

            resultText.setText(name + " " + group);
        });

    }

/*    public void navigateToSecondaryActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("group", group);
        startActivityForResult(intent, 1);
    }*/


}