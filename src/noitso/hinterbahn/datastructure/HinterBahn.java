//package noitso.hinterbahn.datastructure;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.imageio.ImageIO;
//
//
//public final class HinterBahn {
//	
//	ArrayList<Forhindring> forhindringer;
//	
//	public HinterBahn(){
//		forhindringer = new ArrayList<Forhindring>();
//		BufferedImage img1 = null;
//		BufferedImage img2 = null;
//		ArrayList<BufferedImage> imgList = new ArrayList<BufferedImage>();
//		
//		Forhindring f1 = new Forhindring();
//		f1.nummer = 1;
//		f1.navn = "Rebstigen";
//		f1.krav="Klatre over FH.";
//		f1.sikkerhed="Mindst en hånd og en fod skal have kontakt med FH.\nNedspring foretages fra et af de nederste\ntrin.\nDen maksimale nedsprings højde på\n2 m skal overholdes";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\1_rebstige(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\1_rebstige(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//		}
//		if(imgList.size()>0) f1.imgList= imgList;	
//		forhindringer.add(f1);
//		
//		Forhindring f2 = new Forhindring();
//		f2.nummer = 2;
//		f2.navn = "Dobbelt bom";
//		f2.krav="Over de to bomme med berøring af jorden mellem dem.";
//		f2.sikkerhed=null;
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\2_dobbeltBom(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\2_dobbeltBom(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f2.imgList= imgList;
//		
//		forhindringer.add(f2);
//		
//		Forhindring f3 = new Forhindring();
//		f3.nummer = 3;
//		f3.navn = "Hoptråd";
//		f3.krav="Hoppe over trådene";
//		f3.sikkerhed="Passagen gennemføres i moderat tempo.";
//		forhindringer.add(f3);	
//		
//		Forhindring f4 = new Forhindring();
//		f4.nummer = 4;
//		f4.navn = "Krybetråd.";
//		f4.krav="Krybe under trådene";
//		f4.sikkerhed=null;
//		forhindringer.add(f4);
//		
//		Forhindring f5 = new Forhindring();
//		f5.nummer = 5;
//		f5.navn = "Vadested.";
//		f5.krav="Passere FH uden at træde ned i sandet";
//		f5.sikkerhed="Passagen gennemføres i moderat tempo";
//		forhindringer.add(f5);
//		
//		Forhindring f6 = new Forhindring();
//		f6.nummer = 6;
//		f6.navn = "Svensk barre";
//		f6.krav="Over den øverste bom";
//		f6.sikkerhed=null;
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\6_SvenskBarre(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\6_SvenskBarre(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f6.imgList= imgList;
//		
//		forhindringer.add(f6);
//		
//		Forhindring f7 = new Forhindring();
//		f7.nummer = 7;
//		f7.navn = "Balancebom";
//		f7.krav="Over FH uden at røre jorden mellem de to streger, før og efter FH";
//		f7.sikkerhed="Passagen gennemføres i moderat tempo";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\7_BalanceBom(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\7_BalanceBom(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f7.imgList= imgList;
//		
//		forhindringer.add(f7);
//		
//		Forhindring f8 = new Forhindring();
//		f8.nummer = 8;
//		f8.navn = "Klippevæg.";
//		f8.krav="Over væggen";
//		f8.sikkerhed="Tovet må ikke komme mellem benene, og den maksimale nedspringshøjde på 2 m skal overholdes";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\8_KlippeVæg(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\8_KlippeVæg(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f8.imgList= imgList;
//		
//		forhindringer.add(f8);
//		
//		Forhindring f9 = new Forhindring();
//		f9.nummer = 9;
//		f9.navn = "Over/under";
//		f9.krav="Over den første bom under den næste osv.";
//		f9.sikkerhed="Passagen gennemføres i moderat tempo.";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\9_OverUnder(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\9_OverUnder(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f9.imgList= imgList;
//		
//		forhindringer.add(f9);
//		
//		Forhindring f10 = new Forhindring();
//		f10.nummer = 10;
//		f10.navn = "Irske bænk";
//		f10.krav="Over bænken";
//		f10.sikkerhed=null;
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\10_IrskBænk(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\10_IrskBænk(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f10.imgList= imgList;
//		
//		forhindringer.add(f10);
//		
//		Forhindring f11 = new Forhindring();
//		f11.nummer = 11;
//		f11.navn = "Hundehul";
//		f11.krav="Kravle igennem hullet, derefter over første bom og under anden bom, som ved FH nr. 9.";
//		f11.sikkerhed="Passagen gennemføres i moderat tempo.";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\11_HundeHul(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\11_HundeHul(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f11.imgList= imgList;	
//		
//		forhindringer.add(f11);
//		
//		Forhindring f12 = new Forhindring();
//		f12.nummer = 12;
//		f12.navn = "Firebom.";
//		f12.krav="Over alle fire bomme";
//		f12.sikkerhed="Passagen gennemføres i moderat tempo. Den maksimale nedspringshøjde på 2 m skal overholdes";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\12_FireBom(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\12_FireBom(2).PNG"));
//		    BufferedImage img3 = ImageIO.read(new File("rod\\Hinterbahn\\12_FireBom(3).PNG"));
//		    BufferedImage img4 = ImageIO.read(new File("rod\\Hinterbahn\\12_FireBom(4).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		    imgList.add(img3);
//		    imgList.add(img4);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f12.imgList= imgList;	
//		
//		forhindringer.add(f12);
//		
//		Forhindring f13 = new Forhindring();
//		f13.nummer = 13;
//		f13.navn = "Banket med grav";
//		f13.krav="Over FH.";
//		f13.sikkerhed=null;
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\13_BanketMedGrav(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\13_BanketMedGrav(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f13.imgList= imgList;	
//		
//		forhindringer.add(f13);
//		
//		Forhindring f14 = new Forhindring();
//		f14.nummer = 14;
//		f14.navn = "Lille mur";
//		f14.krav="Over muren";
//		f14.sikkerhed="Passage gennemføres i moderat tempo.";
//		
//		img1 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\14_LilleMur(1).PNG"));
//		    imgList.add(img1);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f14.imgList= imgList;	
//		
//		forhindringer.add(f14);
//		
//		Forhindring f15 = new Forhindring();
//		f15.nummer = 15;
//		f15.navn = "Sukkenes dal";
//		f15.krav="Ned og røre jorden og op";
//		f15.sikkerhed=null;
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\15_SukkenesDal(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\15_SukkenesDal(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f15.imgList= imgList;	
//		
//		forhindringer.add(f15);
//		
//		Forhindring f16 = new Forhindring();
//		f16.nummer = 16;
//		f16.navn = "Rudestige.";
//		f16.krav="Kravle over FH";
//		f16.sikkerhed="Den maksimale nedspringshøjde på 2 m skal overholdes";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\16_RudeStige(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\16_RudeStige(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f16.imgList= imgList;	
//		
//		forhindringer.add(f16);
//		
//		Forhindring f17 = new Forhindring();
//		f17.nummer = 17;
//		f17.navn = "2 meter mur.";
//		f17.krav="Over muren";
//		f17.sikkerhed=null;
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\17_2MeterMur(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\17_2MeterMur(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f17.imgList= imgList;
//		
//		forhindringer.add(f17);
//		
//		Forhindring f18 = new Forhindring();
//		f18.nummer = 18;
//		f18.navn = "Zig-zag bom";
//		f18.krav="Ikke røre jorden mellem de to streger, før og efter FH";
//		f18.sikkerhed="Passagen gennemføres i moderat tempo";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\18_ZigZagBom(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\18_ZigZagBom(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f18.imgList= imgList;
//		
//		forhindringer.add(f18);
//		
//		Forhindring f19 = new Forhindring();
//		f19.nummer = 19;
//		f19.navn = "Labyrint.";
//		f19.krav="Følg passagen gennem labyrinten";
//		f19.sikkerhed=null;
//		forhindringer.add(f19);
//		
//		Forhindring f20 = new Forhindring();
//		f20.nummer = 20;
//		f20.navn = "Tre mure";
//		f20.krav="Over murene";
//		f20.sikkerhed="Passagen gennemføres i moderat tempo";
//		
//		img1 = null;
//		img2 = null;
//		imgList = new ArrayList<BufferedImage>();
//		try {
//		    img1 = ImageIO.read(new File("rod\\Hinterbahn\\20_TreMure(1).PNG"));
//		    img2 = ImageIO.read(new File("rod\\Hinterbahn\\20_TreMure(2).PNG"));
//		    imgList.add(img1);
//		    imgList.add(img2);
//		} catch (IOException e) {
//			System.out.println("Fejl i læsning af billede.\n"+e.getMessage());
//		}
//		if(imgList.size()>0) f20.imgList= imgList;
//		
//		forhindringer.add(f20);
//		
//	}
//
//	public Forhindring getForhindring(int nr){
//		return forhindringer.get(nr-1);
//	}
//}
