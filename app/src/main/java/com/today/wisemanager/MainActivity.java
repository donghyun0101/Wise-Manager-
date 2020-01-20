package com.today.wisemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends AppCompatActivity
{
    private EditText edWise, edName;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("관리자 앱");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setElevation(0);

        NetWorkUtil.setNetworkPolicy();

        edWise = findViewById(R.id.ed_wise);
        edName = findViewById(R.id.ed_name);
        btnSend = findViewById(R.id.btn_sendwise);

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    PHPRequest request = new PHPRequest("http://kdonghyun0101.dothome.co.kr/data_insert.php");
                    String result = request.PHPsend(String.valueOf(edWise.getText()), String.valueOf(edName.getText()));
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    Crouton.makeText(MainActivity.this, R.string.success, Style.INFO).show();
                } catch (MalformedURLException e)
                {
                    e.printStackTrace();
                    Crouton.makeText(MainActivity.this, R.string.fail + "\n" + e.getMessage(), Style.ALERT).show();
                }
            }
        });
    }
}