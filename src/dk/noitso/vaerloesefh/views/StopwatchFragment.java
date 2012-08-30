package dk.noitso.vaerloesefh.views;

import java.util.List;

import noitso.chrono.stopwatch.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import dk.noitso.vaerloesefh.data.Obstruction;
import dk.noitso.vaerloesefh.data.ObstructionListCreator;
import dk.noitso.vaerloesefh.data.Settings;
import dk.noitso.vaerloesefh.data.SqliteHandler;

public class StopwatchFragment extends Fragment implements OnClickListener {
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	private TextView timerMsTextView, timerTextView; // Temporary TextView
	private Button lapButton, resetButton, startButton, stopButton, finalResetButton; // Temporary Button
	private Handler mHandler = new Handler();
	private long startTime;
	private long elapsedTime;
	private final int REFRESH_RATE = 10;
	private String hours, minutes, seconds, milliseconds;
	private long secs, mins, hrs, msecs;
	private boolean stopped = false;
	private ImageView imageView1, imageView2;
	private TextView nameNumberTextView, startTimeTextView, endTimeTextView; 
	private View v;
	private List<Obstruction> obstructionList;
	private Spinner nameSpinner;
	private SqliteHandler dbHandler;
	
	private int numberOfLaps = 0;
	
	public StopwatchFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		
		dbHandler = new SqliteHandler(getActivity());
		
