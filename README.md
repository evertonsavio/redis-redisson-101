### Redis

```
docker-compose up
docker exec -it redis bash
redis-cli
```

## Redis: Key-Value
* Key values are always "strings"
```
ping
PONG

set a b
OK

get a
"b"

get c
(nil)

set a 1
OK

get a
"1"
```
