package ongraph.com.blooddonate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;


//import com.android.volley.RequestQueue;


public class CreateAccount  extends Activity implements View.OnClickListener {
    EditText EnterUserName, EnterUserEmailId, EditUserBloodGroup, setUserPassword;
    TextView createAccount;
    private ProgressDialog pDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;


   // String URL = Constraints.RegistrationUrl;

    // JSONParser jsonParser = new JSONParser();
    //private static final String TAG_SUCCESS = "success";


    //private static RequestQueue mRequestQueue;
    //LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);


        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        Firebase.setAndroidContext(this);


      //  loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        //loginDataBaseAdapter = loginDataBaseAdapter.open();
        EnterUserName = (EditText) findViewById(R.id.EnterUserName);
        setUserPassword = (EditText) findViewById(R.id.setUserPassword);

        EditUserBloodGroup = (EditText) findViewById(R.id.EditUserBloodGroup);
        EnterUserEmailId = (EditText) findViewById(R.id.EnterUserEmailId);
        createAccount = (TextView) findViewById(R.id.createAccount);
        createAccount.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        String name = EnterUserName.getText().toString();
        String password = setUserPassword.getText().toString();

        String userbloodgroup = EditUserBloodGroup.getText().toString();
        String useremail = EnterUserEmailId.getText().toString();

        switch (v.getId()) {
            case R.id.createAccount:

                // Save the Data in Database
                if (name.isEmpty())
                    EnterUserName.setError("Enter Name ");
                else
                    EnterUserName.setError(null);
                if (password.isEmpty())
                    setUserPassword.setError("Enter Password ");
                else
                    setUserPassword.setError(null);
                if (userbloodgroup.isEmpty())
                    EditUserBloodGroup.setError("Enter blood group");
                else
                    EditUserBloodGroup.setError(null);
                if (useremail.isEmpty())
                    EnterUserEmailId.setError("Enter Email ");
                else
                    EnterUserEmailId.setError(null);

                if (!name.isEmpty() && !password.isEmpty() && !userbloodgroup.isEmpty() && !useremail.isEmpty()) {

                    // new CreateNewUser().execute();



                    Firebase ref = new Firebase("https://healthcare-df667.firebaseio.com");




                    ref.createUser(
                            name,
                            password,
                            new Firebase.ResultHandler() {
                                @Override
                                public void onSuccess() {
                                    // Great, we have a new user. Now log them in:
                                    Toast.makeText(getBaseContext(), "Success ", Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(CreateAccount.this, LoginActivity.class));

                                    /*
                                    ref.authWithPassword(
                                            username,
                                            password,
                                            new Firebase.AuthResultHandler() {
                                                @Override
                                                public void onAuthenticated(AuthData authData) {
                                                    // Great, the new user is logged in.
                                                    // Create a node under "/users/uid/" and store some initial information,
                                                    // where "uid" is the newly generated unique id for the user:
                                                    rootRef.child("users").child(authData.getUid()).child("status").setValue("New User");
                                                }

                                                @Override
                                                public void onAuthenticationError(FirebaseError error) {
                                                    // Should hopefully not happen as we just created the user.
                                                }
                                            }
                                    );
                                    */
                                }

                                @Override
                                public void onError(FirebaseError firebaseError) {
                                    Toast.makeText(getBaseContext(), "error ", Toast.LENGTH_LONG).show();
                                    // Couldn't create the user, probably invalid email.
                                    // Show the error message and give them another chance.
                                }
                            }
                    );




                }

                // startActivity(new Intent(CreateAccount.this, LoginActivity.class));
                //loginDataBaseAdapter.insertEntry(Name, password, userbloodgroup, useremail);


                break;


        }
/*
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
*/

    }
}
/*
    class CreateNewUser extends AsyncTask<String, String, String> {
        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateAccount.this);
            pDialog.setMessage("Creating New Account.....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
// Building Parameters
            List<NameValuePair> param= new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("userName", Name));
            param.add(new BasicNameValuePair("password", password));
            param.add(new BasicNameValuePair("bloodGroup", userbloodgroup));
            param.add(new BasicNameValuePair("email", useremail));
            // getting JSON Object
            // create user url accepts POST method

            JSONObject json = jsonParser.makeHttpRequest(URL,
                    "POST", param);
            Log.d("Create Response", json.toString());


            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created user
                    Intent i = new Intent(CreateAccount.this, LoginActivity.class);
                    Toast.makeText(getApplicationContext(),"Account Successfully Created ! Please Login!", Toast.LENGTH_LONG).show();
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

return null;

        }
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
    }

}
*/