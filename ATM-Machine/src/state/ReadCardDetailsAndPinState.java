package state;

import Enums.ATMState;
import factories.CardManagerFactory;
import models.ATM;
import models.Card;
import services.CardManagerService;

public class ReadCardDetailsAndPinState implements State{
    private final ATM atm;

    public ReadCardDetailsAndPinState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public int initTransaction() {
        throw new IllegalStateException("Can't initialize transaction while reading card details and pin");
    }

    @Override
    public boolean readCardDetailsAndPin(Card card,String pin) {
        CardManagerService manager = CardManagerFactory.getCardManagerService(card.getCardType());
        boolean isCardValid = manager.validateCard(card,pin);

        if(isCardValid){
            this.atm.changeState(new ReadingCashWithdrawlDetailsState(this.atm));
        }else {
            this.atm.changeState(new EjectingCardState(this.atm));
        }
        return isCardValid;
    }

    @Override
    public int dispenseCash(Card card,int amount,int transactionId) {
        throw new IllegalStateException("Cannot dispense cash while reading card details and pin");
    }

    @Override
    public void ejectCard() {
        throw new IllegalStateException("Cannot eject card while reading card details and pin");
    }

    @Override
    public boolean cancelTransaction(int transactionId) {
        try {
            this.atm.changeState(new ReadyForTransactionState(this.atm));
            return true;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot cancel transaction while reading card details and pin");
        }
    }

    @Override
    public boolean readCashWithdrawDetails(Card card,int transactionId, int amount) {
        throw  new IllegalStateException("Cannot read cash withdraw details while reading card details and pin");
    }

    @Override
    public ATMState getATMState() {
        return ATMState.READ_CARD_DETAILS_AND_PIN;
    }
}
