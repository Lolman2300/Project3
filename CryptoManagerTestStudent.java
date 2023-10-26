package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CryptoManagerTestStudent {

    @BeforeEach
    public void setup() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testStringInBounds() {
        assertTrue(CryptoManager.isStringInBounds("HOW MUCH WOOD CAN A WOODCHUCK CHUCK IF A WOOD CHUCK COULD CHUCK"));
        assertFalse(CryptoManager.isStringInBounds("HOW MUCH WOOD can A WOODCHUCK CHUCK IF A WOOD CHUCK COULD CHUCK"));
    }

    @Test
    public void testEncryptCaesar() {
        assertEquals("W^&/\\$RW/&^^S/RP]/P/&^^SRW$RZ/RW$RZ/XU/P/&^^S/RW$RZ/R^$[S/RW$RZ",
                CryptoManager.caesarEncryption("HOW MUCH WOOD CAN A WOODCHUCK CHUCK IF A WOOD CHUCK COULD CHUCK", 15));
    }

    @Test
    public void testDecryptCaesar() {
        assertEquals("HOW MUCH WOOD CAN A WOODCHUCK CHUCK IF A WOOD CHUCK COULD CHUCK",
                CryptoManager.caesarDecryption("W^&/\\$RW/&^^S/RP]/P/&^^SRW$RZ/RW$RZ/XU/P/&^^S/RW$RZ/R^$[S/RW$RZ", 15));
    }

    @Test
    public void testEncryptBellaso() {
        assertEquals("PT#,\\]HT,&WTP,RIS,M/_T[PRPZOW/KM!OZ(NR,P(\\[[S(HT!RS%O[$TI,OW]HW",
                CryptoManager.bellasoEncryption("HOW MUCH WOOD CAN A WOODCHUCK CHUCK IF A WOOD CHUCK COULD CHUCK", "HELLO"));
    }

    @Test
    public void testDecryptBellaso() {
        assertEquals("HOW MUCH WOOD CAN A WOODCHUCK CHUCK IF A WOOD CHUCK COULD CHUCK",
                CryptoManager.bellasoDecryption("PT#,\\]HT,&WTP,RIS,M/_T[PRPZOW/KM!OZ(NR,P(\\[[S(HT!RS%O[$TI,OW]HW", "HELLO"));
    }
}
