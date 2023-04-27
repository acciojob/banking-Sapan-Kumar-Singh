package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;
     private static int minBalance=0;
     private int nWithDraw;
    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name,balance,minBalance);

        this.maxWithdrawalLimit=maxWithdrawalLimit;
        this.rate=rate;
       this.nWithDraw=0;
    }
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
         if(nWithDraw>maxWithdrawalLimit){
             throw new Exception("Maximum Withdraw Limit Exceed");
         }
         if(amount> super.getBalance()){
             throw new Exception("Insufficient Balance");
         }
         nWithDraw++;
         super.setBalance(super.getBalance()-amount);
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double si=(super.getBalance()*years*rate);
        return si+super.getBalance();
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        double ci=super.getBalance()*Math.pow(1+rate/times,times*years);
        return ci;
    }

}
