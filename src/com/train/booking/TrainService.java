package com.train.booking;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TrainService {

	private static List<Train> allTrains = new LinkedList<>();
	
	// Static block is executed automatically when a specific .class file is loaded into memory i.e no need for method calls
	static { 
		allTrains.add(new Train(101, "Train_one", "Hyderabad", "Bangalore", 100, 800, LocalDate.now()));
		allTrains.add(new Train(102, "Train_Two", "Bangalore", "Hyderabad", 100, 800, LocalDate.now()));
		allTrains.add(new Train(103, "Train_Three", "Hyderabad", "Bangalore", 80, 600, LocalDate.now()));
		allTrains.add(new Train(104, "Train_Four", "Bangalore", "Hyderabad", 80, 800, LocalDate.now()));
		allTrains.add(new Train(105, "Train_Five", "Hyderabad", "Chennai", 100, 900, LocalDate.now()));
		allTrains.add(new Train(106, "Train_Six", "Chennai", "Hyderabad", 100, 900, LocalDate.now()));
	}

	public TrainService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Method to find the train number among all the trains available. This will loop all the trains and returns the matched train.
	public static Train findTrain(int trainNumber) {
		Train temp = null;
		for(Train t : allTrains) {
			if(t.getTrainNumber() == trainNumber) {
				temp = t;
				break;
			}
		}
		return temp;
	}
	
	// Method to search list of trains between two given stations on specific dates and specific number of seats
	public static void searchTrainsBetweenStations(String fromStation, String toStation, LocalDate doj, int numOfSeats) {
		List<Train> searchTrainList = new LinkedList<>();
		for(Train t : allTrains) {
			if(t.getFromStation().equals(fromStation) &&
					t.getToStation().equals(toStation) &&
					t.getDoj().equals(doj) &&
					t.getSeatsAvailable() > numOfSeats) {
				searchTrainList.add(t);
			}
		}
		if(searchTrainList.size() == 0) {
			System.out.println("Sorry, no matching trains available for the searching criteria...");
		}else {
			System.out.println("Tr.No\tTrain Name\tFrom Station\tTo Station\tDoj\tSeats Available\tFare");
			System.out.println();
			for(Train t : searchTrainList) {
				System.out.printf("%4d%20s%15s%15s%12s%4d%5d\n", t.getTrainNumber(),t.getTrainName(),t.getFromStation(),t.getToStation(),t.getDoj(),t.getSeatsAvailable(),t.getFare());
			}
		}
	}
	
	// Method to book tickets
	public static void bookTickets(int trainNumber, List<Passenger> passengerList) {
		
		// Step-1 : We need to subtract the train fare for all passengers together from the bank account object
		int numOfSeats = passengerList.size();
		/*
		 * Passenger tempPassenger = passengerList.get(0); BankAccount account =
		 * tempPassenger.getBankAccount();
		 */
		BankAccount account = passengerList.get(0).getBankAccount();
		
		Train tempTrain = findTrain(trainNumber);
		int fare = tempTrain.getFare();
		int totalFare = fare * numOfSeats;
		
		account.withdraw(totalFare);
		
		// Step-2 : We need to subtract the number of seats that are being booked from the actual no of tickets
		tempTrain.setSeatsAvailable(tempTrain.getSeatsAvailable() - numOfSeats);
		
		// Step-3 : We need to add one entry (one record) into tickets collection/database
		TicketService.addNewTicket(trainNumber, passengerList);
	}
}

