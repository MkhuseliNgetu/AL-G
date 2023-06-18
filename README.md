# AL-G
AL-G is a Java-based desktop application that aims to determine whether a hard drive is suitable to be placed in a NAS configuration by identifying the hard drive's attributes and providing a judgment on whether the hard drive is suitable or not.

System Requirements

Minimum\
OS (Operating System): Linux\ 
IDE/Coding Platform: Apache Netbeans 18.\
Storage: 20MB\
RAM: 4GB\
Misc. Software: smartctl (Install command : sudo <insert your package manager> install smartmontools)\ 
(P.S Remember to update your system before installing smartctl to reduce that chances of this tool not working properly on your system.)\
A functional keyboard and mouse.\
Recommended\
OS (Operating System): Linux (Fedora 38)\
IDE/Coding Platform: Apache Netbeans 18.\
Storage: Greater than 20MB\
RAM: 4GB\
Misc. Software: smartctl (Install command : sudo <insert your package manager> install smartmontools)\
(P.S Remember to update your system before installing smartctl to reduce that chances of this tool not working properly on your system.)\
A functional keyboard and mouse.

*How To Compile (as of 19/06/2023)*
1. Decrypt (Unlock) the hard drives you wish to use in the application.
2. Locate the folder in which AL-G was cloned or downloaded.
3. Enter into the folder and find a subfolder named 'src'.
4. Enter into the folder named 'src' and enter into the folder named 'Scripts'.
5. Open up a new terminal within the folder.
6. Enter the following command 'chmod 755 GetAllAvailableDrives.sh && bash GetAllAvailableDrives.sh' and press 'Enter' on your keyboard.
7. After you have recieved a positive response after executing the command, open up Netbeans.
8. Press CTRL+Shift+O on your keyboard at the same time.
9. Locate the folder in which AL-G was cloned or downloaded and click 'Open Project'.
10. Wait for the project to load.
11. Click on the green 'play' button to run the application.
