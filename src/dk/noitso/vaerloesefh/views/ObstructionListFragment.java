package dk.noitso.vaerloesefh.views;

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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dk.noitso.vaerloesefh.data.Obstruction;
import dk.noitso.vaerloesefh.data.ObstructionListCreator;

public class ObstructionListFragment extends ListFragment {
	public static final String ARG_SECTION_NUMBER = "section_number";
	private List<Obstruction> obstructionList;
	private ObstructionAdapter obstructionAdapter;
	
	public ObstructionListFragment() {
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setBackgroundResource(R.drawable.background);
		ObstructionListCreator olc = ObstructionListCreator.getInstance(getActivity());
		obstructionList = olc.getObstructionList();
		
		obstructionAdapter = new ObstructionAdapter(obstructionList);
		
		getListView().setBackgroundResource(R.drawable.background);
		setListAdapter(obstructionAdapter);
		getListView().setDivider(new BitmapDrawable(getActivity().getResources(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.hline)));
	}
	
	private class ObstructionAdapter extends BaseAdapter {
		private List<Obstruction> list;
		
		public ObstructionAdapter(List<Obstruction> list) {
			this.list = list;
		}
		
		
		@Override
		public int getCount() {
			return this.list.size();
		}

		@Override
		public Obstruction getItem(int position) {
			return this.list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
	            convertView = LayoutInflater.from(getActivity()).inflate(
	                    R.layout.obstruction_view, null);
	        }
			
			List<Integer> imageList = this.list.get(position).getImages();
				for(int i = 0; i < imageList.size(); i++) {
					switch (i) {
					case 0:
						ImageView image1 = (ImageView) convertView.findViewById(R.id.imageView1);
						image1.setImageResource(imageList.get(i));
						image1.setVisibility(View.VISIBLE);
						break;
					case 1:
						ImageView image2 = (ImageView) convertView.findViewById(R.id.imageView2);
						image2.setImageResource(imageList.get(i));
						image2.setVisibility(View.VISIBLE);
						break;
					case 2:
						ImageView image3 = (ImageView) convertView.findViewById(R.id.imageView3);
						image3.setImageResource(imageList.get(i));
						image3.setVisibility(View.VISIBLE);
						break;
					case 3:
						ImageView image4 = (ImageView) convertView.findViewById(R.id.imageView4);
						image4.setImageResource(imageList.get(i));
						image4.setVisibility(View.VISIBLE);
						break;
					default:
						break;
					}
				}
				
			Log.d(ObstructionListFragment.class.getSimpleName(), this.list.get(position).toString());

	        TextView nameText = (TextView) convertView.findViewById(R.id.nameTextView);
	        TextView numberText = (TextView) convertView.findViewById(R.id.numberTextView);
	        TextView descriptionText = (TextView) convertView.findViewById(R.id.descriptionTextView);
	        TextView safetyText = (TextView) convertView.findViewById(R.id.safetyTextView);
	        nameText.setText(this.list.get(position).getName());
	        numberText.setText("#" + this.list.get(position).getNumber());
	        descriptionText.setText(this.list.get(position).getDescription());
	        if(!this.list.get(position).getSafety().isEmpty()) {
	        	safetyText.setText(this.list.get(position).getSafety() + "\n");
	        } else {
	        	safetyText.setText("Ingen\n");
	        }
	        return convertView;
		}
	}
}    