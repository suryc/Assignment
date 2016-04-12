package Domain;

public class GeneralStaff extends Staff {

	public GeneralStaff() {
	}

	@Override
	public boolean rightForEndorsement() {
		return false;
	}

	@Override
	public boolean rightForLeaveApp() {
		return true;
	}

}
