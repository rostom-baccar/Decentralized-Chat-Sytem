# INSA-POO-Project-Chat-Sytem-4IR
Professional Decentralized Chat System using Socket Programming
## [APP STILL IN DEVELOPMENT]

## Authors
- Rostom BACCAR 4IR B1
- Wissem FERCHICHI 4IR B1

## Requirements
- Local Network
- Ideally more that two computers on that Local Network (in order to properly test all the app features)

## User Manual
- Identify the IP Adress of the computer that will act as the Server
- Assign that value to the Server_IP attribute in the Client class. You'll have to do that with every computer that you want to use as Client
- Run Jenkins Pipeline and execute the generated jar file:
  - With the Server class as argument to run Server
  - With the Client class as argument to create a Client
  - [Add commands here]

## App Features
- Disconnect at any time
- Edit username while being connected (it has to be unique)
- Private Chat with multiple users
- Group Chat with all the connected users
- Get the names of all connected people at any time
- Getting notified when someone:
  - connects
  - disconnects
  - changes their username
  - wants to chat with you
  - the person you want to chat with opens a Chat Window with you

## Limitations / Aspects to improve
- When a user changes their username while chatting with someone, they'll have to close that Chat Window and re-open it again with the same person in order to send / recieve messages from there on
- The Dialog Boxes that notify the user that someone wants to chat with them or that someone has opened a Chat Window with them only work when no other user attemps to connect with you while connecting with someone
- Database in the works
- Other bugs that are yet to be discovered
