package Domain;

public class NextHandler2 implements Handler {

	private Handler nextEndorser;

	public void setNext(Handler h) {
		nextEndorser = h;
	}

	public void process(Staff curStaff, LeaveData curLeaveData, int leaveID, String actionType) {
		if (!curStaff.getSupervisor().equals("")) {
			curLeaveData.setSupervisor(leaveID, curStaff.getSupervisor());
			Domain.Main.leavesData.setSupervisor(leaveID, curStaff.getSupervisor());
		} else {
			nextEndorser.process(curStaff, curLeaveData, leaveID, actionType);
		}

	}
}
