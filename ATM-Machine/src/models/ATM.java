package models;

import DTO.UpdateATMStateDTO;
import apis.BackendAPI;
import apis.NodeBackendApi;
import state.ReadyForTransactionState;
import state.State;

public class ATM {
    private final String atmId;
    private State state;
    private final BackendAPI backendAPI;

    ATM(String atmId) {
        this.atmId = atmId;
        this.backendAPI = new NodeBackendApi();
        this.state = new ReadyForTransactionState(this);
    }
    public String getAtmId() {
        return atmId;
    }

    public void changeState(State newState) {
        this.state = newState;
        // call the server to persist the state on the server also
        this.backendAPI.updateState(new UpdateATMStateDTO(this.atmId,newState.getATMState()));
    }
}
