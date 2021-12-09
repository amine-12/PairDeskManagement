# PairDeskManagement
## MySql Database Setup
1. Open MySql 8.0 Command Line Client
2. Enter your password
3. Enter these commands
    - `create database pairdesk_db;`
    - `create user 'springusers1'@'%' identified by 'ThePassword';`
    - `grant all on pairdesk_db.* to 'springusers1'@'%';`
## How to run service
- Open git on the root directory of the project
- Enter `./gradlew bootRun`
## How to run vue application
- Open a command line in 
- Enter `cd ~/source/pairdesksystem/src/main/resources/User-interface/pairdesk-ui` 
- Enter `npm run serve`
