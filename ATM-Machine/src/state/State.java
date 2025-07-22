package state;

import Enums.ATMState;
import models.Card;

public interface State {
    int initTransaction();

    boolean readCardDetailsAndPin(Card card,String pin);

    int dispenseCash(Card card,int amount,int transactionId);

    void ejectCard();

    boolean cancelTransaction(int transactionId);

    boolean readCashWithdrawDetails(Card card,int transactionId,int amount);

    ATMState getATMState();
}
