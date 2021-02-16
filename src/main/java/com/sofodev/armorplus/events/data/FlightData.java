package com.sofodev.armorplus.events.data;

public class FlightData {

    private boolean allowFlying;
    private boolean isFlying;
    private boolean wasFlyingAllowed;

    public FlightData(boolean allowFlying, boolean isFlying, boolean wasFlyingAllowed) {
        this.allowFlying = allowFlying;
        this.isFlying = isFlying;
        this.wasFlyingAllowed = wasFlyingAllowed;
    }

    public boolean allowFlying() {
        return allowFlying;
    }

    public void setAllowFlying(boolean allowFlying) {
        this.allowFlying = allowFlying;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    public boolean wasFlyingAllowed() {
        return wasFlyingAllowed;
    }

    public void setWasFlyingAllowed(boolean wasFlyingAllowed) {
        this.wasFlyingAllowed = wasFlyingAllowed;
    }
}
