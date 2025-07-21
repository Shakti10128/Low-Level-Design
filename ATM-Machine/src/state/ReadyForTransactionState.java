package state;

import DTO.CreateTransactionDTO;
import Enums.ATMState;
import apis.BackendAPI;
import models.ATM;
import models.Card;

public class ReadyForTransactionState implements State{
    private final ATM atm;
    private final BackendAPI backendAPI;

    public ReadyForTransactionState(ATM atm, BackendAPI backendAPI) {
        this.atm = atm;
        this.backendAPI = backendAPI;
    }

    @Override
    public int initTransaction() {
        // at the time of init transaction atm should send the details of itself to the server so that server can
        // know from which ATM the transaction is initiated
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(this.atm.getAtmId());
        int transactionId = this.backendAPI.createTransaction(createTransactionDTO);
        if(transactionId == 0){
            throw new RuntimeException("Transaction could not be created");
        }

        // now that we have transactionId from the backend, we should move the atm to the next state
        this.atm.changeState(new ReadCardDetailsAndPinState());
        return transactionId;
    }

    @Override
    public boolean readCardDetailsAndPin(Card card) {
        throw new IllegalStateException("Cannot read card details and pin without inserting card");
    }

    @Override
    public int dispenseCash() {
        throw new IllegalStateException("Cannot dispense cash without reading card details and pin");
    }

    @Override
    public void ejectCard() {
        throw new IllegalStateException("Cannot eject card without reading card details and pin");
    }

    @Override
    public boolean readCashWithdrawDetails(int transactionId, int amount) {
        throw new IllegalStateException("Cannot read cash withdraw details without reading card details and pin");
    }

    @Override
    public ATMState getATMState() {
        return ATMState.READY_FOR_TRANSACTION;
    }
}
