package com.example.android.newsapp;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Result>> {

    /**
     * Adapter for the list of news
     */
    private NewsAdapter mAdapter;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;



    /**
     * URL for google books data from the Google API
     */
    private static final String GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";

    /**
     * Returns true if network is available or about to become available
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        // Find a reference to the {@link ListView} in the layout
        ListView newsView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        //   View emptyView = findViewById(R.id.empty_view);
        // newsView.setEmptyView(emptyView);

        // Create a new adapter that takes an empty list of books as input
       mAdapter = new NewsAdapter(this, new ArrayList<Result>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsView.setAdapter(mAdapter);


        Context context = getApplicationContext();

        //Check for internet connection
        if (isNetworkAvailable(context)) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        } else {
            //Provide feedback about no internet connection
            Toast.makeText(NewsActivity.this, "Please check your internet connection - No internet!", Toast.LENGTH_LONG).show();
        }


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected book.
        newsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current book that was clicked on
                Result currentResult = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri resultUri = Uri.parse(currentResult.getUrl());

                // Create a new intent to view the book URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, resultUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    ProgressDialog progDailog;

    @Override
    public Loader<List<Result>> onCreateLoader(int i, Bundle bundle) {
        progDailog = new ProgressDialog(NewsActivity.this);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();

        // Create a new loader for the given URL
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Result>> loader, List<Result> news) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();
        progDailog.dismiss();
        // If there is a valid list of {@link Result}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Result>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


}



