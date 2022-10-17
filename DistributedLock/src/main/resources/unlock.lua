if(redis.call('hexists', KEYS[1], ARGV[1]) == 0) then
    return nil;
elseif(redis.call('hincrby', KEYS[1], ARGV[1], -1) > 0) then
    return 0;
else
    redis.call('del', KEYS[1]);
    return 1;
end;