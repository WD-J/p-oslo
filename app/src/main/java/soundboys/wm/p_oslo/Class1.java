package soundboys.wm.p_oslo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import soundboys.wm.p_oslo.DataAdapter;
import soundboys.wm.p_oslo.MainActivity;
import soundboys.wm.p_oslo.R;

public class Class1 extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ProgressDialog mProgressDialog;
    private String url = "https://web.archive.org/web/20180312172227/https://www.oslo.kommune.no/gate-transport-og-parkering/veiarbeid-og-vedlikehold/broyting-og-bortkjoring-av-sno/bortkjoring-av-sno-i-omrade-sentrum/";
    private ArrayList<String> mGatenavnList = new ArrayList<>();
    private ArrayList<String> mStrekningList = new ArrayList<>();
    private ArrayList<String> mDatoList = new ArrayList<>();
    private ArrayList<String> mTidspunktList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class1);
        init();
        new Description().execute();

        getSupportActionBar().setTitle("Nord");
        getSupportActionBar().setSubtitle("Trykk for å få opp lokasjonen i google maps.");

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void init() {
        mRecyclerView = findViewById(R.id.act_recyclerview);
        mDataAdapter = new DataAdapter(Class1.this, mDatoList, mGatenavnList, mStrekningList, mTidspunktList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDataAdapter);
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
        mDataAdapter.setOnItemClickListener(new DataAdapter.ClickListener() {
            @Override
            public void onItemClick(String title, View v) {
                openGoogleMap(title);
            }
        });

    }

    private void openGoogleMap(String region) {
        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme("geo")
                .path("0,0")
                .appendQueryParameter("q", region + ", Oslo");

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uriBuilder.build());
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mDataAdapter.getFilter().filter(s);
        return false;
    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Class1.this);
            mProgressDialog.setTitle("Tabell");
            mProgressDialog.setMessage("Lastes inn...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document mBlogDocument = Jsoup.connect(url).get();
                // Using Elements to get the Meta data
                Elements mElementDataSize = mBlogDocument.select("table");
                Elements rows = mElementDataSize.select("tr");
                // Locate the content attribute

                /* mElementDataSize.select("table").eq(1).remove();
                mElementDataSize.select("table").eq(2).remove();
                mElementDataSize.select("table").eq(3).remove();
                mElementDataSize.select("table").eq(4).remove(); */

                for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
                    Elements mElementAuthorName = mBlogDocument.select("td:nth-child(2)").eq(i); // Strekning
                    String mAuthorName = mElementAuthorName.text();

                    Elements mElementBlogUploadDate = mBlogDocument.select("td:nth-child(3)").eq(i); // Dato
                    String mBlogUploadDate = mElementBlogUploadDate.text();

                    Elements mElementBlogTitle = mBlogDocument.select("td:nth-child(1)").eq(i); // Navn
                    String mBlogTitle = mElementBlogTitle.text();

                    Elements mElementTidspunkt = mBlogDocument.select("td:nth-child(4)").eq(i); // Tidspunkt
                    String mTidspunkt = mElementTidspunkt.text();

                    if (mElementAuthorName.hasText() && mElementBlogUploadDate.hasText() && mElementBlogTitle.hasText() && mElementTidspunkt.hasText()) {

                        mTidspunktList.add(mTidspunkt);
                        mGatenavnList.add(mAuthorName);
                        mStrekningList.add(mBlogUploadDate);
                        mDatoList.add(mBlogTitle);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
            mDataAdapter.reloadData(mDatoList, mGatenavnList, mStrekningList, mTidspunktList);
            mProgressDialog.dismiss();
        }
    }
}