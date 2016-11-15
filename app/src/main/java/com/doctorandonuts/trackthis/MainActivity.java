package com.doctorandonuts.trackthis;

import android.app.FragmentManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String testOne;
    public String testTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadRootView();


// THIS WAS JUST TEST CODE TO SHOW COUCH DATABASE WORKING
/** TODO(jason): Should I just put this little bit of code anywhere I need to access the DB instead of
 * trying to access it out of the MainActivity. And if that is the case, maybe I make a quick little class. */






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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        ListTracker listTracker = (ListTracker) getFragmentManager().findFragmentByTag("ListTrackerFragment");

        if (id == android.R.id.home) { /** main button on top left, typically hamburger menu or back arrow **/
            if (listTracker != null && listTracker.isVisible()) {
                drawerLayout.openDrawer(GravityCompat.START);
                return true; /** Consumes event, not letting it eventually go to the "onBackPress" **/
            }
        }

        return super.onOptionsItemSelected(item);
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

    private void loadRootView() {
        ListTracker listTracker = new ListTracker();
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); /** Sets the back stack to nothing, so when I back it will go back to main list screen. **/
        getFragmentManager()
                .beginTransaction()
                .add(R.id.content_main, listTracker, "ListTrackerFragment")
                .commit();
        setDrawerState(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
    }
}