package systems.floo.yessentials.messages;

public class MessageProvider {

    public static String getPrefix(){
        return "§8[§cEssentials§8] §7";
    }

    //TODO: Do message provider with yml config

    public static String getMessage(String key){
        return getPrefix() + "null";
    }

}
