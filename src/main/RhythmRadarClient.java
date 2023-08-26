import java.util.List;
import java.util.Map;

public interface RhythmRadarClient {
    String getArtistName(String artistId);
    int getMonthlyListeners(String artistId);
    List<String> getSongs(String artistId);
    Map<String, Object> getStreamingData(String artistId);
    Map<String, Object> getSocialMediaData(String artistId);
}
