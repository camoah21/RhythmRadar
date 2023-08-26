import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RhythmRadarAppTest {
    @Mock
    private RhythmRadarClient rhythmRadarClient;
    private RhythmRadarApp rhythmRadarApp;
    private String artistName = "Test Artist";
    private int monthlyListeners = 123456;
    private List<String> songs = Arrays.asList("Song1", "SOng2", "Song3");
    private Map<String, Object> streamingData = new HashMap<>();
    private Map<String, Object> socialMediaData = new HashMap<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rhythmRadarApp = new RhythmRadarApp(rhythmRadarClient);

        streamingData.put("Spotify", 500000);
        streamingData.put("Apple Music", 400000);
        when(rhythmRadarClient.getStreamingData(anyString())).thenReturn(streamingData);

        socialMediaData.put("Instagram", 30000);
        socialMediaData.put("Twitter", 20000);
        when(rhythmRadarClient.getSocialMediaData(anyString())).thenReturn(socialMediaData);

        when(rhythmRadarClient.getArtistName(anyString())).thenReturn(artistName);
        when(rhythmRadarClient.getMonthlyListeners(anyString())).thenReturn(monthlyListeners);
        when(rhythmRadarClient.getSongs(anyString())).thenReturn(songs);
    }

    @Test
    void testGetArtistName() {
        String returnedArtistName = rhythmRadarApp.getArtistName("artistID");
        assertEquals(artistName, returnedArtistName);
    }

    @Test
    void testGetMonthlyListeners() {
        int returnedListeners = rhythmRadarApp.getMonthlyListeners("artistID");
        assertEquals(monthlyListeners, returnedListeners);
    }

    @Test
    void testGetSongs() {
        List<String> returnedSongs = rhythmRadarApp.getSongs("artistID");
        assertEquals(songs, returnedSongs);
    }

    @Test
    void testGetStreamingData() {
        Map<String, Object> returnedStreamingData = rhythmRadarApp.getStreamingData("artistID");
        assertEquals(streamingData, returnedStreamingData);
    }

    @Test
    void testGetSocialMediaData() {
        Map<String, Object> returnedSocialMediaData = rhythmRadarApp.getSocialMediaData("artistID");
        assertEquals(socialMediaData, returnedSocialMediaData);
    }
}

//Implement the missing logic: In the RhythmRadarClientImpl class, 
//complete the logic for retrieving the artist data based on your 
//application requirements. For each method, implement the necessary 
//code to fetch the data from the appropriate source (e.g., a database, 
//API, or file system).