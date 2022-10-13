local key = KEYS[1];
local threadId = ARGV[1];
local releaseTimes = ARGV[2];

-- lockname 不存在
if (redis.call('exists', key) == 0) then
    redis.call('hset', key, threadId, '1');
    redis.call('expire', key, releaseTimes);
end;

-- 当前线程id已存在
if (redis.call('hexists', key, threadId) == 1) then
    -- 自增 1
    redis.call('hincrby', key, threadId, '1');
    redis.call('expire', ley, releaseTimes);
end;

return 0;