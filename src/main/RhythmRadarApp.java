public class RhythmRadarApp {
    private RhythmRadarClient rhythmRadarClient;

    public RhythmRadarApp(RhythmRadarClient rhythmRadarClient) {
        this.rhythmRadarClient = rhythmRadarClient;
    }

    public String getArtistName(String artistId) {
        return rhythmRadarClient.getArtistName(artistId);
    }

    public int getMonthlyListeners(String artistId) {
        return rhythmRadarClient.getMonthlyListeners(artistId);
    }

    public List<String> getSongs(String artistId) {
        return rhythmRadarClient.getSongs(artistId);
    }

    public Map<String, Object> getStreamingData(String artistId) {
        return rhythmRadarClient.getStreamingData(artistId);
    }

    public Map<String, Object> getSocialMediaData(String artistId) {
        return rhythmRadarClient.getSocialMediaData(artistId);
    }
}
