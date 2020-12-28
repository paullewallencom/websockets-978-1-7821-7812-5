package me.kalali.books.async.ch4;


import java.io.IOException;
import java.util.Queue;
import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author masoud
 */
class ResponseWriteListener implements WriteListener {
    private ServletOutputStream output = null;
    private Queue queue = null;
    private AsyncContext context = null;

    ResponseWriteListener(ServletOutputStream sos, Queue q, AsyncContext c) {
        this.output = sos;
        this.queue = q;
        this.context = c;
    }

    public void onWritePossible() throws IOException {
        while (queue.peek() != null && output.isReady()) {
            String data = (String) queue.poll();
            output.print(data);
        }
        if (queue.peek() == null) {
            context.complete();
        }
    }

    public void onError(final Throwable t) {
        context.complete();
        t.printStackTrace();
    }
}
