package state;

import Enums.ATMState;
import models.ATM;
import models.Card;

public class EjectingCardState implements State {
    private final ATM atm;

    public EjectingCardState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public int initTransaction() {
        throw new IllegalStateException("Cannot initialize transaction while ejecting card");
    }

    @Override
    public boolean readCardDetailsAndPin(Card card, String pin) {
        throw new IllegalStateException("Cannot read card details and pin while ejecting card");
    }

    @Override
    public int dispenseCash(Card card, int amount, int transactionId) {
        throw new IllegalStateException("Cannot dispense cash while ejecting card");
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejecting card, please take it");
        this.atm.changeState(new ReadyForTransactionState(this.atm));
    }

    @Override
    public boolean cancelTransaction(int transactionId) {
        throw new IllegalStateException("Can't cancel transaction while ejecting card");
    }

    @Override
    public boolean readCashWithdrawDetails(Card card, int transactionId, int amount) {
        throw new IllegalStateException("Cannot read cash withdraw details while ejecting card");
    }

    @Override
    public ATMState getATMState() {
        return ATMState.EJECTING_CARD;
    }
}
