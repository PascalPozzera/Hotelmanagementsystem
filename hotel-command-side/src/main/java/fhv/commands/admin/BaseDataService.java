package fhv.commands.admin;

import fhv.commands.booking.BookingAggregate;
import fhv.commands.booking.CreateBookingCommand;
import fhv.commands.customer.CreateCustomerCommand;
import fhv.commands.customer.CustomerAggregate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@ApplicationScoped
public class BaseDataService {

    @Inject
    CustomerAggregate customerAggregate;

    @Inject
    BookingAggregate bookingAggregate;

    @Transactional
    public void loadBaseData() {
        customerAggregate.handle(new CreateCustomerCommand("Anna", "Muster", "anna@example.com", "Straße 1", LocalDate.of(1990, 1, 1)));
        customerAggregate.handle(new CreateCustomerCommand("Ben", "Beispiel", "ben@example.com", "Gasse 2", LocalDate.of(1985, 5, 5)));
        customerAggregate.handle(new CreateCustomerCommand("Clara", "Test", "clara@example.com", "Weg 3", LocalDate.of(1992, 3, 3)));
        customerAggregate.handle(new CreateCustomerCommand("David", "Demo", "david@example.com", "Allee 4", LocalDate.of(1988, 6, 6)));
        customerAggregate.handle(new CreateCustomerCommand("Eva", "Probe", "eva@example.com", "Platz 5", LocalDate.of(1995, 9, 9)));

        bookingAggregate.handle(new CreateBookingCommand(101, "anna@example.com", todayPlus(1), todayPlus(3), 2));
        bookingAggregate.handle(new CreateBookingCommand(102, "ben@example.com", todayPlus(2), todayPlus(4), 3));
        bookingAggregate.handle(new CreateBookingCommand(103, "clara@example.com", todayPlus(1), todayPlus(2), 2));
        bookingAggregate.handle(new CreateBookingCommand(104, "david@example.com", todayPlus(3), todayPlus(5), 1));
        bookingAggregate.handle(new CreateBookingCommand(105, "eva@example.com", todayPlus(2), todayPlus(6), 2));
        bookingAggregate.handle(new CreateBookingCommand(106, "anna@example.com", todayPlus(4), todayPlus(6), 2));
        bookingAggregate.handle(new CreateBookingCommand(107, "ben@example.com", todayPlus(3), todayPlus(5), 3));
        bookingAggregate.handle(new CreateBookingCommand(108, "clara@example.com", todayPlus(5), todayPlus(7), 2));
        bookingAggregate.handle(new CreateBookingCommand(109, "david@example.com", todayPlus(6), todayPlus(8), 1));
        bookingAggregate.handle(new CreateBookingCommand(110, "eva@example.com", todayPlus(7), todayPlus(9), 2));
    }

    private LocalDate todayPlus(int days) {
        return LocalDate.now().plusDays(days);
    }
}
