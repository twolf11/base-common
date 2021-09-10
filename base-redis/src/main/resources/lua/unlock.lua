
if ARGV[1] == redis.call('get', KEYS[1])
then
    return redis.call('del', KEYS[1])
else
    return false
end
