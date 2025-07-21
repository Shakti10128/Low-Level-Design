package state;

import Enums.ATMState;
import models.Card;

public class ReadCardDetailsAndPinState implements State{
    @Override
    public int initTransaction() {
        throw new IllegalStateException("Can't initialize transaction while reading card details and pin");
    }

    @Override
    public boolean readCardDetailsAndPin(Card card) {
        return false;
    }

    @Override
    public int dispenseCash() {
        throw new IllegalStateException("Cannot dispense cash while reading card details and pin");
    }

    @Override
    public void ejectCard() {
        throw new IllegalStateException("Cannot eject card while reading card details and pin");
    }

    @Override
    public boolean readCashWithdrawDetails(int transactionId, int amount) {
        throw  new IllegalStateException("Cannot read cash withdraw details while reading card details and pin");
    }

    @Override
    public ATMState getATMState() {
        return ATMState.READ_CARD_DETAILS_AND_PIN;
    }
}
