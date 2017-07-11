# Lorenzo il Magnifico
## Prova Finale (Ingegneria del Software) - Group ps46

Authors: 
- Garbujo Lorenzo
- Masi Andrea
- Mondolo Alessia

Lorenzo Il Magnifico is a strategy board game.
We developed this game for our Software Engineering Final Project.
## How to run the Application
In order to run the application, follow these steps
### Import the project
- Using the git command line client for your OS, type the following command:
```bash
 # clone the repo on your current folder
 git clone https://github.com/alessiamondolo/progettone7.git
 ```
 - Import it in Eclipse as Maven Project:
  * from Eclipse, select `File > Import... > Existing Maven Project`
  * click `Browse...` and select the directory where you cloned the project
  * make sure the project is listed and selected under `Projects`
  * select `Finish`
  * you should now see the project **ps46** listed in the Package Explorer view of Eclipse
  
### Run the Server
First run the server, therefore go to the "server" package, select the "ServerApplication" class and run it.
 
### Run the Clients
Now run the clients, choose the "client" package and select the "Client" class and run it. Now a client is running, in order to start playing at least two running clients are needed.

### We implemented:

° Simplified Rules
° Complete Rules
° Command Line Interface
° Graphic User Interface
° Socket

ABOUT THE GUI: 
It is suggested to adjust the computer resolution in order to be able to display the entire game board. Moreover, the pop-up windows sometimes are hidden behind other processes screens, to be able to keep playing the game, it is necessary to find and close them, otherwise the game stays in a waiting state.
The game guides the user interaction, but always remember to click on the action cell you want to move to and then choose the family member you want (you can find them on the left part of the game window, just next to the zoom panel).



