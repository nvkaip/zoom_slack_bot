# zoom_slack_bot
This is learning project for zoom_slack bot. In this project I used next stack:

- Java 8,
- Spring Boot,
- Spring DJPA - PostgreSQL Driver,
- Lombok,
- JWT.

The purpose of bot is to gain some information from Zoom by receiving a slash commands from Slack. 
And return information back in Slack.

The bot was deployd on heroku.com and connected there to postgre database. 
Database is used to save JWT for particular user and then gain it by email of user.
