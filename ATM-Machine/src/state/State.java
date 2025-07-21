package state;

import Enums.ATMState;
import models.Card;

public interface State {
    int initTransaction();

    boolean readCardDetailsAndPin(Card card);

    int dispenseCash();

    void ejectCard();

    boolean readCashWithdrawDetails(int transactionId,int amount);

    ATMState getATMState();
}
