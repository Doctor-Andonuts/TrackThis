package com.doctorandonuts.trackthis;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.util.HashMap;
import java.util.Map;


public class ListTracker extends Fragment {

    public ListTracker() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Create Tracker");
                builder.setCancelable(true);
                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), String.valueOf(i), Toast.LENGTH_SHORT).show();


//                        Database myDatabase = new MyDatabase(getContext()).getDatabase();
//                        myDatabase.getExistingDocument()
//                        // create an empty document
//                        Document document = myDatabase.createDocument();
//
//                        Map<String, Object> docContent = new HashMap<>();
//                        docContent.put("message", "Hello Couchbase Lite");
//
//                        try {
//                            document.putProperties(docContent);
//                            Log.d ("CouchDB", "Document with ID = " + document.getId());
//                        } catch (CouchbaseLiteException e) {
//                            Log.e("CouchDB", "Cannot write document to database", e);
//                        }
                    }
                });

                builder.setView(R.layout.create_tracker);

                AlertDialog alert = builder.create();
                alert.show();

/** NOTES(jason): Handling navigation between fragments, Not needed now but wanted to keep for reference later **/
//                ListTracker listTracker = new ListTracker();
//                // Sets the back stack to nothing, so when I back it will go back to main list screen.
//                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content_main, listTracker, "ListTrackerFragment")
//                        .addToBackStack(null)
//                        .commit();
//                setDrawerState(false);
//                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//                fab.hide();
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                getSupportActionBar().setTitle("Create New Tracker");

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tracker, container, false);
    }
}
