# INSA-POO-Project-Chat-Sytem-4IR
Professional Decentralized Chat system

## [APP STILL IN DEVELOPMENT]

## Authors:
- Rostom BACCAR 4IR B1
- Wissem FERCHICHI 4IR B1

## Requirements:
- Local Network
- Ideally more that two computers on that Local Network (in order to properly test all the app features)

## User Manual: 
1. Identify the IP Adress of the computer that will act as the Server
2. Assign that value to the Server_IP attribute in the Client class. You'll have to do that with every computer that you want to use as Client
3. Run the Server class on the computer that acts as the server
4. Run as many instances of the Client class as you want there to be clients (not necessarily on different computers)

## App Features:
- Edit username while being connected (it has to be unique)
- Chat with multiple users
- Group Chat with all the connected users
- Getting notified when someone:
  - connects
  - disconnects
  - changes their username
  - wants to chat with you
  - the person you want to chat with opens a Chat Window with you
- Get the names of all connected people at any time (the list is not dynamic. You'll have to refresh it if you see that someone has just connected / disconnected / changed their username)
- Disconnect at any moment

## Limitations / Aspects to improve:
- When a user changes their username while chatting with someone, they'll have to close that Chat Window and re-open it again with the same person in order to send / recieve messages from there on
- The Dialog Boxes that notify the user that someone wants to chat with them or that someone has opened a Chat Window with them only work when no other user attemps to connect with you while connecting with someone
- Buttons are sometimes laggy / Dialog Boxes sometimes show with a delay
- The code needs a lot of cleaning and optimization
- No database for now
- Other bugs that are yet to be discovered

