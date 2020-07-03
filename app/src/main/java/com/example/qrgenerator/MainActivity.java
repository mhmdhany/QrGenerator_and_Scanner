package com.example.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
   EditText qrValue;
   Button generate , scan;
   ImageView qrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init views
        qrValue = findViewById(R.id.qrInput);
        generate = findViewById(R.id.generateBtn);
        scan = findViewById(R.id.scanBtn);
        qrImage = findViewById(R.id.qrPlaceHolder);

        //Generate QrCode
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = qrValue.getText().toString();
                if (data.isEmpty()) {
                    qrValue.setError("Value Required");
                }else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);

                    Bitmap qrBits = qrgEncoder.getBitmap();
                    qrImage.setImageBitmap(qrBits);
                }
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Scanner.class));
            }
        });

    }
}