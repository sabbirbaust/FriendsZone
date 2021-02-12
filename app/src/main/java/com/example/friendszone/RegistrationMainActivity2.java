package com.example.friendszone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RegistrationMainActivity2 extends AppCompatActivity
{
    private CountryCodePicker ccp;
    private EditText phoneText;
    private EditText codeText;
    private Button continueAndNextBtn;
    private String checker = "",phoneNumber= "";
    private RelativeLayout relativeLayout;
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mresendToken;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main2);
        mAuth=firebaseAuth.getInstance();
        loadingBar=newProgressDialog(context:this);

        phoneText = findViewById(R.id.phoneText);
        codeText = findViewById (R.id.codeText);
        continueAndNextBtn =findViewById(R.id.continueNextButton);
        relativeLayout =findViewById(R.id.phoneAuth);
        continueAndNextBtn.setOnClickListener(new_veiw.onclickListener()
    {
            @Override
                    public void onClick(View v)
            {
                if (continueAndNextBtn.getText().equals("Submit") || checker.equals("Code Sent")) {
                    {
                     String verificationCode=codeText.getText().toString();
                     if(verificationCode.equals(""))
                     {
                         Toast.makeText(context:RegistrationMainActivity2.this,text"Please write vification code first",Toast.LENGTH_SHORT).show();)
                         else
                         {
                             loadingBar.setTitles("Code Verification ");
                             loadingBar.setMessage("Plaese wait , while we are verifying your code.");
                             loadingBar.setCanceledOnTouchOutside(false);
                             loadingBar .show();
                             PhoneAuthCredential credential= phoneAuthprovidert.getcredential(mVerificationId,verificationCode);
                             signInWithPhoneAuthCredential(credential);
                         }
                    }
                else{
                        phoneNumber = ccp.getFullNumberWithPlus();
                        if (!phoneNumber.equals("")) {
                         loadingBar.setTitles("Phone Number Verification ");
                         loadingBar.setMessage("Plaese wait , while we are verifying your phone number.");
                         loadingBar.setCanceledOnTouchOutside(false);
                         loadingBar .show();

                            PhoneAuthProvider.getInstance().veryfyphonenumber(phoneNumber, 60, TimeUnit.SECONDS,activity.registerActivity.this,mCallbacks);


                        } else {
                            Toast.makeText(context:RegistrationActivity2.this, text
                            "please write valid phone number.")
                        }
                    }
                }
            };
            mCallbacks= new_phoneAuthProvider.OnVerificationStateChangedCallbacks(){

            }
                @Override
                        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
            signInWithPhoneAuthCredential();
            }

                        public void onVerificationFailed(FirebaseException e)
        {
            Toast.makeText(context:RegistrationMainActivity2.this,text("Invalid Phone Number...",Toast,LENGTH__SHORT).show();)
               relativeLayout.setVisibility(Veiw.VISIBLE);
            continueAndNextBtn.setText("continue");
            codeText.setVisibility(View.GONE);

                }
            @Override
            public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken forceResendingToken){
                supper.onCodeSent(s,forceResendingToken);
                mVerificationId=s;
                mresendToken=ForceResendingToken;

                relativeLayout.setVisibility(Veiw.GONE)
                checker="Code Sent";
                continueAndNextBtn.setText("Submit");
                codeText.setVisibility(View.VISIBLE);
                Toast.makeText(context:RegistrationMainActivity2.this,text"Code has been sent, please check.",Toast.LENGTH_SHORT).show();)

            }
            };

            }

}private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loadingBar.dismiss();
                        Toast.makeText(context:RegistrationMainActivity2.this,text("Cngratulation, You are logged in successfuly.",Toast,LENGTH_SHORT).show();
                          sendUserToMainActivity();
                    } else {
                    loadingBar.dissmiss();
                    Toast.makeText(context:RegistrationMainActivity2.this,text"Error:"+e,Toast.LENGTH_SHORT).show();)

                        }
                    }
                }
            });
}
private void sendUserToMainActivity()
{
    Internet intent= onNewIntent(RegistrationMainActivity2.this,MainActivity.class);
    startActivity(intent);
    finish();
}
