package lorenz_sort;
import java.util.*;
import java.security.SecureRandom;
/**
 * I made this program for fun in May 2019. It uses solutions to the Lorenz equations to determine teams for Escape Rooms.
 * There are much easier ways of randomizing teams, but I'm fond of the Lorenz equations after working with them in previous
 * projects.
 */
public class driverClass {
	
	public static void main(String[] args){
		workerClass newClass = new workerClass();
		newClass.math();
		newClass.teamSort();
		return;
		}
}

class workerClass{
	
	ArrayList sol = new ArrayList();
	ArrayList xsoln = new ArrayList();
	ArrayList ysoln = new ArrayList();
	ArrayList zsoln = new ArrayList();

	public void math() {
		//parameters used by Lorenz boi (according to Wikipedia)
		float sigma = 10;
		float rho = 28;
		float beta = 8/3;
		
		//arbitrary starting conditions
		float x, y, z;
		double rand = Math.random();
		System.out.println("Random value: " + rand);
		
		x = y = z = (float) rand; //0.15;
		
		float dt = (float) 0.01;
		
		for(int a = 0; a < 50; a++) {
			//lorenz equations
			float dx = (sigma*(y - x))*dt;
			float dy = (x*(rho - z) - y)*dt;
			float dz = (x*y - beta*z)*dt;	
			
			x = x + dx;
			y = y + dy;
			z = z + dz;
			
			//pass solutions to array
			collectSoln(x, y, z);
		}	
		collectArr();
		printArray();
	}
	
	public void collectSoln(float x, float y, float z){
		//add solutions to respective arraylists
		xsoln.add((float) x);
		ysoln.add((float) y);
		zsoln.add((float) z);
	}
	
	public void collectArr() {
		//add array to the main arraylist
		sol.add(xsoln);
		sol.add(ysoln);
		sol.add(zsoln);	
	}
	
	public void printArray() {
		int mySize = sol.size();
		int myArrSize = xsoln.size();
		float sum = 0;
		System.out.println(mySize);
		for(int q = 0; q < mySize; q++) {
//			sum = xsoln.get(q) + ysoln.get(q) + zsoln.get(q);
//			System.out.println("x: \n" + sol.get(q));// + "\ty: " + ysoln.get(q) + "\tz: " + zsoln.get(q));
		}
		for(int q = 0; q < myArrSize; q++) {
//			sum = xsoln.get(q) + ysoln.get(q) + zsoln.get(q);
			System.out.println("x: \t" + xsoln.get(q) + "\ty: " + ysoln.get(q) + "\tz: " + zsoln.get(q));
		}
		
//		System.out.println(sol.get(2).getOject());
//		Object[] ob = sol.toArray();
//
//	      System.out.println("Printing elements from first to last:"); 
//	      for (Object value : ob) {
//	         System.out.println("Number = " + value);
//	      }
	}
	
	public int randomPick(){
		SecureRandom rand = new SecureRandom();
		int selectedTeam = 1;
		int randAxis = rand.nextInt(3);
		int randSoln = rand.nextInt(50);
		float roundOut = 0;
		float pickSoln = 0;
//		System.out.println("Random axis: " + randAxis);
		
		if(randAxis == 1) {
			pickSoln = (float) (xsoln.get(randSoln));
			roundOut = Math.round(pickSoln);
//			System.out.println("Rounded value: " + roundOut);
		}else if(randAxis == 2) {
			pickSoln = (float) (ysoln.get(randSoln));
			roundOut = Math.round(pickSoln);
//			System.out.println("Rounded value: " + roundOut);
		}else if(randAxis == 0) {
			pickSoln = (float) (zsoln.get(randSoln));
			roundOut = Math.round(pickSoln);
//			System.out.println("Rounded value: " + roundOut);
		}
		
//		System.out.println("Selected value: " + pickSoln + "; Rounded val: " + roundOut);
		if(roundOut % 2 == 0) {
			selectedTeam = 1;
		}else {
			selectedTeam = 2;
		}
		
		return selectedTeam;
		
//		switch(randAxis){
//		case 0:
//			System.out.println("It's 0: " + randAxis);
//		case 1:
//			System.out.println("Random int is: " + randAxis);
//		case 2: 
//			System.out.println("It's 2: " + randAxis);
//		}
	}
	
	public void teamSort() {
		String mates[] = new String[10];
//		String Team1[] = new String[10];
//		String Team2[] = new String[10];
		ArrayList<String> Team1 = new ArrayList<String>();
		ArrayList<String> Team2 = new ArrayList<String>();
		mates[0] = "Cristy";
		mates[1] = "Sinead";
		mates[2] = "Daire";
		mates[3] = "Karen";
		mates[4] = "Melissa";
		mates[5] = "Daryl";
		mates[6] = "Conor";
		mates[7] = "David";
		mates[8] = "Seamas";
		mates[9] = "Daryl's bf";
		
		for(int t = 0; t < mates.length; t++) {
//			System.out.println(mates[t] + " is on Team " + randomPick());
			if(randomPick() == 1) {
				Team1.add(mates[t]);
			}else {
				Team2.add(mates[t]);
			}
		}
		System.out.println("Team 1: ");
		printList(Team1);
		System.out.println("Team 2: ");
		printList(Team2);
		
		System.out.println("Team 1 size: " + Team1.size());
		int t1 = Team1.size();
		int t2 = Team2.size();
		if(t1 != t2) {
			System.out.println("Your teams aren't even. \n" + "Trying again...");
			evenTeams(Team1, Team2, t1, t2);
			System.out.println("Team 1: ");
			printList(Team1);
			System.out.println("Team 2: ");
			printList(Team2);
		}else {
			System.out.println("We good.");
		}
	
	}
	
	public void evenTeams(ArrayList<String> Team1, ArrayList<String> Team2, int t1, int t2) {
		int diff = Math.abs(t1 - t2);
//		System.out.println(diff);
		
		if(t1 > t2) {
			do{
				int x = Math.abs(Team1.size() - Team2.size());
				diff = x;
//				System.out.println(Team1.size() - Team2.size());
				Team2.add(Team1.get(x));
				Team1.remove(x);
//				System.out.println(Team1.remove(x));
			}while(Team2.size() != 5);
		}else {
			do{
				int x = Math.abs(Team1.size() - Team2.size());
				diff = x;
//				System.out.println(Team1.size() - Team2.size());
				Team1.add(Team2.get(x));
				Team2.remove(x);
			}while(Team1.size() != 5);
		}
		
	}
	
	public void printList(ArrayList<String> list) {
		for(int index = 0; index < list.size(); index++) {
			System.out.print("\t" + list.get(index));
		}
		System.out.println("");
	}
}
