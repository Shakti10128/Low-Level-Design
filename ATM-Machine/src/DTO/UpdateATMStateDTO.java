package DTO;

import Enums.ATMState;

public class UpdateATMStateDTO {
    private final String atmId;
    private final ATMState state;

    public UpdateATMStateDTO(String atmId, ATMState state) {
        this.atmId = atmId;
        this.state = state;
    }
    public String getAtmId() {
        return atmId;
    }
    public ATMState getState() {
        return state;
    }
}
