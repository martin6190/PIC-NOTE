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
import android.widget.Toast;

public class AddNote extends AppCompatActivity {
    Button IB_save;
    Button IB_cancel;
    Button capture;
    ImageView picture;
    EditText ET_title;
    EditText ET_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        IB_save = (Button) findViewById(R.id.IB_save);
        IB_cancel = (Button) findViewById(R.id.IB_cancel);
        ET_title = (EditText)  findViewById(R.id.ET_title);
        ET_note = (EditText)  findViewById(R.id.ET_note);
        picture = (ImageView) findViewById(R.id.ADD_PICTURE);
        capture = (Button) findViewById(R.id.ADD_CAPTURE);

   /*     Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String username_string = extras.getString("EXTRA_USERNAME");
        String password_string = extras.getString("EXTRA_PASSWORD");
        ET_title.setText(username_string);
        ET_note.setText(password_string);
        */
        IB_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNote.this,MainActivity.class);
               Bundle extras = new Bundle();
                String title = ET_title.getText().toString();
                String note = ET_note.getText().toString();
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
       //         Intent cancelIntent = new Intent();
        //        Intent intent = new Intent(AddNote.this,MainActivity.class);
          //      setResult(-1,intent);
        //        setResult(RESULT_CANCELED, cancelIntent);
                finish();
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
                Toast.makeText(AddNote.this,"No picture taken",Toast.LENGTH_SHORT).show();
        }
    }
}
