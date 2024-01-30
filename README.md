# PlayGround 

## MessageCollector

The goal of the project is to understand how gradle works, some java socket programming, and the functional aspects of 
Java.

MessageCollector listens on Socket Port 3333 where clients could connect and send messages. The client messages are 
simply appended to a log.
Multiple clients could connect at the same time and each client is handled by different Jvm threads. This could be an overkill since creating
a thread for each client connection is a costlier process. **Future Implementation should handle all the clients in
a single threaded event loop (more like an Redis style approach)**