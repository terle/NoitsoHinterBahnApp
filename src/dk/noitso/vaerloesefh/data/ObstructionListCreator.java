package dk.noitso.vaerloesefh.data;

import java.util.ArrayList;
import java.util.List;

import noitso.chrono.stopwatch.R;
import android.content.Context;

public class ObstructionListCreator {
	private List<Obstruction> obstructionList;
	private static ObstructionListCreator instance;
	
	public static ObstructionListCreator getInstance(Context context) {
		if(instance == null) {
			instance = new ObstructionListCreator(context);
		}
		return instance;
	}
	
	private ObstructionListCreator(Context context) {
		createObstructions(context);
	}

	private List<Obstruction> createObstructions(Context context) {
		obstructionList = new ArrayList<Obstruction>();
		
		Obstruction obstruction1 = new Obstruction(context.getString(R.string.f1Name), context.getString(R.string.f1Safty), context.getString(R.string.f1Description), 1);
		obstruction1.addImage(R.drawable.f01rebstige1);
		obstruction1.addImage(R.drawable.f01rebstige2);
		obstructionList.add(obstruction1);
		
		Obstruction obstruction2 = new Obstruction(context.getString(R.string.f2Name), context.getString(R.string.f2Safty), context.getString(R.string.f2Description), 2);
		obstruction2.addImage(R.drawable.f02dobbeltbom1);
		obstruction2.addImage(R.drawable.f02dobbeltbom2);
		obstructionList.add(obstruction2);
		
		Obstruction obstruction3 = new Obstruction(context.getString(R.string.f3Name), context.getString(R.string.f3Safty), context.getString(R.string.f3Description), 3);
		obstructionList.add(obstruction3);
		
		Obstruction obstruction4 = new Obstruction(context.getString(R.string.f4Name), context.getString(R.string.f4Safty), context.getString(R.string.f4Description), 4);
		obstructionList.add(obstruction4);
		
		Obstruction obstruction5 = new Obstruction(context.getString(R.string.f5Name), context.getString(R.string.f5Safty), context.getString(R.string.f5Description), 5);
		obstructionList.add(obstruction5);
		
		Obstruction obstruction6 = new Obstruction(context.getString(R.string.f6Name), context.getString(R.string.f6Safty), context.getString(R.string.f6Description), 6);
		obstruction6.addImage(R.drawable.f06svenskbarre1);
		obstruction6.addImage(R.drawable.f06svenskbarre2);
		obstructionList.add(obstruction6);
		
		Obstruction obstruction7 = new Obstruction(context.getString(R.string.f7Name), context.getString(R.string.f7Safty), context.getString(R.string.f7Description), 7);
		obstruction7.addImage(R.drawable.f07balancebom1);
		obstruction7.addImage(R.drawable.f07balancebom2);
		obstructionList.add(obstruction7);
		
		Obstruction obstruction8 = new Obstruction(context.getString(R.string.f8Name), context.getString(R.string.f8Safty), context.getString(R.string.f8Description), 8);
		obstruction8.addImage(R.drawable.f08klippevaeg1);
		obstruction8.addImage(R.drawable.f08klippevaeg2);
		obstructionList.add(obstruction8);
		
		Obstruction obstruction9 = new Obstruction(context.getString(R.string.f9Name), context.getString(R.string.f9Safty), context.getString(R.string.f9Description), 9);
		obstruction9.addImage(R.drawable.f09overunder1);
		obstruction9.addImage(R.drawable.f09overunder2);
		obstructionList.add(obstruction9);
		
		Obstruction obstruction10 = new Obstruction(context.getString(R.string.f10Name), context.getString(R.string.f10Safty), context.getString(R.string.f10Description), 10);
		obstruction10.addImage(R.drawable.f10irskbaenk1);
		obstruction10.addImage(R.drawable.f10irskbaenk2);
		obstructionList.add(obstruction10);
		
		Obstruction obstruction11 = new Obstruction(context.getString(R.string.f11Name), context.getString(R.string.f11Safty), context.getString(R.string.f11Description), 11);
		obstruction11.addImage(R.drawable.f11hundehul1);
		obstruction11.addImage(R.drawable.f11hundehul2);
		obstructionList.add(obstruction11);
		
		Obstruction obstruction12 = new Obstruction(context.getString(R.string.f12Name), context.getString(R.string.f12Safty), context.getString(R.string.f12Description), 12);
		obstruction12.addImage(R.drawable.f12firebom1);
		obstruction12.addImage(R.drawable.f12firebom2);
		obstruction12.addImage(R.drawable.f12firebom3);
		obstruction12.addImage(R.drawable.f12firebom4);
		obstructionList.add(obstruction12);
		
		Obstruction obstruction13 = new Obstruction(context.getString(R.string.f13Name), context.getString(R.string.f13Safty), context.getString(R.string.f13Description), 13);
		obstruction13.addImage(R.drawable.f13banketmedgrav1);
		obstruction13.addImage(R.drawable.f13banketmedgrav2);
		obstructionList.add(obstruction13);
		
		Obstruction obstruction14 = new Obstruction(context.getString(R.string.f14Name), context.getString(R.string.f14Safty), context.getString(R.string.f14Description), 14);
		obstruction14.addImage(R.drawable.f14lillemur1);
		obstructionList.add(obstruction14);
		
		Obstruction obstruction15 = new Obstruction(context.getString(R.string.f15Name), context.getString(R.string.f15Safty), context.getString(R.string.f15Description), 15);
		obstruction15.addImage(R.drawable.f15sukkenesdal1);
		obstruction15.addImage(R.drawable.f15sukkenesdal2);
		obstructionList.add(obstruction15);
		
		Obstruction obstruction16 = new Obstruction(context.getString(R.string.f16Name), context.getString(R.string.f16Safty), context.getString(R.string.f16Description), 16);
		obstruction16.addImage(R.drawable.f16rudestige1);
		obstruction16.addImage(R.drawable.f16rudestige2);
		obstructionList.add(obstruction16);
		
		Obstruction obstruction17 = new Obstruction(context.getString(R.string.f17Name), context.getString(R.string.f17Safty), context.getString(R.string.f17Description), 17);
		obstruction17.addImage(R.drawable.f172metermur1);
		obstruction17.addImage(R.drawable.f172metermur2);
		obstructionList.add(obstruction17);
		
		Obstruction obstruction18 = new Obstruction(context.getString(R.string.f18Name), context.getString(R.string.f18Safty), context.getString(R.string.f18Description), 18);
		obstruction18.addImage(R.drawable.f18zigzagbom1);
		obstruction18.addImage(R.drawable.f18zigzagbom2);
		obstructionList.add(obstruction18);
		
		Obstruction obstruction19 = new Obstruction(context.getString(R.string.f19Name), context.getString(R.string.f19Safty), context.getString(R.string.f19Description), 19);
		obstructionList.add(obstruction19);
		
		Obstruction obstruction20 = new Obstruction(context.getString(R.string.f20Name), context.getString(R.string.f20Safty), context.getString(R.string.f20Description), 20);
		obstruction20.addImage(R.drawable.f20tremure1);
		obstruction20.addImage(R.drawable.f20tremure2);
		obstructionList.add(obstruction20);
	
		return obstructionList;
	}
	
	public List<Obstruction> getObstructionList() {
		return obstructionList;
	}
	
	public List<String> getObstructionNames() {
		List<String> obstructionNamesList = new ArrayList<String>();
		for(Obstruction obstruction : obstructionList) {
			obstructionNamesList.add("#" + obstruction.getNumber() + " - " + obstruction.getName());
		}
		return obstructionNamesList;
	}
}
