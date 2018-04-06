package mjsquared.r2d2yrobo;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.ButterKnife;
//import butterknife.InjectView;
import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    //boolean loginFailed = false;
    public static List<Contacts> contact = null;

    //@BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    //@BindView(R.id.link_signup) TextView _signupLink;
    public  TextView _signupLink;
    public  TextView _emailText;
    public static String email;
    //public static String email;
    public static boolean factCheck = false;
    public static String dataC;
    bgLogin_Authen bgl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        bgl = new bgLogin_Authen();
        bgl.execute();
        _signupLink = (TextView) findViewById(R.id.link_signup);
        _emailText = (TextView) findViewById(R.id.input_email);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                //dbQuery dbQuery = new dbQuery();
                //dbQuery.start();
            }
        });
        /**
         * This starts the SignupActivity while requesting for results hence
         * startActivityForResult();
         */
        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                //Starts the signup activity
                startActivity(intent);

                //Removing the entries on the email and password fields
                _emailText.setText("");
                _passwordText.setText("");
            }
        });
    }

    /**
     * The login authentication should be taken care of here by grabbing info
     * from the database and making sure they are valid and correct accounts.
     */
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            //onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String type = "login";
        // TODO: Implement authentication logic here.
        //bgLogin_Authen bgLogin = new bgLogin_Authen();
        //bgLogin.execute();
        String name = "";

        try {
            //JSONObject object = new JSONObject(data);
            JSONArray array = new JSONArray(dataC);
            String email_db = "";
            String password_db = "";
            String name_db = "";
            int user_id = 0;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
               user_id = object.getInt("user_id");
                email_db = (String) object.get("email");
                password_db = (String) object.get("password");
                name_db = (String) object.get("name");
                Log.i("Name is ",name_db);
                if(email.equals(email_db) && password.equals(password_db)){
                    factCheck = true;
                    break;
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        //_signupLink.setText(dataC);


        if(factCheck){
            factCheck = false;
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            //onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }else {
            //onLoginFailed();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            //onLoginSuccess();
                            onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }
    }

    /**
     * This method takes care of the results that come from the SignupActivity.
     * This is where the information from the SignupActivity should be uploaded into a database.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically

                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
        _emailText.setText("");
        _passwordText.setText("");

    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        //For now it will stay in this activity when successfull.
        Toast.makeText(getBaseContext(),"Login Successful..", Toast.LENGTH_LONG).show();

        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed...\nIncorrect email or password", Toast.LENGTH_LONG).show();
        _passwordText.setText("");
        _loginButton.setEnabled(true);
    }

    /**
     * This method validates the inputs (email and password) are valid types
     * of inputs, but not their correctness.
     */
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            //disableEText(_passwordText);
            valid = false;
            //_passwordText.setEnabled(false);
            //_passwordText.setFocusable(false);
        } else {
            _emailText.setError(null);
            //_passwordText.setEnabled(true);
            //_passwordText.setFocusable(true);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }


        return valid;
    }
    /*
    *Disable the password field until
    * there is a valid email
     */

}

