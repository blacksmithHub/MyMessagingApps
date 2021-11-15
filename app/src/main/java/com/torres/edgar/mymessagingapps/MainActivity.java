package com.torres.edgar.mymessagingapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtMessage,txtNumber;
    EditText txtPhonenumber,txtMessageSent;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage =(TextView)findViewById(R.id.textViewmessage);
        txtNumber=(TextView) findViewById(R.id.textViewnumber);
        txtMessageSent=(EditText) findViewById(R.id.editTextMessage);
        txtPhonenumber=(EditText) findViewById(R.id.editTextPhoneNumber);
        btnSend=(Button)findViewById(R.id.buttonSend);

//         Get intent object sent from the SMSBroadcastReceiver
        Intent sms_intent = getIntent();
        Bundle b = sms_intent.getExtras();

        if (b!=null){


            SMSClass smsObj = (SMSClass) b.getSerializable("sms_obj");
            txtMessage.setText("From :"+smsObj.getNumber().toString());
            txtNumber.setText("Message:"+smsObj.getMessage().toString());


    }

    btnSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String phonetNo = txtPhonenumber.getText().toString();
            String msg = txtMessageSent.getText().toString();

            try {
                SmsManager smsMngr = SmsManager.getDefault();
                smsMngr.sendTextMessage(phonetNo, null, msg, null, null);
                Toast.makeText(getApplicationContext(), "message send!!", Toast.LENGTH_LONG).show();

                txtPhonenumber.setText("");
                txtMessageSent.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    });


    }
}
