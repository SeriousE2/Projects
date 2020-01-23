
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingMachineItems;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VendingMachineStubDao {
    private final static String TEST_PATH = "test-inventory.txt";
    
    VendingMachineDaoFileImpl dao = new VendingMachineDaoFileImpl(TEST_PATH);
    
    public VendingMachineStubDao(){
    
    }
    
    @BeforeAll
    public static void setup() throws IOException {
        Files.copy(Paths.get("test-inventory.txt"), 
                Paths.get(TEST_PATH), 
                StandardCopyOption.REPLACE_EXISTING);
    }
    
    @Test
    public void testGetAllItems() {
        List<VendingMachineItems> all = dao.getAllItems();
        assertEquals(6, all.size());
    }

    @Test
    public void testGetItemById() {
        VendingMachineItems v = dao.getItemById(1);
        assertNotNull(v);
        assertEquals("Ginger ale", v.getItemName());
    }

}
