## LIST   

### Push & Pop
```
# right push  
rpush users sam mike jake
(integer) 3

keys *
"users"

type users
list

llen users
(integer) 3
  
lrange users 0 -1
"sam"
"mike"
"jake"

# left push  
lpush
```

### Pop
```
lpop users
"sam"

lrange users 0 -1
"mike"
"jake"

lpop users 2

```

### List as Stack
```
rpush users 1 2 3 4 5 6
rpop users
lrange users 0 -1
keys *
```
