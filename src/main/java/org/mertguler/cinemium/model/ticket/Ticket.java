package org.mertguler.cinemium.model.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.model.building.Seat;
import org.mertguler.cinemium.model.session.Session;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ticketId;

    private String ticketStatus;

    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @OneToOne
    private Seat seat;

    @ManyToOne
    private Order order;
}
