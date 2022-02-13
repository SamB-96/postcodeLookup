package postcodeLookup;

public interface IPostcodeLookup {
    String[] getValidDeliveryOptions(String postcode);
}
