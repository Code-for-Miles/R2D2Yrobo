package mjsquared.r2d2yrobo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.button;
import static android.R.attr.finishOnTaskLaunch;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //
    TextView _signupLink;
    TextView _loginLink;
    @BindView(R.id.item) EditText _itemRequested;
    @BindView(R.id.button) Button _requestBtn;
    ArrayAdapter<?> aa;
    Spinner spinner;
    String roomNum;
    String item;
    String _email;
    String type;
    boolean requested;
    //backgroundDatabaseHelper bdh;
    //String [] rooms = {"3001", "3002", "3003", "3004"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //final Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(this);
        this.setTitle("ORDER ROOM SERVICE");


        spinner = (Spinner) findViewById(R.id.room_num);
        aa = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinner_item,R.layout.support_simple_spinner_dropdown_item);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setPrompt("rooms");
        //spinner.setOnItemSelectedListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //view.bringToFront();
                roomNum = spinner.getSelectedItem().toString();
                //((TextView) view).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(pos) + " Selected", Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

         //bdh = new backgroundDatabaseHelper(this);requested = false;

        _requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bdh.execute(type,_email,item,roomNum);

                updateRequest();
                //System.out.println(roomNum);
            }
        });




        /*spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" Selected", Toast.LENGTH_SHORT).show();
            }
        });*/

        //_loginLink = (TextView) findViewById(R.id.back_to_login);
        //_signupLink = (TextView) findViewById(R.id.back_to_signup);
        /**
         * if the password or email entered are wrong then go back to login page
         * when the button is pressed.

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(intent);
            }
        });
        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });**/

    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position!=0) {

            //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //@Override
    //public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id){
    //  Toast.makeText(getApplicationContext(), rooms[position], Toast.LENGTH_LONG).show();
    //}
    //@Override
    //public void onNoItemClick(AdapterView<?> arg0){

    //}
    public void updateRequest(){
        type = "request";
        item = _itemRequested.getText().toString();
        _email = "miles@g.win";
        backgroundDatabaseHelper dbh = new backgroundDatabaseHelper(this);
        dbh.execute(type,_email,item,roomNum);
        _itemRequested.setText("");
    }
}
