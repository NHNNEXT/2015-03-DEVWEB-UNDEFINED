package nhnnext.novelizer_android.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import nhnnext.novelizer_android.R;
import nhnnext.novelizer_android.network.ServerConnector;

public class ViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        String novelId = getIntent().getStringExtra("novelId");
        Toast.makeText(this, novelId, Toast.LENGTH_LONG).show();
    }


}
