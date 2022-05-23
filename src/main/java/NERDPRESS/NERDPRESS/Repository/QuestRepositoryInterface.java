package NERDPRESS.NERDPRESS.repository;

import NERDPRESS.NERDPRESS.domain.Quest;

public interface QuestRepositoryInterface {
    
    // 문제 저장
    void saveQuest(Quest q);

    // License_id 에 해당하는 문제 모두 출력
    Quest findQuest(int license_id);
}
