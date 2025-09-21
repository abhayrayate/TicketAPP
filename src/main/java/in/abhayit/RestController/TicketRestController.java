package in.abhayit.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.ResponseEntity;
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
@CrossOrigin(origins = "*")  // CORS enabled
public class TicketRestController {

    private Map<Integer, Ticket> tickets = new ConcurrentHashMap<>();
    private AtomicInteger ticketCounter = new AtomicInteger(1000);

    @PostMapping(consumes = { "application/xml", "application/json" }, 
                 produces = { "application/xml", "application/json" })
    public Ticket bookTicket(@RequestBody Passanger passdata) {
        Ticket t = new Ticket();
        int ticketid = ticketCounter.getAndIncrement();

        t.setTicketid(ticketid);
        t.setTicketfrom(passdata.getFrom());
        t.setTicketto(passdata.getTo());
        t.setTicketno(10000 + ticketid);  // unique ticket number
        t.setTicketprice(500);

        tickets.put(ticketid, t);
        return t;
    }

    @GetMapping(value = "/{ticketid}", produces = { "application/xml", "application/json" })
    public ResponseEntity<Ticket> getTicket(@PathVariable Integer ticketid) {
        Ticket ticket = tickets.get(ticketid);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }
}
