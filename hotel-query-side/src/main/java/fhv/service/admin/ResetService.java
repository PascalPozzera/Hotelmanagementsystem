package fhv.service.admin;

import fhv.models.booking.BookingQueryPanacheModel;
import fhv.models.customer.CustomerQueryPanacheModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ResetService {

    @Transactional
    public void resetQueryModels() {
        BookingQueryPanacheModel.deleteAll();
        CustomerQueryPanacheModel.deleteAll();
    }
}
