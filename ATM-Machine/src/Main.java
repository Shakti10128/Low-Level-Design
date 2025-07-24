import Enums.ATMState;
import Enums.CardType;
import models.ATM;
import models.Card;
import models.VisaDebitCard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pre-initialized ATM and Card
        ATM atm = new ATM("ATM-123");
        Card card = new VisaDebitCard(12345, "1234", "Shakti", CardType.DEBIT, "PNB");

        boolean sessionActive = true;

        System.out.println("\n💳 Welcome to ATM-123");
        System.out.println("==========================");

        while (sessionActive) {
            int transactionId = -1;

            while (true) {
                ATMState currentState = atm.getState().getATMState();
                System.out.println("\n➡️ Current ATM State: " + currentState);

                switch (currentState) {
                    case READY_FOR_TRANSACTION -> {
                        System.out.println("\n🔘 Options:");
                        System.out.println("1. Start New Transaction");
                        System.out.println("2. Exit");
                        System.out.print("👉 Enter your choice: ");
                        int choice = sc.nextInt();

                        if (choice == 1) {
                            transactionId = atm.getState().initTransaction();
                            System.out.println("\n🔁 Transaction started.");
                            System.out.println("🆔 Transaction ID: " + transactionId);
                        } else {
                            System.out.println("👋 Thank you for visiting ATM-123!");
                            sessionActive = false;
                            break;
                        }
                    }

                    case READ_CARD_DETAILS_AND_PIN -> {
                        System.out.print("\n🔐 Please enter your 4-digit PIN: ");
                        String pin = sc.next();

                        boolean isVerified = atm.getState().readCardDetailsAndPin(card, pin);

                        if (isVerified) {
                            System.out.println("✅ Card and PIN verified successfully!");
                        } else {
                            System.out.println("❌ Verification failed. Ending transaction.");
                        }
                    }

                    case READING_CASH_WITHDRAW_DETAILS -> {
                        System.out.print("\n💵 Enter amount to withdraw: ₹");
                        int amount = sc.nextInt();

                        boolean success = atm.getState().readCashWithdrawDetails(card, transactionId, amount);
                        if (success) {
                            System.out.println("✅ Withdrawal request accepted.");
                        } else {
                            System.out.println("❌ Withdrawal failed. Canceling transaction...");
                            atm.getState().cancelTransaction(transactionId);
                        }
                    }

                    case DISPENSING_CASH -> {
                        System.out.print("\n💸 Confirm amount to dispense: ₹");
                        int amount = sc.nextInt();

                        int dispensed = atm.getState().dispenseCash(card, amount, transactionId);
                        System.out.println("✅ Dispensed: ₹" + dispensed);
                        System.out.println("🧾 Please collect your cash.");
                    }

                    case CANCELING_TRANSACTION -> {
                        boolean cancelled = atm.getState().cancelTransaction(transactionId);
                        System.out.println(cancelled ? "❌ Transaction cancelled." : "⚠️ Could not cancel transaction.");
                    }

                    case EJECTING_CARD -> {
                        atm.getState().ejectCard();
                        System.out.println("\n💳 Card ejected. ✅ Please collect your card.");
                        break;
                    }

                    default -> {
                        System.out.println("⚠️ Unexpected ATM state. Exiting...");
                        sessionActive = false;
                        break;
                    }
                }

                // After each transaction, check if ATM is ready for next
                if (atm.getState().getATMState() == ATMState.READY_FOR_TRANSACTION) {
                    System.out.println("\n🔁 Transaction completed.");
                    break;
                }
            }

            if (!sessionActive) break;

            System.out.print("\n🔄 Would you like to perform another transaction? (yes/no): ");
            String again = sc.next().trim().toLowerCase();

            if (!again.equals("yes")) {
                sessionActive = false;
                System.out.println("\n👋 Thank you for using ATM-123. Have a great day!");
            }
        }

        sc.close();
    }
}
