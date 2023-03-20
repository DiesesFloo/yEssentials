package systems.floo.yessentials.messages;

public enum PrefixType {
    MAIN("main"),
    BROADCAST("broadcast"),
    ECONOMY("economy"),
    NONE("none");

    private final String key;

    private PrefixType(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
