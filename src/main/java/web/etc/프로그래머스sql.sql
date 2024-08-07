/*프로그래머스 sql 문제 */
# ============== (1일차 SQL 문제) 2024-08-06 =================#
# 1. 인기있는 아이스크림
select FLAVOR from FIRST_HALF order by TOTAL_ORDER desc, SHIPMENT_ID asc;
# 2.모든 레코드 조회하기
SELECT * from ANIMAL_INS order by ANIMAL_ID asc;
# 3.역순 정렬하기
SELECT NAME, DATETIME from ANIMAL_INS order by ANIMAL_ID desc;
# 4. 동물의 아이디와 이름
SELECT ANIMAL_ID, NAME from ANIMAL_INS order by ANIMAL_ID asc;
# 5. 여러 기준으로 정렬하기
SELECT ANIMAL_ID, NAME, DATETIME from ANIMAL_INS order by NAME asc, DATETIME desc;
# 6. 과일로 만든 아이스크림 고르기
SELECT FIRST_HALF.FLAVOR from ICECREAM_INFO inner join FIRST_HALF on ICECREAM_INFO.FLAVOR = FIRST_HALF.FLAVOR where TOTAL_ORDER > 3000 and INGREDIENT_TYPE = 'fruit_based';
# 7. 강원도에 위치한 생산공장 목록 출력하기
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS from FOOD_FACTORY where ADDRESS like "강원도%" order by FACTORY_ID asc ;
# 8. 아픈 동물 찾기
SELECT ANIMAL_ID, NAME from ANIMAL_INS where INTAKE_CONDITION = 'Sick' order by ANIMAL_ID asc;
# 9. 어린 동물 찾기
SELECT ANIMAL_ID, NAME from ANIMAL_INS where INTAKE_CONDITION != 'Aged' order by ANIMAL_ID asc;
# 10. 상위 n개 레코드
SELECT NAME from ANIMAL_INS order by DATETIME asc limit 1;
# 11. Python 개발자 찾기
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME from DEVELOPER_INFOS where 'Python' in (SKILL_1, SKILL_2, SKILL_3) order by ID asc;

# ============== (2 일차 SQL 문제) 2024-08-07 =================#
# 1.[ SUM, MAX, MIN ] 가장 비싼 상품 구하기
SELECT MAX(PRICE) MAX_PRICE from PRODUCT;
# 2.[ SUM, MAX, MIN ] 최댓값 구하기
SELECT MAX(DATETIME) from ANIMAL_INS;
# 3.[ SUM, MAX, MIN ] 가격이 제일 비싼 식품의 정보 출력하기
SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE from FOOD_PRODUCT order by PRICE desc limit 1;
# 4.[ SUM, MAX, MIN ] 최솟값 구하기
SELECT MIN(DATETIME) from ANIMAL_INS;
# 5.[ SUM, MAX, MIN ] 동물 수 구하기
SELECT count(*) from  ANIMAL_INS;
# 6.[ SUM, MAX, MIN ] 중복 제거하기
SELECT count(distinct(NAME)) count from ANIMAL_INS;
# 7.[ SUM, MAX, MIN ] 조건에 맞는 아이템들의 가격의 총합 구하기
SELECT SUM(PRICE) TOTAL_PRICE from ITEM_INFO where  RARITY = 'LEGEND';
# 8. [SELECT] 조건에 맞는 회원수 구하기
SELECT COUNT(USER_ID) USERS from USER_INFO where JOINED like '2021%' and AGE >= 20 and AGE <= 29;
# 9. [SELECT] 잔챙이 잡은 수 구하기
SELECT count(ID) FISH_COUNT from FISH_INFO where LENGTH is NULL;
# 10. [SELECT] 특정 형질을 가지는 대장균 찾기
SELECT count(ID) count from ECOLI_DATA where NOT (GENOTYPE & 2) AND (GENOTYPE & 1 or GENOTYPE & 4);
# 11. [GROUP BY]자동차 종류 별 특정 옵션이 포함된 자동차 수 구하기
SELECT CAR_TYPE, count(CAR_ID) CARS from CAR_RENTAL_COMPANY_CAR where OPTIONS like "%시트%" group by CAR_TYPE order by CAR_TYPE asc;
# 12. [GROUP BY]성분으로 구분한 아이스크림 총 주문량
SELECT INGREDIENT_TYPE, SUM(TOTAL_ORDER) as TOTAL_ORDER from FIRST_HALF inner join ICECREAM_INFO on FIRST_HALF.FLAVOR = ICECREAM_INFO.FLAVOR group by INGREDIENT_TYPE order by SUM(TOTAL_ORDER) asc;
# 13. [GROUP BY]진료과별 총 예약 횟수 출력하기
SELECT MCDP_CD as 진료과코드, count(APNT_NO) as 5월예약건수 from APPOINTMENT where APNT_YMD like "2022-05%" group by MCDP_CD order by count(APNT_NO) asc, MCDP_CD asc;
# 14. [GROUP BY]고양이와 개는 몇 마리 있을까
SELECT ANIMAL_TYPE, count(ANIMAL_ID) from ANIMAL_INS group by ANIMAL_TYPE order by ANIMAL_TYPE;
# 15. [GROUP BY]동명 동물 수 찾기
SELECT NAME, count(NAME) COUNT from ANIMAL_INS where NAME is not null group by name having count(name) > 1 order by NAME asc;
# 16. [GROUP BY]조건에 맞는 사원 정보 조회하기
SELECT sum(G.SCORE) SCORE, E.EMP_NO, E.EMP_NAME, E.POSITION, E.EMAIL from HR_DEPARTMENT D inner join HR_EMPLOYEES E on D.DEPT_ID = E.DEPT_ID join HR_GRADE G on E.EMP_NO = G.EMP_NO group by EMP_NO order by SCORE desc limit 1;
# 17. [GROUP BY]물고기 종류 별 잡은 수 구하기
SELECT count(*) FISH_COUNT, N.FISH_NAME from FISH_NAME_INFO N inner join FISH_INFO I on N.FISH_TYPE = I.FISH_TYPE group by N.FISH_NAME order by FISH_COUNT desc;
# 18. [JOIN]상품 별 오프라인 매출 구하기
SELECT P.PRODUCT_CODE, sum(O.SALES_AMOUNT * P.PRICE) SALES from PRODUCT P inner join OFFLINE_SALE O on P.PRODUCT_ID = O.PRODUCT_ID group by P.PRODUCT_CODE order by SALES desc, P.PRODUCT_CODE asc;

