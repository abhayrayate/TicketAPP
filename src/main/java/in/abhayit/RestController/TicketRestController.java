package in.abhayit.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.abhayit.request.Passanger;
import in.abhayit.response.Ticket;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*")  // CORS enabled for all origins
public class TicketRestController {

    private Map<Integer, Ticket> tickets = new ConcurrentHashMap<>();
    private AtomicInteger ticketCounter = new AtomicInteger(1000); // safe incremental ID

    @PostMapping(consumes = { "application/xml", "application/json" }, 
                 produces = { "application/xml", "application/json" })
    public Ticket bookTicket(@RequestBody Passanger passdata) {
        Ticket t = new Ticket();

        int ticketid = ticketCounter.getAndIncrement();
        t.setTicketid(ticketid);
        t.setTicketfrom(passdata.getFrom());
        t.setTicketto(passdata.getTo());
        t.setTicketno(12345);
        t.setTicketprice(500);

        tickets.put(ticketid, t);
        return t;
    }

    @GetMapping(value = "/{ticketid}", produces = { "application/xml", "application/json" })
    public Ticket getTicket(@PathVariable Integer ticketid) {
        return tickets.get(ticketid); // returns null if not found
    }
}
