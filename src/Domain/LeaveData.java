package Domain;

import java.util.ArrayList;
import Domain.Leave;

public class LeaveData extends Leave {
	private ArrayList<Leave> leavesList = new ArrayList<Leave>();

	public void setAllLeave(ArrayList<Leave> newList) {
		leavesList = newList;
	}

	public ArrayList<Leave> getAllLeave() {
		return leavesList;
	}

	public ArrayList<Leave> getAllOwnRecord(String applicant) {
		ArrayList<Leave> leaveList = new ArrayList<Leave>();
		ArrayList<Leave> avaList = new ArrayList<Leave>();

		leaveList = Domain.Main.leavesData.getAllLeave();

		if (leaveList.size() > 0) {
			for (int i = 0; i < leaveList.size(); i++) {
				if (leaveList.get(i).getApplicant().equals(applicant)) {
					avaList.add(leaveList.get(i));
				}
			}
		}
		return avaList;
	}

	public ArrayList<Leave> getAllEndorseRecord(String supervisor) {
		ArrayList<Leave> leaveList = new ArrayList<Leave>();
		ArrayList<Leave> avaList = new ArrayList<Leave>();

		leaveList = Domain.Main.leavesData.getAllLeave();

		if (leaveList.size() > 0) {
			for (int i = 0; i < leaveList.size(); i++) {
				if (leaveList.get(i).getSupervisor().equals(supervisor)
						&& leaveList.get(i).getLeaveStatus().equals("Waiting for endorsement")) {
					avaList.add(leaveList.get(i));
				}
			}
		}
		return avaList;
	}

	public void addNewLeave(Leave newLeave) {
		try {

			leavesList.add(newLeave);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public void deleteStaff(int id) {
		try {
			for (int i = 0; i < leavesList.size(); i++) {
				if (leavesList.get(i).getID() == id) {
					leavesList.remove(i);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateStatus(int index, String status) {
		try {
			for (int i = 0; i < leavesList.size(); i++) {
				if (leavesList.get(i).getID() == index) {
					leavesList.get(i).setLeaveStatus(status);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setSupervisor(int index, String supervisor) {
		try {
			for (int i = 0; i < leavesList.size(); i++) {
				if (leavesList.get(i).getID() == index) {

					leavesList.get(i).setSupervisor(supervisor);

					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
