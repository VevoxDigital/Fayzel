package io.vevox.fayzel.core.client.gui.inventory;

import io.vevox.fayzel.core.api.FayzelGuiContainer;
import io.vevox.fayzel.core.inventory.ContainerWorktable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author Matthew Struble
 */
public class GuiWorktable extends FayzelGuiContainer {

  private static final ResourceLocation TEXTURE = new ResourceLocation(
      "fayzel-core", "textures/gui/container/worktable.png");

  public GuiWorktable(ContainerWorktable worktable) {
    super(worktable);

    xSize = 247;
    ySize = 222;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1, 1, 1, 1);
    mc.getTextureManager().bindTexture(TEXTURE);

    drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    String inventoryTitle = container.inventory.getDisplayName().getUnformattedText();
    fontRendererObj.drawString(inventoryTitle,
        xSize / 2 - fontRendererObj.getStringWidth(inventoryTitle) / 2,
        -12, Integer.parseInt("c6c6c6", 16));
  }
}
