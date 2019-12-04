package presentation.models.messages;

import presentation.models.LeaderboardRecord;

import java.util.ArrayList;
import java.util.List;


public class LeaderboardMessageModel extends EmptyMessageModel {
    private ArrayList<LeaderboardRecord> records = new ArrayList();

    public LeaderboardMessageModel()
    {
        setType("leaderboard");
    }

    public void addPlayer(String name, Integer score)
    {
        records.add(new LeaderboardRecord(name, score));
    }

    public void addPlayer(LeaderboardRecord record) {
        records.add(record);
    }

    public List<LeaderboardRecord> getRecords()
    {
        return records;
    }
}
