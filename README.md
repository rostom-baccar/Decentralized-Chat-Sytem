# INSA-POO-Project-Chat-Sytem-4IR
Professional Decentralized Chat system

**APP STILL IN DEVELOPMENT**

*Authors:
Rostom BACCAR 4IR B1 \n
Wissem FERCHICHI 4IR B1 \n

## Requirements:
- Local Network \n
- More that two computers on that Local Network (in order to test all the features) \n

## User Manual: 
1. Identify the IP Adress of the computer that will act as the Server \n
2. Assign that value to the Server_IP attribute in the Client class. You'll have to do that with every computer that you want to use as Client \n
3. Run the Server class on the computer that acts as the server \n
4. Run as many instances of the Client class as you want there to be clients (on differents computers or not) \n

## App Features:
-Edit username while being connected (it has to be unique) \n
-Chat with multiple users \n
-Getting notified when someone: \n
  -connects \n
  -disconnects \n
  -changes their username \n
  -wants to chat with you \n
  -the person you want to chat with opens a Chat Window with you \n
-Get the names of all connected people (the list is not dynamic. You'll have to refresh it if you see that someone has just connected/disconnected/changed their username) \n
-Disconnect at any moment \n

## Limitations/Aspects to improve:
-When a user changes their username while chatting with someone, they'll have to close that Chat Window and re-open it again with the same person in order to send/recieve messages 
-The Dialog Boxes that notify the user that someone wants to chat with them or that someone has opened a Chat Window with them only work when no other user attemps to connect with you while connecting with someone \n
-Buttons are sometimes laggy / Dialog Boxes sometimes show with a delay \n
-The code needs a lot of cleaning and optimization \n
-No database for now \n


