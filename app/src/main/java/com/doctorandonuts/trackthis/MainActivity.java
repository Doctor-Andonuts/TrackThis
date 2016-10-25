package com.doctorandonuts.trackthis;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateTracker createTracker = new CreateTracker();
                // Sets the back stack to nothing, so when I back it will go back to main list screen.
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, createTracker, "CreateTrackerFragment")
                        .addToBackStack(null)
                        .commit();
                setDrawerState(false);
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.hide();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Create New Tracker");
            }
        });



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

// THIS WAS JUST TEST CODE TO SHOW COUCH DATABASE WORKING
//        final String TAG = "HelloWorld";
//        Log.d(TAG, "Begin Hello World App");
//
//        // create a manager
//        Manager manager;
//        try {
//            manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
//            Log.d (TAG, "Manager created");
//        } catch (IOException e) {
//            Log.e(TAG, "Cannot create manager object");
//            return;
//        }
//
//        // create a name for the database and make sure the name is legal
//        String dbname = "hello";
//        if (!Manager.isValidDatabaseName(dbname)) {
//            Log.e(TAG, "Bad database name");
//            return;
//        }
//
//        // create a new database
//        Database database;
//        try {
//            database = manager.getDatabase(dbname);
//            Log.d (TAG, "Database created");
//
//        } catch (CouchbaseLiteException e) {
//            Log.e(TAG, "Cannot get database");
//            return;
//        }
//
//        // get the current date and time
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        Calendar calendar = GregorianCalendar.getInstance();
//        String currentTimeString = dateFormatter.format(calendar.getTime());
//
//        // create an object that contains data for a document
//        Map<String, Object> docContent = new HashMap<String, Object>();
//        docContent.put("message", "Hello Couchbase Lite");
//        docContent.put("creationDate", currentTimeString);
//
//        // display the data for the new document
//        Log.d(TAG, "docContent=" + String.valueOf(docContent));
//
//        // create an empty document
//        Document document = database.createDocument();
//
//        // add content to document and write the document to the database
//        try {
//            document.putProperties(docContent);
//            Log.d (TAG, "Document written to database named " + dbname + " with ID = " + document.getId());
//        } catch (CouchbaseLiteException e) {
//            Log.e(TAG, "Cannot write document to database", e);
//        }
//
//        // save the ID of the new document
//        String docID = document.getId();
//
//        // retrieve the document from the database
//        Document retrievedDocument = database.getDocument(docID);
//
//        // display the retrieved document
//        Log.d(TAG, "retrievedDocument=" + String.valueOf(retrievedDocument.getProperties()));
//
//        // update the document
//        Map<String, Object> updatedProperties = new HashMap<>();
//        updatedProperties.putAll(retrievedDocument.getProperties());
//        updatedProperties.put("temperature", "95");
//
//        try {
//            retrievedDocument.putProperties(updatedProperties);
//            Log.d(TAG, "updated retrievedDocument=" + String.valueOf(retrievedDocument.getProperties()));
//        } catch (CouchbaseLiteException e) {
//            Log.e (TAG, "Cannot update document", e);
//        }
//
//        // delete the document
//        try {
//            retrievedDocument.delete();
//            Log.d (TAG, "Deleted document, deletion status = " + retrievedDocument.isDeleted());
//        } catch (CouchbaseLiteException e) {
//            Log.e (TAG, "Cannot delete document", e);
//        }
//
//        // retrieve the document from the database
//        Document retrievedDocumentDel = database.getDocument(docID);
//
//        // display the retrieved document
//        Log.d(TAG, "retrievedDocument=" + String.valueOf(retrievedDocumentDel.getProperties()));
//
//        Log.d(TAG, "End Hello World App");


    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        CreateTracker createTracker = (CreateTracker) fragmentManager.findFragmentByTag("CreateTrackerFragment");

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (createTracker != null && createTracker.isVisible()) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle(R.string.app_name);
            setDrawerState(true);
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setDrawerState(boolean isEnabled) {
        if (isEnabled) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            actionBarDrawerToggle.syncState();

        }
        else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            actionBarDrawerToggle.syncState();
        }
    }
}
