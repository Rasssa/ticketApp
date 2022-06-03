package IT.Data;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ticket.app.ticketApp.model.Ticket;

public class RestUtils {

    private static final String BASE_URI = "http://localhost:8080/";
    private static RequestSpecification request;

    public static void buildRequest(int port) {
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URI);
        request.port(port);
        RestUtils.request = request;
    }

    private static RequestSpecification request() {
        return RestAssured.given(request);
    }

    public static Response addTicketViaEndpoint(Ticket ticket) {
        return request()
                .param("email", ticket.getEmail())
                .param("title", ticket.getTitle())
                .param("category", ticket.getCategory())
                .param("description", ticket.getDescription())
                .post("addTicket");
    }

    public static Response deleteTicket(Long ticketId) {
        return request()
                .param("TicketName", ticketId)
                .post("delete");
    }
}
