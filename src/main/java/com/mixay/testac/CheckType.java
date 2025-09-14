package com.mixay.testac;

public class CheckType {
    public enum checks {
        PITCH(PitchYawCheck.class);

        checks(Class<PitchYawCheck> pitchYawCheckClass) {
        }
    }
}
