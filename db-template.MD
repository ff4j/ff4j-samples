## Cassandra
![alt text](https://github.com/clun/ff4j/raw/master/ff4j-web/src/main/resources/static/img/db/db-cassandra.png "Cassandra Log")

The [Apache Cassandra](https://cassandra.apache.org/) database is the right choice when you need scalability 
and high availability without compromising performance. Linear scalability and proven 
fault-tolerance on commodity hardware or cloud infrastructure make it the perfect platform 
for mission-critical data.Cassandra's support for replicating across multiple datacenters is 
best-in-class, providing lower latency for your users and the peace of mind of knowing that 
you can survive regional outages.

### (a) Scope
As a noSQL column-oriented DB we decided to implement all stores for Cassandra. It can be used for all use cases.

| Scope              | Status        | Comment  |
| ------------------ |:-------------:| --------:|
| FeatureStore       |       OK      |   N/A    |
| PropertyStore      |       OK      |   N/A    |
| EventRepository    |       OK      |   N/A    |
| CacheProxy         |       KO      | No real added-value to be used only as Cache |
| UnitTests Embedded |       OK      | Embedded Cassandra Db is provided by [Cassandra-Unit](https://github.com/jsevellec/cassandra-unit)     |


### (b) Data Model

The DDL file is provided. Find here an overview of the created column Family
```sql
-- Create table Feature
CREATE TABLE ff4j.features ( 
   UID varchar, 
   ENABLE int, 
   DESCRIPTION varchar, 
   STRATEGY varchar, 
   GROUPNAME varchar, 
   ROLES set<varchar>, 
   PROPERTIES map<varchar,varchar>, 
   PRIMARY KEY (UID)
);

-- create table properties
CREATE TABLE ff4j.properties ( 
   UID varchar, 
   CLAZZ varchar, 
   VALUE varchar, 
   DESCRIPTION varchar,
   FIXEDVALUES set<varchar>, 
   PRIMARY KEY (UID)
);

-- create table audit (group per date)
CREATE TABLE ff4j.audit (
    uid varchar,
    date varchar,
    time timestamp,
    type varchar,
    name varchar,
    action varchar,
    hostName varchar,
    source varchar,
    duration int,
    user varchar,
    value varchar,
    custom map<varchar,varchar>,
    PRIMARY KEY (UID)
);
```



### (c) Configuration
