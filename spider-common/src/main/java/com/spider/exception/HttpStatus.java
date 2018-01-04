package com.spider.exception;

/**
 * Created by qd on 2016/1/12.
 */
public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "No Content"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, " HTTP Version Not Supported");

    private final int code;
    private final String reason;
    private HttpStatus.Family family;

    private HttpStatus(int statusCode, String reasonPhrase) {
        this.code = statusCode;
        this.reason = reasonPhrase;
        switch(this.code / 100) {
            case 1:
                this.family = HttpStatus.Family.INFORMATIONAL;
                break;
            case 2:
                this.family = HttpStatus.Family.SUCCESSFUL;
                break;
            case 3:
                this.family = HttpStatus.Family.REDIRECTION;
                break;
            case 4:
                this.family = HttpStatus.Family.CLIENT_ERROR;
                break;
            case 5:
                this.family = HttpStatus.Family.SERVER_ERROR;
                break;
            default:
                this.family = HttpStatus.Family.OTHER;
        }

    }

    public HttpStatus.Family getFamily() {
        return this.family;
    }

    public int getStatusCode() {
        return this.code;
    }

    public String toString() {
        return this.reason;
    }

    public static HttpStatus fromStatusCode(int statusCode) {
        HttpStatus[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            HttpStatus s = arr$[i$];
            if(s.code == statusCode) {
                return s;
            }
        }

        return null;
    }

    public static enum Family {
        INFORMATIONAL,
        SUCCESSFUL,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        OTHER;

        private Family() {
        }
    }
}
