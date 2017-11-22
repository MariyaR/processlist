package ProcessPackage;

public interface Layer <T extends Layer> {
    String compareTo (T l);
}
