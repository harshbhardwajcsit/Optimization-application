package ongraph.com.blooddonate;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import ongraph.com.blooddonate.AdapterClasses.MyAdapter;
import ongraph.com.blooddonate.TabsClasses.TabActivity;


public class MainActivity extends Activity implements View.OnClickListener{
    //views
 CardView card_view4,card_view3,card_view2,card_view1;

    final Firebase ref = new Firebase("https://healthcare-df667.firebaseio.com");
    //click actions
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.card_view4:
                startActivity(new Intent(MainActivity.this,Cloudtest.class));
                break;


            case R.id.card_view3:
                startActivity(new Intent(MainActivity.this, Donaters.class));
                break;


            case R.id.card_view2:
                startActivity(new Intent(MainActivity.this, TabActivity.class));
                break;


            case R.id.card_view1:
                startActivity(new Intent(MainActivity.this, Donaters.class));
                break;

        }
    }

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

//navigation drawer content
    String TITLES[] = {"Home","Events/camps","My Account","Share","Logout"};
    int ICONS[] = {R.drawable.username,R.drawable.images,R.drawable.images,R.drawable.images,R.drawable.images};

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);


        card_view4 = (CardView) findViewById(R.id.card_view4);
        card_view3 = (CardView) findViewById(R.id.card_view3);
        card_view2 = (CardView) findViewById(R.id.card_view2);
        card_view1 = (CardView) findViewById(R.id.card_view1);



        card_view4.setOnClickListener(this);
        card_view3.setOnClickListener(this);
        card_view2.setOnClickListener(this);
        card_view1.setOnClickListener(this);





        Bundle extras= getIntent().getExtras(); ///info of previos page reference
        String NAME = extras.getString("key");

        String EMAIL = "harsh@gmail.com";
        int PROFILE = R.drawable.user;



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE,this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

//action on navigation drawer
                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();
                    Toast.makeText(MainActivity.this,"The Item Clicked is: "+recyclerView.getChildPosition(child),Toast.LENGTH_SHORT).show();



// if( recyclerView.getChildPosition(child)==3){
                      // startActivity(new Intent(MainActivity.this, TabActivity.class));}

                    return true;
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
       /*
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State
*/
    }



    @Override
    public void onStart(){

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                //Getting the data from snapshot
                Person sty = snapshot.getValue(Person.class);

                //Adding it to a string
               String string =  sty.getName();
                Toast.makeText(getBaseContext(), "go", Toast.LENGTH_LONG).show();


                Log.v("E value",string);



                //Displaying it on textview
                //  textViewPersons.setText(string);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;}
        return super.onOptionsItemSelected(item);
    }
}








