package Domain;

public class Director extends Staff {

	@Override
	public boolean rightForEndorsement() {
		return true;
	}

	@Override
	public boolean rightForLeaveApp() {
		return false;
	}

}
