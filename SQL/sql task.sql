create database dailytask;

use dailytask;

create table student 
(sid int primary key,	
stnumber varchar(40) unique not null,
fname varchar(40) not null,
address varchar(40),
gender enum('male','female')
)

----Basic SQL commands ----

select * from student;

insert into student values (1,'CS100','Abilash','India','male'),(2,'ME201','Azar','UK','male'),(3,'CS101','Mehrin','UK','female'),(4,'ME202','Ashish','India','male'),(5,'EE300','Mariam','UK','female');

select stnumber as student_id,fname as FullName,address,gender from student;

select stnumber as student_id,upper(fname) as FullName,address,gender from student;

update student set fname='Micheal' where sid=1;

select stnumber as student_id,upper(fname) as FullName,address,gender from student limit 3;

select stnumber as student_id,upper(fname) as FullName,address,gender from student where fname like 'A%';

select stnumber as student_id,upper(fname) as FullName,address,gender from student where address ='UK';

select * from tutorial.us_housing_units where west >50;

select * from tutorial.us_housing_units where south <= 20;

select * from tutorial.us_housing_units WHERE month_name='February';

select * from tutorial.us_housing_units WHERE month_name > 'N';

select year,month,west,south,midwest,northeast,(south + west + midwest) as total from tutorial.us_housing_units; 

select year,month,west,south,midwest,northeast from tutorial.us_housing_units 
where west > (northeast + midwest);

select 
west/(west+south+midwest+northeast) * 100 as westPercentage,
south/(west+south+midwest+northeast) * 100 as southPercentage,
midwest/(west+south+midwest+northeast) * 100 as midwestPercentage,
northeast/(west+south+midwest+northeast) * 100 as northeastPercentage
from tutorial.us_housing_units 
where year >= 2000;

SELECT *
  FROM tutorial.billboard_top_100_year_end where group_name like '%Ludacris%';

SELECT *
  FROM tutorial.billboard_top_100_year_end where upper(artist) like 'DJ%';

SELECT *
  FROM tutorial.billboard_top_100_year_end  where artist in ('M.C. Hammer','Hammer','Elvis Presley');
  
  SELECT *
  FROM tutorial.billboard_top_100_year_end 
    where year between 1985 and 1990;
    
    SELECT *
  FROM tutorial.billboard_top_100_year_end where song_name is NULL;
    
    SELECT *
  FROM tutorial.billboard_top_100_year_end where year_rank <= 10 and group_name like '%Ludacris%';
  
SELECT *
  FROM tutorial.billboard_top_100_year_end where year in (1990, 2000, 2010) and year_rank=1;
  
  SELECT *
  FROM tutorial.billboard_top_100_year_end where year = 1960 and song_name like '%love%';
  
   SELECT *
  FROM tutorial.billboard_top_100_year_end 
   where year_rank <= 10 and (artist='Katy Perry' OR artist='Bon Jovi');

SELECT *
  FROM tutorial.billboard_top_100_year_end 
   where (year between 1970 and 1979 OR year between 1990 and 1999) and song_name like '%California%';
   
 SELECT *
  FROM tutorial.billboard_top_100_year_end 
  where artist = 'Dr. Dre' and (year < 2001 or year > 2009);
  
   SELECT *
  FROM tutorial.billboard_top_100_year_end 
  where song_name not like '%a%' and year=2013 ;
  
  SELECT *
  FROM tutorial.billboard_top_100_year_end
 WHERE year = 2012
 ORDER BY song_name DESC;
 
  SELECT *
  FROM tutorial.billboard_top_100_year_end
 WHERE year = 2010
 ORDER BY year_rank, artist ;
 
 SELECT *
  FROM tutorial.billboard_top_100_year_end
  where group_name like '%T-Pain%' ORDER by year_rank desc;
  
  SELECT *
  FROM tutorial.billboard_top_100_year_end
  where year_rank between 10 and 20 ----rank from 10 to 20
  and year in (1993, 2003,2013) ---select year 
  order by year, year_rank
  
---------------------------------------------------------------------------------

------------- intermediate SQL Query -----------  

  SELECT count(low) as low FROM tutorial.aapl_historical_stock_price 
  
  SELECT 
count(year) as year,
count(month) as month,
count(open) as open,
count(high) as high,
count(low) as low,
count(close) as close,
count(volume) as volume
  FROM tutorial.aapl_historical_stock_price 
  
select sum(open)/count(open) as avg FROM tutorial.aapl_historical_stock_price
  
