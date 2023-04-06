package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    Button buttonNavigateToSecondaryActivity, buttonDisplayInfo;
    TextView resultText;
    CheckBox checkBoxName, checkBoxGroup;
    EditText nameText, groupText;

    private String name = "";
    private String group = "";

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();

    private class MessageBroadcastReceiver extends android.content.BroadcastReceiver {
        @Override
        public void onReceive(android.content.Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_EXTRA, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

    private void startPracticalTestService() {
        if (name != null && group != null) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
            intent.putExtra("name", name);
            intent.putExtra("group", group);
            getApplicationContext().startService(intent);
        }
    }

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
                if (name.isEmpty()) {
                    Toast.makeText(this, "Name value is incorrect!", Toast.LENGTH_SHORT).show();
                }
            } else {
                name = "";
            }

            if (checkBoxGroup.isChecked()) {
                group = groupText.getText().toString();
                if (group.isEmpty()) {
                    Toast.makeText(this, "Group value is incorrect!", Toast.LENGTH_SHORT).show();
                }
            } else {
                group = "";
            }

            resultText.setText(name + " " + group);
            startPracticalTestService();
        });

        buttonNavigateToSecondaryActivity.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("group", group);
            startActivityForResult(intent, 1);
        });

        for (String action : Constants.actionTypes) {
            intentFilter.addAction(action);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", name);
        outState.putString("group", group);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("name")) {
            name = savedInstanceState.getString("name");
        }

        if (savedInstanceState.containsKey("group")) {
            group = savedInstanceState.getString("group");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "OK result", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "NOT OK result", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
        getApplicationContext().stopService(intent);
    }
}