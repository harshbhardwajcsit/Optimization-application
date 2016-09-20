package ongraph.com.blooddonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;


//import com.android.volley.RequestQueue;

public class LoginActivity extends Activity implements View.OnClickListener {
    //global declaration
    static String userName;
    String userpassword;
     EditText editUserName, editUserPassword, setUserPassword, EnterUserName;
    ImageButton login;
    ImageButton newu;




   // private static RequestQueue mRequestQueue;
       // LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);
        Firebase.setAndroidContext(this);


        // create a instance of SQLite Database
       // loginDataBaseAdapter=new LoginDataBaseAdapter(this);
       /// loginDataBaseAdapter=loginDataBaseAdapter.open();


        editUserName = (EditText) findViewById(R.id.editUserName);
        editUserPassword = (EditText) findViewById(R.id.editUserPassword);

        login = (ImageButton) findViewById(R.id.login);
        newu = (ImageButton) findViewById(R.id.newu);
        login.setOnClickListener(this);
        newu.setOnClickListener(this);
    }


    @Override


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.newu: {
                Intent in1 = new Intent(LoginActivity.this, CreateAccount.class);
                startActivity(in1);

                break;
            }
            case R.id.login: {
                 userpassword = editUserPassword.getText().toString();
                userName = editUserName.getText().toString();
                // String storedPassword=loginDataBaseAdapter.getSingleEntry(userName);

                if (userName.isEmpty()) {
                    editUserName.setError("Enter User Name");
                } else {
                    editUserName.setError(null);
                }
                if (userpassword.isEmpty() || userpassword.length() < 4 || userpassword.length() > 10) {
                    editUserPassword.setError("Password should contain atleast 5 character");
                } else {
                    editUserPassword.setError(null);
                }


                if (!userName.isEmpty() && !userpassword.isEmpty() && userpassword.length() > 5) {

//data sending to cloud

                    Firebase ref = new Firebase("https://healthcare-df667.firebaseio.com");

                    Firebase fb_to_read = ref.child("students/names");
                    Firebase fb_put_child = fb_to_read.push(); // REMEMBER THIS FOR PUSH METHOD


                   // Creating Person object
                    Person person = new Person();

                    //Adding values
                    person.setName(userName);
                    person.setPassword(userpassword);

                    //Storing values to firebase
                   
                  // ref.setValue(person);
                    fb_put_child.setValue(person);





                    Intent in = new Intent(LoginActivity.this, Image.class);
                    in.putExtra("key", userName);
                    startActivity(in);
                    //passing data to login Api
                    // String URL = Constraints.LoginUrl;
                    /*
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("userpassword", editUserPassword.getText().toString().trim());
                    params.put("userName", editUserName.getText().toString().trim());

                    processPostRequest(URL, params, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("response", response.toString());
                            // Toast.makeText(getBaseContext(), "go" + response.toString(), Toast.LENGTH_LONG).show();

                            int responceCode = response.optInt("Identity");
                            if (responceCode == 0) {
                                try {
                                    JSONObject result = response.getJSONObject("Result");
                                    JSONArray arrauerrors = result.getJSONArray("Errors");
                                    Toast.makeText(getApplicationContext(), arrauerrors.getString(0).toString(), Toast.LENGTH_LONG).show();

                                } catch (JSONException e) {

                                }

                            } else{
                                 ///status from Api
                                Toast.makeText(getApplicationContext(), "Successfully Login!", Toast.LENGTH_LONG).show();
                                //startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                            }
                        }


                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getBaseContext(), "error", Toast.LENGTH_LONG).show();
                            //hideProgressDialog();
                        }
                    }, "Test", false);
*/

                }


                break;

            }
        }

    }    }
/*

    protected MyJsonRequest processPostRequest(String requestUrl, Map<String, String> params,
                                               Response.Listener<JSONObject> responseListener,
                                               Response.ErrorListener errorListener, String Tag, boolean shouldCache) {
        MyJsonRequest request = new MyJsonRequest(this, Request.Method.POST, requestUrl
                , params, responseListener, errorListener);
        request.setShouldCache(shouldCache);
        addToRequestQueue(request, Tag);
        return request;
    }

    protected <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(tag);
        getRequestQueue().add(req);
    }
    protected RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
        }
        return mRequestQueue;
    }

*/
