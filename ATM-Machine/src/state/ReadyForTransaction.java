package state;

import Enums.ATMState;
import models.Card;

public class ReadyForTransaction implements State{
    @Override
    public int initTransaction() {
        return 0;
    }

    @Override
    public boolean readCardDetailsAndPin(Card card) {
        return false;
    }

    @Override
    public int dispenseCash() {
        return 0;
    }

    @Override
    public void ejectCard() {

    }

    @Override
    public boolean readCashWithdrawDetails(int transactionId, int amount) {
        return false;
    }

    @Override
    public ATMState getATMState() {
        return null;
    }
}
