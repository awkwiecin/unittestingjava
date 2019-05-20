package pl.awkwieicn.testing.order;

import org.junit.jupiter.api.Test;
import pl.awkwieicn.testing.order.Order;
import pl.awkwieicn.testing.order.OrderBackup;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingFileShouldThrowException() throws IOException {
        //given
        OrderBackup orderBackup = new OrderBackup();

        //then
        assertThrows(IOException.class, () -> orderBackup.backupOrder(new Order()));

    }
}
