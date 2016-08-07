package sokratis12gr.armorplus.api.iface;

public interface InteractiveImageRenderer extends ImageRenderer {

    String getTooltip(String tooltip);

    boolean onMouseClick(int mouseX, int mouseY);
}