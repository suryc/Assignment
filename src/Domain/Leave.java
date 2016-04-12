package Domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Leave {
	private int leaveID;
	// private Date dateX;
	// private Date dateY;

	private String dateX;
	private String dateY;
	private String leaveStatus;
	private String applicant;
	private String supervisor;

	public Leave() {

	}

	public void setID(int id) {
		this.leaveID = id;
	}

	// public void setDateX(Date dateX) {
	// this.dateX = dateX;
	// }

	// public void setDateY(Date dateY) {
	// this.dateY = dateY;
	// }

	public void setDateX(String dateX) {
		this.dateX = dateX;
	}

	public void setDateY(String dateY) {
		this.dateY = dateY;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public int getID() {
		return leaveID;
	}

	public String getDateX() {
		// SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MMM-dd");
		// System.out.println(dt1.format(date));
		// return dt1.format(this.dateX);
		return this.dateX;
	}

	public String getDateY() {
		// SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MMM-dd");
		// return dt1.format(this.dateY);
		return this.dateY;
	}

	public String getLeaveStatus() {
		return this.leaveStatus;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public String getSupervisor() {
		return this.supervisor;
	}

}
