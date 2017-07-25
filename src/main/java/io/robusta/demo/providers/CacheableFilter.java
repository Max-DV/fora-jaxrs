package io.robusta.demo.providers;

import io.robusta.demo.cache.PeopleStory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This cacheable feature only works with Date for simplification.
 * It could work also with Etags with a few more lines.
 * Unfortunately, nothing is automatic, mainly because JaxRS
 * can't easily guess what are your update rules or Etag optimizations
 */
@Cacheable
@Provider
public class CacheableFilter implements ContainerResponseFilter {

    // Quite complicated format needed for Cache Validation
    private static final String RFC1123_DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
    SimpleDateFormat dateFormat = new SimpleDateFormat(RFC1123_DATE_FORMAT_PATTERN, Locale.US);

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        System.out.println("--- HAVING CACHEABLE Filter ----");


        Object o = responseContext.getEntity();
        System.out.println(o);
        PeopleStory story;
        if (o == null || !(o instanceof PeopleStory)) {
            System.out.println("Not a People Story, filter returns as usual");
            return;
        } else {
            story = (PeopleStory) o;
        }



        MultivaluedMap<String, Object> responseHeaders = responseContext.getHeaders();
        // Use Cache-Control : private to avoid that Provider caches personal data
        // responseHeaders.putSingle("Cache-Control", "private");
        String lastModifiedString = dateFormat.format(story.getDate());
        responseHeaders.putSingle("Last-Modified", lastModifiedString);


        String ifModifiedSince = requestContext.getHeaderString("If-Modified-Since");
        System.out.println("Headers : " + ifModifiedSince);
        Date ifDate = new Date();


        boolean hasIfModifiedHeaders = false;
        boolean parseDateOK = true;
        if (ifModifiedSince != null && !ifModifiedSince.trim().equals("")) {
            hasIfModifiedHeaders = true;
            try {
                ifDate = dateFormat.parse(ifModifiedSince);
                System.out.println("Client sends date : " + ifDate);

            } catch (ParseException e) {
                parseDateOK = false;
            }
        }


        boolean hasBeenModified = story.getTimestamp() > ifDate.getTime();

        if (parseDateOK && hasIfModifiedHeaders && hasBeenModified) {// We found the same date
            System.out.println("Using @Cacheable, we have found the date");
            System.out.println("304, NOTHING to send");
            responseContext.setStatus(304);
            responseContext.setEntity("");

        } else {
            //we add the tag in our Response
            System.out.println("Sending full request");
        }


    }

}
