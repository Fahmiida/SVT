import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// QuestionNo.4
public class OrderServiceTest {


    private ShippingService shippingServiceMock;
    private OrderService orderService;

    @BeforeEach
    void initialize() {
        shippingServiceMock = mock(ShippingService.class);
        orderService = new OrderService(shippingServiceMock);
    }

    @Test
    @DisplayName("Order can be placed with Chanel No. 5")
    void shouldPlaceOrderForValidQuantity() {
        String item = "Chanel No. 5";
        int quantity = 12;

        when(shippingServiceMock.ship(item, quantity)).thenReturn(true);

        boolean result = orderService.placeOrder(item, quantity);

        assertTrue(result, "Order should be placed successfully");

        verify(shippingServiceMock).ship(item, quantity);
    }

    @Test
    @DisplayName("Order can't be placed with Dior Sauvage")
    void shouldNotPlaceOrderForInvalidQuantity() {
        String item = "Dior Sauvage";
        int quantity = -3;

        boolean result = orderService.placeOrder(item, quantity);

        assertFalse(result, "Order should not be placed with invalid quantity");

        verify(shippingServiceMock, never()).ship(item, quantity);
    }

    @Test
    @DisplayName("Order can't be placed when shipment fails for Gucci Bloom")
    void shouldFailOrderWhenShipmentFails() {
        String item = "Gucci Bloom";
        int quantity = 5;

        when(shippingServiceMock.ship(item, quantity)).thenReturn(false);

        boolean result = orderService.placeOrder(item, quantity);

        assertFalse(result, "Order should fail when shipment fails");

        verify(shippingServiceMock).ship(item, quantity);
    }
}
