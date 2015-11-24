package nhnnext.novelizer_android.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

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
                final ServerConnector serverConnector = new ServerConnector();
                final TextView blockListTextView = (TextView) findViewById(R.id.fab);
                findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final String data = serverConnector.getBlockListByJSON();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        blockListTextView.setText(data);
                                    }
                                });
                            }
                        }).start();
                    }
                });
            }
        });
    }

}
