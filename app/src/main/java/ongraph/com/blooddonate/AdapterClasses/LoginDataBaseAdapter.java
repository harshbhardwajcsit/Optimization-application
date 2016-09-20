package ongraph.com.blooddonate.AdapterClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import ongraph.com.blooddonate.HelperClasses.DataBaseHelper;

public class LoginDataBaseAdapter {


    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final String USER="users_table";


    public static final String USER_NAME="users";
    public static final String PASSWORD="Passoword";
    public static final String BLOOD_GROUP="Blood_group";
    public static final String EMAIL_ID="email_id";

    // SQL Statement to create a new database.
    public static String DATABASE_CREATE = "CREATE TABLE " + USER+ " ("+
            USER_NAME + " VARCHAR(250) NOT NULL, " +
            PASSWORD + " VARCHAR(250) NOT NULL, " +
            BLOOD_GROUP + " VARCHAR(250) NOT NULL, " +
            EMAIL_ID + " VARCHAR(250) NOT NULL)";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public  void insertEntry(String Name, String password,String userbloodgroup,String useremail) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(USER_NAME, Name);
        newValues.put(PASSWORD, password);
        newValues.put(BLOOD_GROUP, userbloodgroup);
        newValues.put(EMAIL_ID, useremail);

        // Insert the row into your table
        db.insert("USER", null, newValues);
        Toast.makeText(context.getApplicationContext(), "Registration Successfull ! Please Login", Toast.LENGTH_LONG).show();


    }



    public String getSingleEntry(String Name) {
        Cursor cursor = db.query("USER",null, "USER_NAME=?", new String[]{Name}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();

        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }


}