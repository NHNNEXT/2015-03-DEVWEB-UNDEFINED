package nhnnext.novelizer_android;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Henry on 2015. 11. 23..
 */
public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable(){
            public void run() {
                finish();
            }
        },2000);
        overridePendingTransition(0, 0);
    }
}

