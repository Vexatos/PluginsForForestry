package net.minecraft.server.denoflionsx.denLib;

public enum Faces
{
    BOTTOM(0),
    TOP(1),
    NORTH(2),
    SOUTH(3),
    EAST(4),
    WEST(5);
    private int value;

    private Faces(int var3)
    {
        this.value = var3;
    }

    public int get()
    {
        return this.value;
    }
}
