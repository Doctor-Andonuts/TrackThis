package com.doctorandonuts.trackthis;

import android.util.Log;

import com.couchbase.lite.Context;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;

/**
 * Created by jgowing on 11/15/2016.
 */

public class MyDatabase {
    private Database myDatabase;

    public MyDatabase(android.content.Context context) {
        final String TAG = "CouchDB";


        /** Create a manager */
        Manager manager;
        try {
            manager = new Manager(new AndroidContext(context), Manager.DEFAULT_OPTIONS);
            Log.d (TAG, "Manager created");
        } catch (IOException e) {
            Log.e(TAG, "Cannot create manager object");
            return;
        }

        /** create a name for the database and make sure the name is legal */
        String dbname = "trackthis";
        if (!Manager.isValidDatabaseName(dbname)) {
            Log.e(TAG, "Bad database name");
            return;
        }

        /** create a new database or get it if it already exists */
        try {
            myDatabase = manager.getDatabase(dbname);
            Log.d (TAG, "MyDatabase created");

        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Cannot get database");
            return;
        }
    }

    public Database getDatabase() {
        return myDatabase;
    }
}
