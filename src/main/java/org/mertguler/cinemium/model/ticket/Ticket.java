package org.mertguler.cinemium.model.ticket;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.seat.Seat;
import org.mertguler.cinemium.model.session.Session;
import org.mertguler.cinemium.util.validator.EnumValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Min(0)
    private BigDecimal totalPrice;

    private LocalDateTime creationDate;

    @EnumValidator(enumClass = TicketStatus.class)
    private String ticketStatus;

    @EnumValidator(enumClass = PaymentStatus.class)
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @OneToMany
    @JoinTable(name = "ticket_seats", joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seats = new ArrayList<>();
}
