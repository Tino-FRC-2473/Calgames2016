////////SETUP YOUR DEVELOPMENT ENVIRONMENT
////////////////////////

Read the structure documentation: https://wpilib.screenstepslive.com/s/4485/m/13809/l/599732-what-is-command-based-programming
Scan the WPI Java Api: http://first.wpi.edu/FRC/roborio/release/docs/java/

Install Eclipse neon
Set up WPI on eclipse: https://wpilib.screenstepslive.com/s/4485/m/13809/l/599681-installing-eclipse-c-java
Check if eclipse setup properly:
	1. Click new project(Not java project)
	2. Find WPILIB Robot Development
	3. If you found this, Good Job!, you set up your eclipse correctly

Install Github
	Two Options: Github Desktop or Git Command Line
	
	Option 1: Github Desktop
		1. Install https://desktop.github.com/
		2. Open the newly installed github
		3. Navigate to the plus button on the top left
		4. Clone the Repo under Tino-FRC-2473 - Calgames2016 
		   directly into your eclipse workspace folder
			a. If you don't see Tino-FRC-2473 request to be added to the CHS Github group

	Option 2. Command Line
		1. install git
		2. Open a command line and go into your eclipse workspace(cd %FOLDERNAME%)
		3. initialize the local repo (git init)
		4. clone the remote (git clone https://github.com/Tino-FRC-2473/Calgames2016.git)
		
Adding the cloned repo to Eclipse
	1. Go to eclipse
	2. Create a new project(Not a java project)
		a. New WPILib Robot Development - Robot Java Project
		b. Name the project exactly "Calgames2016" and finish
	3. Setup Gitignore
		a. Add everything thats not .java files

/////////BEGINNING A NEW PROJECT
///////////////////////
	
	Option 1: Github Desktop
		1. Click the triple dots with lines at the top-left on Github
		2. Name your brach and for "From Branch" put "master"
		3. On Eclipse right click on your project and click refresh

	Option 2. Command Line
		1. Open a command line and navigate to your repo
		2. Create a new branch (git checkout -b %BRANCH NAME%)
		3. Switch to your branch (git checkout %BRANCH NAME%)
		4. On Eclipse right click on your project and click refresh
		

/////////DAILY WORKFLOW
///////////////////////
	
	1. Pull changes from the git server
	2. Update from master (Merge master into your branch)
	3. Work
	4. Commit your changes to your branch
	5. Push your branch to the server


/////////AFTER YOU HAVE A FINISHED PRODUCT:
/////////////////////

	1. Start a pull request with master
	2. Wait for a few peers to evaluate your work
		a. They will put comments and accept/decline your pull
	3. If your pull is accepted, merge the two files together