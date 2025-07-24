package services;

import models.Card;

public class DebitCardManagerService implements CardManagerService{
    @Override
    public boolean validateCard(Card card, String pin) {
        return card.getPin().equals(pin);
    }

    @Override
    public boolean validateWithdrawal(int transactionId, double amount) {
        // we should make these also connect to api
        return true;
    }

    @Override
    public boolean doTransaction(Card card, double amount, int transactionId) {
        // we should make these also connect to api
        return true;
    }
}
