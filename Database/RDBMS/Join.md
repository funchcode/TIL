# 001. 조인: JOIN

> ❗️ 다른 벤더 제품에서는 left join, left outer join의 차이가 있는지는 확인하지 못함.

연관된 데이터가 서로 다른 테이블에 저장되어 있을 때 하나의 데이터 Row로 조회하기 위해 Join을 사용한다.

## INNER JOIN

서로 다른 테이블에 연관 데이터가 **양쪽 모두 존재**해야만 조회되는 케이스다.

### MariaDB

```sql
select * from student st inner join score s on st.id = s.student_id;
```

| id | name | id | value | student_id |
| --- | ---- | --- | ---- | ---- |
|1|김철수|1|60|1|
|2|박미미|2|71|2|
|5|박버거|3|99|5|

## LEFT JOIN

왼쪽에 선언한 테이블을 기준으로 오른쪽에 선언한 테이블에 존재하는 데이터를 잇는데 존재하지 않을 경우 **null** 값이 세팅된다.

### MariaDB

```sql
select * from student st left join score s on st.id = s.student_id;

select * from student st left outer join score s on st.id = s.student_id;
```

| id | name | id | value | student_id |
| --- | ---- | --- | ---- | ---- |
|1|김철수|1|60|1|
|2|박미미|2|71|2|
|3|정다비|null|null|null|
|4|멘토스|null|null|null|
|5|박버거|3|99|5|

## RIGHT JOIN

`left join`과 반대로 동작.

### MariaDB

```sql
select * from student st right join score s on st.id = s.student_id;

select * from student st right outer join score s on st.id = s.student_id;student_id;
```

| id | name | id | value | student_id |
| --- | ---- | --- | ---- | ---- |
|1|김철수|1|60|1|
|2|박미미|2|71|2|
|5|박버거|3|99|5|
|null|null|4|22|7|

### 가데이터 생성 쿼리



* MariaDB

```sql
create table student (
    id varchar(100) primary key,
    name varchar(15)
);

create table score (
    id varchar(100) primary key,
    value int(100),
    student_id varchar(100)
);

insert into student (id, name) value ('1', '김철수');
insert into student (id, name) value ('2', '박미미');
insert into student (id, name) value ('3', '정다비');
insert into student (id, name) value ('4', '멘토스');
insert into student (id, name) value ('5', '박버거');

insert into score (id, value, score.student_id) value ('1', 60, '1');
insert into score (id, value, score.student_id) value ('2', 71, '2');
insert into score (id, value, score.student_id) value ('3', 99, '5');
insert into score (id, value, score.student_id) value ('4', 22, '7');
```
