package ongraph.com.blooddonate;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by samsung on 29-Jun-16.
 */
public class DonatorsList extends ListFragment {

String sty;
String string="t";
    // Array of strings storing country names
    String[] names = new String[]{
            string,
            sty,
            "gaurav tak",
            "hemant saini",
            "dhawal jain",
            "himanshu tongya",
            "yash maheshwari",
            "gaurav agarwal",
            "paresh gupta",
            "nilesh ",
            "amit agarwal"
    };

    // Array of integers points to images stored in /res/drawable/
    int[] images = new int[]{
            R.drawable.download1,
            R.drawable.download2,
            R.drawable.download4,
            R.drawable.download5,
            R.drawable.download3,
            R.drawable.download1,
            R.drawable.download4,
            R.drawable.download2,
            R.drawable.download4,
            R.drawable.download2
    };

    // Array of strings to store currencies
    String[] age = new String[]{
            "23",
            "24",
            "17",
            "46",
            "56",
            "19",
            "28",
            "47",
            "24",
            "47"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", names[i]);
            hm.put("cur", "age : " + age[i]);
            hm.put("images", Integer.toString(images[i]));
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"images", "txt", "cur"};

        // Ids of views in listview_layout
        int[] to = {R.id.image, R.id.txt, R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_layout, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    final Firebase ref = new Firebase("https://healthcare-df667.firebaseio.com");
@Override
 public void onStart(){
    super.onStart();
    Firebase fire=ref.child("name");

    fire.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {

                //Getting the data from snapshot

                 sty = String.valueOf(snapshot.getValue(Person.class));

                //Adding it to a string
            //  string =  sty.getName();

              Log.v("E value",string);




        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
        }
    });


}


}