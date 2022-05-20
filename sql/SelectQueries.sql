-- 아래의 구문들은 DB로부터 한정된 개수의 행(rows)을 가져오기 위해서 쓰입니다.
-- rownum이라는 열(column)은 예약어이기 때문에 따로 정의할 필요가 없습니다. 우리에게는 안보이지만 컴퓨터만 가지고 있는 열(column)이라고 생각하시면 됩니다.
SELECT id,userId from (SELECT rownum AS rn, id,userId from member) where rn between 11 and 20;
SELECT id,userId from (SELECT rownum AS rn, id,userId from member) where rn between 21 and 30;