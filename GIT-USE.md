# Instructions for Pulling and Pushing
Step 1: Clone Repository and Set Up

Open Visual Studio Code.
Go to the terminal (bottom in VS Code).
Navigate to the folder where you want to store the repository.
Enter the following command to clone the repository:

git clone https://github.com/LeoJunginger/aitutor.git

Set you credentials in the git config:

git config --global user.name "USERNAME"

git config --global user.email "YOURMAIL"

Step 2: Switch to the Feature Branch

Navigate to the cloned repository directory:

cd <repository-directory>

Switch to the feature/branchXYZ branch:

git checkout feature/branchXYZ


Step 3: Make Changes

Open the code in VS Code and make the desired changes.
Save your changes.

Step 4: Commit and Push

Go back to the terminal.
Execute the following commands to commit your changes and push to the branch:

git add .

git commit -m "Describe the changes made"

git push origin feature/branchXYZ

Step 5: Create a Pull Request

Go to the GitHub repository in your web browser.
Click the "Compare & pull request" button next to your branch.
Create a pull request to integrate your changes into the main branch.
Wait for your changes to be reviewed and merged.
