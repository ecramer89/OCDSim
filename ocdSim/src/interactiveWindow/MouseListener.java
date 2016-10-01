package interactiveWindow;

public interface MouseListener {
	
	public abstract void handleMousePressed(int mouseX, int mouseY);
	public abstract void handleMouseReleased(int mouseX, int mouseY);
	public abstract boolean isUnderMouse(int mouseX, int mouseY);
}
