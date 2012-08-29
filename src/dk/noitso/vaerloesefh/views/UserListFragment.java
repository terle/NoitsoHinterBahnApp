package dk.noitso.vaerloesefh.views;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import dk.noitso.vaerloesefh.CreateUserActivity;
import dk.noitso.vaerloesefh.data.Settings;
import dk.noitso.vaerloesefh.data.SqliteHandler;

public class UserListFragment extends ListFragment {
	private ArrayAdapter<String> adapter;
	private SqliteHandler dbHandler = null;
	private List<String> list = null;
	private String selectedUser = "";
	public static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		dbHandler = new SqliteHandler(getActivity());
		list = dbHandler.getUsers();
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
		
		setListAdapter(adapter);
		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		    if (v.getId() == android.R.id.list) {
		    	selectedUser = list.get(info.position);
		    }
		menu.add("Delete");
		menu.add("Edit");
	}

	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(resultCode == Settings.USER_SAVED){
    		list.clear();
			list = dbHandler.getUsers();
			adapter.clear();
			for(String user : list) {
				adapter.add(user);
			}
			adapter.notifyDataSetChanged();
    	}
    }
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle().equals("Delete")) {
			// Delete user...
			Log.i("FragmentList", "Need to delete user...");
			if(dbHandler.deleteUser(selectedUser)) {
				list.clear();
				list = dbHandler.getUsers();
				adapter.clear();
				for(String user : list) {
					adapter.add(user);
				}
				adapter.notifyDataSetChanged();
			}
		} else if (item.getTitle().equals("Edit")) {
			// Edit user...
			Intent editUserIntent = new Intent(getActivity(), CreateUserActivity.class);
			editUserIntent.putExtra("username", selectedUser);
			startActivityForResult(editUserIntent, Settings.USER_SAVED);
			Log.i("FragmentList", "Need to edit user...");
		}
		int menuItemIndex = item.getItemId();
		Log.i("FragmentList",
				"Item #" + menuItemIndex + ", name " + item.getTitle());
		return true;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.i("FragmentList", "Item clicked: " + id);
	}

	@Override
	public void onResume() {
		super.onResume();
		getActivity().registerReceiver(userSavedReceiver, userSavedFilter);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		getActivity().unregisterReceiver(userSavedReceiver);
	}
	
	private IntentFilter userSavedFilter = new IntentFilter("dk.noitso.vaerloesefh.data.Settings.USER_SAVED");
	private BroadcastReceiver userSavedReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d(UserListFragment.class.getSimpleName(), "I received a broadcast!");
			if (intent.getAction().equals("dk.noitso.vaerloesefh.data.Settings.USER_SAVED")) {
				Log.d(UserListFragment.class.getSimpleName(), "It was the right broadcast!");
				list.clear();
				list = dbHandler.getUsers();
				Log.d(UserListFragment.class.getSimpleName(), "Numbers of users are: " + list.size());
				adapter.clear();
				for(String user : list) {
					adapter.add(user);
				}
				adapter.notifyDataSetChanged();
			} else {
				Log.d(UserListFragment.class.getSimpleName(), "It was the wrong broadcast!");
			}
		}
	};
}