select min(low) FROM tutorial.aapl_historical_stock_price

select max(close-open) FROM tutorial.aapl_historical_stock_price

select avg(volume) as avg_volume from tutorial.aapl_historical_stock_price where volume is NOT NULL

 select year,month,SUM(volume) as share_total
  FROM tutorial.aapl_historical_stock_price 
  group by year,month 
  order by year,month
  
    select year,AVG(close - open) as daily_avg 
    from tutorial.aapl_historical_stock_price 
    group by year
    
select year,month,MIN(low) as lowest,MAX(high) as highest from tutorial.aapl_historical_stock_price group by month,year

 select year,month,MIN(low) as lowest,MAX(high) as highest 
        from tutorial.aapl_historical_stock_price 
        group by month,year
        having max(high)>400
        ORDER by year,month
        
select player_name,state,CASE WHEN state ='CA' then 'yes' ELSE 'NO' END as from_california
        FROM benn.college_football_players  order by from_california DESC;
        
select player_name,height,
	CASE WHEN height > 80 then 'over 80' 
         WHEN height > 70  and height <= 80 then '70 - 80' 
         WHEN height > 60  and height <= 70 then '60 - 70' 
         WHEN height > 50  and height <= 60 then '50 - 60' 
        ELSE 'below 50' END as height_range
        FROM benn.college_football_players  order by height_range DESC;
        
 select *,CASE WHEN year in ('JR','SR') then player_name 
 ELSE NULL end as jr_sr_players
 from benn.college_football_players
        
select CASE WHEN state in ('CA','OR','WA') then 'West coast'
WHEN state = 'TX' then 'Texas' 
else 'other' END as regions,
count(1) as players
from benn.college_football_players
where weight >= 300
group by 1

SELECT case WHEN year in ('FR','SO') then 'under class'
WHEN year in ('JR','SR')  then 'upper class'
ELSE null end as class,
sum(weight)
from  benn.college_football_players WHERE state='CA' GROUP BY 1

select state, 
COUNT(case when year='FR' then 1 else null END) as fr_count,
COUNT(case when year='SO' then 1 else null END) as so_count,
COUNT(case when year='JR' then 1 else null END) as jr_count,
COUNT(case when year='SR' then 1 else null END) as sr_count,
count(1) as total_players
from benn.college_football_players group by state;

select case when school_name < 'n' then 'A-M'
when school_name  > 'n' then 'N-Z'
else null end as name_group,
count(1)
from benn.college_football_players group by 1 order by name_group

SELECT DISTINCT year
  FROM tutorial.aapl_historical_stock_price
 ORDER BY year
 
SELECT count(DISTINCT month) as count_month, year FROM tutorial.aapl_historical_stock_price group by year

SELECT count(DISTINCT month) as count_month, count(distinct year) as count_year FROM tutorial.aapl_historical_stock_price

select players.school_name,players.player_name,players.position,weight
  from benn.college_football_players players 
  where players.state='GA'
  order by weight desc
  
SELECT *
  FROM benn.college_football_players players
  JOIN benn.college_football_teams teams
    ON teams.school_name = players.school_name
    
SELECT players.player_name,players.school_name,teams.conference
  FROM benn.college_football_players players
  JOIN benn.college_football_teams teams
    ON (teams.school_name = players.school_name) where division ='FBS (Division I-A Teams)'
    
select count(acq.company_permalink) as acquisition_count,count(comp.permalink) as comp_count 
from tutorial.crunchbase_acquisitions acq inner join 
tutorial.crunchbase_companies comp on (acq.company_permalink=comp.permalink)

select count(acq.company_permalink) as acquisition_count,count(comp.permalink) as comp_count 
from tutorial.crunchbase_acquisitions acq left join 
tutorial.crunchbase_companies comp on (acq.company_permalink=comp.permalink)

select comp.state_code,
count(distinct acq.company_permalink) as acquired_count,count(distinct comp.permalink) as permcomp_count  
from tutorial.crunchbase_acquisitions acq 
right join tutorial.crunchbase_companies comp on (acq.company_permalink=comp.permalink)
where comp.state_code is not null group by 1 order by 1 desc

select comp.name as company_name,comp.status as status,count(distinct investment.investor_name) as unique_investment_name
from tutorial.crunchbase_companies comp 
left join tutorial.crunchbase_investments investment 
on (investment.company_permalink = comp.permalink)
WHERE comp.state_code = 'NY'
group by comp.name,comp.status
order by 3 desc;

