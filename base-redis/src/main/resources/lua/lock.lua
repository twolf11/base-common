
if redis.call('set', KEYS[1], ARGV[1], 'nx', 'px', ARGV[2]) == 'OK' then
    return true
else
    if redis.call('get', KEYS[1]) == ARGV[1] then
        if redis.call('EXPIRE', KEYS[1], ARGV[2]) == 1 then
            return true
        end
    end
end