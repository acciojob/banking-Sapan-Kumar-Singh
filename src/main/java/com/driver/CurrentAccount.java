package com.driver;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public static int minBalance=5000;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name,balance,minBalance);
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        if(balance<minBalance){
            throw new Exception("Insufficient Balance");
        }

        this.tradeLicenseId=tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
         String temp=isValid();
         if(temp.equals("-1")){
             throw new Exception("Valid License can not be generated");
         }
         this.tradeLicenseId=temp;
    }



    public class pair{
         int freq;
        char ch;

        public pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }

    class KeyComparator implements Comparator<pair> {

        // Overriding compare()method of Comparator
        public int compare(pair k1, pair k2)
        {
            if (k1.freq < k2.freq)
                return 1;
            else if (k1.freq > k2.freq)
                return -1;
            return 0;
        }
    }


    private String isValid(){
        int freq[]=new int[26];
        String temp=tradeLicenseId;
        for(int i=0;i<temp.length();i++){
            freq[temp.charAt(i)-'A']++;
        }
        PriorityQueue<pair>pq=new PriorityQueue<>( new KeyComparator());

        for (char c = 'A'; c <= 'Z'; c++) {
            int val = c - 'a';
            if (freq[val] > 0)
                pq.add(new pair(freq[val], c));
        }
        String ans="";
        pair prev = new pair(-1, '#');
        while (pq.size() != 0) {

            pair k = pq.peek();
            pq.poll();
            ans = ans + k.ch;

            if (prev.freq > 0)
                pq.add(prev);

            (k.freq)--;
            prev = k;
        }

        if(ans.length()!=temp.length()){
            return "-1";
        }
        return ans;
    }

}
