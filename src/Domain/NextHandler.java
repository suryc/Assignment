package Domain;

public class NextHandler implements Handler {
	private Handler nextEndorser;

	public void setNext(Handler h) {
		nextEndorser = h;
	}

	public void process(Staff curStaff, LeaveData curLeaveData, int leaveID, String actionType) {
		if (curStaff.getSupervisor().equals("")) {
			curLeaveData.updateStatus(leaveID, actionType);
			Domain.Main.leavesData.updateStatus(leaveID, actionType);
		} else {
			nextEndorser.process(curStaff, curLeaveData, leaveID, actionType);
		}
	}
}
