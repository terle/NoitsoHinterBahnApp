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
		
		Obstruction obstruction2 = new Obstruction(context.getString(R.string.f2Name), context.getString(R.string.f2Safty), context.getString(R.string.f2Description), 2);
		list.add(obstruction2);
		
		Obstruction obstruction3 = new Obstruction(context.getString(R.string.f3Name), context.getString(R.string.f3Safty), context.getString(R.string.f3Description), 3);
		list.add(obstruction3);
		
		Obstruction obstruction4 = new Obstruction(context.getString(R.string.f4Name), context.getString(R.string.f4Safty), context.getString(R.string.f4Description), 4);
		list.add(obstruction4);
		
		Obstruction obstruction5 = new Obstruction(context.getString(R.string.f5Name), context.getString(R.string.f5Safty), context.getString(R.string.f5Description), 5);
		list.add(obstruction5);
		
		Obstruction obstruction6 = new Obstruction(context.getString(R.string.f6Name), context.getString(R.string.f6Safty), context.getString(R.string.f6Description), 6);
		list.add(obstruction6);
		
		Obstruction obstruction7 = new Obstruction(context.getString(R.string.f7Name), context.getString(R.string.f7Safty), context.getString(R.string.f7Description), 7);
		list.add(obstruction7);
		
		Obstruction obstruction8 = new Obstruction(context.getString(R.string.f8Name), context.getString(R.string.f8Safty), context.getString(R.string.f8Description), 8);
		list.add(obstruction8);
		
		Obstruction obstruction9 = new Obstruction(context.getString(R.string.f9Name), context.getString(R.string.f9Safty), context.getString(R.string.f9Description), 9);
		list.add(obstruction9);
		
		Obstruction obstruction10 = new Obstruction(context.getString(R.string.f10Name), context.getString(R.string.f10Safty), context.getString(R.string.f10Description), 10);
		list.add(obstruction10);
		
		Obstruction obstruction11 = new Obstruction(context.getString(R.string.f11Name), context.getString(R.string.f11Safty), context.getString(R.string.f11Description), 11);
		list.add(obstruction11);
		
		Obstruction obstruction12 = new Obstruction(context.getString(R.string.f12Name), context.getString(R.string.f12Safty), context.getString(R.string.f12Description), 12);
		list.add(obstruction12);
		
		Obstruction obstruction13 = new Obstruction(context.getString(R.string.f13Name), context.getString(R.string.f13Safty), context.getString(R.string.f13Description), 13);
		list.add(obstruction13);
		
		Obstruction obstruction14 = new Obstruction(context.getString(R.string.f14Name), context.getString(R.string.f14Safty), context.getString(R.string.f14Description), 14);
		list.add(obstruction14);
		
		Obstruction obstruction15 = new Obstruction(context.getString(R.string.f15Name), context.getString(R.string.f15Safty), context.getString(R.string.f15Description), 15);
		list.add(obstruction15);
		
		Obstruction obstruction16 = new Obstruction(context.getString(R.string.f16Name), context.getString(R.string.f16Safty), context.getString(R.string.f16Description), 16);
		list.add(obstruction16);
		
		Obstruction obstruction17 = new Obstruction(context.getString(R.string.f17Name), context.getString(R.string.f17Safty), context.getString(R.string.f17Description), 17);
		list.add(obstruction17);
		
		Obstruction obstruction18 = new Obstruction(context.getString(R.string.f18Name), context.getString(R.string.f18Safty), context.getString(R.string.f18Description), 18);
		list.add(obstruction18);
		
		Obstruction obstruction19 = new Obstruction(context.getString(R.string.f19Name), context.getString(R.string.f19Safty), context.getString(R.string.f19Description), 19);
		list.add(obstruction19);
		
		Obstruction obstruction20 = new Obstruction(context.getString(R.string.f20Name), context.getString(R.string.f20Safty), context.getString(R.string.f20Description), 20);
		
		list.add(obstruction20);
	
		return list;
	}
	
	public List<Obstruction> getObstructionList() {
		return obstructionList;
	}
}
