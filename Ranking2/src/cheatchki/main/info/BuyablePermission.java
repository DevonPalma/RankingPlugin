package cheatchki.main.info;

public class BuyablePermission {
	/*
	 * requires
	 * 		permission
	 * 		price
	 * 		requirements
	 * 			ingroup: x
	 * 			hasperm: x
	 * 		description
	 */
	
	private String permission;
	private double price;
	private String requirements;
	private String description;
	
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
