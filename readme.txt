How to run:

Click leaveApplication.jar to run the program which located in the folder "dist"




How to read the source code

There are 2 folders, one is UI and one is Domain
All the UI part located in the UI folder and others located in Domain folder.


UI folder

-LoginPanel.java
handle the UI of user login part.

-TabPanel.java
handle the UI of toolbar part.

-ManageStaffPanel.java
handle the UI of staff management part.

-LeavePanel.java
handle the UI of leave application part.

-EndorsePanel.java
handle the UI of leave endorsement part.


Domain folder

Chain of responsibility
-Handler.java
-NextHandler.java
-NextHandler2.java

these 3 file handle the process of chain of responsiblity. Handler.java is the super class and NextHandler.java and NextHandler2.java are the sub class.

Staff part
-Staff.java
-GeneralStaff.java
-Supervisor.java
-Director.java

there are 3 role class which respresent the role of generalstaff, supervisor and director. Staff.java is the super class and others are sub class.


Data record part
-StaffData.java
-LeaveData.java

StaffData.java handle the process of managing staff records, such as get all staffs, get one staff record.

LeaveData.java handle the process of managing leave records, such as get all leave records, get the endorsed leave records.

