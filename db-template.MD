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
| FeatureStore       |      ![](https://github.com/clun/ff4j/raw/master/src/site/resources/images/on.png)   |   N/A    |
| PropertyStore      |      ![](https://github.com/clun/ff4j/raw/master/src/site/resources/images/on.png)      |   N/A    |
| EventRepository    |      ![](https://github.com/clun/ff4j/raw/master/src/site/resources/images/on.png)      |   N/A    |
| CacheProxy         |      ![](https://github.com/clun/ff4j/raw/master/src/site/resources/images/off.png)     | No real added-value to be used only as Cache |
| UnitTests Embedded |      ![](https://github.com/clun/ff4j/raw/master/src/site/resources/images/on.png)      | Embedded Cassandra Db is provided by [Cassandra-Unit](https://github.com/jsevellec/cassandra-unit)     |


### (b) Data Model

The DDL file is provided. Find here an overview of the created column Family
```sql
cqlsh:ff4j> describe ff4j

CREATE KEYSPACE ff4j WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}  AND durable_writes = true;

CREATE TABLE ff4j.audit (
    uid text PRIMARY KEY,
    action text,
    date text,
    duration bigint,
    hostname text,
    keys map<text, text>,
    name text,
    source text,
    time timestamp,
    type text,
    user text,
    value text
) WITH bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';

CREATE TABLE ff4j.features (
    uid text PRIMARY KEY,
    description text,
    enable int,
    groupname text,
    properties map<text, text>,
    roles set<text>,
    strategy text
) WITH bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';
CREATE INDEX features_groupname_idx ON ff4j.features (groupname);

CREATE TABLE ff4j.properties (
    uid text PRIMARY KEY,
    clazz text,
    description text,
    fixedvalues set<text>,
    value text
) WITH bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';

cqlsh:ff4j>
```


### (c) Configuration
