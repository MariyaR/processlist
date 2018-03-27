package ProcessPackage;

public class ProcessFilter {

    public final String namePrefix;
    public final String topicPrefix;
    public final String analogPrefix;
    public final String substratePrefix;

    public ProcessFilter(String namePrefix, String topicPrefix, String analogPrefix, String substratePrefix) {
        this.namePrefix = namePrefix;
        this.topicPrefix = topicPrefix;
        this.analogPrefix = analogPrefix;
        this.substratePrefix = substratePrefix;
    }
}
