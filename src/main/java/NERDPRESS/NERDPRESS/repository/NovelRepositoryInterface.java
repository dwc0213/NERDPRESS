package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.Novel;

import java.util.List;

public interface NovelRepositoryInterface {

    // 소설을 저장
    void saveNovel(Novel n);

    // 소설찾기
    Novel findNovel(String novel);

    // 소설 20개 목록
    List<Novel> topTwenty();

}
