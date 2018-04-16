package cheatchki.main.information;

import cheatchki.main.menu.SlotType;

public enum Default27 implements IDefault {
	slot0( SlotType.PERMISSION_UNHOVERED ),  
	slot1( SlotType.PERMISSION_UNHOVERED ),  
	slot2( SlotType.PERMISSION_UNHOVERED ),
	slot3( SlotType.PERMISSION_UNHOVERED ),  
	slot4( SlotType.PERMISSION_UNHOVERED ),  
	slot5( SlotType.PERMISSION_UNHOVERED ),
	slot6( SlotType.PERMISSION_UNHOVERED ),	 
	slot7( SlotType.EMPTY ),  
	slot8( SlotType.SCROLL ),
	
	slot9( SlotType.PERMISSION_HOVERED ),	
	slot10( SlotType.PERMISSION_HOVERED ), 
	slot11( SlotType.PERMISSION_HOVERED ),
	slot12( SlotType.PERMISSION_HOVERED ), 
	slot13( SlotType.PERMISSION_HOVERED ), 
	slot14( SlotType.PERMISSION_HOVERED ),
	slot15( SlotType.PERMISSION_HOVERED ), 
	slot16( SlotType.EMPTY ), 
	slot17( SlotType.REFOCUS ),
	
	slot18( SlotType.PERMISSION_UNHOVERED ), 
	slot19( SlotType.PERMISSION_UNHOVERED ), 
	slot20( SlotType.PERMISSION_UNHOVERED ),
	slot21( SlotType.PERMISSION_UNHOVERED ), 
	slot22( SlotType.PERMISSION_UNHOVERED ), 
	slot23( SlotType.PERMISSION_UNHOVERED ),
	slot24( SlotType.PERMISSION_UNHOVERED ), 
	slot25( SlotType.EMPTY ), 
	slot26( SlotType.SCROLL );
	
	private int slot;
	private int slotType;
	
	Default27(int type) {
		slot = this.ordinal();
		slotType = type;
	}
	
	
	public int getSlot() {
		return slot;
	}
	
	
	public int getSlotType() {
		return slotType;
	}
}
