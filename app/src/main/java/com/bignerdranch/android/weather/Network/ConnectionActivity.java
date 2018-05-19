package com.bignerdranch.android.weather.Network;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.weather.MainActivity;
import com.bignerdranch.android.weather.R;

public class ConnectionActivity extends AppCompatActivity{

    private ImageView imageConnection;
    private ImageButton imageUpdate;
    private TextView textConnection;
    private Button dbButton;

    private Boolean exit = false;

    public static final int CONNECTION_DB = 1;
    public static final String DB = "db";

    private CheckNetworkConnection checkNetworkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        imageConnection = (ImageView) findViewById(R.id.image);
        imageUpdate = (ImageButton) findViewById(R.id.update);
        textConnection = (TextView) findViewById(R.id.text);
        dbButton = (Button) findViewById(R.id.db);

        imageConnection.setImageResource(R.drawable.network);
        imageUpdate.setImageResource(R.drawable.update);

        View.OnClickListener onClickListener = v -> {
            switch (v.getId()){
                case R.id.update:
                    if (checkNetworkConnection.isNetworkConnected(v.getContext())){
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else Toast.makeText(this,
                        "Not connection!",
                        Toast.LENGTH_LONG);
                    break;
                case R.id.db:
                    if (checkNetworkConnection.isNetworkConnected(v.getContext())) {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.putExtra(DB, CONNECTION_DB);
                        startActivity(intent);
                        finish();
                    }
            }
        };
        imageUpdate.setOnClickListener(onClickListener);
        dbButton.setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            exit = true;
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> exit = false, 3 * 1000);
        }
    }
}
