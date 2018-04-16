package cheatchki.main.information;

import cheatchki.main.Options;
import cheatchki.main.menu.SlotType;
import cheatchki.main.menu.button.Button;
import cheatchki.main.menu.button.EmptyButton;
import cheatchki.main.menu.button.FrameButton;
import cheatchki.main.menu.button.PermissionButton;
import cheatchki.main.menu.button.RefocusButton;
import cheatchki.main.menu.button.ScrollButton;

public class Default {
	public static IDefault getSlot(int slot) {
		if (Options.inventorySize == 27) {
			if (slot >= 0 && slot < 27)
				return Default27.values()[slot];
		} else if (Options.inventorySize == 45) {
			if (slot >= 0 && slot < 45)
				return Default45.values()[slot];
		}
		return null;
	}
	
	public static Button getButton(int slot) {
		switch(getSlot(slot).getSlotType()) {
		case SlotType.EMPTY:
			return new EmptyButton();
		case SlotType.FRAME:
			return new FrameButton();
		case SlotType.PERMISSION_HOVERED:
			return new PermissionButton(true);
		case SlotType.PERMISSION_UNHOVERED:
			return new PermissionButton(false);
		case SlotType.SCROLL:
			return new ScrollButton();
		case SlotType.REFOCUS:
			return new RefocusButton();
		}
		return null;
	}
	
	public static int getRelativePage(int page, int slot) {
		if (Options.inventorySize == 27) {
			if (Math.floor(slot/9) == 0)
				return page - 1;
			if (Math.floor(slot/9) == 1)
				return page;
			if (Math.floor(slot/9) == 2)
				return page + 1;
			
			
		} else if (Options.inventorySize == 45) {
			if (Math.floor(slot/9) == 1)
				return page - 1;
			if (Math.floor(slot/9) == 2)
				return page;
			if (Math.floor(slot/9) == 3)
				return page + 1;
			
		}
		return -1;
	}
}
