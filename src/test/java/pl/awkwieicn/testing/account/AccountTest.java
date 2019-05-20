package pl.awkwieicn.testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.awkwieicn.testing.account.Account;
import pl.awkwieicn.testing.account.Address;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
@Tag("fries")
class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation() {
        //given + when
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive(), "Check if newAccount is not active");
        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void accountShouldBeActiveAfterActivation() {
        //given
        Account newAccount = new Account();
        //whem
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive()).isTrue();

    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address).isNull();

    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {

        //given
        Address address = new Address("Marokańska", "18/67");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);
        assertThat(defaultAddress).isNotNull();
    }

    @RepeatedTest(12)
    void newAccountWithNotNullAddressShouldBeActive() {
        //given
        Address address = new Address("Puławska", "46/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address!=null, () -> {
            assertThat(account.isActive());
        });
    }

    @Test
    void invslidEmailShouldThrowException() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("wrongEmail"));
    }

    @Test
    void validEmailShouldBeSet() {
        //given
        Account account = new Account();

        //when
        account.setEmail("awkwiecin@gmail.com");

        //then
        assertThat(account.getEmail()).isEqualTo("awkwiecin@gmail.com");
    }

}