select case when investment.investor_name is null then 'No Investor'
else investment.investor_name end as investor_details,
count(distinct comp.permalink) as company_invested_in
from tutorial.crunchbase_companies comp 
left join tutorial.crunchbase_investments investment 
on (investment.company_permalink = comp.permalink)
group by 1
order by 2 desc

select 
count(case when comp.permalink is not null and inv.company_permalink is null then comp.permalink
else null end) as company_only,
count(case when comp.permalink is  null and inv.company_permalink is not null then inv.company_permalink
else null end) as investment_only,
count(case when comp.permalink is  not null and inv.company_permalink is not null then comp.permalink
else null end) as both_count
from tutorial.crunchbase_companies comp
full join tutorial.crunchbase_investments_part1 inv
on comp.permalink = inv.company_permalink

 select company_permalink,company_name,investor_name 
 from tutorial.crunchbase_investments_part1 
 where upper(company_name) ilike 'T%'
 union all
 select company_permalink,company_name,investor_name 
 from tutorial.crunchbase_investments_part2 
 where upper(company_name) ilike 'M%'
 
 select 'data set 1' as data_set_no,comp.status,count(distinct part1.investor_name)
from tutorial.crunchbase_companies comp
left join tutorial.crunchbase_investments_part1 part1
on (part1.company_permalink = comp.permalink)
group by 1,2
union all
select 'data set 2' as data_set_no,comp.status,count(distinct part2.investor_name)
from tutorial.crunchbase_companies comp
left join tutorial.crunchbase_investments_part2 part2
on (part2.company_permalink = comp.permalink)
group by 1,2

SELECT companies.permalink,
       companies.name,
       investments.company_name,
       investments.company_permalink
  FROM tutorial.crunchbase_companies companies
  LEFT JOIN tutorial.crunchbase_investments_part1 investments
    ON companies.permalink = investments.company_permalink
   AND companies.name = investments.company_name
   
select CAST(funding_total_usd as varchar) as funding_total,
CAST(founded_at_clean as varchar) as founded_cat_clean 
from tutorial.crunchbase_companies_clean_date

select company.category_code,
count(case when acquisitions.acquired_at_cleaned <= company.founded_at::timestamp + INTERVAL '3 years'
then 1 ELSE null END) as acquired_3_year,
count(case when acquisitions.acquired_at_cleaned <= company.founded_at::timestamp + INTERVAL '5 years'
then 1 ELSE null END) as acquired_5_year,
count(case when acquisitions.acquired_at_cleaned <= company.founded_at::timestamp + INTERVAL '10 years'
then 1 ELSE null END) as acquired_10_year,
count(1) as total
FROM tutorial.crunchbase_companies_clean_date company
  JOIN tutorial.crunchbase_acquisitions_clean_date acquisitions
  ON (acquisitions.company_permalink = company.permalink)
  where founded_at_clean IS NOT NULL
  group by 1 order by 5 desc

SELECT incidnt_num,date,SUBSTR(date, 4, 2) AS day
  FROM tutorial.sf_crime_incidents_2014_01
  
 SELECT incidnt_num,
RIGHT(date,17) as cleaned_time,
       LEFT(date, 10) AS cleaned_date  
  FROM tutorial.sf_crime_incidents_2014_01

SELECT location,
       TRIM(both '()' FROM location)
  FROM tutorial.sf_crime_incidents_2014_01
  
SELECT location,
trim(leading '(' from LEFT(location,POSITION(',' IN location)-1))  as lattitude,
trim(trailing ')' from RIGHT(location,LENGTH(location) - POSITION(',' IN location)))  as longitude
FROM tutorial.sf_crime_incidents_2014_01

SELECT location,LEFT(location,POSITION(',' IN location)) as pos 
FROM tutorial.sf_crime_incidents_2014_01

select CONCAT('(',lat,' , ', lon, ')') as location FROM tutorial.sf_crime_incidents_2014_01
 
