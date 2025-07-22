package factories;

import Enums.CardType;
import services.CardManagerService;
import services.CreditCardManagerService;
import services.DebitCardManagerService;

public class CardManagerFactory {
    public static CardManagerService getCardManagerService(CardType type) {
        CardManagerService cardManagerService = null;
        if(type.equals(CardType.DEBIT)){
            cardManagerService = new DebitCardManagerService();
        }
        else if(type.equals(CardType.CREDIT)){
            cardManagerService = new CreditCardManagerService();
        }
        else {
            throw new IllegalArgumentException("Invalid Card Type");
        }
        return cardManagerService;
    }
}
