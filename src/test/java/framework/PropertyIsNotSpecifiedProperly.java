package framework;

public class PropertyIsNotSpecifiedProperly extends RuntimeException {

    public PropertyIsNotSpecifiedProperly(String propertyType, Throwable reason) {
        super("Specified property '" + propertyType + "' is not found, due to following reason\n" + reason.getMessage() +
                "\n" + reason);
    }
}
