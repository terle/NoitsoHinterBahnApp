package dk.noitso.vaerloesefh.data;

import java.util.ArrayList;
import java.util.List;

import dk.noitso.vaerloesefh.R;

import android.content.Context;

public class ObstructionListCreator {
	private List<Obstruction> obstructionList;
	private Context context;
	
	public ObstructionListCreator(Context context) {
		this.context = context;
		this.obstructionList = new ArrayList<Obstruction>();
	}

	public List<Obstruction> createObstructions() {
		List<Obstruction> list = new ArrayList<Obstruction>();
		
		Obstruction obstruction1 = new Obstruction(context.getString(R.string.f1Name), context.getString(R.string.f1Safty), context.getString(R.string.f1Description), 1);
		
		list.add(obstruction1);
		return list;
	}
	
	public List<Obstruction> getObstructionList() {
		return obstructionList;
	}
}
