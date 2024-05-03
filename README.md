# RailwayTickets

Flow of this project : --

Authentication 
----------------
Welcome page

User Register

User login (ADMIN, USER)

with ADMIN access : -
After login -- add train details
            API{
                    /api/admin/addAllNewTrains
                    /api/admin/addNewTrain
                }

with USER access : -
After login --- REQUEST (Train object)
			source
			distination
			date 
			 --- SUBMIT
			 
			 GET DB - Train details-----
									Tain no
									Train Name
									source
									distination
									train start time
									train end time
										------------- SELECT train with all details above 
										------------- GET the available seats with Train Number
										------------- if seat available alow to booking orElse throw exp
										------------- BOOKING -> Passenger details
																 tain details
																 User Profile (login user)
																 
															--> Response --> Ticket booked succes	
																			
																		return->{
																					Ticket object JSON return 
																					Booking object JSON
																				}
									
									
