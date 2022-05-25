package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.Quest;

import java.util.List;

public interface QuestRepositoryInterface {
    
    // 문제 저장
    void saveQuest(Quest q);

    // License_id 에 해당하는 문제 모두 출력
    List<Quest> findQuest(int license_id);
}
