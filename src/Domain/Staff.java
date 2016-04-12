package Domain;

import javax.swing.JOptionPane;

import UI.LeavePanel;
import UI.LoginPanel;

public abstract class Staff {
	private String name;
	private String supervisor;
	private String role;
	private String pass;

	public Staff() {

	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return this.pass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return this.name;
	}

	public String getSupervisor() {
		return this.supervisor;
	}

	public String getRole() {
		return this.role;
	}

	public boolean validHasSupervisor() {
		if (this.role != "Director" && this.supervisor == "") {
			return false;
		} else {
			return true;
		}
	}

	public boolean validStaffSize() {
		if (this.name.length() < 3) {
			return false;
		} else {
			return true;
		}
	}

	public boolean rightForEndorsement() {
		return false;
	}

	public boolean rightForLeaveApp() {
		return true;
	}

	public boolean validStaffLogin() {
		if (this.role != "" && this.pass.equals("pass")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validStaffLoginHR() {
		if (this.name.equals("admin") && this.pass.equals("pass")) {
			return true;
		} else {
			return false;
		}
	}

}
