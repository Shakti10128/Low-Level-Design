package services;

import models.Card;

public class CreditCardManagerService implements CardManagerService{
    @Override
    public boolean validateCard(Card card, String pin) {
        // we should make these also connect to api
        return true;
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
