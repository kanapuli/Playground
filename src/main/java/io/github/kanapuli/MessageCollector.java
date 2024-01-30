package io.github.kanapuli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * <p>
 *     MessageCollector collects messages from various client sockets and write them all to a single log.
 *     The class extends Thread in order to handle messages from multiple clients. This is a naive implementation.
 *     In future, this method should evolve using an EventLoop pattern that is managed by a single thread.
 * </p>
 */
public class MessageCollector extends Thread {
    private final Socket clientSocket;
    private final BufferedReader inputStream;
    private static Logger logger = LoggerFactory.getLogger(MessageCollector.class);

    public MessageCollector(final Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    /**
     * <p>
     *     run() reads the word stream from client socket and writes it to the log
     *     run() need not to be explicitly called. It is invoked as a Runnable component by the Thread.
     * </p>
     * @throws IOException
     * @return void
     */
    public void run() {
        String line;
        try {
            while ((line = this.inputStream.readLine()) != null) {
                logger.info(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                this.inputStream.close();
                this.clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
