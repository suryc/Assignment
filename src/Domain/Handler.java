package Domain;

public interface Handler {
	public abstract void setNext(Handler nextHandler);

	public abstract void process(Staff curStaff, LeaveData curLeaveData, int leaveID, String actionType);
}
