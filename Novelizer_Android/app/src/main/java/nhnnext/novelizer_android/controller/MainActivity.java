package nhnnext.novelizer_android.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import nhnnext.novelizer_android.R;
import nhnnext.novelizer_android.SplashActivity;
import nhnnext.novelizer_android.network.ServerConnector;

public class MainActivity extends AppCompatActivity {

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
                final TextView blockListTextView = (TextView) findViewById(R.id.textView_viewer_blockList);
                findViewById(R.id.button_viewer_test).setOnClickListener(new View.OnClickListener() {
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
