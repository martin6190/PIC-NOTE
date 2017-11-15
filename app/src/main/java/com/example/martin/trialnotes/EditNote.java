package com.example.martin.trialnotes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.martin.trialnotes.R.id.EDIT_TITLE;

public class EditNote extends AppCompatActivity {
    Button IB_save;
    Button IB_cancel;
    Button IB_delete;
    EditText ET_NOTE;
    EditText ET_TITLE;
    ImageView picture;
    Button capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        IB_save = (Button) findViewById(R.id.IB_save);
        IB_cancel = (Button) findViewById(R.id.IB_cancel);
        IB_delete = (Button) findViewById(R.id.IB_delete);
        ET_NOTE = (EditText) findViewById(R.id.EDIT_NOTE);
        ET_TITLE = (EditText) findViewById(EDIT_TITLE);
        picture = (ImageView) findViewById(R.id.EDIT_PICTURE);
        capture = (Button) findViewById(R.id.EDIT_CAPTURE);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String rtitle = extras.getString("NOTE_TITLE");
        String rnote = extras.getString("NOTE_NOTE");
        ET_TITLE.setText( rtitle, TextView.BufferType.EDITABLE);
        ET_NOTE.setText(rnote, TextView.BufferType.EDITABLE);

        IB_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditNote.this,MainActivity.class);
                Bundle extras = new Bundle();
                String title = ET_TITLE.getText().toString();
                String note = ET_NOTE.getText().toString();
                extras.putString("EXTRA_TITLE",title);
                extras.putString("EXTRA_NOTE",note);
                intent.putExtras(extras);
                setResult(2,intent);
                finish();
                //      startActivity(new Intent(this, AddNote.class));

            }
        });
        IB_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //      startActivity(new Intent(this, AddNote.class));

            }
        });
        IB_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditNote.this,MainActivity.class);
                startActivity(intent);
                //      startActivity(new Intent(this, AddNote.class));

            }
        });

        capture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                if (data != null) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    picture.setImageBitmap(bitmap);
                }
            }
            else
                Toast.makeText(EditNote.this,"No picture taken",Toast.LENGTH_SHORT).show();
        }
    }
}
