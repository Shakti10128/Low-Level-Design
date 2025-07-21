package services;

import models.Card;

public class DebitCardManagerService implements CardManagerService{
    @Override
    public boolean validateCard(Card card, String pin) {
        return false;
    }

    @Override
    public boolean validateWithdrawal(Card card, double amount) {
        return false;
    }

    @Override
    public boolean doTransaction(Card card, double amount, String transactionId) {
        return false;
    }
}
