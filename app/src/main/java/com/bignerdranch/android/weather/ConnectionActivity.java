package com.bignerdranch.android.weather;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConnectionActivity extends AppCompatActivity{

    private ImageView imageConnection;
    private ImageButton imageUpdate;
    private TextView textConnection;

    private Boolean exit = false;

    private CheckNetworkConnection checkNetworkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        imageConnection = (ImageView) findViewById(R.id.image);
        imageUpdate = (ImageButton) findViewById(R.id.update);
        textConnection = (TextView) findViewById(R.id.text);

        imageConnection.setImageResource(R.drawable.network);
        imageUpdate.setImageResource(R.drawable.update);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.update:
                        if (checkNetworkConnection.isNetworkConnected(v.getContext())){
                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            startActivity(intent);
                        }else Toast.makeText(v.getContext(),
                            "Not connection!",
                            Toast.LENGTH_LONG);
                        break;
                }
            }
        };
        imageUpdate.setOnClickListener(onClickListener);
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//        System.exit(0);
//    }
}
