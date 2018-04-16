package cheatchki.main.information;

public enum Groups {
	Alpha(), 
	Beta(),
	Gamma(),
	Delta(),
	Epsilon(),
	Zeta(),
	Eta(),
	Theta(),
	Iota(),
	Kappa(),
	Lambda(),
	Mu(),
	Nu(),
	Xi(),
	Omicron(),
	Pi(),
	Rho(),
	Sigma(),
	Tau(),
	Upsilon(),
	Phi(),
	Chi(),
	Psi(),
	Omega();
	
	
	private BuyablePermission[] permissions;
	Groups() {
		
		permissions = new BuyablePermission[7];
		for (int i = 0; i < permissions.length; i++) {
			permissions[i] = new BuyablePermission(this.name(), i);
		}
	}
	
	public static Groups getGroup(int i) {
		try {
			return Groups.values()[i];
		} catch (ArrayIndexOutOfBoundsException e) {
//			Ranking.getInstance().getLogger().severe("Could not find group corresponding to " + i);
			return null;
		}
	}
	
	public int getPermSize() {
		return permissions.length;
	}
	
	public BuyablePermission getBuyablePermission(int slot) {
		return permissions[slot];
	}
}