		v = inflater.inflate(R.layout.stopwatch_layout, container, false);
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "altehaasgroteskbold.ttf");
		timerTextView = (TextView) v.findViewById(R.id.timer);
		timerTextView.setTypeface(font);
		timerMsTextView = (TextView) v.findViewById(R.id.timerMs);
		timerMsTextView.setTypeface(font);
		font = Typeface.createFromAsset(getActivity().getAssets(), "coolvetica.ttf");
		startButton = (Button) v.findViewById(R.id.startButton);
		startButton.setTypeface(font);
		startButton.setOnClickListener(this);
		resetButton = (Button) v.findViewById(R.id.resetButton);
		resetButton.setTypeface(font);
		resetButton.setOnClickListener(this);
		stopButton = (Button) v.findViewById(R.id.stopButton);
		stopButton.setTypeface(font);
		stopButton.setOnClickListener(this);
		lapButton = (Button) v.findViewById(R.id.lapButton);
		lapButton.setTypeface(font);
		lapButton.setOnClickListener(this);
		finalResetButton = (Button) v.findViewById(R.id.finalResetButton);
		finalResetButton.setTypeface(font);
		finalResetButton.setOnClickListener(this);
		
		nameSpinner = (Spinner) v.findViewById(R.id.nameSpinner);
		List<String> userList = dbHandler.getUsers();
		userList.add(0, "Select a user");
		nameSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, userList));
		
		initializeObstructionItems();
		return v;
	}
	
	private void initializeObstructionItems() {
		imageView1 = (ImageView) v.findViewById(R.id.obstructionImageView1);
		imageView2 = (ImageView) v.findViewById(R.id.obstructionImageView2);
		
		nameNumberTextView = (TextView) v.findViewById(R.id.nameNumberTextView);
		startTimeTextView = (TextView) v.findViewById(R.id.startTimeTextView);
		endTimeTextView = (TextView) v.findViewById(R.id.endTimeTextView);
		
		ObstructionListCreator olc = ObstructionListCreator.getInstance(getActivity());
		obstructionList = olc.getObstructionList();
		
		setObstructionToShow(0);
	}

	private void showStopButton() {
		startButton.setVisibility(View.GONE);
		resetButton.setVisibility(View.GONE);
		stopButton.setVisibility(View.VISIBLE);
		lapButton.setVisibility(View.VISIBLE);
		finalResetButton.setVisibility(View.GONE);
	}

	private void hideStopButton() {
		startButton.setVisibility(View.VISIBLE);
		resetButton.setVisibility(View.VISIBLE);
		stopButton.setVisibility(View.GONE);
		lapButton.setVisibility(View.GONE);
		finalResetButton.setVisibility(View.GONE);
	}
	
	private void showFinalResetButton() {
		startButton.setVisibility(View.GONE);
		resetButton.setVisibility(View.GONE);
		stopButton.setVisibility(View.GONE);
		lapButton.setVisibility(View.GONE);
		finalResetButton.setVisibility(View.VISIBLE);
	}

	private void updateTimer(float time) {
		secs = (long) (time / 1000);
		mins = (long) ((time / 1000) / 60);
		hrs = (long) (((time / 1000) / 60) / 60);

		/*
		 * Convert the seconds to String and format to ensure it has a leading
		 * zero when required
		 */
		secs = secs % 60;
		seconds = String.valueOf(secs);
		if (secs == 0) {
			seconds = "00";
		}
		if (secs < 10 && secs > 0) {
			seconds = "0" + seconds;
		}

		/* Convert the minutes to String and format the String */

		mins = mins % 60;
		minutes = String.valueOf(mins);
		if (mins == 0) {
			minutes = "00";
		}
		if (mins < 10 && mins > 0) {
			minutes = "0" + minutes;
		}

		/* Convert the hours to String and format the String */

		hours = String.valueOf(hrs);
		if (hrs == 0) {
			hours = "00";
		}
		if (hrs < 10 && hrs > 0) {
			hours = "0" + hours;
		}

		/*
		 * Although we are not using milliseconds on the timer in this example I
		 * included the code in the event that you wanted to include it on your
		 * own
		 */
		milliseconds = String.valueOf((long) time);
		if (milliseconds.length() == 2) {
			milliseconds = "0" + milliseconds;
		}
		if (milliseconds.length() <= 1) {
			milliseconds = "00";
		}
		milliseconds = milliseconds.substring(milliseconds.length() - 3,
				milliseconds.length() - 2);

		/* Setting the timer text to the elapsed time */
		((TextView) v.findViewById(R.id.timer)).setText(minutes+ ":" + seconds);//setText(hours + ":" + minutes+ ":" + seconds);
		((TextView) v.findViewById(R.id.timerMs)).setText("." + milliseconds);
	}

	private Runnable startTimer = new Runnable() {
		public void run() {
			elapsedTime = System.currentTimeMillis() - startTime;
			updateTimer(elapsedTime);
			mHandler.postDelayed(this, REFRESH_RATE);
		}
	};

	private void setObstructionToShow(int obstructionNumber) {
		imageView1.setVisibility(View.GONE);
		imageView2.setVisibility(View.GONE);
		
		Obstruction obstruction = obstructionList.get(obstructionNumber);
		if(!obstruction.getImages().isEmpty()) {
			for(int i = 0; i < obstruction.getImages().size(); i++) {
				switch (i) {
				case 0:
					int imageRes1 = obstruction.getImages().get(i);
					imageView1.setImageResource(imageRes1);
					imageView1.setVisibility(View.VISIBLE);
					break;
				case 1:
					int imageRes2 = obstruction.getImages().get(i);
					imageView2.setImageResource(imageRes2);
					imageView2.setVisibility(View.VISIBLE);
					break;
				default:
					break;
				}
			}	
		}
		nameNumberTextView.setText("#" + obstruction.getNumber() + " - " + obstruction.getName());
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.startButton:
			showStopButton();
			if (stopped) {
				startTime = System.currentTimeMillis() - elapsedTime;
			} else {
				startTime = System.currentTimeMillis();
			}
			mHandler.removeCallbacks(startTimer);
			mHandler.postDelayed(startTimer, 0);
			break;
		case R.id.stopButton:
			hideStopButton();
			mHandler.removeCallbacks(startTimer);
			stopped = true;
			break;
		case R.id.resetButton:
			stopped = false;
			timerTextView.setText("00:00");
			timerMsTextView.setText(".0");
			startTimeTextView.setText("Start time: -:-.-");
			endTimeTextView.setText("End Time: -:-.-");
			setObstructionToShow(0);
			break;
		case R.id.lapButton:
			numberOfLaps++;
			if(numberOfLaps >= (Settings.NUMBER_OF_OBSTRUCTIONS * 2)) {
				// STOP! We've reached the number of obstructions and should be done!
				mHandler.removeCallbacks(startTimer);
				stopped = true;
				endTimeTextView.setText("End time: " +mins+":"+secs+"."+milliseconds) ;
				showFinalResetButton();
				numberOfLaps=0;
				
				break;
			} else { 
				if(numberOfLaps % 2 == 0) { // Set endtime.
					endTimeTextView.setText("End time: " +mins+":"+secs+"."+milliseconds) ;//((double)elapsedTime/1000) + " ms");
					setObstructionToShow(numberOfLaps/2);
					//Reset timers labels, to show ... nothing at next obstacle
					startTimeTextView.setText("Start time: -:-.-");
					endTimeTextView.setText("End Time: -:-.-");
					//TODO: Make it wait 100 ms before changing view in a beautiful way
					// TODO:Change view in a beautiful way... or maybe not ;-) 
				} else { // Set starttime
					startTimeTextView.setText("Start time: " +mins+":"+secs+"."+milliseconds) ;//+ ((double)elapsedTime/1000) + " ms");
				}
			}
			break;
		case R.id.finalResetButton:
			//Everything is set back to start conditions
			timerTextView.setText("00:00");
			timerMsTextView.setText(".0");
			elapsedTime=0;
			setObstructionToShow(0);
			hideStopButton();
		break;
		
		default:
			break;
		}
	}
}
