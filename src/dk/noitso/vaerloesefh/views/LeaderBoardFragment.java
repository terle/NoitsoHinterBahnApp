package dk.noitso.vaerloesefh.views;

import java.util.ArrayList;
import java.util.List;

import noitso.chrono.stopwatch.R;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import dk.noitso.vaerloesefh.data.Observer;
import dk.noitso.vaerloesefh.data.ObstructionListCreator;
import dk.noitso.vaerloesefh.data.SqliteHandler;
import dk.noitso.vaerloesefh.data.User;

public class LeaderBoardFragment extends ListFragment implements OnItemSelectedListener, Observer {
	private List<User> userList;
	private SqliteHandler dbHandler;
	private LeaderBoardAdapter adapter;
	private Spinner timeSpinner;
	
	public LeaderBoardFragment() {
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dbHandler = new SqliteHandler(getActivity());
		
		// Setting the time spinner.
		setListAdapter(null);
		timeSpinner = new Spinner(getActivity());
		timeSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getTimeSpinnerNames()));
		timeSpinner.setOnItemSelectedListener(this);
		getListView().addHeaderView(timeSpinner);
		
		// Setting the adapter for the list.
		userList = dbHandler.getUsersAndTimes(false);
		adapter = new LeaderBoardAdapter(userList);
		setListAdapter(adapter);
				
		getListView().setBackgroundResource(R.drawable.background);
		getListView().setDivider(new BitmapDrawable(getActivity().getResources(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.hline)));
	}

	private List<String> getTimeSpinnerNames() {
		ObstructionListCreator olc = ObstructionListCreator.getInstance(getActivity());
		List<String> timeNameList = olc.getObstructionNames();
		timeNameList.add(0, "Total time");
		return timeNameList;
	}
	
	private class LeaderBoardAdapter extends BaseAdapter {
		private List<User> list;
		
		public LeaderBoardAdapter(List<User> list) {
			this.list = list;
		}
		
		public int getCount() {
			return this.list.size();
		}

		public User getItem(int position) {
			return this.list.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public void clear() {
			this.list.clear();
		}
		
		public void add(User user) {
			this.list.add(user);
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
	            convertView = LayoutInflater.from(getActivity()).inflate(
	                    R.layout.leaderboard_view, null);
	        }
			ImageView imageLeader = (ImageView) convertView.findViewById(R.id.leaderImageView);
			ImageView imageNotLeader = (ImageView) convertView.findViewById(R.id.notLeaderImageView);
			
			if(position == 0 && this.list.size() > 1 && this.list.get(position).getTotalTimeInMs() > 0) { // It's the leader!
				imageNotLeader.setVisibility(View.GONE);				
				imageLeader.setVisibility(View.VISIBLE);
			} else { // It's not the leader!
				imageLeader.setVisibility(View.GONE);
				imageNotLeader.setVisibility(View.VISIBLE);
			}
			TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
			nameTextView.setText(this.list.get(position).getName());
			TextView timeTextView = (TextView) convertView.findViewById(R.id.timeTextView);
			timeTextView.setText("" + ((double)this.list.get(position).getTotalTimeInMs() / 1000) + " s");
			
			return convertView;
		}
	}

	public void update() {
		if(adapter != null) {
			userList = dbHandler.getUsersAndTimes(false);
			adapter.clear();
			for(User user : userList) {
				adapter.add(user);
			}
			adapter.notifyDataSetChanged();
		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		List<User> listOfUsersAndTimes = new ArrayList<User>();
		if(position == 0) {
			Log.i(LeaderBoardFragment.class.getSimpleName(), "Position is: " + position);
			listOfUsersAndTimes = dbHandler.getUsersAndTimes(false);
		} else {
			Log.i(LeaderBoardFragment.class.getSimpleName(), "Position is: " + position);
			listOfUsersAndTimes = dbHandler.getUsersAndObstructionTimes(position, false);
		}
		if(adapter != null) {
			adapter.clear();
			for(User user : listOfUsersAndTimes) {
				adapter.add(user);
			}
			adapter.notifyDataSetChanged();
		}
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
}
