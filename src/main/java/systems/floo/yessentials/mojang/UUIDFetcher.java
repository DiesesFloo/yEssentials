package systems.floo.yessentials.mojang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.util.UUIDTypeAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class UUIDFetcher {

    private static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/%s?at=%d";
    private static Gson gson = new GsonBuilder().registerTypeAdapter(UUID.class, new UUIDTypeAdapter()).create();

    private UUID id;

    /**
     * Fetches the uuid synchronously for a specified name and time
     *
     * @param name The name
     * @see UUIDFetcher
     */
    public static UUID getUUID(String name){
        if (name == null) return null;

        name = name.toLowerCase();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(UUID_URL, name, System.currentTimeMillis()/1000)).openConnection();
            connection.setReadTimeout(5000);
            UUIDFetcher data = gson.fromJson(new BufferedReader(new InputStreamReader(connection.getInputStream())), UUIDFetcher.class);

            return data.id;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
