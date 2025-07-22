package services;

import models.Card;

public interface CardManagerService {
    boolean validateCard(Card card,String pin);

    boolean validateWithdrawal(int transactionId,double amount);

    boolean doTransaction(Card card,double amount,int transactionId);
}
