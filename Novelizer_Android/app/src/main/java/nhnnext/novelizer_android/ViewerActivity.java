package nhnnext.novelizer_android;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
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


}
