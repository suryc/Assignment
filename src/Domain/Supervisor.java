package Domain;

public class Supervisor extends Staff {

	@Override
	public boolean rightForEndorsement() {
		return true;
	}

	@Override
	public boolean rightForLeaveApp() {
		return true;
	}

}
