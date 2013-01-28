
package thermalexpansion.api.core;

public interface IPaintableTile {

    public void setSideTexture(String textureFile, int textureIndex, int side);

    public SidedTexture getSideTexture(int side);

    public void removeFrame();

    public boolean isPaintable();

    public boolean applyFrame();
}