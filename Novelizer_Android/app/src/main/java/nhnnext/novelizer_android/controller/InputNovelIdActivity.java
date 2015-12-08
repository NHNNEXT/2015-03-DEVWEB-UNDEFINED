package nhnnext.novelizer_android.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nhnnext.novelizer_android.R;


public class InputNovelIdActivity extends Activity {

    EditText novelIdEditor;
    Button getNovelDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_novel_id);
        novelIdEditor = (EditText)findViewById(R.id.novel_id_edit_text);
        getNovelDataBtn = (Button)findViewById(R.id.get_novel_data_btn);

        getNovelDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("novelId", novelIdEditor.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
