package nhnnext.novelizer_android.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.view.View;
import android.os.Handler;
import android.widget.Toast;

import nhnnext.novelizer_android.R;
import nhnnext.novelizer_android.SplashActivity;

public class MainActivity extends Activity {
    private static final int LAUNCHED_ACTIVITY = 1;
    private Handler dialogHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InputNovelIdActivity.class);
                startActivityForResult(intent, LAUNCHED_ACTIVITY);
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            Toast.makeText(this, data.getStringExtra("novelId"), Toast.LENGTH_LONG).show();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            /* TODO : 네트워크 붙이는 부분은 이후 구현 예정 */
            dialogHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 3000);





        }
    }

}
