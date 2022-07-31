# Websocket Challenge

## BUILDING

In order to start the application, you are required to start the docker-compose file which is
located in the next folder:
```
src/test/resources/docker
```
You will find 2 scripts to start and shutdown the docker-container with the services that the application needs to run.
The services I am using are:
- RabbitMQ: To send messages to the topic.
- MySQL: To save the messages that are being sent.

Once those services are running, you will be able to start the app locally.

## Testing the service
The service is running by default in the port 8080.
Accessing to the next URL ```http://localhost:8080``` you will find a simple UI where you will need to type your name in,
and press the connect button.
Once that is done, you can start to send messages.
To test that the messages are also available when the other user is typing, you will need to open a new tab in your browser.
Once you send messages, they will be available in both sides.

Also, going to this URL ```http://localhost:8080/action-monitor.html``` you will be able to see the different insertions
that are being made in the DB for each user. Every row will be related to one message.

If you want to follow the complete trace since the users are connected until both have finished, you will need to connect 
the monitor first.

## Improvements

Firstly, My apologies because I didn't have enough time to develop the application 100% correct. I know what I am going to
say it is not a excuse, but I am in different processes, and I don't have enough time to do all the tasks since I ak working
during the week. It is not because you're challenge deserves less time, it is because I am doing the tasks in order.

As you guys can see, I don't have tests. That is something really important. I should have added the different unit tests
with Junit5 and Mockito, and some integration tests. I am going to try to share with you other challenge I am working on where
I could add different tests. Not 100% since it is not finished yet, but some tests for different layers of the service.
In this way you will be able to see the way I create tests for the services.

Also, I think the domain can be improved, but I've never worked before with WebSocket. I had to research how to work with it,
reading documentation, to understand the best way to find a correct solution.

Again, my apologies for that and in case you have any question I will be more than happy to answer them.
