package com.example.aeonz.assignment5;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences shared;
    private SharedPreferences.Editor sharedEditor;
    private EditText userName;
    private EditText passWord;
    private CheckBox checkBox;
    private TextView savedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Shared Preference");
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.password);
        savedUser = (TextView) findViewById(R.id.txtUserSaved);


        shared = PreferenceManager.getDefaultSharedPreferences(this);
        sharedEditor =  shared.edit();
        checkSharedPreference();


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
         btnLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(checkBox.isChecked()) {
                     setTitle("Checked");
                     sharedEditor.putString(getString(R.string.checkBox), "True");
                     sharedEditor.commit();


                        String user = userName.getText().toString();
                        sharedEditor.putString(getString(R.string.user), user);
                        sharedEditor.commit();

                     String pass = passWord.getText().toString();
                     sharedEditor.putString(getString(R.string.password) , pass);
                     sharedEditor.commit();

                     // savedUser.setText(user);
                    //  savedPassword.setText(pass);
                 }

                 else {
                     setTitle("Unchecked");

                     sharedEditor.putString(getString(R.string.checkBox), "False");
                     sharedEditor.commit();


                     sharedEditor.putString(getString(R.string.user), "");
                     sharedEditor.commit();

                     sharedEditor.putString(getString(R.string.password) , "");
                     sharedEditor.commit();

                 }


             }
         });


    }

    public void checkSharedPreference(){
        String user = shared.getString(getString(R.string.user), "");
        String password = shared.getString(getString(R.string.password), "");
        String check = shared.getString(getString(R.string.checkBox), "False");

        userName.setText(user);
        passWord.setText(password);
       // savedUser.setText(user);
      //  savedPassword.setText(password);


        if (check.equals("True")){
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }
    }

    public void btnSave(View view){
        setTitle("File Saved");
      String fileUser = userName.getText().toString();
      String filePass = passWord.getText().toString();
      String fileName = "User_Login_Information";
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName , MODE_PRIVATE);
            fileOutputStream.write(fileUser.getBytes());
            fileOutputStream.write(filePass.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void btnOpen(View view){
        try {
            String openUser;

            FileInputStream fileInputStream = openFileInput("User_Login_Information");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader =  new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((openUser = bufferedReader.readLine()) != null){
                stringBuffer.append(openUser);
            }
            savedUser.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
