package Domain;

import javax.swing.JFrame;

import Domain.LeaveData;
import Domain.StaffData;
import UI.TabPanel;

public class Main {

	public static StaffData staffsData = new StaffData();
	public static LeaveData leavesData = new LeaveData();
	public static int leaveID = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TabPanel tp = new TabPanel();
		tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tp.setSize(800, 100);
		tp.setLocation(800, 0);
		tp.setVisible(true);
	}
}
