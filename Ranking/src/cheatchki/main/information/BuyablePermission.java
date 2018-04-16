package cheatchki.main.information;

import cheatchki.main.managers.files.RanksFileManager;

public class BuyablePermission {
	private boolean enabled;
	private String permission;
	private String description;
	private double price;
	
	private String requiredGroup;
	
	public BuyablePermission(String groupName, int slot) {
		enabled = RanksFileManager.getEnabled(groupName, slot);
		permission = RanksFileManager.getPermission(groupName, slot);
		description = RanksFileManager.getDescription(groupName, slot);
		price = RanksFileManager.getPrice(groupName, slot);
		
		requiredGroup = groupName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRequiredGroup() {
		return requiredGroup;
	}

	public void setRequiredGroup(String requiredGroup) {
		this.requiredGroup = requiredGroup;
	}
	
}
