package cheatchki.main.information;

import cheatchki.main.menu.SlotType;

public enum Default45 implements IDefault {
	slot0( SlotType.FRAME ),
	slot1( SlotType.FRAME ),
	slot2( SlotType.FRAME ),
	slot3( SlotType.FRAME ),
	slot4( SlotType.FRAME ),
	slot5( SlotType.FRAME ),
	slot6( SlotType.FRAME ),
	slot7( SlotType.FRAME ),
	slot8( SlotType.FRAME ),
	
	slot9( SlotType.PERMISSION_UNHOVERED ),
	slot10( SlotType.PERMISSION_UNHOVERED ),
	slot11( SlotType.PERMISSION_UNHOVERED ),
	slot12( SlotType.PERMISSION_UNHOVERED ),
	slot13( SlotType.PERMISSION_UNHOVERED ),
	slot14( SlotType.PERMISSION_UNHOVERED ),
	slot15( SlotType.PERMISSION_UNHOVERED ),
	slot16( SlotType.EMPTY ),
	slot17( SlotType.SCROLL ),
	
	slot18( SlotType.PERMISSION_HOVERED ),
	slot19( SlotType.PERMISSION_HOVERED ),
	slot20( SlotType.PERMISSION_HOVERED ),
	slot21( SlotType.PERMISSION_HOVERED ),
	slot22( SlotType.PERMISSION_HOVERED ),
	slot23( SlotType.PERMISSION_HOVERED ),
	slot24( SlotType.PERMISSION_HOVERED ),
	slot25( SlotType.EMPTY ),
	slot26( SlotType.REFOCUS ),
	
	slot27( SlotType.PERMISSION_UNHOVERED ),
	slot28( SlotType.PERMISSION_UNHOVERED ),
	slot29( SlotType.PERMISSION_UNHOVERED ),
	slot30( SlotType.PERMISSION_UNHOVERED ),
	slot31( SlotType.PERMISSION_UNHOVERED ),
	slot32( SlotType.PERMISSION_UNHOVERED ),
	slot33( SlotType.PERMISSION_UNHOVERED ),
	slot34( SlotType.EMPTY ),
	slot35( SlotType.SCROLL ),
	
	slot36( SlotType.FRAME ),
	slot37( SlotType.FRAME ),
	slot38( SlotType.FRAME ),
	slot39( SlotType.FRAME ),
	slot40( SlotType.FRAME ),
	slot41( SlotType.FRAME ),
	slot42( SlotType.FRAME ),
	slot43( SlotType.FRAME ),
	slot44( SlotType.FRAME );
		
	private int slot;
	private int slotType;
	
	Default45(int slotType) {
		slot = this.ordinal();
		this.slotType = slotType;
	}
	
	
	public int getSlot() {
		return slot;
	}
	
	
	public int getSlotType() {
		return slotType;
	}
}
