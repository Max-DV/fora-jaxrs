package io.robusta.demo.providers;



import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RepresentationException extends WebApplicationException {


	/**
	 * Throws an exception by customizing the Http response
	 * @param status
	 * @param message
     */
	public RepresentationException(int status, String message) {
		super(Response.status(status).entity(message).build());
	}

	/**
	 * Does pretty much the same, but automatically
     */
	public RepresentationException(String message, int status) {
		super(message, status);
	}

    /**
     * Will catch the error sending (maybe) a nicer http response instead of 500
     * @param throwable
     * @param status
     */
	public RepresentationException(Throwable throwable, int status) {
		this(throwable.getMessage(), status);
	}
}

