package state;

import DTO.GetATMAmountReqDTO;
import Enums.ATMState;
import apis.BackendAPI;
import apis.NodeBackendApi;
import factories.CardManagerFactory;
import models.ATM;
import models.Card;
import services.CardManagerService;

public class ReadingCashWithdrawlDetailsState implements State {
    private final ATM atm;
    private final BackendAPI backendAPI;

    public ReadingCashWithdrawlDetailsState(ATM atm) {
        this.atm = atm;
        this.backendAPI = new NodeBackendApi();
    }

    @Override
    public int initTransaction() {
        throw new IllegalStateException("Cannot initialize transaction while reading cash withdraw details");
    }

    @Override
    public boolean readCardDetailsAndPin(Card card, String pin) {
        throw new IllegalStateException("Reading card details and pin is not supported while reading cash withdraw details");
    }

    @Override
    public int dispenseCash(Card card,int amount,int transactionId) {
        throw new IllegalStateException("Can't dispense the cash without reading cash details");
    }

    @Override
    public void ejectCard() {
        throw new IllegalStateException("Cannot eject card while reading cash");
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
        CardManagerService manager = CardManagerFactory.getCardManagerService(card.getCardType());
        boolean isWithdrawValid = manager.validateWithdrawal(transactionId, amount);
        int ATMAmount = backendAPI.getATMAmount(new GetATMAmountReqDTO(atm.getAtmId()));
        if(amount > ATMAmount) {
            System.out.println("Insufficient amount in the ATM");
            isWithdrawValid = false;
        }
        if(isWithdrawValid) {
            this.atm.changeState(new DispensingCashState(this.atm));
        }
        else{
            this.atm.changeState(new EjectingCardState(this.atm));
        }
        return isWithdrawValid;
    }

    @Override
    public ATMState getATMState() {
        return ATMState.READING_CASH_WITHDRAW_DETAILS;
    }
}
