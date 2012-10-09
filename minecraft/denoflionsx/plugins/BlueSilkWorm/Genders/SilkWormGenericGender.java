package denoflionsx.plugins.BlueSilkWorm.Genders;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGender;

public class SilkWormGenericGender implements ISilkWormGender{
    
    private String gender;

    public SilkWormGenericGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getGender() {
        return gender;
    }

}