select '(' || lat || ' , ' || lon || ')' as location FROM tutorial.sf_crime_incidents_2014_01

 select date,SUBSTR(date, 7, 4)||'-'||LEFT(date,2)||'-'||SUBSTR(date,4,2) as newdate 
 from  tutorial.sf_crime_incidents_2014_01
 
 select category,
 UPPER(SUBSTR(category,1,1))||''||LOWER(SUBSTR(category,2,length(category)-1)) as newcategory 
 from  tutorial.sf_crime_incidents_2014_01
 
 select date,
 (substr(date,7,4)||'-'||LEFT(date,2)||'-'||substr(date,4,2)||' '|| time || ':00')::timestamp as newdate,
  (substr(date,7,4)||'-'||LEFT(date,2)||'-'||substr(date,4,2)||' '|| time || ':00')::timestamp 
 + INTERVAL '1 week' AS newtimestamp
 from  tutorial.sf_crime_incidents_2014_01
 
 SELECT DATE_TRUNC('week', cleaned_date)::date AS week,COUNT(*) AS incidents
  FROM tutorial.sf_crime_incidents_cleandate
 GROUP BY 1
 ORDER BY 1
 
 SELECT cleaned_date,NOW() AT TIME ZONE 'UTC-8' AS now,NOW() AT TIME ZONE 'UTC-8' - cleaned_date AS time_ago 
  FROM tutorial.sf_crime_incidents_cleandate

SELECT descript,COALESCE(descript, 'No Description')
  FROM tutorial.sf_crime_incidents_cleandate
 ORDER BY descript DESC
 
select * from (SELECT *
              FROM tutorial.sf_crime_incidents_2014_01
             WHERE descript = 'WARRANT ARREST') sub where sub.resolution='NONE';
             
select category,sub.month,avg(sub.incidents) as avg_incidents from 
(select extract('month' from cleaned_date) as month ,category,count(1) as incidents
 FROM tutorial.sf_crime_incidents_cleandate
 group by 1,2) sub 
 group by 1,2;

select c.*,sub.incidents as total_incidents from tutorial.sf_crime_incidents_2014_01 c
 join (select category,count(1) as incidents
 from tutorial.sf_crime_incidents_2014_01
 group by 1
 order by 2
 limit 3) sub on (sub.category=c.category);
 
 SELECT COALESCE(companies.quarter, acquisitions.quarter) AS quarter,companies.companies_founded,acquisitions.companies_acquired
      FROM (
            SELECT founded_quarter AS quarter,COUNT(permalink) AS companies_founded
              FROM tutorial.crunchbase_companies
             WHERE founded_year >= 2012
             GROUP BY 1
           ) companies
      LEFT JOIN (
            SELECT acquired_quarter AS quarter,COUNT(DISTINCT company_permalink) AS companies_acquired
              FROM tutorial.crunchbase_acquisitions
             WHERE acquired_year >= 2012
             GROUP BY 1
           ) acquisitions
        ON companies.quarter = acquisitions.quarter
     ORDER BY 1
     
SELECT investor_name,COUNT(*) AS investments
  FROM (
        SELECT * FROM tutorial.crunchbase_investments_part1
         UNION ALL
         SELECT * FROM tutorial.crunchbase_investments_part2
       ) sub
 GROUP BY 1
 ORDER BY 2 DESC
 
SELECT investments.investor_name,COUNT(investments.*) AS investments
  FROM tutorial.crunchbase_companies companies
  JOIN (SELECT * FROM tutorial.crunchbase_investments_part1
         UNION ALL
         SELECT * FROM tutorial.crunchbase_investments_part2
       ) investments
    ON investments.company_permalink = companies.permalink
 WHERE companies.status = 'operating'
 GROUP BY 1
 ORDER BY 2 DESC
 
SELECT duration_seconds,SUM(duration_seconds) OVER (ORDER BY start_time) AS running_total
  FROM tutorial.dc_bikeshare_q1_2012
  
SELECT product_name, price, RANK() OVER (ORDER BY price) AS price_rank
FROM Products;

SELECT product_name, price, dense_rank
FROM (
  SELECT product_name, price, DENSE_RANK() OVER (ORDER BY price) AS dense_rank
  FROM Products
) AS rankings;


SELECT 
  product_name, 
  price, 
  (SELECT COUNT(DISTINCT p2.price) 
   FROM Products p2 
   WHERE p2.price <= p1.price) AS dense_rank
FROM Products p1;

SELECT 
  product_name, 
  price, 
  (SELECT COUNT(DISTINCT p2.price) 
   FROM Products p2 
   WHERE p2.price <= p1.price) AS rank_value
FROM Products p1
LIMIT 0, 1000;


SELECT sales_id, product_id, order_id, ROW_NUMBER() OVER (ORDER BY sales_id) AS row_number
FROM Sales;

SELECT 
  sales_id, 
  product_id, 
  order_id, 
  (@row_number := @row_number + 1) AS row_number
FROM Sales, (SELECT @row_number := 0) AS r
ORDER BY sales_id;

SELECT product_name, price, PERCENT_RANK() OVER (ORDER BY price) AS percent_rank
FROM Products;
  

  
