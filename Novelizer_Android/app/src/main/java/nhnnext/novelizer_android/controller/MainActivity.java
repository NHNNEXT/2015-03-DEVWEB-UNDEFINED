package nhnnext.novelizer_android.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import nhnnext.novelizer_android.R;
import nhnnext.novelizer_android.SplashActivity;
import nhnnext.novelizer_android.network.ServerConnector;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ServerConnector serverConnector = new ServerConnector();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), serverConnector.getScene(), Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }).start();

                    }
                });
            }
        });
    }

}
