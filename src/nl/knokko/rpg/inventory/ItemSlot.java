package nl.knokko.rpg.inventory;

public class ItemSlot {
	
	public ItemStack stack;
	
	public ItemSlot(ItemStack itemStack) {
		stack = itemStack;
	}
	
	public ItemStack addItemStack(ItemStack itemstack){
		if(itemstack != null){
			if(stack == null){
				stack = itemstack;
				return null;
			}
			else if(stack.item == itemstack.item){
				stack.size += itemstack.size;
				return null;
			}
			else {
				ItemStack i = stack;
				stack = itemstack;
				return i;
			}
		}
		return takeItemStack();
	}
	
	private ItemStack takeItemStack(){
		ItemStack i = stack;
		stack = null;
		return i;
	}
	
	@Override
	public String toString(){
		return "slot: " + stack;
	}
}
