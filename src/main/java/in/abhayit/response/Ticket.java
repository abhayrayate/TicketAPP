package in.abhayit.response;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class Ticket {
    private Integer ticketid;
    private String ticketfrom;
    private String ticketto;
    private Integer ticketprice;
    private Integer ticketno;
}
