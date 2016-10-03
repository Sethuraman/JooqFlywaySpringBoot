package csmart;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import csmart.api.model.Address;

/**
 * Created by sethur on 2/10/2016.
 */
public class Test {

    @org.junit.Test
    public void test(){
        Address address = new Address();
        address.setLine1("line1");
        address.setLine2("line2");
        address.setCity("city");
        address.setPincode("pincode");
        address.setState("state");

        System.out.println(JsonNodeFactory.instance.pojoNode(address).asText());
    }

}
