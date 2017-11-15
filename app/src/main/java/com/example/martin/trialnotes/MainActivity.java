package com.example.martin.trialnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView RV_note;
    Button B_add;
    TextView text1;
    ArrayList<Note> listNote;
    NoteAdapter foodAdapter;
    public void AddNote(String a, String b, int c){

        listNote.add(new Note(a,b,c));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNote = new ArrayList<>();
        RV_note = (RecyclerView)  findViewById(R.id.RV_note);
        B_add = (Button) findViewById(R.id.IB_add);
        text1 = (TextView) findViewById(R.id.text1);

         foodAdapter = new NoteAdapter(listNote);

        RV_note.setAdapter(foodAdapter);
        RV_note.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        B_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,AddNote.class);
           /*     Bundle extras = new Bundle();
                extras.putString("EXTRA_USERNAME","my_username");
                extras.putString("EXTRA_PASSWORD","my_password");
                intent.putExtras(extras);
                */
                startActivityForResult(intent,2);
          //      startActivity(new Intent(this, AddNote.class));

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        text1.setText("nep");
        // check if the request code is same as what is passed  here it is 2 which is the add note
        if(requestCode==-RESULT_CANCELED){
            return;
        }
       else if(requestCode==2&&data!=null)
        {
            text1.setText("hello");
            Intent intent = getIntent();
            Bundle bundle2 = intent.getExtras();
            Bundle extras = data.getExtras();

            int a;
            if (listNote.size()==0)//first note
                a = 0;
            else//if at least one. +1 of the id of the last note
            a = listNote.get(listNote.size()-1).getId()+1;
            String rtitle =Integer.toString(a)+ extras.getString("EXTRA_TITLE");
            String rnote = extras.getString("EXTRA_NOTE");
            listNote.add(new Note(rtitle,rnote,a));

            String b = Integer.toString(listNote.size());

           foodAdapter = new NoteAdapter(listNote);
            RV_note.setAdapter(foodAdapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
