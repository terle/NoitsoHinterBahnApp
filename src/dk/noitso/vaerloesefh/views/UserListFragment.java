package dk.noitso.vaerloesefh.views;

import java.util.List;

import noitso.chrono.stopwatch.R;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import dk.noitso.vaerloesefh.data.SqliteHandler;
import dk.noitso.vaerloesefh.data.Observer;

public class UserListFragment extends ListFragment implements Observer {
	private ArrayAdapter<String> adapter;
	private SqliteHandler dbHandler = null;
	private List<String> list;
	private String selectedUser = "";
	public static final String ARG_SECTION_NUMBER = "section_number";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		dbHandler = new SqliteHandler(getActivity());
		list = dbHandler.getUsers();
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
		getListView().setBackgroundResource(R.drawable.background);
		setListAdapter(adapter);
		registerForContextMenu(getListView());
		getListView().setDivider(new BitmapDrawable(getActivity().getResources(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.hline)));
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
		menu.add("Export data to xml");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle().equals("Delete")) {
			// Delete user...
			Log.i("FragmentList", "Need to delete user...");
			if(dbHandler.deleteUser(selectedUser)) {
				update();
			}
		} else if (item.getTitle().equals("Edit")) {
			// Edit user...
			Intent editUserIntent = new Intent(getActivity(), CreateUserActivity.class);
			editUserIntent.putExtra("username", selectedUser);
			startActivity(editUserIntent);
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
	public void update() {
		if(adapter != null) {
			list = dbHandler.getUsers();
			adapter.clear();
			for(String user : list) {
				adapter.add(user);
			}
			adapter.notifyDataSetChanged();
		}
	}
}