package dk.noitso.vaerloesefh.views;

import java.util.List;

import noitso.chrono.stopwatch.R;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dk.noitso.vaerloesefh.data.SqliteHandler;
import dk.noitso.vaerloesefh.data.User;

public class LeaderBoardFragment extends ListFragment {
	private List<User> userList;
	private SqliteHandler dbHandler;
	private LeaderBoardAdapter adapter;
	
	public LeaderBoardFragment() {
	
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setBackgroundResource(R.drawable.background);
		dbHandler = new SqliteHandler(getActivity());
		
		userList = dbHandler.getUsersAndTimes();
		adapter = new LeaderBoardAdapter(userList);

		getListView().setBackgroundResource(R.drawable.background);
		setListAdapter(adapter);
		getListView().setDivider(new BitmapDrawable(getActivity().getResources(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.hline)));
	}
	
	private class LeaderBoardAdapter extends BaseAdapter {
		private List<User> list;
		
		public LeaderBoardAdapter(List<User> list) {
			this.list = list;
			User user1 = new User();
			user1.setName("Terkel");
			user1.setTotalTimeInMs(4000);
			this.list.add(user1);
			User user2 = new User();
			user1.setName("Kasper");
			user1.setTotalTimeInMs(14000);
			this.list.add(user2);
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

		
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
	            convertView = LayoutInflater.from(getActivity()).inflate(
	                    R.layout.leaderboard_view, null);
	        }
			
			if(position == 0) { // It's the leader!
				ImageView imageLeader = (ImageView) convertView.findViewById(R.id.leaderImageView);
				imageLeader.setVisibility(View.VISIBLE);
			} else { // It's not the leader!
				ImageView imageNotLeader = (ImageView) convertView.findViewById(R.id.notLeaderImageView);
				imageNotLeader.setVisibility(View.VISIBLE);
			}
			TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
			nameTextView.setText(this.list.get(position).getName());
			TextView timeTextView = (TextView) convertView.findViewById(R.id.timeTextView);
			timeTextView.setText("" + ((double)this.list.get(position).getTotalTimeInMs() / 1000) + " S");
			
			return convertView;
		}
	}
}
