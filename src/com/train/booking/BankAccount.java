package com.train.booking;

public class BankAccount {

	private int actNumber;
	private int actBalance;
	
	
	public int getActNumber() {
		return actNumber;
	}
	public void setActNumber(int actNumber) {
		this.actNumber = actNumber;
	}
	public int getActBalance() {
		return actBalance;
	}
	public void setActBalance(int actBalance) {
		this.actBalance = actBalance;
	}
	
	
	//Method to deposit money
	public void deposit(int amount) {
		actBalance += amount;
	}
	
	
	// Method to withdraw money
	public void withdraw(int amount) {
		actBalance -= amount;
	}
	
	
	// Method to show the account details
	public void showBalance() {
		System.out.println("Act Balance : " + actBalance);
	}
}
