package net.minecraft.src.denoflionsx.plugins.Forestry;

import net.minecraft.src.denoflionsx.denLib.Colors;

public class EnumContainers {
    
    public static enum Containers{
        CAPSULE,CAN(Colors.shiftRow(0, 3)),CAPSULE_RED(Colors.shiftRow(0, 5)),
        BUCKET(Colors.shiftRow(0, 1)),BOTTLE(Colors.shiftRow(0, 2)),
        CELL(Colors.shiftRow(0, 6));
        
        int texture;
        private Containers(){
            // Default texture: Wax cell.
            texture = Colors.shiftRow(0, 4);
        }
        private Containers(int texture){
            this.texture = texture;
        }
        public int getTexture(){
            return this.texture;
        }
    }
    
}
