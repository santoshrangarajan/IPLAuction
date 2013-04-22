Primary Interfaces
------------------------
AuctionModerator - Interface to handle steps in Auction
BiddingStrategy  - Interface which enables selection of team for bidding
TeamManager      - Interface to handle operations related to team
Input            - Interface to generate input for auction

Domain Objects
------------------------
Auction - Object which orchestrates the auction
Player - Object representing Player
Team   - Object representing Team
PlayersQueue - Auctioners queue to store players avaliable for auction
UnsoldPlayersQueue - Queue to store unsold players


Third party libraries
----------------------
jax-api - For parsing input xml

Assumptions
----------------------
1. If player is rejected twice the is moved to unsold queue
2. If selection for particular team is complete ( size and strength) player is returned to auctioner's queue 
3. If auctioners queue is empty program terminates
4. If desired strength and desired size for both team is met, auction terminates
5. If particular player is eligible for team, below scenarios can occur
   a. Adding player to team - signified by Operation ADD
         1. This scenario occur's, If after adding particular player, strength will be less than desired strength & size will be less than desired size
   b. Shuffling the team   - signified by Operation SHUFFLE
         1. This scenario occur's if above scenario (Adding player to team) fails
         1. In this case most expensive eligible player (if available) is replaced with new player
         2. If for a particular team ( adding a player) results in strength equal to desired strength, however the size is doesn't 
              match desired size, then player is not added


Main Class
---------------------
com.ipl.auction.Auction
params - location of sample xml file (complete path)
By default - when main is run, it runs with Use case 3. How ever to run other 2 cases, change below
i.) change desired strengths when Team is initialized (line 120,121)
ii.) change bidding order (line 124,125)

Outputs 
Use case 1 - Desired Strength 60, A calls First
-----------------------
Team=B1, Cost=INR18,500,000.00, Strength=60, Players= [Pujara,Peeyush,Munaf,Sidhu,Zaheer]

Team=B2, Cost=INR16,100,000.00, Strength=59, Players= [Gauti,Adam,Parthiv,Bhajji]


Use case 2 - Desired Strength 65, B calls First
-----------------------
Team=B1, Cost=INR16,600,000.00, Strength=64, Players= [Ishant,Peeyush,Parthiv,Adam]

Team=B2, Cost=INR21,750,000.00, Strength=65, Players= [Ricky,Gauti,Munaf,Zaheer,Vijay]


Use case 3 - Desired Strength 70, A calls First
-----------------------
Team=B1, Cost=INR21,150,000.00, Strength=70, Players= [Rahul,Pujara,Munaf,Zaheer,Vijay]

Team=B2, Cost=INR18,850,000.00, Strength=70, Players= [Ishant,Peeyush,Parthiv,Sidhu,Adam]
