package state;

import Enums.ATMState;
import factories.CardManagerFactory;
import models.ATM;
import models.Card;
import services.CardManagerService;
import services.CashDispenserService;
import services.CashDispenserServiceImpl;

public class DispensingCashState implements State {
    private final ATM atm;
    private final CashDispenserService cashDispenserService;

    public DispensingCashState(ATM atm) {
        this.atm = atm;
        this.cashDispenserService = new CashDispenserServiceImpl();
    }

    @Override
    public int initTransaction() {
        throw new IllegalStateException("Cannot initialize transaction while dispensing the cash");
    }

    @Override
    public boolean readCardDetailsAndPin(Card card, String pin) {
        throw new IllegalStateException("Cannot read card details and pin while dispensing cash");
    }

    @Override
    public int dispenseCash(Card card,int amount,int transactionId) {
        CardManagerService manager = CardManagerFactory.getCardManagerService(card.getCardType());
        boolean isTxtSuccessful = manager.doTransaction(card,amount,transactionId);
        if(isTxtSuccessful) {
            this.cashDispenserService.dispenseCash(this.atm,amount);
        }
        else{
            System.out.println("Something went wrong while dispensing the cash");
        }
        this.atm.changeState(new EjectingCardState(this.atm));
        return amount;
    }

    @Override
    public void ejectCard() {
        throw new IllegalStateException("Cannot eject card while dispensing cash");
    }

    @Override
    public boolean cancelTransaction(int transactionId) {
        throw new IllegalStateException("Can't cancel transaction while dispensing cash");
    }

    @Override
    public boolean readCashWithdrawDetails(Card card, int transactionId, int amount) {
        throw new IllegalStateException("Cannot read cash withdraw details while dispensing cash");
    }

    @Override
    public ATMState getATMState() {
        return ATMState.DISPENSING_CASH;
    }
}
