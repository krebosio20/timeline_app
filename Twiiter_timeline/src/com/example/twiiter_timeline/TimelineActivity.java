package com.example.twiiter_timeline;

import java.util.ArrayList;

import com.example.twiiter_timeline.adapter.TweetAdapter;
import com.example.twiiter_timeline.db.DBOperations;
import com.example.twiiter_timeline.model.Tweet;
import com.example.twiiter_timeline.utils.ConstantsUtils;
import com.example.twiiter_timeline.utils.NetworkUtils;
import com.example.twiiter_timeline.utils.TwitterUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class TimelineActivity extends Activity {
	
	private ListView listView;
	private DBOperations dbOperations;
	
	private TweetAdapter adapter;
	public String TweetTerm = getIntent().getStringExtra("tag");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		listView = (ListView) findViewById(R.id.lv_timeline);
		
		dbOperations = new DBOperations(this);
		new TweetsSearchTask().execute();
	}
	
	private void updateListView(ArrayList<Tweet> tweets){
		adapter = new TweetAdapter(this, R.layout.row_tweet, tweets);
		listView.setAdapter(adapter);
	}

	private class TweetsSearchTask extends AsyncTask<Object, Void, ArrayList<Tweet>>{

		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			progressDialog = new ProgressDialog(TimelineActivity.this);
			progressDialog.setMessage(getResources().getString(R.string.label_tweet_search_loader));
			progressDialog.show();
		}
		
		@Override
		protected ArrayList<Tweet> doInBackground(Object... param) {
			if(NetworkUtils.haveNetworkConnection(TimelineActivity.this)){
				return TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
			}else{
				return dbOperations.getStatusUpdates();
			}
		}
		
		@Override
		protected void onPostExecute(ArrayList<Tweet> tweets){
			progressDialog.dismiss();

			if (tweets.isEmpty()) {
				Toast.makeText(TimelineActivity.this, getResources().getString(R.string.label_tweets_not_found),
						Toast.LENGTH_SHORT).show();
			} else {
				updateListView(tweets);
				Toast.makeText(TimelineActivity.this, getResources().getString(R.string.label_tweets_downloaded),
						Toast.LENGTH_SHORT).show();
			}
		}


	}
}
