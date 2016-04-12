package Domain;

import java.util.ArrayList;

public class StaffData extends Staff {

	private ArrayList<Staff> staffsList = new ArrayList<Staff>();

	public ArrayList<Staff> getAllStaff() {
		return staffsList;
	}

	public void addStaffRecord(Staff newStaff) {
		try {

			staffsList.add(newStaff);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public void deleteStaff(String name) {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getName() == name) {
					staffsList.remove(i);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setName(String name, String newName) {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getName() == name) {
					staffsList.get(i).setName(newName);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setSupervisor(String name, String supervisor) {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getName().equals(name)) {
					staffsList.get(i).setSupervisor(supervisor);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setRole(String name, String role) {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getName().equals(name)) {
					staffsList.get(i).setRole(role);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean checkDuplidate(String name) {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getName().equals(name)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public String getSupervisor(String name) {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getName().equals(name)) {
					return staffsList.get(i).getSupervisor();
				}
			}
			return "";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public String getRole(String name) {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getName().equals(name)) {
					return staffsList.get(i).getRole();
				}
			}
			return "";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public boolean hasDirector() {
		try {
			for (int i = 0; i < staffsList.size(); i++) {
				if (staffsList.get(i).getRole().equals("Director")) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
