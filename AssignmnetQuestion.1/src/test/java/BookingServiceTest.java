import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
//QuestionNo.9
class BookingServiceTest {

    @Mock
    private PaymentService paymentServiceMock;

    @InjectMocks
    private BookingService bookingService;

    BookingServiceTest() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessPaymentIsCalled() {

        bookingService.bookService();
        verify(paymentServiceMock).processPayment();
    }
}
