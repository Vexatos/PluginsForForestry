
package thermalexpansion.api.core;

public interface IRedstoneControl {

    public boolean getRedstoneDisable();

    public boolean getRedstoneState();

    public boolean redstoneControl();

    public boolean redstoneControlOrDisable();

    public boolean toggleRedstoneDisable();

    public boolean toggleRedstoneState();

    public boolean setRedstoneInfo(boolean disable, boolean state);
}