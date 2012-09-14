package dk.noitso.vaerloesefh;

import java.util.ArrayList;
import java.util.List;

import noitso.chrono.stopwatch.R;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import dk.noitso.vaerloesefh.data.Observer;
import dk.noitso.vaerloesefh.data.Settings;
import dk.noitso.vaerloesefh.data.SqliteHandler;
import dk.noitso.vaerloesefh.data.User;
import dk.noitso.vaerloesefh.views.LeaderBoardFragment;
import dk.noitso.vaerloesefh.views.ObstructionListFragment;
import dk.noitso.vaerloesefh.views.StopwatchFragment;
import dk.noitso.vaerloesefh.views.UserListFragment;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	private SqliteHandler dbHandler;
	private List<Observer> observers = new ArrayList<Observer>();
	private ShareActionProvider shareActionProvider;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		dbHandler = new SqliteHandler(this);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab.
		// We can also use ActionBar.Tab#select() to do this if we have a
		// reference to the
		// Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter.
			// Also specify this Activity object, which implements the
			// TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		actionBar.setSubtitle("Flyvestation V¾rl¿se");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_users:
			Intent addUserIntent = new Intent(MainActivity.this,
					CreateUserActivity.class);
			startActivityForResult(addUserIntent, Settings.USER_SAVED);
			break;
		case R.id.clear_users:
			new AlertDialog.Builder(this).setTitle("Confirm Delete")
	        .setMessage("Do you want to clear EVERYTHING?")
	        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialogInterface, int i) {
	            	dbHandler.deleteAllUsers();
	    			updateViews();
	            }
	        })
	        .setNeutralButton("Cancel", null) // don't need to do anything but dismiss here
	        .create()
	        .show();
			
			
			break;
		case R.id.menu_share:
			Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			// Add data to the intent, the receiving app will decide what to do
			// with it.
			intent.putExtra(Intent.EXTRA_SUBJECT,
					"Time tables from Noitso's day at the track");
			intent.putExtra(Intent.EXTRA_TEXT,createCSVfile());
			startActivity(Intent.createChooser(intent,
					"How do you want to share the csv-file?"));
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		// shareActionProvider =
		// (ShareActionProvider)menu.findItem(R.id.menu_share).getActionProvider();
		// shareActionProvider.setShareIntent(getS
		return true;
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private UserListFragment userListFragment = new UserListFragment();
		private StopwatchFragment stopwatchFragment = new StopwatchFragment();
		private ObstructionListFragment obstructionListFragment = new ObstructionListFragment();
		private LeaderBoardFragment leaderBoardFragment = new LeaderBoardFragment();

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			addFragmentsToObserverList();
		}

		private void addFragmentsToObserverList() {
			if (observers != null) {
				observers.clear();
				observers.add(userListFragment);
				observers.add(stopwatchFragment);
				observers.add(leaderBoardFragment);
			}
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				return userListFragment;
			case 1:
				return stopwatchFragment;
			case 2:
				return obstructionListFragment;
			case 3:
				return leaderBoardFragment;
			default:
				return new Fragment();
			}
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			case 2:
				return getString(R.string.title_section3).toUpperCase();
			case 3:
				return getString(R.string.title_section4).toUpperCase();
			}
			return null;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(MainActivity.class.getSimpleName(), "OnResumse was called!");
		updateViews();
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(MainActivity.class.getSimpleName(), "OnPause was called!");
	}

	private void updateViews() {
		if (observers != null) {
			for (Observer observer : observers) {
				observer.update();
			}
		}
	}

	private String createCSVfile() {
		String csv = "Name;Total Time; f1;f2;f3;f4;f5;f6;f7;f8;f9;f10;f11;f12,f13;f14;f15;f16;f17;f18;f19;f20";
		List<User> usersAndTimesList = dbHandler.getUsersAndTimes(true);
		// get all times and add them to the given user
		for (int i = 1; i < 21; i++) {
			List<User> userAndObstructionTimes = dbHandler.getUsersAndObstructionTimes(i, true);

			for (int j =0; j<usersAndTimesList.size();j++) {
				int time = (int) userAndObstructionTimes.get(j).getTotalTimeInMs();
				
				usersAndTimesList.get(j).setTimeInArray(i, time);
			}
		}
		// create the csv string
		for (User u : usersAndTimesList) {
			csv+="\n"+u.getName()+u.getTimeArrayAsCSVFormat();
		}

		return csv;
	}
}
